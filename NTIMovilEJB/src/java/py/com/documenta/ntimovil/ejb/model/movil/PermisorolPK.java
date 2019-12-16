/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.model.movil;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tokio
 */
@Embeddable
public class PermisorolPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idrol")
    private int idrol;
    @Basic(optional = false)
    @Column(name = "idmenu")
    private int idmenu;

    public PermisorolPK() {
    }

    public PermisorolPK(int idrol, int idmenu) {
        this.idrol = idrol;
        this.idmenu = idmenu;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idrol;
        hash += (int) idmenu;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisorolPK)) {
            return false;
        }
        PermisorolPK other = (PermisorolPK) object;
        if (this.idrol != other.idrol) {
            return false;
        }
        if (this.idmenu != other.idmenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovil.ejb.model.movil.PermisorolPK[ idrol=" + idrol + ", idmenu=" + idmenu + " ]";
    }
    
}
