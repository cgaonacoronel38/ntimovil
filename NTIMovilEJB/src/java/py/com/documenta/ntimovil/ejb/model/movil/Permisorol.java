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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "permisorol", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Permisorol.findAll", query = "SELECT p FROM Permisorol p")})
public class Permisorol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PermisorolPK permisorolPK;
    @Basic(optional = false)
    @Column(name = "audadddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audadddate;
    @Basic(optional = false)
    @Column(name = "audadduser")
    private int audadduser;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Menu menu;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    public Permisorol() {
    }

    public Permisorol(PermisorolPK permisorolPK) {
        this.permisorolPK = permisorolPK;
    }

    public Permisorol(PermisorolPK permisorolPK, Date audadddate, int audadduser) {
        this.permisorolPK = permisorolPK;
        this.audadddate = audadddate;
        this.audadduser = audadduser;
    }

    public Permisorol(int idrol, int idmenu) {
        this.permisorolPK = new PermisorolPK(idrol, idmenu);
    }

    public PermisorolPK getPermisorolPK() {
        return permisorolPK;
    }

    public void setPermisorolPK(PermisorolPK permisorolPK) {
        this.permisorolPK = permisorolPK;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permisorolPK != null ? permisorolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisorol)) {
            return false;
        }
        Permisorol other = (Permisorol) object;
        if ((this.permisorolPK == null && other.permisorolPK != null) || (this.permisorolPK != null && !this.permisorolPK.equals(other.permisorolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovil.ejb.model.movil.Permisorol[ permisorolPK=" + permisorolPK + " ]";
    }
    
}
