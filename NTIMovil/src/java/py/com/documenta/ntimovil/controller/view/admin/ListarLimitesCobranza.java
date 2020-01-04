package py.com.documenta.ntimovil.controller.view.admin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.documenta.ntimovil.common.MsgUtil;
import py.com.documenta.ntimovil.controller.session.NtiSession;
import py.com.documenta.ntimovil.ejb.model.movil.Limitecobranza;
import py.com.documenta.ntimovil.ejb.sessionBean.movil.LimiteCobranzaFacade;

/**
 *
 * @author tokio
 */
@javax.inject.Named(value = "listarLimiteCobranza")
@javax.enterprise.context.ConversationScoped
public class ListarLimitesCobranza implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ListarLimitesCobranza.class);
    private static final long serialVersionUID = 1L;

    @Inject
    private NtiSession sessionEJB;

    @Inject
    private LimiteCobranzaFacade limiteCobranzaEJB;

    private List<Limitecobranza> limiteCobranzaList;

    public ListarLimitesCobranza() {
    }

    @PostConstruct
    private void init() {
        actualizarListaLimitesCobranza();
    }

    public List<Limitecobranza> getLimiteCobranzaList() {
        return limiteCobranzaList;
    }

    public void setLimiteCobranzaList(List<Limitecobranza> limiteCobranzaList) {
        this.limiteCobranzaList = limiteCobranzaList;
    }
    
    public void onRowEdit(RowEditEvent event) {
        try {
            Limitecobranza ur = (Limitecobranza)event.getObject();
            if(ur.getDiasoperacion() <= 0){
                MsgUtil.addWarningMessage("Debe asignar al menos un día de operación!");
                return;
            }
            
            if(ur.getMontooperacion() == null || ur.getMontooperacion().longValue() <= 0){
                ur.setMontooperacion(new BigDecimal(0));
            }
            
            ur.setUsuarioajuste(sessionEJB.getUser().getIdusuario());
            ur.setFechaajuste(new Date());
            limiteCobranzaEJB.edit(ur);
            MsgUtil.addInfoMessage("Limite de cobranza editado exitosamente!");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            MsgUtil.addInfoMessage("Error al editar limite de cobranza.");
        }
    }

    public void onRowCancel(RowEditEvent event) {
    }
    
    public void deleteUserLimit(Limitecobranza limite) {
        try{
            limiteCobranzaEJB.remove(limite);
            MsgUtil.addInfoMessage("Limite de cobranza removido exitosamente!");
        } catch(Exception ex){
            MsgUtil.addErrorMessage("Error al remover limite.");
        }
    }
    
    public void informarRegistro(){
        actualizarListaLimitesCobranza();
        MsgUtil.addInfoMessage("Limite registrado exitosamente!");
    }
    
    private void actualizarListaLimitesCobranza(){
        try {
            limiteCobranzaList = limiteCobranzaEJB.findAll();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ListarLimitesCobranza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void asignarLimiteCobranza() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 390);
        options.put("height", 345);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "Crear usuario");
        PrimeFaces.current().dialog().openDynamic("asignCollectionLimit", options, null);
    }
}
