package py.com.documenta.ntimovil.controller.view.admin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoUsuario;
import py.com.documenta.ntimovil.ejb.model.movil.Limitecobranza;
import py.com.documenta.ntimovil.ejb.model.movil.Persona;
import py.com.documenta.ntimovil.ejb.model.movil.Rol;
import py.com.documenta.ntimovil.ejb.model.movil.Usuario;
import py.com.documenta.ntimovil.ejb.model.movil.Usuariorol;
import py.com.documenta.ntimovil.ejb.model.movil.UsuariorolPK;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.LimiteCobranzaFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.PersonaFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.RolFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioFacade;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.UsuarioRolFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "asignarLimiteCobranza")
@javax.enterprise.context.ConversationScoped
public class AsignarLimiteCobranza implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(AsignarLimiteCobranza.class);
    private static final long serialVersionUID = 1L;
    
    @Inject
    private NtiSession sessionEJB;

    @Inject
    private UsuarioFacade usuarioEJB;
    
    @Inject
    private LimiteCobranzaFacade limiteCobranzaEJB;

    @Inject @New
    private Limitecobranza limitecobranza;
    private List<Usuario> usuarioList;
    
    public AsignarLimiteCobranza() {
    }
    
    @PostConstruct
    private void init(){
        usuarioList = usuarioEJB.findAll();
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Limitecobranza getLimitecobranza() {
        return limitecobranza;
    }

    public void setLimitecobranza(Limitecobranza limitecobranza) {
        this.limitecobranza = limitecobranza;
    }

    public void asignarLimite() {
        try {
            if(limitecobranza.getDiasoperacion() <= 0){
                MsgUtil.addWarningMessage("Debe asignar al menos un día de operación");
                return;
            }
            
            if(limitecobranza.getMontooperacion() == null){
                limitecobranza.setMontooperacion(new BigDecimal(0));
            }
            
            limitecobranza.setFecharegistro(new Date());
            limitecobranza.setUsuarioregistro(sessionEJB.getUser().getIdusuario());
            limitecobranza.setFechaajuste(new Date());
            limitecobranza.setUsuarioajuste(sessionEJB.getUser().getIdusuario());
            limiteCobranzaEJB.create(limitecobranza);
            limitecobranza = new Limitecobranza();
            PrimeFaces.current().dialog().closeDynamic("asignCollectionLimit");
        } catch (Exception ex) {
            log.info("Error al asignar limite al usuario: "+ex.getMessage());
            try{
                if(ex.getCause() instanceof SQLException){
                    SQLException sqlE = (SQLException)ex.getCause();
                    log.info("SQL exception message: {}, codigo: {}",sqlE.getMessage(),sqlE.getErrorCode());
                } else {
                    
                }
            } catch(Exception e){
                log.warn("Error al parsear excepción a tipo sql: "+e.getMessage());
            }
            MsgUtil.addErrorMessage("Error al asignar limite. Favor verifique que el usuario ya no se encuentre asignado.");
            java.util.logging.Logger.getLogger(AsignarLimiteCobranza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
