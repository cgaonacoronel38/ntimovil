package py.com.documenta.ntimovil.ejb.bean.setup;

import java.util.List;


/**
 * Clase para obtener informaión sobre la configuración general de la aplicación
 *
 * @author alcides.alarcon
 */
public class AppSetup {

    private String appTitle;
    private String productName;
    private boolean master;
    private boolean enableHttps;
    private String urlIndex;
    private String domain;
    private String persistenceUnitNameMaster;
    private String persistenceUnitNameSlave;
    private int restartTime;
    private String passPhrase;
    private int loginFailedAttempts;
    private int loginHoursBloqued;
    private String jsfSuffix;
    private String defaultRealmGroupName;
    private int defaultBaseColor;
    private String defaultHtmlColor;
    private String localeLanguage;
    private String localeCountry;
    private String timeZoneId;
    private String rcDocumentaUrl;
    private Integer dataBase;
    private List<String> referer;
    private String urlPractigiroWS;
    private String reportLocation;
    private String urlCrmMigracion;
    private boolean homeAd;
    private String homeBankingURL;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public int getRestartTime() {
        return restartTime;
    }

    public void setRestartTime(int restartTime) {
        this.restartTime = restartTime;
    }

    public String getUrlIndex() {
        return urlIndex;
    }

    public void setUrlIndex(String urlIndex) {
        this.urlIndex = urlIndex;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPersistenceUnitNameMaster() {
        return persistenceUnitNameMaster;
    }

    public void setPersistenceUnitNameMaster(String persistenceUnitNameMaster) {
        this.persistenceUnitNameMaster = persistenceUnitNameMaster;
    }

    public String getPersistenceUnitNameSlave() {
        return persistenceUnitNameSlave;
    }

    public void setPersistenceUnitNameSlave(String persistenceUnitNameSlave) {
        this.persistenceUnitNameSlave = persistenceUnitNameSlave;
    }

    public String getPassPhrase() {
        return passPhrase;
    }

    public void setPassPhrase(String passPhrase) {
        this.passPhrase = passPhrase;
    }

    public int getLoginFailedAttempts() {
        return loginFailedAttempts;
    }

    public void setLoginFailedAttempts(int loginFailedAttempts) {
        this.loginFailedAttempts = loginFailedAttempts;
    }

    public int getLoginHoursBloqued() {
        return loginHoursBloqued;
    }

    public void setLoginHoursBloqued(int loginHoursBloqued) {
        this.loginHoursBloqued = loginHoursBloqued;
    }

    /**
     * El sufijo esta relacionado con la configuración en el web.xml para url
     * pattern que levanta el servlet de jsf.
     *
     * @return
     */
    public String getJsfSuffix() {
        return jsfSuffix;
    }

    public void setJsfSuffix(String jsfSuffix) {
        this.jsfSuffix = jsfSuffix;
    }

    public String getDefaultRealmGroupName() {
        return defaultRealmGroupName;
    }

    public void setDefaultRealmGroupName(String defaultRealmGroupName) {
        this.defaultRealmGroupName = defaultRealmGroupName;
    }

    public int getDefaultBaseColor() {
        return defaultBaseColor;
    }

    public void setDefaultBaseColor(int defaultBaseColor) {
        this.defaultBaseColor = defaultBaseColor;
    }

    public String getDefaultHtmlColor() {
        return defaultHtmlColor;
    }

    public void setDefaultHtmlColor(String defaultHtmlColor) {
        this.defaultHtmlColor = defaultHtmlColor;
    }

    public String getLocaleLanguage() {
        return localeLanguage;
    }

    public void setLocaleLanguage(String localeLanguage) {
        this.localeLanguage = localeLanguage;
    }

    public String getLocaleCountry() {
        return localeCountry;
    }

    public void setLocaleCountry(String localeCountry) {
        this.localeCountry = localeCountry;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getRcDocumentaUrl() {
        return rcDocumentaUrl;
    }

    public void setRcDocumentaUrl(String rcDocumentaUrl) {
        this.rcDocumentaUrl = rcDocumentaUrl;
    }

    public Integer getDataBase() {
        return dataBase;
    }

    public void setDataBase(Integer dataBase) {
        this.dataBase = dataBase;
    }

    public List<String> getReferer() {
        return referer;
    }

    public void setReferer(List<String> referer) {
        this.referer = referer;
    }

    public String getUrlPractigiroWS() {
        return urlPractigiroWS;
    }

    public void setUrlPractigiroWS(String urlPractigiroWS) {
        this.urlPractigiroWS = urlPractigiroWS;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public String getUrlCrmMigracion() {
        return urlCrmMigracion;
    }

    public void setUrlCrmMigracion(String urlCrmMigracion) {
        this.urlCrmMigracion = urlCrmMigracion;
    }

    public boolean isHomeAd() {
        return homeAd;
    }

    public void setHomeAd(boolean homeAd) {
        this.homeAd = homeAd;
    }

    public boolean isEnableHttps() {
        return enableHttps;
    }

    public void setEnableHttps(boolean enableHttps) {
        this.enableHttps = enableHttps;
    }

    public String getHomeBankingURL() {
        return homeBankingURL;
    }

    public void setHomeBankingURL(String homeBankingURL) {
        this.homeBankingURL = homeBankingURL;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n :: Configuración General ::\n");
        sb.append("- Https Habilitado: ").append(enableHttps).append("\n");
        sb.append("- Nombre del Producto: ").append(productName).append("\n");
        sb.append("- Título Oficial: ").append(appTitle).append("\n");
        sb.append("- URL Index: ").append(urlIndex).append("\n");
        sb.append("- Dominio: ").append(domain).append("\n");
        sb.append("- Unidad de persistencia M: ").append(persistenceUnitNameMaster).append("\n");
        sb.append("- Unidad de persistencia S: ").append(persistenceUnitNameSlave).append("\n");
        sb.append("- Key: ").append("******").append("\n");
        sb.append("- Intentos de logín: ").append(loginFailedAttempts).append("\n");
        sb.append("- Hs. de bloqueo por intentos de login fallidos: ").append(loginHoursBloqued).append("\n");
        sb.append("- URL sufijo para activar faces-servlet: ").append(jsfSuffix).append("\n");
        sb.append("- ROL por defecto (glassfish realm): ").append(defaultRealmGroupName).append("\n");
        sb.append("- Color por defecto: ").append(defaultBaseColor).append("\n");
        sb.append("- Color Html: ").append(defaultHtmlColor).append("\n");
        sb.append("- Locale Lenguaje: ").append(localeLanguage).append("\n");
        sb.append("- Locale País: ").append(localeCountry).append("\n");
        sb.append("- TimeZone por defecto: ").append(timeZoneId).append("\n");
        sb.append("- WS RCDocumenta URL: ").append(rcDocumentaUrl).append("\n");
        sb.append("- WS Practigiro URL: ").append(urlPractigiroWS).append("\n");
        sb.append("- Base de datos (1- PostgreSQL 2- Oracle): ").append(dataBase).append("\n");
        sb.append("- Referer: ").append(referer).append("\n");
        sb.append("- Ubicacion reporte: ").append(reportLocation).append("\n");
        sb.append("- Ws Crm Geene: ").append(urlCrmMigracion).append("\n");

        return sb.toString();
    }
}
