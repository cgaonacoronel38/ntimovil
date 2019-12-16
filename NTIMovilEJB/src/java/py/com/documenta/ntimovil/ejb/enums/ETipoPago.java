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
public enum ETipoPago implements Encodeable {
    CASH("EFE"),
    CHECK("CHEQ"),
    CREDIT_CARD("TARC"),
    DEBIT_CARD("TARD"),
    OTHERS("OTROS"),
    ACCOUNT("CUENT");
    
    private final String codeBD;
    
    private ETipoPago(String codeBD) {
        this.codeBD = codeBD;
    }
    
    public String getCodeBD() {
        return codeBD;
    }
    
    public ETipoPago valueOfByCodeBD(String codeBD) {
        if(codeBD.equalsIgnoreCase("efe")) {
            return ETipoPago.CASH;
        }
        
        if(codeBD.equalsIgnoreCase("cheq")) {
            return ETipoPago.CHECK;
        }
        
        if(codeBD.equalsIgnoreCase("tarc")) {
            return ETipoPago.CREDIT_CARD;
        }
     
        if(codeBD.equalsIgnoreCase("tard")) {
            return ETipoPago.DEBIT_CARD;
        }        
        
        if(codeBD.equalsIgnoreCase("others")) {
            return ETipoPago.OTHERS;
        }
        
        if(codeBD.equalsIgnoreCase("account")) {
            return ETipoPago.ACCOUNT;
        }
        
        throw new RuntimeException(String.format("El codebd [%s] no es válido.", codeBD));
    }
}