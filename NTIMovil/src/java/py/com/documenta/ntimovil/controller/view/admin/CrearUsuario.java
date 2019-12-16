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
import py.com.documenta.ntimovil.ejb.model.movil.Rol;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.model.movil.Usuariorol;
import py.com.documenta.ntimovil.ejb.model.movil.UsuariorolPK;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.PersonaFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.RolFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioRolFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "crearUsuario")
@javax.enterprise.context.ConversationScoped
public class CrearUsuario implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(CrearUsuario.class);
    private static final long serialVersionUID = 1L;
    
    @Inject
    private NtiSession sessionEJB;

    @Inject
    private UsuarioFacade usuarioEJB;
    
    @Inject
    private PersonaFacade personaEJB;
    
    @Inject
    private RolFacade rolEJB;
    
    @Inject
    private UsuarioRolFacade usuarioRolEJB;

    @Inject @New
    private Usuario usuario;
    @Inject @New
    private Persona selectedPersona;
    private List<Persona> personaList;
    
    public CrearUsuario() {
    }
    
    @PostConstruct
    private void init(){
        personaList = personaEJB.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public void crearUsuario() {
        try {
            log.info("persona; "+selectedPersona.getNombre());
            usuario.setPersona(selectedPersona);
            usuario.setPassword(usuarioEJB.setPasswordSHA512Hex(usuario.getPassword()));
            usuario.setTipoestado(ETipoEstadoUsuario.ACTIVE);
            usuario.setActivo(true);
            usuario.setAudIdusuarioalta(sessionEJB.getUser().getIdusuario());
            usuario.setAudFecalta(new Date());
            usuario.setDiasvencpwd(0);
            usuario.setIntentosdeacceso(0);
            usuario.setCantcambiopwd(0);
            usuarioEJB.create(usuario);
            
            usuario = new Usuario();
            MsgUtil.addInfoMessage("Usuario "+usuario.getUsername()+" registrado exitosamente!");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(Level.SEVERE, null, ex);
            log.info("Error al registrar usuario: "+ex);
            MsgUtil.addErrorMessage("Error al crear usuario. Favor notifique al SAU.");
        }
    }
}
