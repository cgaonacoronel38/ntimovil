package py.com.documenta.ntimovil.controller.view.auth;

//package com.documenta.nti.controller.view.auth;
//
//import com.documenta.gateway.MessengerSms;
//import com.documenta.gateway.sms.Sms_Type;
//import com.documenta.nti.permits.CheckPermission;
//import py.com.documenta.ntimovil.common.MsgUtil;
//import com.documenta.nti.controller.session.NavigateBean;
//import com.documenta.nti.controller.session.NtiSession;
//
//import com.documenta.nti.ejb.bean.setup.EmailTemplateSetup;
//import com.documenta.nti.ejb.bean.setup.GatewayGSMSetup;
//import com.documenta.nti.ejb.exception.NandutiEJBException;
//import com.documenta.nti.ejb.exception.NandutiException;
//import com.documenta.nti.ejb.model.base.Usuario;
//import com.documenta.nti.ejb.sessionbean.base.UsuarioFacade;
//import com.documenta.nti.ejb.sessionbean.reddepago.CajaFacade;
//import com.documenta.nti.ejb.sessionbean.reddepago.CajacierreFacade;
//import com.documenta.nti.ejb.sessionbean.reddepago.DispositivoFacade;
//import com.documenta.nti.enums.ETypeAlert;
//
//import static com.documenta.nti.ejb.enums.ETipoEstadoUsuario.*;
//import com.documenta.nti.ejb.model.reddepago.Caja;
//import com.documenta.nti.ejb.sessionbean.services.EmailService;
//import com.documenta.nti.ejb.sessionbean.services.SetupManager;
//
//
//import com.documenta.util.encript.AESSymetricCrypto;
//import com.documenta.util.exception.DCTAUtilException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.File;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// *
// * @author eduardo.caceres
// */
//@ManagedBean(name = "changePassForgotMB")
//@ViewScoped
//public class ChangePasswordForgot implements Serializable {
//
//    private final Logger log = LoggerFactory.getLogger(ChangePasswordForgot.class);
//
//    @Inject
//    private NavigateBean nav;
//
//    @Inject
//    private UsuarioFacade usuarioEJB;
//
//    @Inject
//    private CajaFacade cajaEJB;
//
//    @Inject
//    private DispositivoFacade dispositivoEJB;
//
//    @Inject
//    private NtiSession ntiSession;
//
//    @Inject
//    private CheckPermission permiso;
//
//    @Inject
//    private CajacierreFacade cajaCierreEJB;
//    
//    @Inject
//    private EmailService emailService;
//
//    @Inject
//    private SetupManager sm;
//    
//    private GatewayGSMSetup gatewayGSMSetup;
//    private static final String DOCU_DOMAIN = "localhost";
//    private Usuario user;
//    private String password;
//    private Long idTerminal;
//    private String email;
//    private String nroCel;
//    private MessengerSms messengerSms;
//    
//
//    public String getNroCel() {
//        return nroCel;
//    }
//
//    public void setNroCel(String nroCel) {
//        this.nroCel = nroCel;
//    }
//    private String newPassword;
//    private String confirmNewPassword;
//    private String encryptedToken;
//    private Boolean isMANDATORY_CHANGE;
//    private Boolean isNEW_REGISTERED;
//     
//    private EmailTemplateSetup templateEmail;
//     private String deviceInfoDoorway;
//
//    public String getDeviceInfoDoorway() {
//        return deviceInfoDoorway;
//    }
//
//    public void setDeviceInfoDoorway(String deviceInfoDoorway) {
//        this.deviceInfoDoorway = deviceInfoDoorway;
//        log.info("deviceInfoDoorway" + this.deviceInfoDoorway);
//    }
//    
//    public Boolean getIsNEW_REGISTERED() {
//        return isNEW_REGISTERED;
//    }
//
//    public void setIsNEW_REGISTERED(Boolean isNEW_REGISTERED) {
//        this.isNEW_REGISTERED = isNEW_REGISTERED;
//    }
//
//    public Boolean getIsMANDATORY_CHANGE() {
//        return isMANDATORY_CHANGE;
//    }
//
//    public void setIsMANDATORY_CHANGE(Boolean isMANDATORY_CHANGE) {
//        this.isMANDATORY_CHANGE = isMANDATORY_CHANGE;
//    }
//            
//    public String getEncryptedToken() {
//        return encryptedToken;
//    }
//
//    public void setEncryptedToken(String encryptedToken) {
//        this.encryptedToken = encryptedToken;
//    }
//
//    
//
//    public String getConfirmNewPassword() {
//        return confirmNewPassword;
//    }
//
//    public void setConfirmNewPassword(String confirmNewPassword) {
//        this.confirmNewPassword = confirmNewPassword;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getNewPassword() {
//        return newPassword;
//    }
//
//    public void setNewPassword(String newPassword) {
//        this.newPassword = newPassword;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public Long getIdTerminal() {
//        return idTerminal;
//    }
//
//    public void setIdTerminal(Long idTerminal) {
//        this.idTerminal = idTerminal;
//    }
//
//    private boolean valid() throws NandutiEJBException {
//        boolean ok = true;
//
//        if(!this.newPassword.equals(this.confirmNewPassword)) // si no coincide el password nuevo.
//        {
//            ok=false;
//            MsgUtil.addMessageToast(ETypeAlert.WARNING,"Confirmacion de Contraseña no coincide.");
//        }
//        
//        String passSHA = this.user.getPassword();
//        String passEnterSHA= this.usuarioEJB.setPasswordSHA512Hex(this.password);
//        
//        if(!passSHA.equals(passEnterSHA)) // si no coincide el hash
//        {
//            ok=false;
//            MsgUtil.addMessageToast(ETypeAlert.WARNING,"Constraseña incorrecta.");
//        }
//        
//        return ok;
//    }
//
//    public void changePass() throws IOException, DCTAUtilException  {
//        try{
//            //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//            if (!valid()) {
//                return;
//            }
//            String infoDispositivo = infoDispositivo();
//            log.info("infoDispositivo =" + infoDispositivo);
//            String passSHA = this.usuarioEJB.setPasswordSHA512Hex(this.newPassword);
//            this.user.setPassword(passSHA);
//            this.user.setTipoestado(ACTIVE);
//            Integer cantcambiopwd = this.user.getCantcambiopwd();
//            this.user.setCantcambiopwd(cantcambiopwd);
//            this.usuarioEJB.edit(this.user);
//            this.sendEmail(user);
//            Caja findByIdUser = this.cajaEJB.findByIdUser(user.getIdusuario());
//            this.sendEmailCambioPasswordResponsableRecaudador(user, findByIdUser);
//            this.sendEmailForgotPasswordJefeSAU(user, findByIdUser);
//            if(user.getNrocel() != null || !user.getNrocel().isEmpty()){
//                this.sendSMSChangeForgotPassword(user);
//            }
//            MsgUtil.addMessageToast(ETypeAlert.INFO, "Contraseña Cambiada con Exito");
//            //ec.redirect(this.nav.getLogin());
//            //this.sendEmail(this.user); //Enviar Email Informe de cambio de contraseña
//        } catch(NandutiEJBException ex){
//             log.error("Ocurrio un error al momento de cambiar la contraseña.", ex);
//              MsgUtil.addMessageToast(ETypeAlert.ERROR, "Error al cambiar constraseña:" + ex.getMessage());
//            
//        }
//    }
//    
//    private boolean validForgotPassword() throws NandutiEJBException, Exception {
//        boolean ok = true; 
//        
//        String passPhrase="";
//        String tipoEstado= user.getTipoestado().toString();
//        if(!user.getTipoestado().equals(MANDATORY_CHANGE) && !user.getTipoestado().equals(NEW_REGISTERED)){
//            passPhrase = user.getDiasvencpwd() + user.getTipoestado().name(); // se utiliza para descifrar el key
//            
//        }else {
//            passPhrase = user.getTipoestado().name();// passPhrase para MANDATORY_CHANGE
//            
//            while(passPhrase.length()<16){
//                passPhrase = passPhrase + "1";
//                tipoEstado = tipoEstado + "1";
//            }
//            
//        }
//        
//        String token = AESSymetricCrypto.decript(this.encryptedToken, passPhrase);
//        String[] split = token.split("\\|");
//        log.info("token split:" + split.toString());
//        log.info("User password:"+ user.getPassword());
//        log.info("password Descifrado:" + split[1]);
//        String passwordRecibidoEnToken;
//        
//       
//        
//        if(passPhrase.equals(tipoEstado)){ // cuando es MANDATORY_CHANGE o NEW_REGISTERED
//            passwordRecibidoEnToken = split[1];
//        }else {
//            passwordRecibidoEnToken = this.usuarioEJB.setPasswordSHA512Hex(split[1]);
//        }
//        
//        
//        if(!passwordRecibidoEnToken.equals(user.getPassword())) /// compara el hash de las contraseñas
//        {
//            log.info("El hash de las contraseñas es diferente.");
//            MsgUtil.addMessageToast(ETypeAlert.WARNING, "Token alterado");
//            ok=false;
//            return ok;
//        }
//        
//        
//        if(!split[0].equals(user.getUsername()))
//        {
//            log.warn("Los usuarios son diferentes.");
//            MsgUtil.addMessageToast(ETypeAlert.WARNING, "Token alterado");
//            ok=false;
//            return ok;
//        }
//        
//        if(!split[2].equals(user.getDiasvencpwd().toString()))
//        {
//            MsgUtil.addMessageToast(ETypeAlert.WARNING, "Token alterado");
//            log.warn("Dias de Vencimento no coinciden");
//            ok=false;
//            return ok;
//        }
//
//        
//
//        if(!this.newPassword.equals(this.confirmNewPassword)) // si no coincide el password nuevo.
//        {
//            
//            ok=false;
//            MsgUtil.addMessageToast(ETypeAlert.WARNING,"Confirmacion de Contraseña no coincide.");
//        }
//         
//        String passSHA = this.user.getPassword();
//        String passEnterSHA= this.usuarioEJB.setPasswordSHA512Hex(this.password);
//        
//        if(!passSHA.equals(passEnterSHA)) // si no coincide el hash
//        {
//            ok=false;
//            MsgUtil.addMessageToast(ETypeAlert.WARNING,"Constraseña incorrecta.");
//        }
//        
//        if(this.newPassword.length() < 8 ){
//            ok=false;
//            MsgUtil.addMessageToast(ETypeAlert.WARNING,"La contraseña es corta.");
//        }
//        
//        return ok;
//    }
//    
//    public void changeForgotPassword() throws IOException, Exception  {
//        try{
//            
//            if (!this.validForgotPassword()) {
//                return;
//            }
//            String info = this.infoDispositivo();
//            log.info("InfoDevice es:" + info);
//            String passSHA = this.usuarioEJB.setPasswordSHA512Hex(this.newPassword);
//            this.user.setPassword(passSHA);
//            this.user.setDiasvencpwd(0);
//            Date date = new Date();
//            this.user.setUltimocambiopwd(date); 
//            this.user.setTipoestado(ACTIVE);
//            if(this.email != null){
//            
//                //this.user.setCorreo(this.email);
//                log.info("email es distinto de null:" + this.email);
//            }
//            Integer cantcambiopwd = this.user.getCantcambiopwd();
//            cantcambiopwd++;
//            this.user.setCantcambiopwd(cantcambiopwd);
//            this.usuarioEJB.edit(this.user);
//            this.isNEW_REGISTERED=false;
//            this.sendEmail(user);
//            Caja findByIdUser = this.cajaEJB.findByIdUser(user.getIdusuario());
//            this.sendEmailForgotPasswordJefeSAU(user, findByIdUser);
//            this.sendEmailCambioPasswordResponsableRecaudador(user, findByIdUser);
//            if(user.getNrocel() != null || !user.getNrocel().isEmpty()){
//                this.sendSMSChangeForgotPassword(user);
//            }
//            MsgUtil.addMessageToast(ETypeAlert.INFO, "Contraseña Cambiada con Exito");
//            //ec.redirect(this.nav.getLogin());
//            //this.sendEmail(this.user); //Enviar Email Informe de cambio de contraseña
//        } catch(NandutiEJBException ex){
//             log.error("Ocurrio un error al momento de cambiar la contraseña.", ex);
//              MsgUtil.addMessageToast(ETypeAlert.ERROR, "Error al cambiar constraseña:" + ex.getMessage());
//            
//        }
//    }
//
//    public Usuario getUser() {
//        return user;
//    }
//
//    public void setUser(Usuario user) {
//        this.user = user;
//    }
//    
//    @PostConstruct
//    public void init()  {
//        try{
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//                String u = request.getParameter("u");
//                String token= request.getParameter("q");
//                log.info("Change Password Init.");
//                log.info("Toke=" + token);
//                log.info("usuario=" + u);
//                Usuario findByUser = this.usuarioEJB.findByUser(u);
//                log.info("Usuario obtenido:" + findByUser.getUsername());
//                this.user = findByUser;
//                this.encryptedToken = token;
//                //String passPhrase = findByUser.getDiasvencpwd() + findByUser.getTipoestado().name();
//                String passPhrase = "";
//                if(!findByUser.getTipoestado().equals(MANDATORY_CHANGE) && !findByUser.getTipoestado().equals(NEW_REGISTERED) ){
//                        passPhrase = findByUser.getDiasvencpwd() + findByUser.getTipoestado().name();
//                        this.isMANDATORY_CHANGE =false;
//                        this.isNEW_REGISTERED = false;
//                }
//                else if(findByUser.getTipoestado().equals(MANDATORY_CHANGE)){
//                    passPhrase = MANDATORY_CHANGE.toString();
//                    this.isMANDATORY_CHANGE =true;
//                    this.isNEW_REGISTERED = false;
//                    this.email = this.getEmailSemiOculto(findByUser.getCorreo());
//                    this.nroCel = this.getNroSemiOculto(findByUser.getNrocel());
//                    while(passPhrase.length()<16){
//                                passPhrase = passPhrase +"1";
//                            }
//                }else if(findByUser.getTipoestado().equals(NEW_REGISTERED)){
//                    passPhrase = NEW_REGISTERED.toString();
//                    this.isNEW_REGISTERED =true;
//                    this.isMANDATORY_CHANGE = false;
//                    this.email = this.getEmailSemiOculto(findByUser.getCorreo());
//                    this.nroCel = this.getNroSemiOculto(findByUser.getNrocel());
//                    while(passPhrase.length()<16){
//                                passPhrase = passPhrase +"1";
//                    }
//                }
//                
//                this.templateEmail = sm.instance().getEmailTemplateSetup();
//                 this.gatewayGSMSetup = sm.instance().getGatewayGSMSetup();
//                String decript = AESSymetricCrypto.decript(this.encryptedToken, passPhrase);
//                this.messengerSms = new MessengerSms(this.gatewayGSMSetup.getUrlWSDL(),this.gatewayGSMSetup.getPort());
//               
//                
//        }catch(NandutiException ex){
//            try {
//                log.error("Error al obtener parametros por GET, link alterado:", ex);
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/err/errPage.nti");
//            } catch (IOException ex1) {
//                java.util.logging.Logger.getLogger(ChangePasswordForgot.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//        } catch (DCTAUtilException ex) {
//            log.error("DCTAUtil exception",ex);
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/err/errPage.nti");
//            } catch (IOException ex1) {
//                java.util.logging.Logger.getLogger(ChangePasswordForgot.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//        }
//    }
//    
//  public void sendEmail(Usuario user)
//    {
//        try{
//            String correoresponsable = user.getCorreo();
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//            File input = new File(realPath);
//            Document doc = Jsoup.parse(input, "UTF-8");
//            String plantilla= doc.toString();
//            String msg =  plantilla.replace("{{texto}}", "<p>El usuario " + user.getUsername() +", ha modificado la contraseña .</p><p>Te hemos enviado este correo electrónico para notificar esta acción.</p>")
//                    .replace("{{title-header}}", "")
//                    .replace("{{title1}}", "Alerta de seguridad- Ñanduti")
//                    .replace("{{year}}", this.templateEmail.getYear())
//                    + "<p>Favor no responder a este correo o SMS, es un envio automático.</p>";
//            log.info("Texto Email: {}", msg);
//            
//            if(correoresponsable != null){
//                this.emailService.send(3,null,
//                             msg,
//                             null,
//                             "Alerta de seguridad- Ñanduti",
//                             correoresponsable
//                             );
//            
//                log.info("Notificación enviada a Resposable del Recaudador usuario: {}", user.getUsername());
//            }else{
//                MsgUtil.addSweetAlert(ETypeAlert.WARNING, 
//                    "Envío de correo sin finalizar", 
//                    "El usuario no posee configurado el correo para realizar envios!!!");
//            }
//        }
//        catch(NandutiEJBException | IOException ex) {
//            log.error("No se pudo enviar email al usuario.", ex);
//        }
//    }
//  
//  private void sendEmailCambioPasswordResponsableRecaudador(Usuario user,Caja cajaDelUsuario){
//        try{
//            String correoresponsable = cajaDelUsuario.getSucursal().getRecaudador().getCliente().getCorreoresponsable();
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//            File input = new File(realPath);
//            Document doc = Jsoup.parse(input, "UTF-8");
//            String plantilla= doc.toString();
//            String msg =  plantilla.replace("{{texto}}", "<p>El usuario " + user.getUsername() + ", ha modificado la contraseña de la caja: " + cajaDelUsuario.getDescripcion() + "\n" + infoDispositivo() + ".</p><p>Te hemos enviado este correo electrónico para notificar esta acción.</p>")
//                    .replace("{{title-header}}", "")
//                    .replace("{{title1}}", "Alerta de seguridad- Ñanduti")
//                    .replace("{{year}}", this.templateEmail.getYear())
//                    + "<p>Favor no responder a este correo o SMS, es un envio automático.</p>";
//            log.info("Texto Email: {}", msg);
//            
//            this.emailService.send(3,null,
//                             msg,
//                             null,
//                             "Alerta de seguridad- Ñanduti",
//                             correoresponsable
//                             );
//            
//            log.info("Notificación enviada a Resposable del Recaudador usuario: {}", user.getUsername());
//        }
//        catch(NandutiEJBException | IOException ex) {
//            log.error("No se pudo enviar email al responsable del recaudador.", ex);
//        }catch(Exception ex){
//            log.error("Error al enviar email a Responsable de Recaudador: ",ex.getMessage());
//        }
//    }
//  
//   public void sendSMSChangeForgotPassword(Usuario user){
//        try {
//            String nro = user.getNrocel();
//            String nroConCodInternacional= "595" + nro.substring(1, nro.length());
//            String texto = "Cambio de Contraseña ("+user.getCantcambiopwd() +"):Documenta S.A. le informa que el usuario:\n" + user.getUsername() +"\n ha modificado la contraseña exitosamente.";
//            Sms_Type sms = new Sms_Type();
//            sms.setNumeroDestino(nroConCodInternacional);
//            sms.setTexto(texto);
//            sms.setIdTransaccion("0");
//            List listMsj = new ArrayList();
//            listMsj.add(sms);
//            this.messengerSms.enviarMensajes(listMsj);
//            log.info("Notificación enviada al usuario: {}", user.getUsername());
//        } catch(Exception ex) {
//            log.error("No se pudo enviar email al usaurio:",ex);
//        }
//    }
//  
//   
//   public void sendEmailForgotPasswordJefeSAU(Usuario user,Caja cajaDelUsuario) {
//       try {
//            String correoresponsable = this.templateEmail.getEmailJefeSAU();
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//            if(!correoresponsable.equals(user.getCorreo())){
//                File input = new File(realPath);
//                Document doc = Jsoup.parse(input, "UTF-8");
//                String plantilla= doc.toString();
//                String msg =  plantilla.replace("{{texto}}", "<p>El usuario " + user.getUsername() + ", ha modificado la contraseña de la caja: " + cajaDelUsuario.getDescripcion() + "\n" + infoDispositivo() + ".</p><p>Te hemos enviado este correo electrónico para notificar esta acción.</p>"
//                        + "<p>Favor no responder a este correo o SMS, es un envio automático.</p>")
//                        .replace("{{title-header}}", this.templateEmail.getRecoveryTitleHeader().replace("\"", ""))
//                        .replace("{{title1}}", "Alerta de seguridad- Ñanduti")
//                        .replace("{{year}}", this.templateEmail.getYear());
//                log.info("Texto Email: {}", msg);
//                this.emailService.send(3,null,
//                                 msg,
//                                 null,
//                                 "Alerta de seguridad- Ñanduti",
//                                 correoresponsable
//                                 );
//                
//                log.info("Notificacion enviada a Jefe SAU usuario: {}", user.getUsername());
//            } else {
//                log.info("El correo del responsable coincide con el del usuario. No se envia el correo.");
//            }  
//          }
//        catch(NandutiEJBException | IOException ex) {
//           log.error("No se pudo enviar email al Jefe SAU:",ex);
//        } catch(Exception ex){
//            log.error("Error al enviar email a Jefe SAU: ",ex.getMessage());
//        }
//    }
//   
////   private void enviarMensajes(List<Sms_Type> mensajes) 
////    {
////            try {
////                log.info("Enviado Mensaje por API SOAP.");
////                String paramURLGateway = this.gatewayGSMSetup.getUrl_WSDL();
////                String paramPuertoPractigiro = this.gatewayGSMSetup.getPuerto();
////                Sms wsManagerGateway = this.getWSManagerGateway(paramURLGateway, "http://ws.gateway.documenta.com/", "sms", 10, 115);
////                wsManagerGateway.enviarSMS(paramPuertoPractigiro, mensajes);
////            } catch (FileNotFoundException_Exception | InterruptedException_Exception | ParseException_Exception ex) {
////                log.error(ex.getMessage());
////            }
////    }
////   
////   private Sms getWSManagerGateway(String url, String uri, String localPart, int connTo, int readTo) {
////         
////        Sms pexSoap = null;
////        Sms_Service service = null;
////        ReentrantLock lock = new ReentrantLock();
////        //URL wsdlURL = CollectorStatusWS_Service.class.getClassLoader().getResource("src/conf/xml-resources/web-service-references/CollectorStatusWS/wsdl/192.168.30.226_8585/sentinelws/CollectorStatusWS.wsdl");
////        try {
////            if (service == null) {
////                try {
////                    lock.lock();
////                    if (service == null) {
////                        service = new Sms_Service();//CollectorStatusWS_Service(wsdlURL, new QName(uri, localPart));
////                    }
////                } catch (Exception ex) {
////                } finally {
////                    lock.unlock();
////                }
////            } else {
////            }
////            
////            pexSoap = service.getSmsPort();
////            BindingProvider provider = (BindingProvider) pexSoap;
////            provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
////            provider.getRequestContext().put("com.sun.xml.ws.connect.timeout", connTo * 1000);
////            provider.getRequestContext().put("com.sun.xml.ws.request.timeout", readTo * 1000);
////
////            Binding binding = provider.getBinding();
////            List<Handler> handlerList = binding.getHandlerChain();
////            handlerList.add(new SoapMessageHandler());
////            binding.setHandlerChain(handlerList);
////         } catch (Exception ex) {
////        
////             log.error("Error en getWSManagerGateway:", ex.getMessage());
////             MsgUtil.addMessageToast(ETypeAlert.ERROR, "Ocurrio un error en el Servicio de envio de SMS.");
////        
////        }
////
////        
////        return pexSoap;
////    
////   }
////   
////   
//  private String infoDispositivo() {
//        String info = "Sin infoDevice";
//       
//        try {
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode actualObj = mapper.readTree(this.getDeviceInfoDoorway());
//                log.info("getDeviceInfoDoorway textValue:" + actualObj.textValue());
//                log.info("getDeviceInfoDoorway toString:" + actualObj.toString());
//                info="<p> Hardware:</p>";
//                
//                JsonNode deviceHardware = actualObj.get("deviceHardware");
//                JsonNode volume = deviceHardware.get("volume");
//                JsonNode hdSerial = deviceHardware.get("hdSerial");
//                JsonNode mbSerial = deviceHardware.get("mbSerial");
//                JsonNode macAddress = deviceHardware.get("macAddress");
//                info = info + "<p>volume:" + volume + "</p>";
//                info =  info + "<p>hdSerial:" + hdSerial + "</p>";
//                info = info + "<p>mbSerial:" + mbSerial + "</p>";
//                info = info + "<p>macAddress:" + macAddress + "</p>";
//                
//                JsonNode deviceSoftware = actualObj.get("deviceSoftware");
//                JsonNode jreVersion = deviceSoftware.get("deviceHardware");
//                JsonNode osName = deviceSoftware.get("osName");
//                JsonNode osTypeArch = deviceSoftware.get("osTypeArch");
//                JsonNode osVersion = deviceSoftware.get("osVersion");
//                JsonNode deviceName = deviceSoftware.get("deviceName");
//                JsonNode hostAddress = deviceSoftware.get("hostAddress");
//                JsonNode osUserName = deviceSoftware.get("osUserName");
//                JsonNode doorwayVersion = deviceSoftware.get("doorwayVersion");
//                info = info + "<p>Software:</p>";
//                info = info + "<p>jreVersion:" + jreVersion + "</p>";
//                info = info + "<p>osName:" + osName + "</p>";
//                info = info + "<p>osTypeArch:" + osTypeArch + "</p>";
//                info = info + "<p>osVersion:" + osVersion + "</p>";
//                info = info + "<p>deviceName:" + deviceName + "</p>";
//                info = info + "<p>hostAddress:" + hostAddress + "</p>";
//                info = info + "<p>osUserName:" + osUserName + "</p>";
//                info = info + "<p>doorwayVersion:" + doorwayVersion + "<p>";
//                
//        } catch (IOException ex ) {
//            log.error("Error al armar infoDevice:" +  ex.getMessage());
//        }catch (Exception ex){
//            log.error("Error al armar infoDevice:" +  ex.getMessage());
//        }
//       
//        return info;
//    }
//  
//  private String getEmailSemiOculto(String email){
//     String r="";
//      try{
//         int index = email.indexOf("@");
//         String sub = email.substring(1, index);
//         String relleno="";
//         for(int i=0; i<sub.length(); i++){
//             relleno = relleno + "*";
//         }
//         r = email.substring(0,1) + relleno+ email.substring(index, email.length());
//     }catch(Exception ex){
//         log.error("Error al semiocultar "+ ex.getMessage());
//         r="";
//     }
//     
//     return r;
//  }
//  
//  private String getNroSemiOculto(String nro){
//        String r="";
//        
//        try{
//            r = nro.substring(0, 3) + "******" + nro.substring(9);
//        }catch(Exception ex){
//            log.error("No se puso semiocultar el nro "+ ex.getMessage());
//            r="";
//        }
//        
//        
//        return r;
//  }
//}
//                
//                
