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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "rastreo", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Rastreo.findAll", query = "SELECT r FROM Rastreo r")})
public class Rastreo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrastreo")
    private Long idrastreo;
    @Basic(optional = false)
    @Column(name = "idusuario")
    private int idusuario;
    @Size(max = 20)
    @Column(name = "latitud")
    private String latitud;
    @Size(max = 20)
    @Column(name = "longitud")
    private String longitud;
    @Basic(optional = false)
    @Column(name = "fecharesgistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharesgistro;

    public Rastreo() {
    }

    public Rastreo(Long idrastreo) {
        this.idrastreo = idrastreo;
    }

    public Rastreo(Long idrastreo, int idusuario, Date fecharesgistro) {
        this.idrastreo = idrastreo;
        this.idusuario = idusuario;
        this.fecharesgistro = fecharesgistro;
    }

    public Long getIdrastreo() {
        return idrastreo;
    }

    public void setIdrastreo(Long idrastreo) {
        this.idrastreo = idrastreo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Date getFecharesgistro() {
        return fecharesgistro;
    }

    public void setFecharesgistro(Date fecharesgistro) {
        this.fecharesgistro = fecharesgistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrastreo != null ? idrastreo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rastreo)) {
            return false;
        }
        Rastreo other = (Rastreo) object;
        if ((this.idrastreo == null && other.idrastreo != null) || (this.idrastreo != null && !this.idrastreo.equals(other.idrastreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovilejb.ejb.movil.Rastreo[ idrastreo=" + idrastreo + " ]";
    }
    
}
