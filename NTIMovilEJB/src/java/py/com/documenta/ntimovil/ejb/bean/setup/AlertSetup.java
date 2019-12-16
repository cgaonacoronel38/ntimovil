/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author ulises.valdez
 */
public class AlertSetup {
    private boolean sent;
    private String email;
    private boolean control;
    private String templatePath;

    public boolean isControl() {
        return control;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSentEmail(boolean sent) {
        this.sent = sent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}
