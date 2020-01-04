/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.enums;

import py.com.documenta.ntimovil.ejb.converters.enums.Encodeable;


/**
 *
 * @author alcides.alarcon
 */
public enum ETipoDestinoTicket implements Encodeable {
    PREVIEW_BTNPRINTED_AND_BTNCANCEL("VPION"),  //Vista Previa con Impresión
    PREVIEW_BTNDOWNLOAD_AND_BTNCANCEL("VPDGA"), //Vista Previa con Descarga (con FD)
    PREVIEW_BTNSENDEMAIL_AND_BTNCANCEL("VPEML"), //Vista Previa con Envío por Email (con FD)
    UNATTENDED_PRINT("IMDDA"), //Impresión Desatendida
    UNATTENDED_DOWNLOAD("DGADA"); //Descarga Desatendida (con FD)
    
    final String codeBD;

    private ETipoDestinoTicket(String codeBD) {
        this.codeBD = codeBD;
    }

    @Override
    public String getCodeBD() {
        return this.codeBD;
    }
}