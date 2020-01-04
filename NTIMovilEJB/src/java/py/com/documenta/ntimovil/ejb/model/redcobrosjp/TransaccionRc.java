/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.model.redcobrosjp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "transaccion_rc", catalog = "documenta", schema = "redcobrosjp")
@NamedQueries({
    @NamedQuery(name = "TransaccionRc.findAll", query = "SELECT t FROM TransaccionRc t")})
public class TransaccionRc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_transaccion")
    private Long idTransaccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_boleta")
    private int nroBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulado")
    private Character anulado;
    @Size(max = 3000)
    @Column(name = "referencia_pago")
    private String referenciaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "comision")
    private Double comision;
    @Column(name = "id_corte")
    private BigInteger idCorte;
    @Size(max = 3000)
    @Column(name = "referencia_consulta")
    private String referenciaConsulta;
    @Column(name = "notificado")
    private Character notificado;
    @Column(name = "migrado")
    private Character migrado;
    @Column(name = "tasa")
    private Integer tasa;
    @Size(max = 255)
    @Column(name = "server")
    private String server;

    public TransaccionRc() {
    }

    public TransaccionRc(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public TransaccionRc(Long idTransaccion, int nroBoleta, Character anulado, Date fechaIngreso) {
        this.idTransaccion = idTransaccion;
        this.nroBoleta = nroBoleta;
        this.anulado = anulado;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getNroBoleta() {
        return nroBoleta;
    }

    public void setNroBoleta(int nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    public Character getAnulado() {
        return anulado;
    }

    public void setAnulado(Character anulado) {
        this.anulado = anulado;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public BigInteger getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(BigInteger idCorte) {
        this.idCorte = idCorte;
    }

    public String getReferenciaConsulta() {
        return referenciaConsulta;
    }

    public void setReferenciaConsulta(String referenciaConsulta) {
        this.referenciaConsulta = referenciaConsulta;
    }

    public Character getNotificado() {
        return notificado;
    }

    public void setNotificado(Character notificado) {
        this.notificado = notificado;
    }

    public Character getMigrado() {
        return migrado;
    }

    public void setMigrado(Character migrado) {
        this.migrado = migrado;
    }

    public Integer getTasa() {
        return tasa;
    }

    public void setTasa(Integer tasa) {
        this.tasa = tasa;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionRc)) {
            return false;
        }
        TransaccionRc other = (TransaccionRc) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovil.ejb.model.redcobrosjp.TransaccionRc[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
