/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import py.com.documenta.ntimovil.ejb.model.movil.Permisomenu;

/**
 *
 * @author tid
 */
@Stateless
public class PermisoMenuFacade extends AbstractFacadeMovil<Permisomenu> {

    public PermisoMenuFacade() {
        super(Permisomenu.class);
    }

    public boolean isPermissionNATIVE(int idUsuario, String url) throws Exception {
        try {
            String sql = "select distinct m.activo\n"
                    + "from movil.usuario u\n"
                    + "join movil.usuariorol ur on u.idusuario = ur.idusuario\n"
                    + "                         and u.idempresa = ur.idempresa\n"
                    + "join movil.rol r on ur.idrol = r.idrol\n"
                    + "join movil.permisorol pr on r.idrol = pr.idrol\n"
                    + "join movil.menu m on pr.idmenu = m.idmenu\n"
                    + "where r.activo is true\n"
                    + "and u.idusuario = ?1\n"
                    + "and m.url ilike ?2";

            Query q = getEntityManager().createNativeQuery(sql);
            q.setParameter(1, idUsuario);
            q.setParameter(2, url.trim().toLowerCase());

            List l = q.getResultList();

            if (l == null || l.isEmpty()) {
                return false;
            }

            return (Boolean) l.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("No se pudo consultar el permiso, usuario: " + idUsuario + ", url: " + url, ex);
        }
    }

    public Boolean isUrlValidNATIVE(String url) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT activo ");
            sb.append("  FROM movil.menu ");
            sb.append(" WHERE url = ?1");

            Query q = getEntityManager().createNativeQuery(sb.toString());
            q.setParameter(1, url);

            Object o = null;

            try {
                o = q.getSingleResult();
                if (o == null) {
                    return false; //tiene null en el campo active
                }
            } catch (NoResultException ex) {
                return null;
            }

            return Boolean.valueOf(o.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al consultar si la opción de menu existe.", ex);
        }
    }
}
