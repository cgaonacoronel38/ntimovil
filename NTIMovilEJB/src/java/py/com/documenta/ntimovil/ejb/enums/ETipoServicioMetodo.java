/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.enums;

/**
 * Relacionado con la tabla reddepago.TipoServicioMetodo
 * 
 * @see com.documenta.ejb.model.reddepago.Tiposerviciometodo
 * 
 * @author alcides.alarcon
 */
public enum ETipoServicioMetodo {
    QUERY("CNSUA"), //Consulta,
    PAYMENT("PAGO"), //Pago,
    CHECK_PAYMENT("VPAGO"), //VerificacionPago,
    REVERSE("REVSA"), //Reversa,
    CHECK_REVERSE("VREVS"), //VerificacionReversa,
    AUTO_REVERSE("REVAO"), //ReversaAutomatica,
    PING("PING");
    
    //private int id;
    private final String code;

    //private ETipoServicioMetodo(int id) {
    //    this.id = id;
    //}
    
    
    private ETipoServicioMetodo(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    //public int getId() {
    //    return id;
    //}
}
