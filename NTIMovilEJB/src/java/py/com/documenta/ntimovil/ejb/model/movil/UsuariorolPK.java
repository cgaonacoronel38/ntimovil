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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tokio
 */
@Embeddable
public class UsuariorolPK implements Serializable {
    @Column(name = "idempresa")
    private int idempresa;
    @Column(name = "idusuario")
    private int idusuario;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private int idrol;

    public UsuariorolPK() {
    }

    public UsuariorolPK(int idempresa, int idusuario, int idrol) {
        this.idempresa = idempresa;
        this.idusuario = idusuario;
        this.idrol = idrol;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idempresa;
        hash += (int) idusuario;
        hash += (int) idrol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariorolPK)) {
            return false;
        }
        UsuariorolPK other = (UsuariorolPK) object;
        if (this.idempresa != other.idempresa) {
            return false;
        }
        if (this.idusuario != other.idusuario) {
            return false;
        }
        if (this.idrol != other.idrol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovil.ejb.model.movil.UsuariorolPK[ idempresa=" + idempresa + ", idusuario=" + idusuario + ", idrol=" + idrol + " ]";
    }
    
}
