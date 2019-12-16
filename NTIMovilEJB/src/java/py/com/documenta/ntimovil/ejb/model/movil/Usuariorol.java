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
@Table(name = "usuariorol", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Usuariorol.findAll", query = "SELECT u FROM Usuariorol u")})
public class Usuariorol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariorolPK usuariorolPK;
    @Basic(optional = false)
    @Column(name = "audadddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audadddate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "audadduser")
    private int audadduser;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Usuariorol() {
    }

    public Usuariorol(UsuariorolPK usuariorolPK) {
        this.usuariorolPK = usuariorolPK;
    }

    public Usuariorol(UsuariorolPK usuariorolPK, Date audadddate, int audadduser) {
        this.usuariorolPK = usuariorolPK;
        this.audadddate = audadddate;
        this.audadduser = audadduser;
    }

    public Usuariorol(int idempresa, int idusuario, int idrol) {
        this.usuariorolPK = new UsuariorolPK(idempresa, idusuario, idrol);
    }

    public UsuariorolPK getUsuariorolPK() {
        return usuariorolPK;
    }

    public void setUsuariorolPK(UsuariorolPK usuariorolPK) {
        this.usuariorolPK = usuariorolPK;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariorolPK != null ? usuariorolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariorol)) {
            return false;
        }
        Usuariorol other = (Usuariorol) object;
        if ((this.usuariorolPK == null && other.usuariorolPK != null) || (this.usuariorolPK != null && !this.usuariorolPK.equals(other.usuariorolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovil.ejb.model.movil.Usuariorol[ usuariorolPK=" + usuariorolPK + " ]";
    }
    
}
