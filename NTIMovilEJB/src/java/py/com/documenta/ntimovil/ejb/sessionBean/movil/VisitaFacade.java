/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import javax.ejb.Stateless;
import py.com.documenta.ntimovil.ejb.model.movil.Visita;

/**
 *
 * @author tokio
 */
@Stateless
public class VisitaFacade extends AbstractFacadeMovil<Visita> {

    public VisitaFacade() {
        super(Visita.class);
    }
}
