package py.com.documenta.ntimovil.controller.view.admin;

import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
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
@javax.inject.Named(value = "crearMotivoVisita")
@javax.enterprise.context.ConversationScoped
public class CrearMotivoVisita implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(CrearMotivoVisita.class);
    private static final long serialVersionUID = 1L;
    
    @Inject
    private NtiSession sessionEJB;

    @Inject
    private MotivoVisitaFacade motivoVisitaEJB;

    @Inject @New
    private Motivovisita motivoVisita;
    
    public CrearMotivoVisita() {
    }
    
    @PostConstruct
    private void init(){
    }

    public Motivovisita getMotivoVisita() {
        return motivoVisita;
    }

    public void setMotivoVisita(Motivovisita motivoVisita) {
        this.motivoVisita = motivoVisita;
    }

    public void crearMotivoVisita() {
        try {
            log.info("Creando motivo visita..");
            motivoVisita.setActivo(true);
            motivoVisitaEJB.create(motivoVisita);
            PrimeFaces.current().dialog().closeDynamic("@([id$=createVisitReason])");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CrearMotivoVisita.class.getName()).log(Level.SEVERE, null, ex);
            log.info("Error al agregagar motivo de visita: "+ex);
            MsgUtil.addErrorMessage("Error al agregar motivo de visita!");
        }
    }
}
