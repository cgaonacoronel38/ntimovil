

package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author francisco
 */
public class GatewayGSMSetup {
    private String urlWSDL;
    private String port;

    public GatewayGSMSetup() {
    }    
    
    public String getUrlWSDL() {
        return urlWSDL;
    }

    public void setUrlWSDL(String urlWSDL) {
        this.urlWSDL = urlWSDL;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}