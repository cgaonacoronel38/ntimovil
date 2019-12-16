/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.model.movil;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tokio
 */
@Entity
@Table(name = "permisomenu", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Permisomenu.findAll", query = "SELECT p FROM Permisomenu p")})
public class Permisomenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PermisomenuPK permisomenuPK;
    @Basic(optional = false)
    @Column(name = "audadddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audadddate;
    @Basic(optional = false)
    @Column(name = "audadduser")
    private int audadduser;

    public Permisomenu() {
    }

    public Permisomenu(PermisomenuPK permisomenuPK) {
        this.permisomenuPK = permisomenuPK;
    }

    public Permisomenu(PermisomenuPK permisomenuPK, Date audadddate, int audadduser) {
        this.permisomenuPK = permisomenuPK;
        this.audadddate = audadddate;
        this.audadduser = audadduser;
    }

    public Permisomenu(int idrol, int idmenu) {
        this.permisomenuPK = new PermisomenuPK(idrol, idmenu);
    }

    public PermisomenuPK getPermisomenuPK() {
        return permisomenuPK;
    }

    public void setPermisomenuPK(PermisomenuPK permisomenuPK) {
        this.permisomenuPK = permisomenuPK;
    }

    public Date getAudadddate() {
        return audadddate;
    }

    public void setAudadddate(Date audadddate) {
        this.audadddate = audadddate;
    }

    public int getAudadduser() {
        return audadduser;
    }

    public void setAudadduser(int audadduser) {
        this.audadduser = audadduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permisomenuPK != null ? permisomenuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisomenu)) {
            return false;
        }
        Permisomenu other = (Permisomenu) object;
        if ((this.permisomenuPK == null && other.permisomenuPK != null) || (this.permisomenuPK != null && !this.permisomenuPK.equals(other.permisomenuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovil.ejb.model.movil.Permisomenu[ permisomenuPK=" + permisomenuPK + " ]";
    }
    
}