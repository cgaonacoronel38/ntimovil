package py.com.documenta.ntimovil.controller.view.auth;

//package com.documenta.nti.controller.view.auth;
//
//import com.documenta.gateway.MessengerSms;
//import com.documenta.gateway.sms.Sms_Type;
//import com.documenta.nti.permits.CheckPermission;
//import py.com.documenta.ntimovil.common.MsgUtil;
//import com.documenta.nti.controller.session.NavigateBean;
//import com.documenta.nti.controller.session.NtiSession;
//import com.documenta.nti.ejb.bean.setup.EmailTemplateSetup;
//import com.documenta.nti.ejb.bean.setup.GatewayGSMSetup;
//import com.documenta.nti.ejb.exception.NandutiEJBException;
//import com.documenta.nti.ejb.exception.NandutiException;
//import com.documenta.nti.ejb.model.base.Usuario;
//import com.documenta.nti.ejb.model.reddepago.Caja;
//import com.documenta.nti.ejb.sessionbean.base.UsuarioFacade;
//import com.documenta.nti.ejb.sessionbean.reddepago.CajaFacade;
//import com.documenta.nti.ejb.sessionbean.reddepago.CajacierreFacade;
//import com.documenta.nti.ejb.sessionbean.reddepago.DispositivoFacade;
//import com.documenta.nti.ejb.sessionbean.services.EmailService;
//import com.documenta.nti.ejb.sessionbean.services.SetupManager;
//import com.documenta.nti.enums.ETypeAlert;
//
//import com.documenta.util.encript.AESSymetricCrypto;
//import com.documenta.util.exception.DCTAUtilException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.File;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// *
// * @author eduardo.caceres
// */
//@ManagedBean(name = "changePassMB")
//@ViewScoped
//public class ChangePassword implements Serializable {
//
//    private final Logger log = LoggerFactory.getLogger(ChangePassword.class);
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
//    private SetupManager sm;
//
//    @Inject
//    private CajacierreFacade cajaCierreEJB;
//
//    private static final String DOCU_DOMAIN = "localhost";
//
//    private Usuario user;
//    private String password;
//    private Long idTerminal;
//    private String email;
//    private String newPassword;
//    private String confirmNewPassword;
//    
//    @Inject
//    private EmailService emailService;
//    private EmailTemplateSetup templateEmail;
//    private GatewayGSMSetup gatewayGSMSetup;
//    private MessengerSms messengerSms;
//
//    public String getDeviceInfoDoorway() {
//        return deviceInfoDoorway;
//    }
//
//    public void setDeviceInfoDoorway(String deviceInfoDoorway) {
//        this.deviceInfoDoorway = deviceInfoDoorway;
//        log.info("deviceInfoDoorway" + this.deviceInfoDoorway);
//    }
//    private String deviceInfoDoorway;
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
//        if(this.user == null){
//            this.user=this.ntiSession.getUser();
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
//    public void changePass() throws IOException, DCTAUtilException, NandutiException  {
//        try{
//            //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//            if (!valid()) {
//                return;
//            }
//            String infoDispositivo = infoDispositivo();
//            log.info("infoDispositivo =" + infoDispositivo);
//            String passSHA = this.usuarioEJB.setPasswordSHA512Hex(this.newPassword);
//            this.user.setPassword(passSHA);
//            Integer cantcambiopwd = this.user.getCantcambiopwd();
//            cantcambiopwd++;
//            this.user.setCantcambiopwd(cantcambiopwd);
//            this.usuarioEJB.edit(this.user);   
//            MsgUtil.addMessageToast(ETypeAlert.INFO, "Contraseña Cambiada con Exito");
//            Caja cajaByIdUser = this.cajaEJB.findByIdUser(user.getIdusuario());
//            this.sendEmailCambioPasswordResponsableRecaudador(user, cajaByIdUser);
//            this.sendEmail(this.user); //Enviar Email Informe de cambio de contraseña
//            if(!user.getNrocel().equals("") && user.getNrocel() != null){
//                 this.sendSMSChangePassword(user);
//            }
//             
//        } catch(NandutiEJBException ex){
//             log.error("Ocurrio un error al momento de cambiar la contraseña.", ex);
//              MsgUtil.addMessageToast(ETypeAlert.ERROR, "Error al cambiar constraseña:" + ex.getMessage());
//            
//        }
//    }
//    
//    private boolean validForgotPassword() throws NandutiEJBException, Exception {
//        boolean ok = true; 
//        String  q =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("q");
//        String  u =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("u");
//        this.user = this.usuarioEJB.findByUser(u);
//        String passPhrase = user.getDiasvencpwd() + "|||" + user.getUltimocambiopwd(); // se utiliza para descifrar el key
//        String token = AESSymetricCrypto.decript(q, passPhrase);
//        String[] split = token.split("|");
//        
//        if(!AESSymetricCrypto.encriptInSHA512HEX2(split[1]).equals(user.getPassword())) /// compara el hash de las contraseñas
//        {
//            log.info("El hash de las contraseñas es diferente.");
//            ok=false;
//            return ok;
//        }
//        
//        
//        if(!split[0].equals(user.getUsername()))
//        {
//            log.warn("Los usuarios son diferentes.");
//            ok=false;
//            return ok;
//        }
//        
//        if(!split[2].equals(user.getDiasvencpwd()))
//        {
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
//        return ok;
//    }
//    
//    public void changeForgotPassword() throws IOException, Exception  {
//        try{
//            if (!this.validForgotPassword()) {
//                return;
//            }
//            String infoDispositivo = this.infoDispositivo();
//            log.info("Info Dispositivo:"+ infoDispositivo );
//            String passSHA = this.usuarioEJB.setPasswordSHA512Hex(this.newPassword);
//            this.user.setPassword(passSHA);
//            this.usuarioEJB.edit(this.user);   
//            Caja cajaByIdUser = this.cajaEJB.findByIdUser(user.getIdusuario());
//            this.sendEmailCambioPasswordResponsableRecaudador(user, cajaByIdUser);
//            this.sendEmailForgotPasswordJefeSAU(user, cajaByIdUser);
//           
//            
//             if(!user.getNrocel().equals("") && user.getNrocel() != null){
//                 this.sendSMSChangePassword(user);
//            }
//             
//            MsgUtil.addMessageToast(ETypeAlert.INFO, "Contraseña Cambiada con Exito");
//            //ec.redirect(this.nav.getLogin());
//            this.sendEmail(this.user); //Enviar Email Informe de cambio de contraseña
//        } catch(NandutiEJBException ex){
//             log.error("Ocurrio un error al momento de cambiar la contraseña.", ex);
//              MsgUtil.addMessageToast(ETypeAlert.ERROR, "Error al cambiar constraseña:" + ex.getMessage());
//            
//        }
//    }
//
//    public void sendEmailForgoPassword(Usuario user)
//    {
//        
//    }
//    
//    public void sendEmail(Usuario user)
//    {
//        try{
//            String correoresponsable = user.getCorreo();
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//            File input = new File(realPath);
//            Document doc = Jsoup.parse(input, "UTF-8");
//            String plantilla= doc.toString();
//            String msg =  plantilla.replace("{{texto}}", "<p>El usuario " + user.getUsername() + ", ha modificado la contraseña .</p><p>Te hemos enviado este correo electrónico para notificar esta acción.</p>")
//                    .replace("{{title-header}}", "")
//                    .replace("{{title1}}", "Alerta de seguridad- Ñanduti")
//                    .replace("{{year}}", this.templateEmail.getYear())
//                    + "<p>Favor no responder a este correo o SMS, es un envio automático.</p>";
//            log.info("Texto Email: {}", msg);
//            
//            if(correoresponsable != null){
//                this.emailService.send(3,null,
//                                 msg,
//                                 null,
//                                 "Alerta de seguridad- Ñanduti",
//                                 correoresponsable
//                                 );
//
//                log.info("Notificación enviada a Resposable del Recaudador usuario: {}", user.getUsername());
//            }else{
//                MsgUtil.addSweetAlert(ETypeAlert.WARNING, 
//                    "Envío de correo sin finalizar", 
//                    "El usuario no posee configurado el correo para realizar envios!!!");
//            }
//        }
//        catch(NandutiEJBException | IOException ex) {
//            log.error("No se pudo enviar email al responsable del recaudador.", ex);
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
//    private void sendEmailCambioPasswordResponsableRecaudador(Usuario user,
//                                                             Caja cajaDelUsuario)
//            throws NandutiEJBException, IOException, DCTAUtilException {
//        try{
//            String correoresponsable = cajaDelUsuario.getSucursal().getRecaudador().getCliente().getCorreoresponsable();
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//            File input = new File(realPath);
//            Document doc = Jsoup.parse(input, "UTF-8");
//            String plantilla= doc.toString();
//            String msg =  plantilla.replace("{{texto}}", "<p>El usuario " + user.getUsername() + ", ha modificado la contraseña de la caja: " + cajaDelUsuario.getDescripcion() +"\n" + infoDispositivo() +".</p><p>Te hemos enviado este correo electrónico para notificar esta acción.</p>")
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
//        catch(Exception ex) {
//            log.error("No se pudo enviar email al responsable del recaudador.", ex);
//        }
//    }
//    
//    @PostConstruct
//    public void init() {
//        try {
//             this.templateEmail = sm.instance().getEmailTemplateSetup();
//             this.gatewayGSMSetup = sm.instance().getGatewayGSMSetup();
//             this.messengerSms = new MessengerSms(this.gatewayGSMSetup.getUrlWSDL(),this.gatewayGSMSetup.getPort());
//        } catch (NandutiException ex) {
//            log.error("No se pudo obtener el bean de configuración del aplicativo.", ex);
//        }
//    }
//
//    
//     public void sendEmailForgotPasswordJefeSAU(Usuario user,
//                                                             Caja cajaDelUsuario)
//            throws NandutiEJBException, IOException, DCTAUtilException {
//       try {
//            String correoresponsable = this.templateEmail.getEmailJefeSAU();
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//            if(!correoresponsable.equals(user.getCorreo())){
//                File input = new File(realPath);
//                Document doc = Jsoup.parse(input, "UTF-8");
//                String plantilla= doc.toString();
//                String msg =  plantilla.replace("{{texto}}", "<p>El usuario " + user.getUsername() + ", ha modificado la contraseña de la caja: " + cajaDelUsuario.getDescripcion() +"\n" + infoDispositivo() +".</p><p>Te hemos enviado este correo electrónico para notificar esta acción.</p>")
//                        .replace("{{title-header}}", this.templateEmail.getRecoveryTitleHeader().replace("\"", ""))
//                        .replace("{{title1}}", "Alerta de seguridad- Ñanduti")
//                        .replace("{{year}}", this.templateEmail.getYear())
//                        + "<p>Favor no responder a este correo o SMS, es un envio automático.</p>";
//                log.info("Texto Email: {}", msg);
//                
//                this.emailService
//                                 .send(3,"",
//                                 msg,
//                                 null,
//                                 "Alerta de seguridad- Ñanduti",
//                                 correoresponsable
//                                 );
//                
//                log.info("Notificacion enviada a Resposable del Recaudador usuario: {}", user.getUsername());
//            } else {
//                log.info("El correo del responsable coincide con el del usuario. No se envia el correo.");
//}
//          }
//        catch(NandutiEJBException | IOException ex) {
//           log.error("No se pudo enviar email al responsable del recaudador:",ex);
//        }
//    }
//     
//     public void sendSMSChangePassword(Usuario user){
//        try {
//            String nro = user.getNrocel();
//            String nroConCodInternacional= "595" + nro.substring(1, nro.length());
//            String texto = "Cambio de Contraseña ("+user.getCantcambiopwd() +"):Documenta S.A. le informa que el usuario:\n" + user.getUsername() + "\n ha modificado la contraseña exitosamente.";
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
//   private String infoDispositivo() {
//        StringBuilder info = new StringBuilder("<Sin infoDevice>");
//       
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode actualObj = mapper.readTree(this.getDeviceInfoDoorway());
//            log.info("getDeviceInfoDoorway textValue: {} \n {}", actualObj.textValue(), actualObj.toString());
//            
//            info.append("<p> Hardware:</p>");
//            JsonNode deviceHardware = actualObj.get("deviceHardware");
//            JsonNode volume = deviceHardware.get("volume");
//            JsonNode hdSerial = deviceHardware.get("hdSerial");
//            JsonNode mbSerial = deviceHardware.get("mbSerial");
//            JsonNode macAddress = deviceHardware.get("macAddress");
//            info.append("<p>volume:").append(volume).append("</p>");
//            info.append("<p>hdSerial:").append(hdSerial).append("</p>");
//            info.append("<p>mbSerial:").append(mbSerial).append("</p>");
//            info.append("<p>macAddress:").append(macAddress).append("</p>");
//
//            JsonNode deviceSoftware = actualObj.get("deviceSoftware");
//            JsonNode jreVersion = deviceSoftware.get("deviceHardware");
//            JsonNode osName = deviceSoftware.get("osName");
//            JsonNode osTypeArch = deviceSoftware.get("osTypeArch");
//            JsonNode osVersion = deviceSoftware.get("osVersion");
//            JsonNode deviceName = deviceSoftware.get("deviceName");
//            JsonNode hostAddress = deviceSoftware.get("hostAddress");
//            JsonNode osUserName = deviceSoftware.get("osUserName");
//            JsonNode doorwayVersion = deviceSoftware.get("doorwayVersion");
//            
//            info.append("<p>Software:</p>");
//            info.append("<p>jreVersion:").append(jreVersion).append("</p>");
//            info.append("<p>osName:").append(osName).append("</p>");
//            info.append("<p>osTypeArch:").append(osTypeArch).append("</p>");
//            info.append("<p>osVersion:").append(osVersion).append("</p>");
//            info.append("<p>deviceName:").append(deviceName).append("</p>");
//            info.append("<p>hostAddress:").append(hostAddress).append("</p>");
//            info.append("<p>osUserName:").append(osUserName).append("</p>");
//            info.append("<p>doorwayVersion:").append(doorwayVersion).append("<p>");
//        } catch (IOException ex ) {
//            log.error("Error al armar infoDevice:", ex);
//        }catch (Exception ex){
//            log.error("Error al armar infoDevice:", ex);
//        }
//       
//        return info.toString();
//    }
//}