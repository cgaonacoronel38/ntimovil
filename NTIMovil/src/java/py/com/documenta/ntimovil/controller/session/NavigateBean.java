/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.controller.session;

import java.io.Serializable;
import java.util.Base64;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.bean.setup.AppSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.SetupManager;

/**
 *
 * @author tid
 */
//@javax.enterprise.context.ApplicationScoped()
@javax.inject.Named(value = "nav")
@javax.enterprise.context.SessionScoped
public class NavigateBean implements Serializable {

    private final Logger log = LoggerFactory.getLogger(NavigateBean.class);

    private String contextPath;

    private String realPath;

    private final boolean defaultFacesRedirect = false;

    private final static String FACES_REDIRECT_PARAM = "?faces-redirect=true";

    private final static String NAV_PATH = "%s/%s%s%s";

    private final static String NAV_PATH_RESTRICTED = "%s/restricted/%s%s%s";

    private final static String FULL_CONTEXT = "%S://%s:%s/%s";

    private String jsfSuffix;

    private String currentTitle;

    private AppSetup appSetup;

    @Inject
    private SetupManager sm;

    @Inject
    private NtiSession ntiSessionMB;

    @PostConstruct
    public void init() {
        try {
            //XXX el FacesContext no se encuentra cargado por esa razón llenamos de esta forma. 
            contextPath = "/ntimovil";

            realPath = sm.instance().getAppSetup().getReportLocation() + "NandutiWEB.war/nanduti";
            jsfSuffix = "." + sm.instance().getAppSetup().getJsfSuffix();
            appSetup = sm.instance().getAppSetup();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().getExternalContext().log("No se pudo leer el sufijo JSF para link, se utilizará NTI por defecto.", ex);
            jsfSuffix = ".nti";
        }
    }

    /**
     * Ruta real de la aplicación
     *
     * @return
     */
    public String getRealPath() {
        return realPath;
    }

    /**
     * Contexto de ejecución de la aplicación se utiliza para abrir URL.
     *
     * @return
     */
    public String getContextPath() {
        return contextPath;
    }

    /**
     * Contexto de ejecución mas la carpeta de archivos comprimidos de la
     * aplicación.
     *
     * @return
     */
    public String compressPath() {
        return contextPath + "/compress";
    }

