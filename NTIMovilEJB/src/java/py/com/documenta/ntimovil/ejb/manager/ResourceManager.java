package py.com.documenta.ntimovil.ejb.manager;

import com.documenta.util.Util;
import com.documenta.util.ssh.SshSetup;
import com.documenta.util.email.MailSetup;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.bean.setup.AlertSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.AppSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.GatewayGSMSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.PasswordPolicySetup;
import py.com.documenta.ntimovil.ejb.bean.setup.SentinelAlertSetup;

/**
 * Singleton que se encarga de leer los diferentes archivos properties, luego
 * crear los objetos setup y cargar los datos obtenidos de los archivos.<br>
 *
 * Los archivos properties están agrupados según la funcionalidad/utilización,
 * donde cada funcionalidad esta representado en una clase bean con el sufijo
 * setup para ser facilmente reconocido.<br>
 *
 * @see clases setup en: com.pmc.docuemnta.setup
 */
public class ResourceManager {

    private final Logger log = LoggerFactory.getLogger(ResourceManager.class);

    private Util util;
    private final static String NTI_PROPS = "nandutimovil-config.properties";

    // Para concatener con el nombre de los archivos properties
    private String appPathConfig = null;

    // Archivo principal de configuración, se cierra luego del load().
    private Properties propsNTI = null;
    private InputStream inputStreamNTI = null;      // Utilizado en ambiente windows y As400.
    private static String hostAS400 = "localhost";
    private boolean loaded = false;

    //Clases que agrupan las configuraciones
    private AppSetup appSetup;

    private MailSetup mailSetup;
    private SshSetup sshSetup;;
    private GatewayGSMSetup gatewayGSMSetup;
    private AlertSetup alertSetup;
    private SentinelAlertSetup sentinelAlertSetup;
    private PasswordPolicySetup pwdPolicySetup;
    
    /*
    private MailForgotPasswordSetup mailForgotPasswordSetup;
     */
    private long firstLoadTime;

    private static volatile ResourceManager resourceConfig;
    private static final Object LOCK = new Object();
    

