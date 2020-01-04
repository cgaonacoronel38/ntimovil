package py.com.documenta.ntimovil.controller.session;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.bean.setup.AppSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.SentinelAlertSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.SetupManager;

/**
 *
 * @author Eduardo Caceres
 */
@javax.inject.Named(value = "confMB")
@javax.enterprise.context.SessionScoped
public class Config implements Serializable {

    private final Logger log = LoggerFactory.getLogger(Config.class);

    @Inject
    private SetupManager sm;

    @Inject
    private NtiSession ntiMB;

    private AppSetup appSetup;

//    private MailSetup mailSetup;
    
    private SentinelAlertSetup sentinelAlertSetup;

    private String host;

    @PostConstruct
    public void init() {
        try {
            this.appSetup = sm.instance().getAppSetup();
//            this.mailSetup = sm.instance().getMailSetup();
//            this.sentinelAlertSetup = sm.instance().getSentinelAlertSetup();
        } catch (Exception ex) {
            log.error("No se pudo obtener el bean de configuración del aplicativo.", ex);
        }
        
        try {
            this.host = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            this.host = "NN";
            log.error("Error al obtener el hostname");
        }
    }

    public int getCurrYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public String appTitle() throws Exception {
        return appSetup.getAppTitle();
    }

    public String appJSFSufix() throws Exception {
        return appSetup.getJsfSuffix();
    }

    public AppSetup getAppSetup() {
        return appSetup;
    }

//    public MailSetup getMailSetup() {
//        return mailSetup;
//    }
//
//    public void setMailSetup(MailSetup mailSetup) {
//        this.mailSetup = mailSetup;
//    }

    public SentinelAlertSetup getSentinelAlertSetup() {
        return sentinelAlertSetup;
    }

    public void setSentinelAlertSetup(SentinelAlertSetup sentinelAlertSetup) {
        this.sentinelAlertSetup = sentinelAlertSetup;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
