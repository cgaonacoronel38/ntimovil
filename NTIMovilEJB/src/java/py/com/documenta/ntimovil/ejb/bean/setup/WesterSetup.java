/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author Mathias Gonzalez
 */
public class WesterSetup {
    private Integer idWesterService;
    private String url;
    private String userGeneric;
    private String passwordGeneric;

    public Integer getIdWesterService() {
        return idWesterService;
    }

    public void setIdWesterService(Integer idWesterService) {
        this.idWesterService = idWesterService;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserGeneric() {
        return userGeneric;
    }

    public void setUserGeneric(String userGeneric) {
        this.userGeneric = userGeneric;
    }

    public String getPasswordGeneric() {
        return passwordGeneric;
    }

    public void setPasswordGeneric(String passwordGeneric) {
        this.passwordGeneric = passwordGeneric;
    }
    
}
