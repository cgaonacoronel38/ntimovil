package py.com.documenta.ntimovil.controller.view.admin;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.model.movil.Persona;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.PersonaFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "listarPersonas")
@javax.enterprise.context.ConversationScoped
public class ListarPersonas implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ListarPersonas.class);
    private static final long serialVersionUID = 1L;
    
    @Inject
    private NtiSession sessionEJB;
    
    @Inject
    private PersonaFacade personaEJB;

    @Inject @New
    private Persona selectedPersona;
    
    private List<Persona> personaList;
    
    public ListarPersonas() {
    }
    
    @PostConstruct
    private void init(){
        personaList = personaEJB.findAll();
    }

    public Persona getSelectedPersona() {
        return selectedPersona;
    }

    public void setSelectedPersona(Persona selectedPersona) {
        this.selectedPersona = selectedPersona;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public void crearPersona() {
        try {
//            PersonaPK personaPK = new PersonaPK();
//            personaPK.setIdempresa(sessionEJB.getUser().getPersona().getPersonaPK().getIdempresa());
//            persona.setActivo(true);
//            persona.setIdtipodoc(2);
//            persona.setPersonaPK(personaPK);
//            personaEJB.create(persona);
//            MsgUtil.addInfoMessage(persona.getNombre() + " " + persona.getApellido() +" registrado exitosamente!");
//            persona = new Persona();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarPersonas.class.getName()).log(Level.SEVERE, null, ex);
            log.info("Error al registrar a persona: "+ex);
            MsgUtil.addErrorMessage("Error al registrar a persona. Favor notifique al SAU.");
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
        Persona p = (Persona)event.getObject();
        personaEJB.edit(p);
        MsgUtil.addInfoMessage("Edición de registro exitosa!");
    }
     
    public void onRowCancel(RowEditEvent event) {
    }
}
