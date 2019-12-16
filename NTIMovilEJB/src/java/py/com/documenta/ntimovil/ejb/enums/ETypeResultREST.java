/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.enums;

/**
 *
 * @author tid
 */
public enum ETypeResultREST {
    OK(0),
    ERR(-1);
    
    private final int typeResult;
    
    private ETypeResultREST(int typeResult) {
        this.typeResult = typeResult;
    }
    
    public int getResult() {
        return typeResult;
    }
}
