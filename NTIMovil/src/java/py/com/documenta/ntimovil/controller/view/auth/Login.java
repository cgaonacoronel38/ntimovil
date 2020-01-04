package py.com.documenta.ntimovil.controller.view.auth;

import py.com.documenta.ntimovil.controller.session.Config;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NavigateBean;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import com.documenta.util.encript.AESSymetricCrypto;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import javax.annotation.PostConstruct;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.bean.setup.EmailTemplateSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.GatewayGSMSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.PasswordPolicySetup;
import py.com.documenta.ntimovil.ejb.bean.setup.SetupManager;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoUsuario;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;

/**
 *
 * @author eduardo.caceres
 */
@javax.inject.Named(value = "loginMB")
@javax.enterprise.context.SessionScoped
public class Login implements Serializable {

    private final Logger log = LoggerFactory.getLogger(Login.class);

    @Inject
    private NavigateBean nav;

    @Inject
    private UsuarioFacade usuarioEJB;

    @Inject
    private NtiSession ntiSession;

    @Inject
    private NtiSession session;

    @Inject
    private Config config;

    @Inject
    private SetupManager sm;
    
    private EmailTemplateSetup templateEmail;

    private String username;
    private String password;
    private boolean isAutologueo;
    private Long idTerminal;
    private String lsRR; //clave Rol - Red en el LocalStorage
    private String email;
    private String hash = "false";
    private String nroCuenta;
    private String titular;
    private String token;
    private String nroCelular;
    private String nroDocumento;
    private String correo;
    private String fecVen;

    private PasswordPolicySetup pwdPolicySetup;
    private GatewayGSMSetup gatewayGSMSetup;
    private String deviceInfoDoorway;

    public String getDeviceInfoDoorway() {
        return deviceInfoDoorway;
    }

