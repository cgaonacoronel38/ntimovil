package py.com.documenta.ntimovil.controller.view.auth;

//package com.documenta.nti.controller.view.auth;
//
//import com.documenta.gateway.MessengerSms;
//import com.documenta.gateway.sms.Sms_Type;
//import com.documenta.nti.permits.CheckPermission;
//import py.com.documenta.ntimovil.common.MsgUtil;
//import com.documenta.nti.controller.session.NavigateBean;
//import com.documenta.nti.controller.session.NtiSession;
//import com.documenta.nti.css.inliner.CssInliner;
//import com.documenta.nti.ejb.bean.setup.EmailTemplateSetup;
//import com.documenta.nti.ejb.bean.setup.GatewayGSMSetup;
//import com.documenta.nti.ejb.bean.setup.PasswordPolicySetup;
//import com.documenta.nti.ejb.exception.NandutiEJBException;
//import com.documenta.nti.ejb.exception.NandutiException;
//import com.documenta.nti.ejb.model.base.Usuario;
//import com.documenta.nti.ejb.model.reddepago.Caja;
//import com.documenta.nti.ejb.sessionbean.base.UsuarioFacade;
//import com.documenta.nti.ejb.sessionbean.reddepago.CajaFacade;
//import com.documenta.nti.ejb.sessionbean.reddepago.CajacierreFacade;
//import com.documenta.nti.ejb.sessionbean.services.SetupManager;
//import com.documenta.nti.enums.ETypeAlert;
//import com.documenta.nti.ejb.enums.ETipoEstadoUsuario;
//import com.documenta.nti.ejb.sessionbean.services.EmailService;
//import com.documenta.util.encript.AESSymetricCrypto;
//import com.documenta.util.exception.DCTAUtilException;
//import com.documenta.util.random.RandomCreator;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.File;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.inject.Inject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Map;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
///**
// *
// * @author eduardo.caceres
// */
//@ManagedBean(name = "forgotPassMB")
//@ViewScoped
//public class ForgotPassword implements Serializable {
//
//    private final Logger log = LoggerFactory.getLogger(ForgotPassword.class);
//    @Inject
//    private NtiSession ntiSession;
//    @Inject
//    private CheckPermission permiso;
//    @Inject
//    private CajacierreFacade cajaCierreEJB;
//    @Inject
//    private SetupManager sm;
//    @Inject
//    private UsuarioFacade usuarioFacade;
//    @Inject
//    private CajaFacade cajaFacade;    
//    private CssInliner cssInliner;
//    @Inject
//    private EmailService emailService;
//    @Inject
//    private NavigateBean nav;
//    private EmailTemplateSetup templateEmail;
//    private static final String DOCU_DOMAIN = "documenta";
//    private String username;
//    private String email;
//    private PasswordPolicySetup pwdPolicySetup;
//    private GatewayGSMSetup gatewayGSMSetup;
//    //private static Sms_Service smsService = null;
//
//    private String opcionSeleccionada;
//    private String telefono;
//    private Date expirationDateCode;
//    private RandomCreator randomCreator;
//    private String deviceInfoDoorway;
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
//    
//    public String getTelefono() {
//        return telefono;
//    }
//    
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public ForgotPassword() {
//        
//    }
//    
//    public String getOpcionSeleccionada() {
//        return opcionSeleccionada;
//    }
//    
//    public void setOpcionSeleccionada(String opcionSeleccionada) {
//        log.info("Recuperacion de cuenta via:" + opcionSeleccionada + " seleccionada.");
//        this.opcionSeleccionada = opcionSeleccionada;
//    }
//    
//    @PostConstruct
//    public void init() {
//        try {
//            Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//            String get = requestParams.get("u");
//            
//            this.pwdPolicySetup = sm.instance().getPwdPolicySetup();
//            this.cssInliner = new CssInliner(); 
//            this.templateEmail = sm.instance().getEmailTemplateSetup();
//            this.gatewayGSMSetup = sm.instance().getGatewayGSMSetup();
//            this.messengerSms = new MessengerSms(this.gatewayGSMSetup.getUrlWSDL(),this.gatewayGSMSetup.getPort());
//        } catch (NandutiException ex) {
//            log.error("Ocurrio un error con DCTAUtil8:", ex);
//        } 
//    }
//
//    public SetupManager getSm() {
//        return sm;
//    }
//
//    public void setSm(SetupManager sm) {
//        this.sm = sm;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//        
//    }
//
//    public String getEmail() {
//        return email;
//        
//    }
//
//    public void setEmail(String email) {
//        
//        if (this.opcionSeleccionada.equals("byTelephone")) {
//            log.info("Nro Cel:" + email);
//            this.setTelefono(email);
//        } else {
//        this.email = email;
//        log.info("Email:" + email);
//    }
//
//    }
//    
//    public PasswordPolicySetup getPwdPolicySetup() {
//        return pwdPolicySetup;
//    }
//
//    public void setPwdPolicySetup(PasswordPolicySetup pwdPolicySetup) {
//        this.pwdPolicySetup = pwdPolicySetup;
//    }
//    
//    public void sendEmailRecovery() {
//        
//        try {
//            List<Usuario> listUser;
//            switch (this.opcionSeleccionada) {
//                case "byEmail":
//                    listUser = this.usuarioFacade.getUserForEmail(this.email);
//                    break;
//                case "byTelephone":
//                    listUser = this.usuarioFacade.getUserForNroCel(this.telefono);
//                    break;
//                default:
//                    listUser = this.usuarioFacade.getUserForEmail(this.email); // para si se da null retorna size 0
//                    break;
//            }
//            
//            log.info("Users con ese email o telefono:" + listUser.size());
//            
//            String infoDispositivo = infoDispositivo();
//            log.info("infoDispositivo =" + infoDispositivo);
//            if (listUser.size() > 0) {
//                
//                    Usuario user = listUser.get(0);
//                if (!user.getTipoestado().equals(ETipoEstadoUsuario.BLOCKED_BY_FAILED_ATTEMPTS.toString())) {
//                        String newPassword = this.changePassword(user);
//                        //this.emailTemplate.sendEmailForgotPassword(user,newPassword);
//                    if (this.opcionSeleccionada.equals("byEmail") && !user.getCorreo().equals(null) && !user.getCorreo().equals("")) {
//                        
//                        sendEmailForgotPassword(user, newPassword);
//                        
//                    } else if (this.opcionSeleccionada.equals("byTelephone") && !user.getNrocel().equals(null) && !user.getNrocel().equals("")) {
//                        
//                        sendSMSForgotPassword(user, newPassword);
//                        
//                    } else {
//                        MsgUtil.addMessageToast(ETypeAlert.WARNING, "No posee correo o celular asociado.");
//                        
//                    }
//                    
//                        Caja caja = this.cajaFacade.findByIdUser(user.getIdusuario());
//                    log.info("Enviando Email a Jefe SAU");
//                    this.sendEmailForgotPasswordJefeSAU(user, caja);
//                    log.info("Enviando Email a Resposable del recaudador...");
//                    this.sendEmailForgotPasswordResponsableRecaudador(user, caja);
//                       
//                    
//                } else {
//                        MsgUtil.addMessageToast(ETypeAlert.WARNING, "Cuenta bloqueada temporalmente.");
//                    }
//            } else {
//                MsgUtil.addMessageToast(ETypeAlert.WARNING, "Es posible que el correo no exista, el celular no este asociado o el usuario ha sido dado de baja.");
//            }
//                
//        } catch (NandutiEJBException ex) {
//            log.error("Ocurrio Un error send mail Recovery o sms:", ex);
//           MsgUtil.addMessageToast(ETypeAlert.ERROR, "Ocurrio un error en el proceso.");
//        } catch (DCTAUtilException ex) {
//            log.error("Ocurrio Un error send mail Recovery:", ex);
//            MsgUtil.addMessageToast(ETypeAlert.ERROR, "Ocurrio un error en el proceso.");
//        } catch (Exception ex) {
//            log.error("Es posible que el usuario no este asociado a ninguno de los datos proporcionados:" + ex.getMessage());
//        }
//        
//    }
//        
//    public void sendSMSForgotPassword(Usuario user, String newPassword) {
//        try {            
//            String nro = user.getNrocel();
//            String nroConCodInternacional = "595" + nro.substring(1, nro.length());
//            String texto = "Cambio de Contraseña (" + user.getCantcambiopwd() + "):Documenta S.A. le informa que la contraseña temporal, para el usuario:\n" + user.getUsername() + " \nes: " + newPassword;
//            Sms_Type sms = new Sms_Type();
//            sms.setNumeroDestino(nroConCodInternacional);
//            sms.setTexto(texto);
//            sms.setIdTransaccion("0");
//            List listMsj = new ArrayList();
//            listMsj.add(sms);
//            this.messengerSms.enviarMensajes(listMsj);
//            log.info("Notificación enviada al usuario: {}", user.getUsername());
//        } catch (Exception ex) {
//            log.error("No se pudo enviar email al usaurio:", ex);
//    }
//    }
//    
//    private String changePassword(Usuario user) throws NandutiEJBException, Exception {   // Establece una contraseña temporal y Setea Tipo Estado ForgotPassword
//        String newPass = PasswordGenerator.getPassword();
//        String passSHA = this.usuarioFacade.setPasswordSHA512Hex(newPass);
//        user.setPassword(passSHA);
//        user.setTipoestado(ETipoEstadoUsuario.FORGOT_PASSWORD);
//        user.setDiasvencpwd(this.pwdPolicySetup.getDaysPasswordTemporaryExpiration());
//        Date fechaActual = new Date();
//        user.setUltimocambiopwd(fechaActual);
//        Integer cantcambiopwd = user.getCantcambiopwd();
//        cantcambiopwd++;
//        user.setCantcambiopwd(cantcambiopwd);
//        this.usuarioFacade.edit(user);
//        
//        return newPass;
//    }
//
//    public void sendEmailForgotPassword(Usuario user, String newPassword) throws IOException, DCTAUtilException, Exception {
//       try {
//            String mails = user.getCorreo();
//        
//            String passPhrase = user.getDiasvencpwd() + user.getTipoestado().name(); // se utiliza para descifrar el key
//            String key = user.getUsername() + "|" + newPassword + "|" + user.getDiasvencpwd(); 
//            String token = AESSymetricCrypto.encript(key, passPhrase);
//            log.debug("token: {}", token);
//            
//            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            String url = req.getRequestURL().toString();        
//            String uri = req.getRequestURI();
//            log.info("Token Generado:" + token);
//            log.info("URI:" + uri);
//            log.info("URL:" + url);
//            String navBeanForgotPassword = nav.getForgotPassword(user.getUsername(), token); //no se por que no funciona!!!!
//            //String navBeanForgotPassword = "?u=" + user.getUsername() + "&q=" + token;
//            log.info("navBeanForgotPassword:" + navBeanForgotPassword);
//            String urlRecovery = url.replace(uri, navBeanForgotPassword);
//            log.info("URL_Recovery: {}, enviando e-mail a: {}", urlRecovery, mails);
//        
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//        
//            File input = new File(realPath);
//            Document doc = Jsoup.parse(input, "UTF-8");
//        
//            String plantilla = doc.toString();
//        
//            String msg = plantilla.replace("{{texto}}", this.templateEmail.getRecoveryText().replace("{{link}}", urlRecovery).replace("{{user}}", user.getUsername()).replace("{{password_temporal}}", newPassword).replace("\"", ""))
//                .replace("{{title-header}}", this.templateEmail.getRecoveryTitleHeader().replace("\"", ""))
//                .replace("{{title1}}", this.templateEmail.getRecoveryTitle1().replace("\"", "").replace("N", "Ñ"))
//                .replace("{{year}}", this.templateEmail.getYear())
//                + "<p>Favor no responder a este correo o SMS, es un envio automático.</p>";
//            
//            if(mails != null){
//                this.emailService.send(3,
//                                  null,
//                                  msg,
//                                  null,
//                                  "Recovery Password-Ñanduti",
//                                  mails
//                                        );
//                //send(String msgPlain, String msgHtml, File file, String msgSubject, String recipients)
//                MsgUtil.addMessageToast(ETypeAlert.INFO,
//                                          "Correo electrónico enviado. Verifique su buzón de correo.");
//
//                log.info("Notificación enviada al usuario: {}", user.getUsername());
//            
//            }else{
//                MsgUtil.addSweetAlert(ETypeAlert.WARNING, 
//                    "Envío de correo sin finalizar", 
//                    "No posee configurado el correo para realizar envios!!!");
//            }
//            
//            
//        } catch (Exception ex) {
//            log.error("No se pudo enviar email al usaurio:", ex);
//        }
//    }  
//    
//    public void sendEmailForgotPasswordResponsableRecaudador(Usuario user,
//                                                             Caja cajaDelUsuario)
//            throws NandutiEJBException, IOException, DCTAUtilException {
//       try {
//            String correoresponsable = cajaDelUsuario.getSucursal().getRecaudador().getCliente().getCorreoresponsable();
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//            if (!correoresponsable.equals(user.getCorreo())) {
//                File input = new File(realPath);
//                Document doc = Jsoup.parse(input, "UTF-8");
//                String plantilla = doc.toString();
//                String msg = plantilla.replace("{{texto}}", "<p>Hemos detectado que el usuario " + user.getUsername() + ", ha solicitado cambio de contraseña via:" + this.viaChangePass() + "\n" + infoDispositivo() + ".</p><p>Te hemos enviado este correo electrónico para notificar esta acción.</p>")
//                        .replace("{{title-header}}", this.templateEmail.getRecoveryTitleHeader().replace("\"", ""))
//                        .replace("{{title1}}", "Alerta de seguridad- Ñanduti")
//                        .replace("{{year}}", this.templateEmail.getYear())
//                        + "<p>Favor no responder a este correo o SMS, es un envio automático.</p>";
//                log.info("Texto Email: {}", msg);
//
//                this.emailService
//                        .send(3,null,
//                                msg,
//                                null,
//                                "Alerta de seguridad- Ñanduti",
//                                correoresponsable
//                        );
//                
//                log.info("Notificacion enviada a Resposable del Recaudador usuario: {}", user.getUsername());
//            } else {
//                log.info("El correo del responsable coincide con el del usuario. No se envia el correo.");
//            }            
//        } catch (NandutiEJBException | IOException ex) {
//            log.error("No se pudo enviar email al responsable del recaudador:", ex);
//        }catch(Exception ex){
//            log.error("No se pudo enviar email al resposable del reacaudador, es posible que no este registrado:"+ ex.getMessage());
//        }
//    }
//    
//    public void sendEmailForgotPasswordJefeSAU(Usuario user,
//            Caja cajaDelUsuario)
//            throws NandutiEJBException, IOException, DCTAUtilException {
//        try {
//            String correoresponsable = this.templateEmail.getEmailJefeSAU();
//            String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/template_email/email_recovery.html");
//            if (!correoresponsable.equals(user.getCorreo())) {
//                File input = new File(realPath);
//                Document doc = Jsoup.parse(input, "UTF-8");
//                String plantilla = doc.toString();
//                String msg = plantilla.replace("{{texto}}", "<p>Hemos detectado que el usuario " + user.getUsername() + ",Caja:" + cajaDelUsuario.getDescripcion() + ", ha solicitado cambio de contraseña via:" + this.viaChangePass() + "\n" + infoDispositivo() + ".</p><p>Te hemos enviado este correo electrónico para notificar esta acción.</p>")
//                        .replace("{{title-header}}", this.templateEmail.getRecoveryTitleHeader().replace("\"", ""))
//                        .replace("{{title1}}", "Alerta de seguridad- Ñanduti")
//                        .replace("{{year}}", this.templateEmail.getYear())
//                        + "<p>Favor no responder a este correo o SMS, es un envio automático.</p>";
//                log.info("Texto Email: {}", msg);
//                
//                this.emailService
//                        .send(3,null,
//                                 msg,
//                                 null,
//                                 "Alerta de seguridad- Ñanduti",
//                                 correoresponsable
//                                 );
//                
//                log.info("Notificacion enviada a Resposable del Recaudador usuario: {}", user.getUsername());
//            } else {
//                log.info("El correo del responsable coincide con el del usuario. No se envia el correo.");
//            }  
//        } catch (NandutiEJBException | IOException ex) {
//            log.error("No se pudo enviar email al responsable del recaudador:", ex);
//          }
//        }
//    
////    private void enviarMensajes(List<Sms_Type> mensajes) {
////        try {
////            log.info("Enviado Mensaje por API SOAP.");
////            String paramURLGateway = this.gatewayGSMSetup.getUrl_WSDL();
////            String paramPuertoPractigiro = this.gatewayGSMSetup.getPuerto();
////            Sms wsManagerGateway = this.getWSManagerGateway(paramURLGateway, "http://ws.gateway.documenta.com/", "sms", 10, 115);
////            wsManagerGateway.enviarSMS(paramPuertoPractigiro, mensajes);
////        } catch (FileNotFoundException_Exception | InterruptedException_Exception | ParseException_Exception ex) {
////            log.error(ex.getMessage());
////    }
////}
////    
////    private Sms getWSManagerGateway(String url, String uri, String localPart, int connTo, int readTo) {
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
////        } catch (Exception ex) {
////            
////            log.error("Error en getWSManagerGateway:", ex.getMessage());
////            MsgUtil.addMessageToast(ETypeAlert.ERROR, "Ocurrio un error en el Servicio de envio de SMS.");
////            
////        }
////        
////        return pexSoap;
////        
////    }
////    
//    private String viaChangePass() {
//        String via = "";
//        if (this.opcionSeleccionada.equals("byTelephone")) {
//            via = "SMS";
//        } else if (this.opcionSeleccionada.equals("byEmail")) {
//            via = "Email";
//        }
//        return via;
//    }
//    
//    private String infoDispositivo() {
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
//}
