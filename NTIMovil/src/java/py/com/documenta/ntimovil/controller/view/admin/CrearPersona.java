package py.com.documenta.ntimovil.controller.view.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoUsuario;
import py.com.documenta.ntimovil.ejb.model.movil.Persona;
import py.com.documenta.ntimovil.ejb.model.movil.PersonaPK;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.PersonaFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "crearPersona")
@javax.enterprise.context.ConversationScoped
public class CrearPersona implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(CrearPersona.class);
    private static final long serialVersionUID = 1L;
    
    @Inject
    private NtiSession sessionEJB;
    
    @Inject
    private PersonaFacade personaEJB;

    @Inject @New
    private Persona persona;
    
    public CrearPersona() {
    }
    
    @PostConstruct
    private void init(){
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void crearPersona() {
        try {
            PersonaPK personaPK = new PersonaPK();
            personaPK.setIdempresa(sessionEJB.getUser().getPersona().getPersonaPK().getIdempresa());
            persona.setActivo(true);
            persona.setIdtipodoc(2);
            persona.setPersonaPK(personaPK);
            personaEJB.create(persona);
            MsgUtil.addInfoMessage(persona.getNombre() + " " + persona.getApellido() +" registrado exitosamente!");
            persona = new Persona();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CrearPersona.class.getName()).log(Level.SEVERE, null, ex);
            log.info("Error al registrar a persona: "+ex);
            MsgUtil.addErrorMessage("Error al registrar a persona. Favor notifique al SAU.");
        }
    }
}