    private ResourceManager()  throws Exception {
        try {
            util = new Util();
            appPathConfig = util.getApplicationConfigPath(false);

            openProperties();
            load();

            new CheckUpdateFile().start();
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void openProperties()  throws Exception {
        try {
            if (inputStreamNTI != null) {
                inputStreamNTI.close();
            }
            inputStreamNTI = getInputStreamConfig(appPathConfig + NTI_PROPS);

            if (inputStreamNTI != null) {
                propsNTI = new Properties();
                propsNTI.load(inputStreamNTI);
            } else {
                throw new Exception("El archivo es nulo, no se pudo abrir el archivo de propiedades: " + NTI_PROPS);
            }
            
            loaded = false;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void closeProperties()  throws Exception {
        try {
            if (inputStreamNTI != null) {
                inputStreamNTI.close();
            }

            inputStreamNTI = null;
            propsNTI = null;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Retorna el stream para escritura en el archivo de propiedades según el
     * sistema operativo.
     *
     * @param file - Recibe la ruta completa del archivo de propiedades
     *
     * @return OutputStream - stream del archivo de propiedades para
     * configuracion.
     */
    private OutputStream getOutputStreamConfig(String file)  throws Exception {
        //FileInputStream fis = null;
        //IFSFileInputStream IFSfis = null;
        OutputStream os = null;

        try {
            if (util.isOS("AS400") || util.isOS("OS400")) {
                // Crea un objeto AS400 para el servidor que contiene los archivos.
                //AS400 as400 = new AS400(hostAS400);

                //os = new IFSFileOutputStream(as400, file, IFSFileOutputStream.SHARE_NONE);
            } else {
                os = new FileOutputStream(file);
            }
            //} catch (AS400SecurityException ex) {
            //    throw new PMCConfigException("Error de seguridad de as400.", ex);
        } catch (Exception ex) {
            throw ex;
        }

        return os;
    }

    /**
     * Retorna el stream del archivo de propiedades según el sistema operativo.
     *
     * @param file - Recibe la ruta completa del archivo de propiedades
     *
     * @return InputStream - stream del archivo de propiedades para
     * configuracion.
     */
    private InputStream getInputStreamConfig(String file)  throws Exception {
        //FileInputStream fis = null;
        //IFSFileInputStream IFSfis = null;
        InputStream is = null;

        try {
            if (util.isOS("SYSTEM") || util.isOS("400")) {
                // Crea un objeto AS400 para el servidor que contiene los archivos.
                //AS400 as400 = new AS400(hostAS400);

                //is = new IFSFileInputStream(as400, file, IFSFileInputStream.SHARE_ALL);
                log.info("Archivo de configuración del servicio en AS400: {}-{}", file, is.available());
            } else {
                is = new FileInputStream(file);
                log.info("Archivo de configuración del servicio en Windows: {}-{}", file, is.available());
            }

            return is;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private String resolvePath(String path)  throws Exception {
        String resultPath = "";

        /*String separator = System.getProperty("file.separator");
                
        File file = new File(path);
        
        if(!file.exists()) {
            throw new Exception("La ruta de la carpeta de imágenes no es válida, valor: " + path);
        }
        
        if(!file.isDirectory()) {
            throw new Exception("La ruta de la carpeta de imágenes no es un directorio, valor: " + path);            
        }        

        if(!file.getPath().endsWith(separator)) {
            resultPath = path + separator;
        } else {
            resultPath = path;
        }*/
        if (!path.endsWith("/")) {
            resultPath = path + "/";
        }

        return resultPath;
    }

    /**
     * Carga propiedades que son leidas en la replicación indicando
     * configuraciones generales del servicio.
     */
    private void loadAppSetup()  throws Exception {
        appSetup = new AppSetup();

        //Configuración general      
        appSetup.setJsfSuffix(getProp(propsNTI, "app.jsfSuffix", NTI_PROPS));
        appSetup.setAppTitle(getProp(propsNTI, "app.title", NTI_PROPS));
        appSetup.setProductName(getProp(propsNTI, "app.productName", NTI_PROPS));
        appSetup.setUrlIndex(getProp(propsNTI, "app.urlIndex", NTI_PROPS, true));
        appSetup.setDomain(getProp(propsNTI, "app.domain", NTI_PROPS));
        appSetup.setPassPhrase(getProp(propsNTI, "app.passPhrase", NTI_PROPS));
        appSetup.setLoginFailedAttempts(Integer.parseInt(getProp(propsNTI, "app.loginFailedAttempts", NTI_PROPS)));
        appSetup.setLoginHoursBloqued(Integer.parseInt(getProp(propsNTI, "app.loginHoursBloqued", NTI_PROPS)));
        appSetup.setDefaultRealmGroupName(getProp(propsNTI, "app.defaultRealmGroupName", NTI_PROPS));

        appSetup.setLocaleLanguage(getProp(propsNTI, "app.locale.language", NTI_PROPS, true).toLowerCase());
        appSetup.setLocaleCountry(getProp(propsNTI, "app.locale.country", NTI_PROPS, true).toUpperCase());
        appSetup.setTimeZoneId(getProp(propsNTI, "app.timeZone.id", NTI_PROPS, true));
    }
    
    private void loadPasswordPolicySetup() throws Exception {
        this.pwdPolicySetup = new PasswordPolicySetup();

        this.pwdPolicySetup.setPwdAttemptsAllowed(Integer.parseInt(getProp(propsNTI, "pwd.policy.allowedAttempts", NTI_PROPS)));
        this.pwdPolicySetup.setBlockHours(Integer.parseInt(getProp(propsNTI, "pwd.policy.blockHours", NTI_PROPS)));
        this.pwdPolicySetup.setDaysPasswordTemporaryExpiration(Integer.parseInt(getProp(propsNTI, "pwd.policy.daysPasswordTemporaryExpirationForgotPassword", NTI_PROPS)));
    }

    private void loadAlertSetup()  throws Exception {
        alertSetup = new AlertSetup();

        alertSetup.setSentEmail((getProp(propsNTI, "control.box.sent", NTI_PROPS, true).equals("yes")));
        alertSetup.setControl((getProp(propsNTI, "control.box.control", NTI_PROPS, true).equals("yes")));
        alertSetup.setEmail(getProp(propsNTI, "control.box.email", NTI_PROPS, true));
        alertSetup.setTemplatePath(getProp(propsNTI, "control.box.template", NTI_PROPS, true));
    }
    
    /**
     * Carga propiedades que son leidas en la replicación indicando
     * configuraciones generales del servicio.
     */
    private void loadMailSetup()  throws Exception {
        mailSetup = new MailSetup();

        mailSetup.setDebug(getProp(propsNTI, "mail.debug", NTI_PROPS).equalsIgnoreCase("yes"));
        mailSetup.setServerHost(getProp(propsNTI, "mail.server.host", NTI_PROPS));
        mailSetup.setServerPort(Integer.parseInt(getProp(propsNTI, "mail.server.port", NTI_PROPS)));
        mailSetup.setTimeout(Integer.parseInt(getProp(propsNTI, "mail.server.timeout", NTI_PROPS)));
        mailSetup.setUsername(getProp(propsNTI, "mail.account.username", NTI_PROPS));
        mailSetup.setPassword(getProp(propsNTI, "mail.account.password", NTI_PROPS));
        mailSetup.setFrom(getProp(propsNTI, "mail.account.from", NTI_PROPS));
        mailSetup.setSubjectPreffix(getProp(propsNTI, "mail.account.subjectPreffix", NTI_PROPS));
        mailSetup.setTextCodec(getProp(propsNTI, "mail.account.textCodec", NTI_PROPS));
        mailSetup.setStartTLS(getProp(propsNTI, "mail.startTLS.enabled", NTI_PROPS).equalsIgnoreCase("yes"));
        mailSetup.setSslPort(Integer.parseInt(getProp(propsNTI, "mail.ssl.port", NTI_PROPS)));
        mailSetup.setSslFallback(getProp(propsNTI, "mail.ssl.fallback.enabled", NTI_PROPS).equalsIgnoreCase("yes"));
        mailSetup.setKeyStoreType(getProp(propsNTI, "mail.keyStore.type", NTI_PROPS));
        mailSetup.setKeyStorePassword(getProp(propsNTI, "mail.keyStore.password", NTI_PROPS));
    }

    /**
     * Carga propiedades que corresponde la conexión SSH al servidor donde se
     * decargan los archivos FTP de Procard.
     */
    private void loadSshSetup()  throws Exception {
        sshSetup = new SshSetup();

        sshSetup.setUserName(getProp(propsNTI, "ssh.appServer.userName", NTI_PROPS));
        sshSetup.setPassword(getProp(propsNTI, "ssh.appServer.password", NTI_PROPS));
        sshSetup.setHost(getProp(propsNTI, "ssh.appServer.host", NTI_PROPS));
        sshSetup.setPort(Integer.parseInt(getProp(propsNTI, "ssh.appServer.port", NTI_PROPS)));
        sshSetup.setTimeout(Integer.parseInt(getProp(propsNTI, "ssh.appServer.timeout", NTI_PROPS)));
    }

    private void loadGatewayGSMSetup()  throws Exception {
        this.gatewayGSMSetup = new GatewayGSMSetup();
        this.gatewayGSMSetup.setUrlWSDL(getProp(propsNTI, "app.gateway.sms.wsdl", NTI_PROPS));
        this.gatewayGSMSetup.setPort(getProp(propsNTI, "app.gateway.sms.puerto", NTI_PROPS));
    }
    
    private void loadSentinelAlertSetup()  throws Exception {
        sentinelAlertSetup = new SentinelAlertSetup();
        
        sentinelAlertSetup.setRol(getProp(propsNTI, "sentinel.alert.rol", NTI_PROPS));
        sentinelAlertSetup.setHighPercent(Integer.parseInt(getProp(propsNTI, "sentinel.alert.high.percent", NTI_PROPS)));
        sentinelAlertSetup.setHighColor(getProp(propsNTI, "sentinel.alert.high.color", NTI_PROPS));
        sentinelAlertSetup.setMiddlePercent(Integer.parseInt(getProp(propsNTI, "sentinel.alert.middle.percent", NTI_PROPS)));
        sentinelAlertSetup.setMiddleColor(getProp(propsNTI, "sentinel.alert.middle.color", NTI_PROPS));
        sentinelAlertSetup.setLowPercent(Integer.parseInt(getProp(propsNTI, "sentinel.alert.low.percent", NTI_PROPS)));
        sentinelAlertSetup.setLowColor(getProp(propsNTI, "sentinel.alert.low.color", NTI_PROPS));
    }

    /**
     * Carga las propiedades que serán utilizadas por la app.
     */
    private void load()  throws Exception {
        if (!loaded) {
            loadAppSetup();
            loadPasswordPolicySetup()
//            loadMailSetup();
//            loadSshSetup();
//            loadGatewayGSMSetup();
//            loadAlertSetup();
//            loadSentinelAlertSetup()
                    ;
            
                    
            closeProperties();
            
            loaded = true;
        }
    }

    private boolean existsKey(Properties p, String key) {
        for (Map.Entry<Object, Object> entry : p.entrySet()) {
            Object key1 = entry.getKey();
            if (key1.toString().equals(key)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Busca y valida que exista la clave que se trata de obtener su valor.
     *
     * @param p
     * @param key
     * @param nameProp
     * @return
     * @throws AkdeECConfigException
     */
    private String getProp(Properties p, String key, String nameProp)  throws Exception {
        return getProp(p, key, nameProp, true);
    }

    private String getProp(Properties p, String key, String nameProp, boolean validExistsKey)  throws Exception {
        String value = null;

        if (!existsKey(p, key)) {
            if (validExistsKey) {
                throw new Exception(String.format("El archivo de propiedades (%s) no contiene la clave: ", nameProp) + key);
            } else {
                return null;
            }
        }

        try {
            value = p.getProperty(key);

            if (value != null && !value.isEmpty()) {
                value = new String(value.getBytes(), "UTF-8"); // encoding UTF-8
            }
        } catch (Exception ex) {
            throw new Exception(String.format("No se codificar a UTF-8 el valor de la propiedad %s: %s", key, value), ex);
        }

        //XXX 1.6 trim() borra enter + avance de linea
        //if (value == null || value.isEmpty()) {
        //    throw new AkdeECConfigException(String.format("El archivo de propiedades (%s) no contiene la clave: ", nameProp) + key);
        //}
        //aa 23/06/2011
        //El trim() fallo, eliminó los caracteres \r\n seteados en el archivo de dss.propiedades,
        //como fin de msg. de interface, esto paso después de una actualizaciónd de java.
        //XXX Preguntar por valores ascii charAt() o algo parecido.
        //return value.trim();
        return value.endsWith("\r") || value.endsWith("\n") ? value : value.trim();
    }

    /**
     * Escribe un valor según su clave en el archivo de propiedades principal.
     *
     * @param key Clave a actualizar.
     * @param value valor a guardarse en la clave.
     */
    private void setPropsDSS(String key, String value)  throws Exception {
        OutputStream os = null;
        Properties outProps = null;
        openProperties();
        propsNTI.setProperty(key, value);

        try {
            os = getOutputStreamConfig(appPathConfig + NTI_PROPS);
            propsNTI.store(os, null);
            os.flush();
        } catch (Exception ex) {
            throw new Exception("Error al setear una propiedad.", ex);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                os = null;
                outProps = null;
            } catch (Exception ex) {
                //DB2InsertLog.saveLog(ex, "", "", "Error al cerrar el archivo de propiedades.", "CONFIG");
            }
        }
    }

    public AppSetup getAppSetup() {
        return appSetup;
    }

    public SentinelAlertSetup getSentinelAlertSetup() {
        return sentinelAlertSetup;
    }

    public void setSentinelAlertSetup(SentinelAlertSetup sentinelAlertSetup) {
        this.sentinelAlertSetup = sentinelAlertSetup;
    }

    public MailSetup getMailSetup() {
        return mailSetup;
    }

    public PasswordPolicySetup getPwdPolicySetup() {
        return pwdPolicySetup;
    }

    public SshSetup getSshSetup() {
        return sshSetup;
    }

    public AlertSetup getAlertSetup() {
        return alertSetup;
    }

    public GatewayGSMSetup getGatewayGSMSetup() {
        return gatewayGSMSetup;
    }

    public void setGatewayGSMSetup(GatewayGSMSetup gatewayGSMSetup) {
        this.gatewayGSMSetup = gatewayGSMSetup;
    }
    
    /*
    public void setMailForgotPasswordSetup(MailForgotPasswordSetup mailForgotPasswordSetup) {
        this.mailForgotPasswordSetup = mailForgotPasswordSetup;
    }
     */
    public static ResourceManager getInstance()  throws Exception {
        try {
             if (resourceConfig == null) {
                synchronized(LOCK) {
                    
                    // Solo una vez se debe realizar el NEW.
                    if(resourceConfig == null) {
                        resourceConfig = resourceConfig = new ResourceManager();
                    }
                    
                }
            }

            return resourceConfig;
        } catch (Exception ex) {
            if (ex instanceof Exception) {
                throw (Exception) ex;
            }

            throw new Exception("Error al intentar instanciar el singleton.", ex);
        }
    }

    public static void main(String[] args)  throws Exception {

        System.out.println(ResourceManager.getInstance().getAppSetup().toString());

        System.out.println("por qui entro!!");

        /*
        System.out.println(ResourceManager.getInstance().getMailUserConfirmSetup().toString());        
         */
    }

    class CheckUpdateFile extends Thread {

        private java.io.File file;

        private CheckUpdateFile() {
            init();
        }

        private void init() {
            file = new File(appPathConfig + NTI_PROPS);

            if (file.exists()) {
                firstLoadTime = file.lastModified();
            }

            file = null;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    file = new File(appPathConfig + NTI_PROPS);

                    if (file.exists()) {
                        long currentLoad = file.lastModified();

                        if (firstLoadTime != currentLoad) {
                            firstLoadTime = currentLoad;

                            loaded = false;
                            openProperties();
                            load();

                            log.info("Se re-cargaron las propiedades al detectarse cambios en el archivo: {}{}", appPathConfig, NTI_PROPS);
                        }
                    } else {
                        log.info("El archivo de configuración {}{} no existe.", appPathConfig, NTI_PROPS);
                    }

                    file = null;
                    Thread.sleep(120000);
                }
            } catch (Exception ex) {
                log.info("Error al chequear cambios en archivo de propiedades.", ex);
            }
        }

        @Override
        public void interrupt() {
            log.info("Se interrumpió el hilo que chequea el archivo de propiedades.");
        }
    }
}