    /**
     * Ruta de la aplicación para la zona segura.
     *
     * @return
     */
    public String getContextPathRestricted() {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "", "", "");
    }

    public String getHome() {
        return contextPath;
    }

    public String getCurrentTitle() {
        return currentTitle;
    }

    public String getIndex() {
        return getIndex(defaultFacesRedirect);
    }

    public String getIndex(boolean facesRedirect) {
        return String.format(NAV_PATH, contextPath, "restricted/indexUser", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getRegister() {
        return getRegister(defaultFacesRedirect);
    }

    public String getRegister(boolean facesRedirect) {
        return String.format(NAV_PATH, contextPath, "register/register", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getForgotPassword(String userName, String token) {
        return getForgotPassword(userName, token, defaultFacesRedirect);
    }

    public String getForgotPassword(String userName, String token, boolean facesRedirect) {
        String pathForgot = NAV_PATH + (facesRedirect ? "&" : "?") + "u=%s&q=%s";
        log.info(pathForgot);
        return String.format(pathForgot,
                contextPath,
                "recovery/forgotPassword",
                jsfSuffix,
                (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                userName,
                token);
    }

    public String getChangePasswordInit() {
        return getChangePasswordInit(defaultFacesRedirect);
    }

    public String getChangePasswordInit(boolean facesRedirect) {
        return String.format(NAV_PATH, contextPath, "recovery/ChangePasswordInit", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getLogout() {
        return getContextPath() + "/logout";
    }

    public String getLogin() {
        return getLogin(defaultFacesRedirect);
    }

    public String getLogin(boolean facesRedirect) {
        return String.format(NAV_PATH, contextPath, "auth/login", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getLoginError() {
        return getLoginError(defaultFacesRedirect);
    }

    public String getLoginError(boolean facesRedirect) {
        return String.format(NAV_PATH, contextPath, "auth/loginError", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getAuthorization() {
        return getAuthorization(defaultFacesRedirect);
    }

    public String getAuthorization(boolean facesRedirect) {
        return String.format(NAV_PATH, contextPath, "auth/authorization", ".jsp", (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getUserIndex() {
        return getUserIndex(defaultFacesRedirect);
    }

    public String getUserIndex(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "indexUser", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getChangePassword() {
        return getChangePassword(defaultFacesRedirect);
    }

    public String getChangePassword(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "recovery/ChangePassword", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    /**
     * Página donde se lista la grilla de servicios activos.
     *
     * @return
     */
    public String getServiceGrid() {
        return getServiceGrid(defaultFacesRedirect);
    }

    public String getServiceGrid(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "service/grid/grid", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    /**
     * Página donde se lista la grilla de servicios activos.
     *
     * @param conversationId Conversación en curso, cobranza activa.
     * @return
     */
    public String getSelectedBiller(int conversationId) {
        return getSelectedBiller(conversationId, defaultFacesRedirect);
    }

    public String getSelectedBiller(int conversationId, boolean facesRedirect) {
        String pathSelBiller = NAV_PATH_RESTRICTED + (facesRedirect ? "&" : "?") + "coid=%s";

        return String.format(pathSelBiller,
                contextPath,
                "service/grid/gridSelectedBiller",
                jsfSuffix,
                (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                conversationId);
    }

    /**
     * Página donde se lista la de referencias del servicio seleccionado.
     *
     * @param conversationId Conversación en curso, cobranza activa.
     * @return
     */
    public String getSelectedService(int conversationId) {
        return getSelectedService(conversationId, defaultFacesRedirect);
    }

    public String getSelectedService(int conversationId, boolean facesRedirect) {
        String pathSelServ = NAV_PATH_RESTRICTED + (facesRedirect ? "&" : "?") + "coid=%s";

        return String.format(pathSelServ,
                contextPath,
                "service/grid/gridSelectedService",
                jsfSuffix,
                (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                conversationId);
    }

    /**
     * Página donde se lista la de referencias del servicio seleccionado.
     *
     * @param conversationId Conversación en curso, cobranza activa.
     * @return
     */
    public String getBarCode(int conversationId) {
        return getBarCode(conversationId, defaultFacesRedirect);
    }

    public String getBarCode(int conversationId, boolean facesRedirect) {
        String pathSelServ = NAV_PATH_RESTRICTED + (facesRedirect ? "&" : "?") + "coid=%s";

        return String.format(pathSelServ,
                contextPath,
                "service/grid/gridBarcode",
                jsfSuffix,
                (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                conversationId);
    }

    /**
     * Página de consulta, una vez que se haya seleccionado un facturador y un
     * servicio a pagar.
     *
     * @param conversationId Conversación en curso, cobranza activa.
     * @return
     */
    public String getConsult(int conversationId) {
        return getConsult(conversationId, defaultFacesRedirect);
    }

    public String getConsult(int conversationId, boolean facesRedirect) {
        String pathConsult = NAV_PATH_RESTRICTED + (facesRedirect ? "&" : "?") + "coid=%s";

        return String.format(pathConsult,
                contextPath,
                "service/conandpay/consult",
                jsfSuffix,
                (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                conversationId);
    }

    public String getPayment(int conversationId) {
        return getPayment(conversationId, defaultFacesRedirect);
    }

    public String getPayment(int conversationId, boolean facesRedirect) {
        String pathPay = NAV_PATH_RESTRICTED + (facesRedirect ? "&" : "?") + "coid=%s";

        return String.format(pathPay,
                contextPath,
                "service/conandpay/payment",
                jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                conversationId);
    }

    public String getServiceTabFilter() {
        return getServiceTabFilter(defaultFacesRedirect);
    }

    public String getServiceTabFilter(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "grid/serviceTabFiltro", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getPerson() {
        return getPerson(defaultFacesRedirect);
    }

    public String getPerson(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "person/person", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getCustomerManagement() {
        return getCustomerManagement(defaultFacesRedirect);
    }

    public String getCustomerManagement(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "client/clientForm", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getPersonManagement() {
        return getPersonManagement(defaultFacesRedirect);
    }

    public String getPersonManagement(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "person/person", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getServiceManagement() {
        return getServiceManagement(defaultFacesRedirect);
    }

    public String getServiceRedOperacion(boolean facesRedirect) {
        return String.format(NAV_PATH, contextPath, "/restricted/red/red", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getLockService() {
        return getLockService(defaultFacesRedirect);
    }

    public String getLockService(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "locksandunlocks/lockService", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getServiceManagement(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "service/service", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getConfigServiceManagement() {
        return getConfigServiceManagement(defaultFacesRedirect);
    }

    public String getConfigServiceManagement(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "service/servicefindconfig", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getTemplateService() {
        return getTemplateService(defaultFacesRedirect);
    }

    public String getTemplateService(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "service/template/serviceTemplate", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getBranchManagement() {
        return getBranchManagement(defaultFacesRedirect);
    }

    public String getBranchManagement(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "collector/collectorBranch", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getManagementNti() {
        return getManagementNti(defaultFacesRedirect);
    }

    public String getManagementNti(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "managementNti", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getCollector() {
        return getCollector(defaultFacesRedirect);
    }

    public String getCollector(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "collector/collector", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getInstaChat() {
        return getInstaChatIframe(defaultFacesRedirect);
    }

    public String getInstaChatIframe(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "instaFeed/chat", ".html", (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getCollectorDetaill() {
        return getCollectorDetaill(defaultFacesRedirect);
    }

    public String getCollectorDetaill(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "collector/collectorDetaill", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getReport() {
        return getReport(defaultFacesRedirect);
    }

    public String getReport(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "report/report", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getPaguitos() {
        return getPaguitos(defaultFacesRedirect);
    }

    public String getPaguitos(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "service/servicepaguitos", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getClubCajeros() {
        return getClubCajeros(defaultFacesRedirect);
    }

    public String getClubCajeros(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "smartloyalty/clubcajeros", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getFactudadorReport() {
        return getFactudadorReport(defaultFacesRedirect);
    }

    public String getFactudadorReport(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "report/facturador", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getStats() {
        return getStats(defaultFacesRedirect);
    }

    public String getStats(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "report/stats", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getPayHistory(int conversationId) {
        return getPayHistory(conversationId, defaultFacesRedirect);
    }

    public String getPayHistory(int conversationId, boolean facesRedirect) {
        //String.format(NAV_PATH_RESTRICTED, contextPath, "report/payHistory", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));

        String pathPayHistory = NAV_PATH_RESTRICTED + (facesRedirect ? "&" : "?") + "coid=%s";

        return String.format(pathPayHistory,
                contextPath,
                "report/payHistoryDetail",
                jsfSuffix,
                (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                conversationId);

    }

    public String getPayHistoryDetail() {
        return getPayHistoryDetail(defaultFacesRedirect);
    }

    public String getPayHistoryDetail(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "report/payHistoryDetail", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getRedOperacion() {
        return getRedOperacion(defaultFacesRedirect);
    }

    public String getRedOperacion(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "red/red", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getHomePage() {
        return getHomePage(defaultFacesRedirect);
    }

    public String getHomePage(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "home", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getServiceOperation() {
        return getServiceOperation(defaultFacesRedirect);
    }

    public String getServiceOperation(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "service/serviceoperationconfig", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getManagement() {
        return getManagement(defaultFacesRedirect);
    }

    public String getManagement(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "management/management", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getCanal() {
        return getCanal(defaultFacesRedirect);
    }

    public String getCanal(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "news/canal/canal", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getReportClubCajero() {
        return getReportClubCajero(defaultFacesRedirect);
    }

    public String getReportClubCajero(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "report/reportClubCajero", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getNoticia() {
        return getNoticia(defaultFacesRedirect);
    }

    public String getNoticia(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "news/noticia/noticia", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getCredencial() {
        return getCredencial(defaultFacesRedirect);
    }

    public String getCredencial(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "user/credentials", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getUser() {
        return getUser(defaultFacesRedirect);
    }

    public String getUser(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "user/userForm", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String gerRegisterClubCajero() {
        return gerRegisterClubCajero(defaultFacesRedirect);
    }

    public String gerRegisterClubCajero(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "smartloyalty/registerClubCajero", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getRol() {
        return getRol(defaultFacesRedirect);
    }

    public String getRol(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "rol/rol", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getRolPrograma() {
        return getRolPrograma(defaultFacesRedirect);
    }

    public String getRolPrograma(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "rol/rolprograma", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getRolProgramaObjeto() {
        return getRolProgramaObjeto(defaultFacesRedirect);
    }

    public String getRolProgramaObjeto(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "rol/rolprogramaobjeto", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getUbicaciones() {
        return getUbicaciones(defaultFacesRedirect);
    }

    public String getUbicaciones(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "data/location", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getUbicacionesDetalle() {
        return getUbicacionesDetalle(defaultFacesRedirect);
    }

    public String getUbicacionesDetalle(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "data/locationDetail", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getUbicacionesDetalle(int conversationId) {
        return getUbicacionesDetalle(conversationId, defaultFacesRedirect);
    }

    public String getUbicacionesDetalle(int conversationId, boolean facesRedirect) {
        String pathSelBrach = NAV_PATH_RESTRICTED + (facesRedirect ? "&" : "?") + "coid=%s";

        return String.format(pathSelBrach,
                contextPath,
                "data/locationDetail",
                jsfSuffix,
                (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                conversationId);
    }

    public String getUbicacionesEdit(int conversationId) {
        return getUbicacionesEdit(conversationId, defaultFacesRedirect);
    }

    public String getUbicacionesEdit(int conversationId, boolean facesRedirect) {
        String pathSelBrach = NAV_PATH_RESTRICTED + (facesRedirect ? "&" : "?") + "coid=%s";

        return String.format(pathSelBrach,
                contextPath,
                "data/locationEdit",
                jsfSuffix,
                (facesRedirect ? FACES_REDIRECT_PARAM : ""),
                conversationId);
    }

    public String getHorarios() {
        return getHorarios(defaultFacesRedirect);
    }

    public String getHorarios(boolean facesRedirect) {
        return String.format(NAV_PATH_RESTRICTED, contextPath, "data/horary", jsfSuffix, (facesRedirect ? FACES_REDIRECT_PARAM : ""));
    }

    public String getDispatchTicket(String trx, boolean reprint, boolean redirectGrid, boolean createBackup, String strTicket) {
        String url = "%sdispatchTicket?trx=%s&reprint=%s&redirectGrid=%s&createBkp=%s&ticket=%s";

        try {
            // encode data on your side using BASE64
            String encodedTicket = Base64.getEncoder().encodeToString(strTicket.getBytes());

            return String.format(url,
                    getContextPathRestricted(),
                    trx,
                    reprint ? "yes" : "no",
                    redirectGrid ? "yes" : "no",
                    createBackup ? "yes" : "no",
                    encodedTicket);
        } catch (Exception ex) {
            log.error("No se pudo crear la URL p/despachar el ticket.", ex);
        }

        return "";
    }

    public String getDownloadFile(String fullFileName, boolean redirectGrid) {
        String url = "%sdownloadFile?fileName=%s&redirectGrid=%s";

        try {
            return String.format(url,
                    getContextPathRestricted(),
                    fullFileName,
                    redirectGrid ? "yes" : "no");
        } catch (Exception ex) {
            log.error("No se pudo crear la URL p/descargar archivos.", ex);
        }

        return "";
    }
}
