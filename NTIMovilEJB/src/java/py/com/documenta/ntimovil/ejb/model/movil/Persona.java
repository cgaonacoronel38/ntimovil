/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.model.movil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tokio
 */
@Entity
@Table(name = "persona", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaPK personaPK;
    @Basic(optional = false)
    @Column(name = "idtipodoc")
    private int idtipodoc;
    @Basic(optional = false)
    @Size(min = 1, max = 40)
    @Column(name = "nrodoc")
    private String nrodoc;
    @Size(max = 1000)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 70)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecnac")
    @Temporal(TemporalType.DATE)
    private Date fecnac;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 2147483647)
    @Column(name = "obs")
    private String obs;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;
    @OneToMany(mappedBy = "persona")
    private List<Usuario> usuarioList;

    public Persona() {
    }

    public Persona(PersonaPK personaPK) {
        this.personaPK = personaPK;
    }

    public Persona(PersonaPK personaPK, int idtipodoc, String nrodoc, boolean activo) {
        this.personaPK = personaPK;
        this.idtipodoc = idtipodoc;
        this.nrodoc = nrodoc;
        this.activo = activo;
    }

    public Persona(int idpersona, int idempresa) {
        this.personaPK = new PersonaPK(idpersona, idempresa);
    }

    public PersonaPK getPersonaPK() {
        return personaPK;
    }

    public void setPersonaPK(PersonaPK personaPK) {
        this.personaPK = personaPK;
    }

    public int getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(int idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public String getNrodoc() {
        return nrodoc;
    }

    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecnac() {
        return fecnac;
    }

    public void setFecnac(Date fecnac) {
        this.fecnac = fecnac;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaPK != null ? personaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.personaPK == null && other.personaPK != null) || (this.personaPK != null && !this.personaPK.equals(other.personaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovilejb.ejb.movil.Persona[ personaPK=" + personaPK + " ]";
    }
    
}
