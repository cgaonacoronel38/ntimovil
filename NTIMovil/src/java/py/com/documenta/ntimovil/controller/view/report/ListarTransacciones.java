package py.com.documenta.ntimovil.controller.view.report;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.bean.TransaccionBean;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.redcobrosjp.TransaccionRcFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "listarTrasnacciones")
@ViewScoped
public class ListarTransacciones implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ListarTransacciones.class);
    private static final long serialVersionUID = 1L;

    @Inject
    private NtiSession sessionEJB;

    @Inject
    private TransaccionRcFacade transaccionesEJB;

    @Inject
    private UsuarioFacade usuarioEJB;

    private Date desdeFecha;
    private Date hastaFecha;
    private Usuario selectedUsuario;
    private List<Usuario> usuarioList;
    private List<TransaccionBean> transaccionList;

    public ListarTransacciones() {
    }

    @PostConstruct
    private void init() {
        desdeFecha = new Date();
        hastaFecha = new Date();
        selectedUsuario = new Usuario();
        usuarioList = usuarioEJB.findAll();
        transaccionList = new ArrayList<>();
    }

    public List<TransaccionBean> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<TransaccionBean> transaccionList) {
        this.transaccionList = transaccionList;
    }

    public Date getDesdeFecha() {
        return desdeFecha;
    }

    public void setDesdeFecha(Date desdeFecha) {
        this.desdeFecha = desdeFecha;
    }

    public Date getHastaFecha() {
        return hastaFecha;
    }

    public void setHastaFecha(Date hastaFecha) {
        this.hastaFecha = hastaFecha;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public void listarTransacciones() {
        try {
            int codigoUsuario = selectedUsuario != null ? getNotNullValue(selectedUsuario.getIdusuariodocumenta()) : 0;
            transaccionList = transaccionesEJB.obtenerListadoTransacciones(codigoUsuario, desdeFecha, hastaFecha);
            if (transaccionList != null && !transaccionList.isEmpty()) {
                MsgUtil.addInfoMessage("Listado de transacciones exitoso!");
            } else {
                MsgUtil.addWarningMessage("No se encontraron resultados con los datos registrados!");
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarVisitas.class.getName()).log(Level.SEVERE, null, ex);
            MsgUtil.addInfoMessage("No se ha pdido realizar el listado con los parámetros insertados!.");
        }
    }

    private int getNotNullValue(BigInteger value) {
        return value == null ? 0 : value.intValue();
    }
}
