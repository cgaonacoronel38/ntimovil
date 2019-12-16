/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.enums;

/**
 *
 * @author alcides.alarcon
 */
public enum ETypeModuleNotif {
    CONSULT_AND_PAYMENT("COYPA"),
    
    // Ejemplos _aún on se utilizan
    // Estos valores tienen que estar asociados a la BD.
    CASHIER_REPORT("REPRE"), // NO se utiliza aún.
    CASHIER_BILLER("REPBI"); // No se utiliza aún.
    
    private final String codeBD;
    
    private ETypeModuleNotif(String codeBD) {
        this.codeBD = codeBD;
    }
    
    public String getCodeBD() {
        return this.codeBD;
    }
}