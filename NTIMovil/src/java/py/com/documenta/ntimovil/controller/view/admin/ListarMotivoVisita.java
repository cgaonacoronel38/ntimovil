package py.com.documenta.ntimovil.controller.view.admin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.model.movil.Motivovisita;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.MotivoVisitaFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "listarMotivoVisita")
@javax.enterprise.context.ConversationScoped
public class ListarMotivoVisita implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ListarMotivoVisita.class);
    private static final long serialVersionUID = 1L;
    
    @Inject
    private NtiSession sessionEJB;
    
    @Inject
    private MotivoVisitaFacade motivoVisitaEJB;
    
    private List<Motivovisita> motivoVisitaList;
    
    public ListarMotivoVisita() {
    }
    
    @PostConstruct
    private void init(){
        actualizarListaMotivosVisita();
    }

    public List<Motivovisita> getMotivoVisitaList() {
        return motivoVisitaList;
    }

    public void setMotivoVisitaList(List<Motivovisita> motivoVisitaList) {
        this.motivoVisitaList = motivoVisitaList;
    }
    
    public void onRowEdit(RowEditEvent event) {
        Motivovisita p = (Motivovisita)event.getObject();
        motivoVisitaEJB.edit(p);
        MsgUtil.addInfoMessage("Edición exitosa!");
    }
        
     
    public void onRowCancel(RowEditEvent event) {
    }
    
    public void deleteMotivoVisita(Motivovisita mv) {
        motivoVisitaEJB.remove(mv);
        MsgUtil.addInfoMessage("Motivo de visita removido exitosamente!");
    }
    
    public void informarRegistro(){
        actualizarListaMotivosVisita();
        MsgUtil.addInfoMessage("Motivo agregado exitosamente!");
    }
    
    private void actualizarListaMotivosVisita(){
        motivoVisitaList = motivoVisitaEJB.findAll();
    }
    
    public void crearMotivoVisita() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 330);
        options.put("height", 220);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("closable", "false");
        options.put("headerElement", "Agregar motivo de visita");
        PrimeFaces.current().dialog().openDynamic("createVisitReason", options, null);
    }
}
