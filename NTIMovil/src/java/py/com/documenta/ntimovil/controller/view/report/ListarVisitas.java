package py.com.documenta.ntimovil.controller.view.report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.bean.VisitaBean;
import py.com.documenta.ntimovil.ejb.model.movil.Motivovisita;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.MotivoVisitaFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.VisitaFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "listarVisitas")
@ViewScoped
public class ListarVisitas implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ListarVisitas.class);
    private static final long serialVersionUID = 1L;

    @Inject
    private NtiSession sessionEJB;

    @Inject
    private VisitaFacade visitaEJB;

    @Inject
    private UsuarioFacade usuarioEJB;
    @Inject
    private MotivoVisitaFacade motivoVisitaEJB;

    private Date desdeFecha;
    private Date hastaFecha;
    private List<Usuario> usuarioList;
    private List<Motivovisita> motivoVisitaList;
    private List<VisitaBean> visitaList;

    private Usuario selectedUsuario;
    private Motivovisita selectedMotivoVisita;

    private MapModel advancedModel;
    private Marker marker;

    public ListarVisitas() {
    }

    @PostConstruct
    private void init() {
        desdeFecha = new Date();
        hastaFecha = new Date();
        selectedUsuario = new Usuario();
        selectedMotivoVisita = new Motivovisita();
        usuarioList = usuarioEJB.findAll();
        motivoVisitaList = motivoVisitaEJB.findAll();
        advancedModel = new DefaultMapModel();
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

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Motivovisita> getMotivoVisitaList() {
        return motivoVisitaList;
    }

    public void setMotivoVisitaList(List<Motivovisita> motivoVisitaList) {
        this.motivoVisitaList = motivoVisitaList;
    }

    public List<VisitaBean> getVisitaList() {
        return visitaList;
    }

    public void setVisitaList(List<VisitaBean> visitaList) {
        this.visitaList = visitaList;
    }

    public Motivovisita getSelectedMotivoVisita() {
        return selectedMotivoVisita;
    }

    public void setSelectedMotivoVisita(Motivovisita selectedMotivoVisita) {
        this.selectedMotivoVisita = selectedMotivoVisita;
    }

    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    public void setAdvancedModel(MapModel advancedModel) {
        this.advancedModel = advancedModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public void obtenerListaVisitas() {
        try {
            int codigoUsuario = selectedUsuario != null ? getNotNullValue(selectedUsuario.getIdusuario()) : 0;
            int codigoVisita = selectedMotivoVisita != null ? getNotNullValue(selectedMotivoVisita.getIdmotivovisita()) : 0;
            visitaList = visitaEJB.obtenerListadoVisitas(codigoUsuario,
                    codigoVisita,
                    desdeFecha,
                    hastaFecha);
            if (visitaList != null && !visitaList.isEmpty()) {
                MsgUtil.addInfoMessage("Listado de visitas exitoso!");
            } else {
                MsgUtil.addWarningMessage("No se encontraron resultados con los datos registrados!");
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarVisitas.class.getName()).log(Level.SEVERE, null, ex);
            MsgUtil.addInfoMessage("No se ha pdido realizar el listado con los parámetros insertados!.");
        }
    }

    public void obtenerListaVisitasGeoreferenciadas() {
        try {
            int codigoUsuario = selectedUsuario != null ? getNotNullValue(selectedUsuario.getIdusuario()) : 0;
            int codigoVisita = selectedMotivoVisita != null ? getNotNullValue(selectedMotivoVisita.getIdmotivovisita()) : 0;
            visitaList = visitaEJB.obtenerListadoVisitas(codigoUsuario,
                    codigoVisita,
                    desdeFecha,
                    hastaFecha);
            if (visitaList != null && !visitaList.isEmpty()) {
                advancedModel = new DefaultMapModel();
                log.info("Listando georeferencias...");
                for (VisitaBean bean : visitaList) {
                    if(bean.getLatitud() != null && bean.getLongitud() != null){
                        //Shared coordinates
                        LatLng coord1 = new LatLng(Double.valueOf(bean.getLatitud()), Double.valueOf(bean.getLongitud()));
                        //Icons and Data
                        advancedModel.addOverlay(new Marker(coord1, "Motivo: "+bean.getMotivoVisita() + "; Cliente: "+ bean.getCliente()+ "; Cobrador:"+bean.getCobrador(), bean.getCobrador(), "https://maps.google.com/mapfiles/ms/micons/red-dot.png"));
                    }
                }

                MsgUtil.addInfoMessage("Listado de visitas exitoso!");
            } else {
                MsgUtil.addWarningMessage("No se encontraron resultados con los datos registrados!");
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarVisitas.class.getName()).log(Level.SEVERE, null, ex);
            MsgUtil.addInfoMessage("No se ha pdido realizar el listado con los parámetros insertados!.");
        }
    }

    private int getNotNullValue(Integer value) {
        return value == null ? 0 : value;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
}
