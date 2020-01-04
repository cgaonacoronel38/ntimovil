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
public enum ETipoEstadoServicio implements Encodeable {
    ACTIVE("ACTVO"), 
    ADMINISTRATIVE_BLOCKING("BLQAD"), 
    TEMPORARY_SUSPENSION("SUTRL"),
    DEFINITIVE_BLOCKING("BADFA"),
    OUT_OF_LINE("FULNA");
    
    final String codeBD;

    private ETipoEstadoServicio(String codeBD) {
        this.codeBD = codeBD;
    }

    @Override
    public String getCodeBD() {
        return this.codeBD;
    }
}