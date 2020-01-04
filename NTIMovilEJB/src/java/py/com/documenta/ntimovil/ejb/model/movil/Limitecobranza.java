/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.model.movil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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

/**
 *
 * @author tokio
 */
@Entity
@Table(name = "limitecobranza", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Limitecobranza.findAll", query = "SELECT l FROM Limitecobranza l")})
public class Limitecobranza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlimitecobranza")
    private Integer idlimitecobranza;
    @JoinColumn(name = "idusuariocobrador", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Column(name = "diasoperacion")
    private short diasoperacion;
    @Column(name = "montooperacion")
    private BigDecimal montooperacion;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(name = "usuarioregistro")
    private int usuarioregistro;
    @Column(name = "fechaajuste")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaajuste;
    @Column(name = "usuarioajuste")
    private int usuarioajuste;

    public Limitecobranza() {
    }

    public Limitecobranza(Integer idlimitecobranza) {
        this.idlimitecobranza = idlimitecobranza;
    }

    public Limitecobranza(Integer idlimitecobranza, short diasoperacion, BigDecimal montooperacion, Date fecharegistro, int usuarioregistro, Date fechaajuste, int usuarioajuste) {
        this.idlimitecobranza = idlimitecobranza;
        this.diasoperacion = diasoperacion;
        this.montooperacion = montooperacion;
        this.fecharegistro = fecharegistro;
        this.usuarioregistro = usuarioregistro;
        this.fechaajuste = fechaajuste;
        this.usuarioajuste = usuarioajuste;
    }

    public Integer getIdlimitecobranza() {
        return idlimitecobranza;
    }

    public void setIdlimitecobranza(Integer idlimitecobranza) {
        this.idlimitecobranza = idlimitecobranza;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public short getDiasoperacion() {
        return diasoperacion;
    }

    public void setDiasoperacion(short diasoperacion) {
        this.diasoperacion = diasoperacion;
    }

    public BigDecimal getMontooperacion() {
        return montooperacion;
    }

    public void setMontooperacion(BigDecimal montooperacion) {
        this.montooperacion = montooperacion;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public int getUsuarioregistro() {
        return usuarioregistro;
    }

    public void setUsuarioregistro(int usuarioregistro) {
        this.usuarioregistro = usuarioregistro;
    }

    public Date getFechaajuste() {
        return fechaajuste;
    }

    public void setFechaajuste(Date fechaajuste) {
        this.fechaajuste = fechaajuste;
    }

    public int getUsuarioajuste() {
        return usuarioajuste;
    }

    public void setUsuarioajuste(int usuarioajuste) {
        this.usuarioajuste = usuarioajuste;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlimitecobranza != null ? idlimitecobranza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Limitecobranza)) {
            return false;
        }
        Limitecobranza other = (Limitecobranza) object;
        if ((this.idlimitecobranza == null && other.idlimitecobranza != null) || (this.idlimitecobranza != null && !this.idlimitecobranza.equals(other.idlimitecobranza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovil.ejb.model.movil.Limitecobranza[ idlimitecobranza=" + idlimitecobranza + " ]";
    }
    
}
