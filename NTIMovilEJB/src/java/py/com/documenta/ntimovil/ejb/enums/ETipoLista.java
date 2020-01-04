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
public enum ETipoLista {
    FAVORITE(1),
    STATISTIC(2);
    
    private final int codeBD;
    
    private ETipoLista(int codeBD) {
        this.codeBD = codeBD;
    }
    
    public int getCodeBD() {
        return codeBD;
    }
}