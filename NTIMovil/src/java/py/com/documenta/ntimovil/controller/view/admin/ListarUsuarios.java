package py.com.documenta.ntimovil.controller.view.admin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoUsuario;
import py.com.documenta.ntimovil.ejb.model.movil.Persona;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.PersonaFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "listarUsuarios")
@javax.enterprise.context.ConversationScoped
public class ListarUsuarios implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ListarUsuarios.class);
    private static final long serialVersionUID = 1L;

    @Inject
    private NtiSession sessionEJB;

    @Inject
    private PersonaFacade personaEJB;

    @Inject
    private UsuarioFacade usuarioEJB;

    @Inject
    @New
    private Persona selectedPersona;

    private List<Persona> personaList;
    private List<Usuario> usuarioList;
    private String password = "";

    public ListarUsuarios() {
    }

    @PostConstruct
    private void init() {
        personaList = personaEJB.findAll();
        actualizarUsuarios();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public void onRowEdit(RowEditEvent event) {
        try {
//            log.info("Password: " + password);
            Usuario u = (Usuario) event.getObject();
//            if (password != null && !password.trim().isEmpty() && password.trim().length() > 3) {
//                u.setPassword(usuarioEJB.setPasswordSHA512Hex(password));
//            }
            
            if(!u.getActivo()){
                u.setActivo(true);
                u.setTipoestado(ETipoEstadoUsuario.BLOCKED_UNDEFINED);
            } else {
                if(u.getTipoestado().getCodeBD().equals(ETipoEstadoUsuario.BLOCKED_UNDEFINED.getCodeBD())){
                    u.setTipoestado(ETipoEstadoUsuario.ACTIVE);
                }
            }

            usuarioEJB.edit(u);
            if (u.getIdusuariodocumenta() != null && u.getIdusuariodocumenta().longValue() > 0) {
                usuarioEJB.updateNTIUserStatus(u.getIdusuariodocumenta().intValue(), u.getTipoestado().getCodeBD().equals(ETipoEstadoUsuario.ACTIVE.getCodeBD()));
            }
            password = "";
            MsgUtil.addInfoMessage("Usuario " + u.getUsername() + " editado exitosamente!");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            MsgUtil.addInfoMessage("Error al editar usuario. Favor contactar al SAU.");
        }
    }

    public void onRowCancel(RowEditEvent event) {
    }

    public void crearUsuario() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 420);
        options.put("height", 410);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "Crear usuario");
        PrimeFaces.current().dialog().openDynamic("createUser", options, null);
    }

    public void crearPersona() {
        Map<String, Object> options = new HashMap<>();
        options.put("width", 500);
        options.put("height", 510);
        options.put("modal", true);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "Registrar persona");
        PrimeFaces.current().dialog().openDynamic("createPerson", options, null);
    }

    public void asignarRol() {
        Map<String, Object> options = new HashMap<>();
        options.put("width", 500);
        options.put("height", 300);
        options.put("modal", true);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "Asignar rol");
        PrimeFaces.current().dialog().openDynamic("asignRole", options, null);
    }

    public void listarPersonas() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 1200);
        options.put("height", 600);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "Listado de personas");
        PrimeFaces.current().dialog().openDynamic("listPerson", options, null);
    }

    public void listarRoles() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 500);
        options.put("height", 600);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "Listado de roles");
        PrimeFaces.current().dialog().openDynamic("listRol", options, null);
    }

    public void actualizarUsuarios() {
        usuarioList = usuarioEJB.findAll();
    }
}
