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
import py.com.documenta.ntimovil.ejb.model.movil.Persona;
import py.com.documenta.ntimovil.ejb.model.movil.Rol;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.model.movil.Usuariorol;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.PersonaFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.RolFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioRolFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "listarRoles")
@javax.enterprise.context.ConversationScoped
public class ListarRoles implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ListarRoles.class);
    private static final long serialVersionUID = 1L;

    @Inject
    private NtiSession sessionEJB;

    @Inject
    private UsuarioRolFacade usuariorolEJB;

    @Inject
    private RolFacade rolEJB;

    private List<Usuariorol> usuariorolList;
    private List<Rol> rolList;

    public ListarRoles() {
    }

    @PostConstruct
    private void init() {
        try {
            usuariorolList = usuariorolEJB.findAll();
            rolList = rolEJB.listarRolesActivos();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarRoles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public List<Usuariorol> getUsuariorolList() {
        return usuariorolList;
    }

    public void setUsuariorolList(List<Usuariorol> usuariorolList) {
        this.usuariorolList = usuariorolList;
    }
    
    public void onRowEdit(RowEditEvent event) {
        try {
            Usuariorol ur = (Usuariorol)event.getObject();
            usuariorolEJB.edit(ur);
            MsgUtil.addInfoMessage("Rol editado exitosamente!");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            MsgUtil.addInfoMessage("Error al editar usuario. Favor contactar al SAU.");
        }
    }

    public void onRowCancel(RowEditEvent event) {
    }
    
    public void deleteRole(Usuariorol rol) {
        try{
            usuariorolEJB.remove(rol);
            MsgUtil.addInfoMessage("Rol removido exitosamente!");
        } catch(Exception ex){
            MsgUtil.addErrorMessage("Error al remover Rol.");
        }
    }
}
