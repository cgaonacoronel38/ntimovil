/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author alcides.alarcon
 */
public class MiddlewareSetup {
    private String rcDocumentaUrl;
    private String packageBase;
    private String defaultControllerClass;
    private String defaultAdapterClass;
    
    public String getRcDocumentaUrl() {
        return rcDocumentaUrl;
    }

    public void setRcDocumentaUrl(String rcDocumentaUrl) {
        this.rcDocumentaUrl = rcDocumentaUrl;
    }

    public String getPackageBase() {
        return packageBase;
    }

    public void setPackageBase(String packageBase) {
        this.packageBase = packageBase;
    }

    public String getDefaultControllerClass() {
        return defaultControllerClass;
    }

    public void setDefaultControllerClass(String defaultControllerClass) {
        this.defaultControllerClass = defaultControllerClass;
    }

    public String getDefaultAdapterClass() {
        return defaultAdapterClass;
    }

    public void setDefaultAdapterClass(String defaultAdapterClass) {
        this.defaultAdapterClass = defaultAdapterClass;
    }
}