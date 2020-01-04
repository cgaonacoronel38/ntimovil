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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "visita", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Visita.findAll", query = "SELECT v FROM Visita v")})
public class Visita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvisita")
    private Long idvisita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusuarioregistro")
    private int idusuarioregistro;
    @Column(name = "idusuariovisitador")
    private Integer idusuariovisitador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechavisita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavisita;
    @Size(max = 20)
    @Column(name = "documento")
    private String documento;
    @Size(max = 100)
    @Column(name = "cliente")
    private String cliente;
    @Size(max = 500)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 20)
    @Column(name = "latitud")
    private String latitud;
    @Size(max = 20)
    @Column(name = "longitud")
    private String longitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visitado")
    private boolean visitado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(name = "fechaactualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaactualizacion;
    @Column(name = "usuarioactualizacion")
    private Integer usuarioactualizacion;
    @JoinColumn(name = "idmotivovisita", referencedColumnName = "idmotivovisita")
    @ManyToOne(optional = false)
    private Motivovisita idmotivovisita;

    public Visita() {
    }

    public Visita(Long idvisita) {
        this.idvisita = idvisita;
    }

    public Visita(Long idvisita, int idusuarioregistro, Date fechavisita, boolean visitado, Date fecharegistro) {
        this.idvisita = idvisita;
        this.idusuarioregistro = idusuarioregistro;
        this.fechavisita = fechavisita;
        this.visitado = visitado;
        this.fecharegistro = fecharegistro;
    }

    public Long getIdvisita() {
        return idvisita;
    }

    public void setIdvisita(Long idvisita) {
        this.idvisita = idvisita;
    }

    public int getIdusuarioregistro() {
        return idusuarioregistro;
    }

    public void setIdusuarioregistro(int idusuarioregistro) {
        this.idusuarioregistro = idusuarioregistro;
    }

    public Integer getIdusuariovisitador() {
        return idusuariovisitador;
    }

    public void setIdusuariovisitador(Integer idusuariovisitador) {
        this.idusuariovisitador = idusuariovisitador;
    }

    public Date getFechavisita() {
        return fechavisita;
    }

    public void setFechavisita(Date fechavisita) {
        this.fechavisita = fechavisita;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Date fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public Integer getUsuarioactualizacion() {
        return usuarioactualizacion;
    }

    public void setUsuarioactualizacion(Integer usuarioactualizacion) {
        this.usuarioactualizacion = usuarioactualizacion;
    }

    public Motivovisita getIdmotivovisita() {
        return idmotivovisita;
    }

    public void setIdmotivovisita(Motivovisita idmotivovisita) {
        this.idmotivovisita = idmotivovisita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvisita != null ? idvisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.idvisita == null && other.idvisita != null) || (this.idvisita != null && !this.idvisita.equals(other.idvisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovilejb.ejb.movil.Visita[ idvisita=" + idvisita + " ]";
    }
    
}
