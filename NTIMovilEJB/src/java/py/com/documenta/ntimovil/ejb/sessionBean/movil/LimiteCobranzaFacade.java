/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import javax.ejb.Stateless;
import py.com.documenta.ntimovil.ejb.model.movil.Limitecobranza;

/**
 *
 * @author tid
 */
@Stateless
public class LimiteCobranzaFacade extends AbstractFacadeMovil<Limitecobranza> {
    public LimiteCobranzaFacade() {
        super(Limitecobranza.class);
    }
}
