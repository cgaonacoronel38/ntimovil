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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "empresa", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idempresa")
    private Integer idempresa;
    @Size(max = 50)
    @Column(name = "razonsocial")
    private String razonsocial;
    @Size(max = 50)
    @Column(name = "representantelegal")
    private String representantelegal;
    @Size(max = 20)
    @Column(name = "ruc")
    private String ruc;
    @Size(max = 20)
    @Column(name = "rucrepresentantelegal")
    private String rucrepresentantelegal;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 50)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "localidad")
    private String localidad;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @Column(name = "aud_fecalta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audFecalta;
    @Basic(optional = false)
    @Column(name = "aud_idusuarioalta")
    private int audIdusuarioalta;
    @Column(name = "aud_fecmodif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audFecmodif;
    @Column(name = "aud_idusuariomodif")
    private Integer audIdusuariomodif;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<Persona> personaList;

    public Empresa() {
    }

    public Empresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Empresa(Integer idempresa, String descripcion, boolean activo, Date audFecalta, int audIdusuarioalta) {
        this.idempresa = idempresa;
        this.descripcion = descripcion;
        this.activo = activo;
        this.audFecalta = audFecalta;
        this.audIdusuarioalta = audIdusuarioalta;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRepresentantelegal() {
        return representantelegal;
    }

    public void setRepresentantelegal(String representantelegal) {
        this.representantelegal = representantelegal;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRucrepresentantelegal() {
        return rucrepresentantelegal;
    }

    public void setRucrepresentantelegal(String rucrepresentantelegal) {
        this.rucrepresentantelegal = rucrepresentantelegal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getAudFecalta() {
        return audFecalta;
    }

    public void setAudFecalta(Date audFecalta) {
        this.audFecalta = audFecalta;
    }

    public int getAudIdusuarioalta() {
        return audIdusuarioalta;
    }

    public void setAudIdusuarioalta(int audIdusuarioalta) {
        this.audIdusuarioalta = audIdusuarioalta;
    }

    public Date getAudFecmodif() {
        return audFecmodif;
    }

    public void setAudFecmodif(Date audFecmodif) {
        this.audFecmodif = audFecmodif;
    }

    public Integer getAudIdusuariomodif() {
        return audIdusuariomodif;
    }

    public void setAudIdusuariomodif(Integer audIdusuariomodif) {
        this.audIdusuariomodif = audIdusuariomodif;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempresa != null ? idempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idempresa == null && other.idempresa != null) || (this.idempresa != null && !this.idempresa.equals(other.idempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovilejb.ejb.movil.Empresa[ idempresa=" + idempresa + " ]";
    }
    
}
