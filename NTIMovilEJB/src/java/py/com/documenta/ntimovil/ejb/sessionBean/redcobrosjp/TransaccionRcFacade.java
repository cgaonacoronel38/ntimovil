/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.sessionBean.redcobrosjp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import py.com.documenta.ntimovil.ejb.bean.TransaccionBean;
import py.com.documenta.ntimovil.ejb.model.redcobrosjp.TransaccionRc;

/**
 *
 * @author tokio
 */
@Stateless
public class TransaccionRcFacade extends AbstractFacadeJP<TransaccionRc> {

    public TransaccionRcFacade() {
        super(TransaccionRc.class);
    }

    public List<TransaccionBean> obtenerListadoTransacciones(int idUsuario, Date desdeFecha, Date hastaFecha) throws Exception {
        List<TransaccionBean> listTrx = null;
        try {
            if (desdeFecha == null) {
                desdeFecha = new Date();
            }
            if (hastaFecha == null) {
                hastaFecha = new Date();
            }

            String sql = "select t.id_transaccion,\n"
                    + "       s.descripcion,\n"
                    + "       t.referencia_consulta,\n"
                    + "       t.referencia_pago,\n"
                    + "       g.id_gestion lote,\n"
                    + "       to_char(t.fecha_ingreso,'dd/mm/yyyy h24:mi:ss'),\n"
                    + "       t.monto,\n"
                    + "       t.anulado,\n"
                    + "       tp.descripcion tipo_pago,\n"
                    + "       e.descripcion estado,\n"
                    + "       p.nombres || ' ' || p.apellidos cobrador\n"
                    + " from redcobrosjp.transaccion_rc t\n"
                    + " join redcobrosjp.servicio_rc s on t.id_servicio = s.id_servicio\n"
                    + " join redcobrosjp.gestion g on t.id_gestion = g.id_gestion\n"
                    + " join redcobrosjp.usuario u on g.usuario = u.id_usuario\n"
                    + " join redcobrosjp.persona p on u.persona = p.id_persona\n"
                    + " join redcobrosjp.estado_transaccion e on t.id_estado_transaccion = e.id_estado_transaccion\n"
                    + " join redcobrosjp.tipo_pago tp on t.id_tipo_pago = tp.id_tipo_pago\n"
                    + " where t.id_servicio in (355,356)\n"
                    + " and t.id_tipo_operacion = 1\n"
                    + " and (0 = ?1 or u.id_usuario = ?2)\n"
                    + " and cast(t.fecha_ingreso as date) between ?3 and ?4 \n"
                    + " order by t.id_transaccion";

            Query q = getEntityManager().createNativeQuery(sql);
            q.setParameter(1, idUsuario);
            q.setParameter(2, idUsuario);
            q.setParameter(3, desdeFecha);
            q.setParameter(4, hastaFecha);

            List<Object[]> resulset = q.getResultList();
            if (resulset != null) {
                listTrx = new ArrayList<>();
                for (Object[] row : resulset) {
                    TransaccionBean bean = new TransaccionBean();
                    bean.setIdTransaccion(new BigDecimal(row[0].toString()));
                    bean.setServicio(row[1].toString());
                    bean.setRefConsulta(row[2].toString());
                    bean.setRefPago(row[3].toString());
                    bean.setGestion(new BigDecimal(row[4].toString()));
                    bean.setFecha(row[5].toString());
                    bean.setMonto(new BigDecimal(row[6].toString()));
                    bean.setAnulado(row[7].toString());
                    bean.setTipoPago(row[8].toString());
                    bean.setEstado(row[9].toString());
                    bean.setCobrador(row[10].toString());
                    listTrx.add(bean);
                }
            }

            return listTrx;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("No se pudo obtener listado de visitas: ", ex);
        }
    }
}
