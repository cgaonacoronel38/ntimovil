/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.movil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import py.com.documenta.ntimovil.ejb.bean.VisitaBean;
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
    
    public List<VisitaBean> obtenerListadoVisitas(int idUsuario, int idMotivoVisita, Date desdeFecha, Date hastaFecha) throws Exception {
        List<VisitaBean> listVisitas = null;
        try {
            if(desdeFecha == null){
                desdeFecha = new Date();
            }
            if(hastaFecha == null){
                hastaFecha = new Date();
            }
            
            String sql = "SELECT p.nombre || ' ' || p.apellido nombre,\n"
                    + "       mv.descripcion motivo,\n"
                    + "       v.documento dcto_cliente,\n"
                    + "       v.cliente cliente,\n"
                    + "       v.observaciones,\n"
                    + "       v.latitud,\n"
                    + "       v.longitud,\n"
                    + "       v.visitado,\n"//
                    + "       to_char(v.fechavisita,'dd/mm/yyyy hh24:mi:ss') fecha \n"
                    + " FROM movil.visita v \n"
                    + " join movil.usuario u on v.idusuariovisitador = u.idusuario \n"
                    + " join movil.persona p on u.idpersona = p.idpersona \n"
                    + "                     and u.idempresa = p.idempresa \n"
                    + " join movil.motivovisita mv on v.idmotivovisita = mv.idmotivovisita \n"
                    + " where (0 = ?1 or u.idusuario = ?2) \n"
                    + " and (0 = ?3 or v.idmotivovisita = ?4) "
                    + " and cast(v.fechavisita as date) between ?5 and ?6 "
                    + " order by v.idvisita desc ";

            Query q = getEntityManager().createNativeQuery(sql);
            q.setParameter(1, idUsuario);
            q.setParameter(2, idUsuario);
            q.setParameter(3, idMotivoVisita);
            q.setParameter(4, idMotivoVisita);
            q.setParameter(5, desdeFecha);
            q.setParameter(6, hastaFecha);

            List<Object[]> resulset = q.getResultList();
            if(resulset != null){
                listVisitas = new ArrayList<>();
                for(Object[] row : resulset){
                    VisitaBean bean = new VisitaBean();
                    bean.setCobrador(row[0].toString());
                    bean.setMotivoVisita(row[1].toString());
                    bean.setDocCliente(row[2].toString());
                    bean.setCliente(row[3].toString());
                    bean.setObservaciones(row[4].toString());
                    bean.setLatitud(row[5].toString());
                    bean.setLongitud(row[6].toString());
                    bean.setVisitado(Boolean.valueOf(row[7].toString()));
                    bean.setFecha(row[8].toString());
                    listVisitas.add(bean);
                }
            }

            return listVisitas;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("No se pudo obtener listado de visitas: ", ex);
        }
    }
}
