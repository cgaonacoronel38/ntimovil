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
public class FolderSetup {

    private String URLServletPattern;
    private String mainResourceFolder;
    private String subFolderNetImage;
    private String subFolderBillerLogo;
    private String subFolderServiceLogo;
    private String subFolderServiceHelp;
    private String subFolderNotification;
    private String subFolderBranch;
    private String subFolderPractiClub;

    public String getURLServletPattern() {
        return URLServletPattern;
    }

    public void setURLServletPattern(String URLServletPattern) {
        this.URLServletPattern = URLServletPattern;
    }

    public String getMainResourceFolder() {
        return mainResourceFolder;
    }

    public void setMainResourceFolder(String mainResourceFolder) {
        this.mainResourceFolder = mainResourceFolder;
    }

    public String getSubFolderNetImage() {
        return subFolderNetImage;
    }

    public void setSubFolderNetLogo(String subFolderNetImage) {
        this.subFolderNetImage = subFolderNetImage;
    }

    public String getSubFolderBillerLogo() {
        return subFolderBillerLogo;
    }

    public void setSubFolderBillerLogo(String subFolderBillerLogo) {
        this.subFolderBillerLogo = subFolderBillerLogo;
    }

    public String getSubFolderServiceLogo() {
        return subFolderServiceLogo;
    }

    public void setSubFolderServiceLogo(String subFolderServiceLogo) {
        this.subFolderServiceLogo = subFolderServiceLogo;
    }

    public String getSubFolderServiceHelp() {
        return subFolderServiceHelp;
    }

    public void setSubFolderServiceHelp(String subFolderServiceHelp) {
        this.subFolderServiceHelp = subFolderServiceHelp;
    }

    public String getSubFolderNotification() {
        return subFolderNotification;
    }

    public void setSubFolderNotification(String subFolderNotification) {
        this.subFolderNotification = subFolderNotification;
    }

    public String getSubFolderBranch() {
        return subFolderBranch;
    }

    public void setSubFolderBranch(String subFolderBranch) {
        this.subFolderBranch = subFolderBranch;
    }

    public String getSubFolderPractiClub() {
        return subFolderPractiClub;
    }

    public void setSubFolderPractiClub(String subFolderPractiClub) {
        this.subFolderPractiClub = subFolderPractiClub;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n :: Configuración Carpetas :: \n");
        sb.append("- URL Servlet p/ruta de imagenes: ").append(URLServletPattern);
        sb.append("- Carpeta principal de images c&p: ").append(mainResourceFolder);
        sb.append("- Carpeta de web-imagen: ").append(subFolderNetImage);
        sb.append("- Carpeta de logo-facturador: ").append(subFolderBillerLogo);
        sb.append("- Carpeta de logo-servicio: ").append(subFolderServiceLogo);
        sb.append("- Carpeta de ayuda-servicio: ").append(subFolderServiceHelp);
        sb.append("- Carpeta de notificación: ").append(subFolderNotification);

        return sb.toString();
    }
}
