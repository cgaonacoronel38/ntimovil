package py.com.documenta.ntimovil.controller.session;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.bean.setup.AppSetup;
import py.com.documenta.ntimovil.ejb.bean.setup.SetupManager;

@javax.inject.Named(value = "i18n")
@javax.enterprise.context.SessionScoped
public class I18nBean implements Serializable{
    private final Logger log = LoggerFactory.getLogger(I18nBean.class);
    
    @Inject
    private SetupManager sm;

    private AppSetup appSetup;
    
    
    @PostConstruct
    private void init() {
        try {
            this.appSetup = sm.instance().getAppSetup();
            log.info("Hola esto se ejecuto");
        } catch (Exception ex) {
            log.error("No se pudo obtener el bean de configuración principal de la aplicación.", ex);
        }  
    }

    public Locale getLocale() {
        return new Locale(appSetup.getLocaleLanguage(), appSetup.getLocaleCountry());
    }
    
    /**
     * Cambia el locale de JSF según la configuración en el archivo
     * de propiedades del aplicativo.
     * 
     * Para enviarle el Locale correcto a este método, se debe invocar
     * a getLocale() que toma la configuración del archivo de propiedades
     * del aplicativo.
     * 
     * @param l Locale a asignar al JSF.
     */
    public void changeLocale(Locale l){
	FacesContext.getCurrentInstance().getViewRoot().setLocale(l);
        
        log.info("Se cambió el LOCALE del JSF a \"{}_{}\"",
                 l.getLanguage(), l.getCountry());
    }
    
    public TimeZone getTimeZoneDefault() {
        return TimeZone.getTimeZone(appSetup.getTimeZoneId());
    }    
}