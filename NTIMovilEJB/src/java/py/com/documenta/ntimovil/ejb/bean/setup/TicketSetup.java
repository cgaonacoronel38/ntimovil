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
public class TicketSetup {
    private String folderBackup;
    private String urlPrintService;
    private Integer maxLimit;


    /**
     * Carpeta donde se crean los ticket como backup.
     * 
     * @return 
     */
    public String getFolderBackup() {
        return folderBackup;
    }

    public void setFolderBackup(String folderBackup) {
        this.folderBackup = folderBackup;
    }

    /**
     * Servicio local p/impresión a través de Doorway/PrintDaemon.
     * 
     * Tiene una cadena que debe ser reemplazada.
     * 1 - Cadena que corresponde al ticket en una sola línea, los caracteres especiales están en el EJB.
     * 
     * Ejemplo de cadena:
     * http://localhost:12345/?printer=%s
     * 
     * %s = corresponde al ticket creado por NTI.
     * 
     * @return 
     */
    public String getUrlPrintService() {
        return urlPrintService;
    }

    public void setUrlPrintService(String urlPrintService) {
        this.urlPrintService = urlPrintService;
    }
    
    /**
    * Cantidad maxima de caracteres que el ticket puede 
    * imprimir por pagina.
    * Está relacionado con el soporte de caracters por parte del navegador
    * en el campo "dirección/url".
    * 
    * @return  maxLimit Integer
    */

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }
    
    
}
