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
public enum EHBTipoCuenta implements Encodeable {
    SAVINGS_BANK("AHORR"),
    CURRENT_ACCOUNT("CTCTE");
    
    private final String codeBD;
    
    private EHBTipoCuenta(String codeBD) {
        this.codeBD = codeBD;
    }
    
    public String getCodeBD() {
        return codeBD;
    }
    
    public static EHBTipoCuenta valueOfByCodeBD(String codeBD) {
        if(codeBD.equalsIgnoreCase("ahorr")) {
            return EHBTipoCuenta.SAVINGS_BANK;
        }
        
        if(codeBD.equalsIgnoreCase("ctcte")) {
            return EHBTipoCuenta.CURRENT_ACCOUNT;
        }
        
        throw new RuntimeException(String.format("El codebd [%s] no es válido.", codeBD));
    }
}