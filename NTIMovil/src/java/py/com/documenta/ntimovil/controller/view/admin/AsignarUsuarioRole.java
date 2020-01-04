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
import py.com.documenta.ntimovil.ejb.model.movil.Persona;
import py.com.documenta.ntimovil.ejb.model.movil.Rol;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.model.movil.Usuariorol;
import py.com.documenta.ntimovil.ejb.model.movil.UsuariorolPK;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.RolFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioRolFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "asignarUsuariorol")
@javax.enterprise.context.ConversationScoped
public class AsignarUsuarioRole implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(AsignarUsuarioRole.class);
    private static final long serialVersionUID = 1L;
    
    @Inject
    private NtiSession sessionEJB;

    @Inject
    private UsuarioFacade usuarioEJB;
    
    @Inject
    private RolFacade rolEJB;
    
    @Inject
    private UsuarioRolFacade usuarioRolEJB;

    @Inject @New
    private Usuario selectedUsuario;
    @Inject @New
    private Rol selectedRol;
    private List<Usuario> usuarioList;
    private List<Rol> rolList;
    
    public AsignarUsuarioRole() {
    }
    
    @PostConstruct
    private void init(){
        usuarioList = usuarioEJB.findAll();
        rolList = rolEJB.findAll();
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
    
    public Rol getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public void asignarRol() {
        try {
            Usuariorol ur = new Usuariorol();
            UsuariorolPK urPk = new UsuariorolPK();
            urPk.setIdempresa(selectedUsuario.getPersona().getEmpresa().getIdempresa());
            urPk.setIdusuario(selectedUsuario.getIdusuario());
            urPk.setIdrol(selectedRol.getIdrol());
            ur.setUsuariorolPK(urPk);
            ur.setAudadduser(sessionEJB.getUser().getIdusuario());
            ur.setAudadddate(new Date());
            usuarioRolEJB.create(ur);
            selectedUsuario = new Usuario();
            selectedRol = new Rol();
            MsgUtil.addInfoMessage("Rol asignado exitosamente!");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AsignarUsuarioRole.class.getName()).log(Level.SEVERE, null, ex);
            log.info("Error al registrar usuario: "+ex);
            MsgUtil.addErrorMessage("Error al crear usuario. Favor notifique al SAU.");
        }
    }
}
