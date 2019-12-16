/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import py.com.documenta.ntimovil.ejb.model.movil.Rol;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;

/**
 *
 * @author tid
 */
@Stateless
public class RolFacade extends AbstractFacadeMovil<Rol> {

    public RolFacade() {
        super(Rol.class);
    }
    
    public List<Rol> listarRolesActivos() throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT r ");
            sb.append("  FROM Rol r ");
            sb.append(" WHERE r.activo = true ");
            sb.append(" and r.sistema = false ");

            Query q = getEntityManager().createQuery(sb.toString());
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");

            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al listar roles: ",ex);
        }
    }
}
