/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.controller.session;

import py.com.documenta.ntimovil.ejb.manager.ResourceManager;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.bean.setup.AppSetup;

/**
 *
 * @author eduardo.cacers
 */
@javax.inject.Named(value = "ntiRestartTimeMB")
@javax.enterprise.context.ApplicationScoped()
public class NtiRestartTime implements Serializable {

    private final Logger log = LoggerFactory.getLogger(NtiRestartTime.class);

    private Date restartDate;
    private String restarDateJS;

    private AppSetup appSetup;

    public NtiRestartTime() {
        try {
            appSetup = ResourceManager.getInstance().getAppSetup();
        } catch (Exception ex) {
            log.error("No se pudo obtener la configuración reinicio.", ex);
        }
    }

    public Date getRestartDate() {
        return restartDate;
    }

    public void setRestartDate(Date restartDate) {
        this.restartDate = restartDate;
    }

    public String getRestarDateJS() {
        return restarDateJS;
    }

    public void setRestarDateJS(String restarDateJS) {
        this.restarDateJS = restarDateJS;
    }

    public String initRestartDate() {

        Calendar fechaActual = Calendar.getInstance();
        fechaActual.add(Calendar.MINUTE, appSetup.getRestartTime());

        restartDate = fechaActual.getTime();
        restarDateJS = String.valueOf(fechaActual.getTimeInMillis());

        return restarDateJS;

    }

}
