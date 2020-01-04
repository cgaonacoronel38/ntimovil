/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.enums;

/**
 *
 * @author alcides.alarcon
 * 
 * Para saber si es consulta/pago/ u otro tipo de evento que se necesite
 * 
 */
public enum ETypeActivity {
    UNKNOWN,
    TICKET,
    CONSULT,
    PAYMENT,
    PING,
    VERIF_PAYMENT,
    CREATE
}
