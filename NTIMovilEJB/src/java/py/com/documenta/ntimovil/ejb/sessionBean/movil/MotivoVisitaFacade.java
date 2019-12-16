/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import javax.ejb.Stateless;
import py.com.documenta.ntimovil.ejb.model.movil.Motivovisita;

/**
 *
 * @author tokio
 */
@Stateless
public class MotivoVisitaFacade extends AbstractFacadeMovil<Motivovisita> {

    public MotivoVisitaFacade() {
        super(Motivovisita.class);
    }
}
