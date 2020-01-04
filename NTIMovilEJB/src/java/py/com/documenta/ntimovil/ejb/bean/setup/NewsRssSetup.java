

package py.com.documenta.ntimovil.ejb.bean.setup;

/**
 *
 * @author francisco
 */
public class NewsRssSetup {
    private String urlReaderRss;
    private String timerRefresh;
    private String codePlatform;
    private String codeClient;
    private String onload;
    private String urlQueryIsUpdateChannels;
    private String urlXmlFile;
    
    public String getOnload() {
        return onload;
    }

    public void setOnload(String onload) {
        this.onload = onload;
    }

    public String getTimerRefresh() {
        return timerRefresh;
    }

    public void setTimerRefresh(String timerRefresh) {
        this.timerRefresh = timerRefresh;
    }

    public String getUrlReaderRss() {
        return urlReaderRss;
    }

    public void setUrlReaderRss(String urlReaderRss) {
        this.urlReaderRss = urlReaderRss;
    }

    public String getCodePlatform() {
        return codePlatform;
    }

    public void setCodePlatform(String codePlatform) {
        this.codePlatform = codePlatform;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getUrlQueryIsUpdateChannels() {
        return urlQueryIsUpdateChannels;
    }

    public void setUrlQueryIsUpdateChannels(String urlQueryIsUpdateChannels) {
        this.urlQueryIsUpdateChannels = urlQueryIsUpdateChannels;
    }

    public String getUrlXmlFile() {
        return urlXmlFile;
    }

    public void setUrlXmlFile(String urlXmlFile) {
        this.urlXmlFile = urlXmlFile;
    }
}