    public void setDeviceInfoDoorway(String deviceInfoDoorway) {
        this.deviceInfoDoorway = deviceInfoDoorway;
        log.info("deviceInfoDoorway" + this.deviceInfoDoorway);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsAutologueo() {
        return isAutologueo;
    }

    public void setIsAutologueo(boolean isAutologueo) {
        this.isAutologueo = isAutologueo;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(String nroCelular) {
        this.nroCelular = nroCelular;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecVen() {
        return fecVen;
    }

    public void setFecVen(String fecVen) {
        this.fecVen = fecVen;
    }

    public Long getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(Long idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getLsRR() {
        return lsRR;
    }

    public void setLsRR(String lsRR) {
        this.lsRR = lsRR;
    }

    private boolean valid() {
        boolean ok = true;

        if (username == null || username.isEmpty()) {
            log.error("El usuario es un valor requerido.");
            MsgUtil.addInfoMessage("El usuario es un valor requerido.");

            ok = false;
        }

        if (password == null || password.isEmpty()) {
            log.error("La contraseña es un valor requerido.");
            MsgUtil.addInfoMessage("La contraseña es un valor requerido.");

            ok = false;
        }

        return ok;
    }

    public void signIn() throws Exception {
        log.info("Intento de acceso: {}",username);
        Usuario su = null;

        if (!valid()) {
            return;
        }

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        HttpServletResponse res = (HttpServletResponse) ec.getResponse();

        try {
            log.info("Contraseña encriptada: "+this.usuarioEJB.setPasswordSHA512Hex(this.password));
            su = usuarioEJB.findByUser(username);

            if (su == null) {
                log.info("Usuario no válido!...");
                MsgUtil.addWarningMessage("Usuario no válido.");
                return;
            } else {
                log.info("El usuario {} es válido",username);
            }

            log.info("El usuario {} está activo? {}", username, su.getActivo());
            if (su.getActivo()) {
                StringBuilder msg2 = new StringBuilder();
                try {
                    if (req.isRequestedSessionIdValid()) {
                        Principal p = req.getUserPrincipal();
                        log.warn("El request tiene un id sesión válido, idsession: {}, usuario: {}.", req.getSession().getId(), p != null ? p.getName() : "null");

                        if (p != null && !p.getName().trim().isEmpty()) {
                            req.logout();

                            log.warn("El usuario {} ya estaba logueado!, se realizó el logout primeramente.");
                        }
                    }

                    switch (su.getTipoestado()) {
                        case BLOCKED_BY_FAILED_ATTEMPTS:
                            Integer horaBloqueo = this.pwdPolicySetup.getBlockHours();
                            Date actual1 = new Date();
                            log.info("Fecha Hora Actual:" + actual1.toString());
                            Date blockUserDate1 = this.usuarioEJB.findByUser(username).getFechabloqueoacceso();
                            log.info("Fecha Hora de Bloqueo a " + username + ":" + blockUserDate1.toString());
                            Long diff = actual1.getTime() - blockUserDate1.getTime();

                            if (diff >= (horaBloqueo * 3600000)) {
                                if (su.getIntentosdeacceso() >= this.pwdPolicySetup.getPwdAttemptsAllowed()) {
                                    this.usuarioEJB.setUsuarioTipoEstado(username, ETipoEstadoUsuario.MANDATORY_CHANGE);
                                    ETipoEstadoUsuario tipoEstado = ETipoEstadoUsuario.MANDATORY_CHANGE;
                                } else {
                                    this.usuarioEJB.setUsuarioTipoEstado(username, ETipoEstadoUsuario.ACTIVE);
                                    ETipoEstadoUsuario tipoEstado = ETipoEstadoUsuario.ACTIVE;
                                    // debe continuar en el bloque ACTIVE
                                }

                            } else {
                                Date actual = new Date();
                                Date blockUserDate = this.usuarioEJB.findByUser(username).getFechabloqueoacceso();
                                msg2.append("Cuenta Bloqueada, quedan:").append(this.diferenciaEnHoraMinutos(actual.getTime(), blockUserDate.getTime()));

                                MsgUtil.addWarningMessage( msg2.toString());

                                return;
                            }
                        //break; 
                        case FORGOT_PASSWORD:
                        case BLOCKED_BY_PASSWORD_EXPIRED:
                            Usuario forgotPWUser = this.usuarioEJB.findByUser(username);
                            Date actualDate = new Date();
                            Date perdidaPwDate = forgotPWUser.getUltimocambiopwd();
                            Integer vencimiento = forgotPWUser.getDiasvencpwd();
                            int dias = (int) ((actualDate.getTime() - perdidaPwDate.getTime()) / 86400000);

                            if (dias >= vencimiento) {
                                log.info("Contraseña Temporal Expirada.");
                                MsgUtil.addWarningMessage( "Contraseña Temporal Expirada.");

                                return;
                            } else {
//                                        String passPhrase = forgotPWUser.getDiasvencpwd() + "||" + forgotPWUser.getUltimocambiopwd(); // se utiliza para descifrar el key
//                                        String key = forgotPWUser.getUsername() + "|" + this.password + "|" + forgotPWUser.getDiasvencpwd();
//                                        String token = AESSymetricCrypto.encript(key, passPhrase);
                                String passPhrase = su.getDiasvencpwd() + su.getTipoestado().name(); // se utiliza para descifrar el key
                                String key = su.getUsername() + "|" + this.password + "|" + su.getDiasvencpwd();
                                String token = AESSymetricCrypto.encript(key, passPhrase);
                                FacesContext.getCurrentInstance().getExternalContext().redirect(nav.getForgotPassword(forgotPWUser.getUsername(), token));
                            }
                            break;
                        case MANDATORY_CHANGE:
                        case NEW_REGISTERED:

                            String ps = this.usuarioEJB.setPasswordSHA512Hex(this.password);
                            if (ps.equals(su.getPassword())) {
                                String passPhrase = su.getTipoestado().name(); /// se utiliza para descifrar el key
                                while (passPhrase.length() < 16) {
                                    passPhrase = passPhrase + "1";
                                }
                                String key = su.getUsername() + "|" + su.getPassword() + "|" + su.getDiasvencpwd();
                                String token = AESSymetricCrypto.encript(key, passPhrase);
                                log.debug("token: " + token);
                                ec.redirect(nav.getChangePasswordInit() + "?u=" + su.getUsername() + "&q=" + token);
                                break;
                            } else {
                                req.login(username, password);
                            }
                        case ACTIVE:
                            AESSymetricCrypto.Encriptar("Password crypto: "+password);
                            Usuario userActive = this.usuarioEJB.findByUser(username);
                            Integer diasvencpwd = userActive.getDiasvencpwd();
                            if (diasvencpwd == 0) {
                                req.login(username, password);
                                this.usuarioEJB.setNumTryAccess(username, 0);
                            } else {
                                Date dateExpired = userActive.getUltimocambiopwd();

                                if (this.afterDays(dateExpired) > diasvencpwd) {
                                    log.info("Contraseña Expirada: user: {}, dias de vigencia: {}", userActive.getUsername(), diasvencpwd);
                                    userActive.setTipoestado(ETipoEstadoUsuario.BLOCKED_BY_PASSWORD_EXPIRED);
                                    this.usuarioEJB.edit(userActive);
                                    MsgUtil.addWarningMessage( "Contraseña Expirada. Solicite una nueva contraseña.");
                                    return;
                                }
                            }
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    log.error("Error en inicio de sesión (request.login()) para el usuario: {}", username, ex);
                    StringBuilder msg = new StringBuilder();
                    this.usuarioEJB.countTryAccess(username);// aumentar contador
                    Integer passIntentos = this.pwdPolicySetup.getPwdAttemptsAllowed();
                    if (this.usuarioEJB.getNumTryAccess(username) < passIntentos) {
                        Integer intentos = passIntentos - this.usuarioEJB.getNumTryAccess(username);
                        msg.append("Usuario o contraseña no válido!!.  Recuerde que solo tiene [ ");
                        msg.append(intentos).append(" ] intentos, ");
                        msg.append("de lo contrario la cuenta se bloquea por [");
                        msg.append(this.pwdPolicySetup.getBlockHours()).append("] horas.");
                    } else if (this.usuarioEJB.getNumTryAccess(username) == passIntentos) {
                        usuarioEJB.setUsuarioTipoEstado(username, ETipoEstadoUsuario.BLOCKED_BY_FAILED_ATTEMPTS);
                        msg.append("Cuenta Bloqueada");

                        Usuario blockUser = this.usuarioEJB.findByUser(username);
//                        if (!blockUser.getNrocel().equals("") && blockUser.getNrocel() != null) {
//                            this.sendSMSCuentaBloqueada(blockUser, cajaBlockuser);
//                        }

                    } else {
                        ETipoEstadoUsuario tipoEstado = this.usuarioEJB.findByUser(username).getTipoestado();

                        if (tipoEstado.equals(ETipoEstadoUsuario.BLOCKED_BY_FAILED_ATTEMPTS)) {
                            Integer horaBloqueo = this.pwdPolicySetup.getBlockHours();
                            Date actual1 = new Date();
                            log.info("Fecha Hora Actual:" + actual1.toString());
                            Date blockUserDate1 = this.usuarioEJB.findByUser(username).getFechabloqueoacceso();
                            log.info("Fecha Hora de Bloqueo a " + username + ":" + blockUserDate1.toString());
                            Long diff = actual1.getTime() - blockUserDate1.getTime();
                            if (diff >= (horaBloqueo * 3600000)) {
                                usuarioEJB.setUsuarioTipoEstado(username, ETipoEstadoUsuario.ACTIVE);
                                tipoEstado = ETipoEstadoUsuario.ACTIVE;
                                msg.append("Cuenta Desbloqueada");
                            } else {
                                msg.append("Cuenta Bloqueada");
                            }
                        }
                    }

                    MsgUtil.addWarningMessage( msg.toString());

                    return;
                }
            } else {
                log.info("El usuario está bloqueado, username: {}, activo: {}", username, su.getActivo());

                MsgUtil.addWarningMessage( "Usuario bloqueado.");
            }
            
            
            if (req.getUserPrincipal() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("\n\t\t >> admin: ").append(req.isUserInRole("admin"));
                sb.append("\n\t\t >> console: ").append(req.isUserInRole("console"));
                sb.append("\n\t\t >> rec-admin: ").append(req.isUserInRole("rec-admin"));
                sb.append("\n\t\t >> rec-super: ").append(req.isUserInRole("rec-super"));
                sb.append("\n\t\t >> rec-cashier: ").append(req.isUserInRole("rec-cashier"));
                sb.append("\n\t\t >> registro-cashier: ").append(req.isUserInRole("registro-cashier"));
                sb.append("\n\t\t >> rec-report: ").append(req.isUserInRole("rec-report"));
                sb.append("\n\t\t >> fac-report: ").append(req.isUserInRole("fac-report"));
                sb.append("\n\t\t >> sau-super: ").append(req.isUserInRole("sau-super"));
                sb.append("\n\t\t >> sau-expert: ").append(req.isUserInRole("sau-expert"));
                sb.append("\n\t\t >> sau-cashier-test: ").append(req.isUserInRole("sau-cashier-test"));

                log.info("Usuario autenticado correctamente! \n\t -> Username: {} \n\t -> Tiempo de inactividad: {} seg. \n\t -> Roles asignados: {}",
                        username,
                        req.getSession().getMaxInactiveInterval(),
                        sb.toString());
                
                //controla que los usuarios que son utilizados para las home banking
                //no ingresen por el logueo normal.
//                if(req.isUserInRole("console")){
//                    log.info("El usuario posee el rol CONSOLE por esa razón se redirecciona al login nuevamente");
//                    ec.redirect(nav.getLogin());
//                }
            }
            
            //Para registrar los logueos
            usuarioEJB.updLastAccess(su);
            usuarioEJB.detached(su);
            ntiSession.setUser(su);

            //Para utilizarlo desde el WebFilter
            ntiSession.addUserInHttpSession(su);
            ntiSession.setSessionId(((HttpSession) ec.getSession(false)).getId());

            log.info("Redireccionando a {}, usuario: {}, advaSessionID: {}, maxInactiveInterval: {}",
                    nav.getIndex(),
                    su.getUsername(),
                    ntiSession.getSessionId(),
                    req.getSession().getMaxInactiveInterval());

            //verifica si doorway esta arriba al loguearse
//            if (hash != null && hash.equals("true")) {
//                ntiSession.setDoorWay(true);
//            }

             ec.redirect(nav.getIndex());

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error en autenticación de usuario: {}", username, ex);

            try {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (Exception ex1) {
                ex1.printStackTrace();
                log.error("No se pudo crear el error 500 desde el login.", ex1);
            }
        }
    }

    public void signOff() {
        HttpServletRequest request = null;
        String userName = null;

        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            StringBuilder sb = new StringBuilder();
            sb.append("Información del LOGOUT:\n");
            Principal p = request.getUserPrincipal();
            if (p != null) {
                userName = request.getUserPrincipal().getName();
                sb.append(String.format(" -1 Usuario principal: %s\n", userName));
                sb.append(String.format("\t->Tiene el rol administrador: %s\n", request.isUserInRole("ADMIN")));
                sb.append(String.format("\t->Tiene el rol supervisor: %s\n", request.isUserInRole("SUPER")));
                sb.append(String.format("\t->Tiene el rol usuario común: %s\n\n", request.isUserInRole("USER")));
            } else {
                sb.append("->No hay usuario logueado.\n");
            }

            sb.append(" -2 Información de conexión/sesión:\n");
            HttpSession hs = request.getSession();
            if (hs != null) {
                sb.append(String.format("\t-> Id. de sessión: %s\n", request.getSession().getId()));
                sb.append(String.format("\t-> Usuario remoto: %s\n", request.getRemoteUser()));
                sb.append(String.format("\t-> Tipo autenticación: %s\n", request.getAuthType()));
                sb.append(String.format("\t-> Puerto remoto: %s\n", request.getRemotePort()));
                sb.append(String.format("\t-> Dirección remota: %s\n", request.getRemoteAddr()));
                sb.append(String.format("\t-> Host remoto: %s\n", request.getRemoteHost()));

                // Se invalida la actual session HTTP.
                // Se llama internamente al método de JAAS LoginModule logout()
                ntiSession.setUser(null);
                ntiSession.removeUserInHttpSession();
                request.getSession().invalidate();

                MsgUtil.addInfoMessage(
                        "Su sesión (" + userName + ") ha finalizada!");

                log.info(sb.toString());
            } else {
                sb.append("No se pudo obtener información de sesión, no se puede invalidar.");
            }

            // Se re-direcciona a la página de inicio, AREA-FREE 
            log.info("El usuario ha sido INVALIDADO, redireccionando al home: {}", nav.getHome());

            FacesContext.getCurrentInstance().getExternalContext().redirect(nav.getHome());
        } catch (Exception ex) {
            log.error("Error al realizar el logout del usuario: {}", username, ex);

            MsgUtil.addErrorMessage("No se pudo realziar el cierre de sesión, consulte con Soporte.");
        }
    }

    @PostConstruct
    public void init() {
        try {
//            this.templateEmail = sm.instance().getEmailTemplateSetup();
            this.pwdPolicySetup = sm.instance().getPwdPolicySetup();
//            this.gatewayGSMSetup = sm.instance().getGatewayGSMSetup();
//            this.messengerSms = new MessengerSms(this.gatewayGSMSetup.getUrlWSDL(), this.gatewayGSMSetup.getPort());
        } catch (Exception ex) {
            log.error("No se pudo obtener el bean de configuración del aplicativo.", ex);
        }
    }

    private String diferenciaEnHoraMinutos(Long h1, Long h2) {
        String result = "";
        Integer bloqueoHoras = this.pwdPolicySetup.getBlockHours();
        h2 = h2 + (bloqueoHoras * 3600000);
        Long diff = h2 - h1;

        Float hora = (float) diff / 3600000;
        Integer parteHora = hora.intValue();
        Integer parteMinutos = (int) ((hora - parteHora) * 60.00);
        result = parteHora + " horas y " + parteMinutos + " minutos.";
        return result;
    }

    private Integer afterDays(Date d2) {/// devuelve la cantidad de dias transcurridos desde la fecha.
        Date actualDate = new Date();
        int dias = (int) ((actualDate.getTime() - d2.getTime()) / 86400000);
        return dias;
    }

    public void sendSMSCuentaBloqueada(Usuario user) {
        try {
//            String nro = user.getNrocel();
//            String nroConCodInternacional = "595" + nro.substring(1, nro.length());
//            String texto = "Bloqueo Temporal (" + user.getCantcambiopwd() + "):Documenta S.A. le informa que el usuario " + user.getUsername() + " ha sido bloqueado temporalmente debido a varios intentos de acceso fallidos.";
//            Sms_Type sms = new Sms_Type();
//            sms.setNumeroDestino(nroConCodInternacional);
//            sms.setTexto(texto);
//            sms.setIdTransaccion("0");
//            List listMsj = new ArrayList();
//            listMsj.add(sms);
//            this.messengerSms.enviarMensajes(listMsj);
            log.info("Notificación enviada al usuario: {}", user.getUsername());
        } catch (Exception ex) {
            log.error("No se pudo enviar email al usaurio:", ex);
        }
    }
}
