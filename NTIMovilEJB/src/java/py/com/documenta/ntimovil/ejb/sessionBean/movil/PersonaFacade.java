/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import py.com.documenta.ntimovil.ejb.model.movil.Persona;

/**
 *
 * @author eduardo
 */
@Stateless
public class PersonaFacade extends AbstractFacadeMovil<Persona> {

    public PersonaFacade() {
        super(Persona.class);
    }

    public List<Persona> findClientByKey(String docNumber) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT c.* ");
            sb.append("  FROM movil.persona c ");
            sb.append(" WHERE   (?1 IS NULL OR c.nrodoc = ?2) ");

            Query q = getEntityManager().createNativeQuery(sb.toString(), Persona.class);
            q.setParameter(1, docNumber == null || docNumber.trim().equals("") ? null : docNumber.trim());
            q.setParameter(2, docNumber == null || docNumber.trim().equals("") ? null : docNumber.trim());

            List l = q.getResultList();

            if (l.isEmpty()) {
                return null;
            } else {
                return l;
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    public Persona findClientByKey(Integer idPersona) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT c.* ");
            sb.append("  FROM movil.persona c ");
            sb.append(" WHERE  c.idpersona = ?1");

            Query q = getEntityManager().createNativeQuery(sb.toString(), Persona.class);
            q.setParameter(1, idPersona);

            List l = q.getResultList();

            if (l.isEmpty()) {
                return null;
            } else {
                return (Persona) l.get(0);
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean checkPersonByDocNumber(String docNumber) throws Exception {

        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT p.* ");
            sb.append(" FROM movil.persona p ");
            sb.append("     JOIN movil.usuario u ON p.idpersona = u.idpersona ");
            sb.append(" WHERE   (?1 IS NULL OR p.nrodoc = ?2) ");

            Query q = getEntityManager().createNativeQuery(sb.toString(), Persona.class);
            q.setParameter(1, docNumber == null || docNumber.trim().equals("") ? null : docNumber.trim());
            q.setParameter(2, docNumber == null || docNumber.trim().equals("") ? null : docNumber.trim());

            List l = q.getResultList();

            if (l.isEmpty()) {
                return false;
            } else {
                return true;
            }

        } catch (Exception ex) {
            throw ex;
        }
    }
}
