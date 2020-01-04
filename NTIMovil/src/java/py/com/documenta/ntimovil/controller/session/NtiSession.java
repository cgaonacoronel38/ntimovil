/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.controller.session;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;

/**
 * CDI + JSF 2.2 => se puede utilizar en cualquier otro componente.
 *
 * @author tid
 */
@javax.inject.Named(value = "ntiSession")
@javax.enterprise.context.SessionScoped
public class NtiSession implements Serializable {

    private final Logger log = LoggerFactory.getLogger(NtiSession.class);

    private Usuario user;

    private String previousURL;

    private String sessionId;

    private boolean report = false;

    private boolean boxControl = false;

    private boolean doorWay = false;

    private boolean doorWayControl;

    private Integer unidadDeSoftware = 1;

    private Integer empresa = 1;

    private boolean instaFeedView;

    //FIXME Modificar no se debe utilizar
    private BigDecimal idGestion = null;

    //FIXME Se deben setear en el logín
    private boolean redirectCaja = false;
    private boolean anotherUser = false;

    //private Profile defaultProfile;
    public void removeUserInHttpSession() {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (fc != null) {
            fc.getExternalContext().getSessionMap().remove("user");
        }

        log.info("Se removio el usuario del objeto HttpSession.");
    }

    public void addUserInHttpSession(Usuario su) {
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap()
                .put("user", su.getUsername());
        log.info("Se cargó el usuario en el objeto HttpSession.");
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public Integer getUnidadDeSoftware() {
        return unidadDeSoftware;
    }

    public void setUnidadDeSoftware(Integer unidadDeSoftware) {
        this.unidadDeSoftware = unidadDeSoftware;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public boolean isRedirectCaja() {
        return redirectCaja;
    }

    public void setRedirectCaja(boolean redirectCaja) {
        this.redirectCaja = redirectCaja;
    }

    public void setDoorWay(boolean doorWay) {
        this.doorWay = doorWay;
    }

    public boolean isDoorWayControl() {
        return doorWayControl;
    }

    public void setDoorWayControl(boolean doorWayControl) {
        this.doorWayControl = doorWayControl;
    }

    /**
     * Retorna TRUE si hay un usuario logueado en el sistema.
     *
     * @return
     */
    public boolean isSignIn() {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (fc == null) {
            log.warn("FacesContext nulo no se puede chequear la sesion.");

            return false;
        }

        return (fc.getExternalContext().getUserPrincipal() != null);
    }

    public boolean isSignIn(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("user") == null || session.getAttribute("user").toString().isEmpty()) {
                return false;
            } else {
                String user = session.getAttribute("user").toString();

                if (!user.trim().isEmpty()) {
                    log.info("Sessión válida, usuario logueado: {}, info desde el HttpSession", user);

                    return true;
                } else {
                    log.info("Sessión válida pero usuario vacío, info desde el HttpSession");

                    return false;
                }
            }
        } catch (Exception ex) {
            log.info("Error al verificar la sesión desde el HttpServer, mensaje: {}", ex.getMessage());

            return false;
        }
    }

    public boolean isRole(String role) {
        HttpServletRequest r = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return r.isUserInRole(role);
    }

    public String getPreviousURL() {
        return previousURL;
    }

    public void setPreviousURL(String previousURL) {
        this.previousURL = previousURL;
    }   

    /**
     * Id. de la apertura de caja desde la interfaz RedCobrosCaja (plataforma
     * actual). Este valor es necesario para consumir el método web del
     * RCDocumenta.
     *
     * @return id de la apertura de caja actual.
     *
     * Funciones deprecadas, se utiliza cajacierre
     */
    @Deprecated
    public BigDecimal getIdGestionDocumenta() {
        return idGestion;
    }

    @Deprecated
    public void setIdGestionDocumenta(BigDecimal idGestion) {
        this.idGestion = idGestion;
    }
    
    @Deprecated
    public BigDecimal getIdGestion() {
        return idGestion;
    }

    @Deprecated
    public void setIdGestion(BigDecimal idGestion) {
        this.idGestion = idGestion;
    }

    /**
     * Dispositivo utilizado actualmente por el usuario que pudiera ser una
     * caja.
     *
     * @return
     */
    /*public Dispositivo getDispositivo() {
    return dispositivo;
    }
    public void setDispositivo(Dispositivo dispositivo) {
    this.dispositivo = dispositivo;
    }*/

    public boolean isBoxControl() {
        return boxControl;
    }

    public void setBoxControl(boolean boxControl) {
        this.boxControl = boxControl;
    }

    public boolean isInstaFeedView() {
        return instaFeedView;
    }

    public void setInstaFeedView(boolean instaFeedView) {
        this.instaFeedView = instaFeedView;
    }

    public boolean isAnotherUser() {
        return anotherUser;
    }

    public void setAnotherUser(boolean anotherUser) {
        this.anotherUser = anotherUser;
    }
}
