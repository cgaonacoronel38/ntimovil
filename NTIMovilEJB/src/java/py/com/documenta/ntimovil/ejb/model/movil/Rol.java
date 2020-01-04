/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.model.movil;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author tokio
 */
@Entity
@Table(name = "rol", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrol")
    private Integer idrol;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Size(min = 1, max = 2147483647)
    @Column(name = "gruporealm")
    private String gruporealm;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 2147483647)
    @Column(name = "nota")
    private String nota;
    @Column(name = "sistema")
    private Boolean sistema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private List<Usuariorol> usuariorolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private List<Permisorol> permisorolList;

    public Rol() {
    }

    public Rol(Integer idrol) {
        this.idrol = idrol;
    }

    public Rol(Integer idrol, String descripcion, String gruporealm, boolean activo) {
        this.idrol = idrol;
        this.descripcion = descripcion;
        this.gruporealm = gruporealm;
        this.activo = activo;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGruporealm() {
        return gruporealm;
    }

    public void setGruporealm(String gruporealm) {
        this.gruporealm = gruporealm;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Boolean getSistema() {
        return sistema;
    }

    public void setSistema(Boolean sistema) {
        this.sistema = sistema;
    }

    public List<Usuariorol> getUsuariorolList() {
        return usuariorolList;
    }

    public void setUsuariorolList(List<Usuariorol> usuariorolList) {
        this.usuariorolList = usuariorolList;
    }

    public List<Permisorol> getPermisorolList() {
        return permisorolList;
    }

    public void setPermisorolList(List<Permisorol> permisorolList) {
        this.permisorolList = permisorolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovil.ejb.model.movil.Rol[ idrol=" + idrol + " ]";
    }
    
}
