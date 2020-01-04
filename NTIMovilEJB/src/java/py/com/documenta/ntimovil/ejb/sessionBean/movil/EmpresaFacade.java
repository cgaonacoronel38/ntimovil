/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.ejb.model.movil.Empresa;

/**
 *
 * @author tokio
 */
@Stateless
public class EmpresaFacade extends AbstractFacadeMovil<Empresa> {
    private final Logger log = LoggerFactory.getLogger(EmpresaFacade.class);

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
}
