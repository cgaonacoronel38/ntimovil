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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tokio
 */
@Entity
@Table(name = "motivovisita", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Motivovisita.findAll", query = "SELECT m FROM Motivovisita m")})
public class Motivovisita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmotivovisita")
    private Integer idmotivovisita;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmotivovisita")
    private List<Visita> visitaList;

    public Motivovisita() {
    }

    public Motivovisita(Integer idmotivovisita) {
        this.idmotivovisita = idmotivovisita;
    }

    public Motivovisita(Integer idmotivovisita, boolean activo) {
        this.idmotivovisita = idmotivovisita;
        this.activo = activo;
    }

    public Integer getIdmotivovisita() {
        return idmotivovisita;
    }

    public void setIdmotivovisita(Integer idmotivovisita) {
        this.idmotivovisita = idmotivovisita;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Visita> getVisitaList() {
        return visitaList;
    }

    public void setVisitaList(List<Visita> visitaList) {
        this.visitaList = visitaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmotivovisita != null ? idmotivovisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motivovisita)) {
            return false;
        }
        Motivovisita other = (Motivovisita) object;
        if ((this.idmotivovisita == null && other.idmotivovisita != null) || (this.idmotivovisita != null && !this.idmotivovisita.equals(other.idmotivovisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovilejb.ejb.movil.Motivovisita[ idmotivovisita=" + idmotivovisita + " ]";
    }
    
}
