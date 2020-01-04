/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.model.movil;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import py.com.documenta.ntimovil.ejb.enums.ETipoEstadoUsuario;

/**
 *
 * @author tokio
 */
@Entity
@Table(name = "usuario", catalog = "nanduti", schema = "movil")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 200)
    @Column(name = "password", updatable = false)
    private String password;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "aud_fecalta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audFecalta;
    @Column(name = "aud_fecmodif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audFecmodif;
    @Column(name = "aud_idusuarioalta")
    private int audIdusuarioalta;
    @Column(name = "aud_idusuariomodif")
    private Integer audIdusuariomodif;
    @Column(name = "lastaccess")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastaccess;
    @Column(name = "idusuariodocumenta")
    private BigInteger idusuariodocumenta;
    @Size(max = 100)
    @Column(name = "correo")
    private String correo;
    @Column(name = "ultimocambiopwd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimocambiopwd;
    @Column(name = "fechabloqueoacceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechabloqueoacceso;
    @Size(max = 11)
    @Column(name = "nrocel")
    private String nrocel;
    @Column(name = "fecbaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecbaja;
    @JoinColumns({
        @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
        , @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")})
    @ManyToOne
    private Persona persona;
    @Column(name = "tipoestado", insertable = false, updatable = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ETipoEstadoUsuario tipoestado;
    @Column(name = "diasvencpwd")
    private Integer diasvencpwd;
    @Column(name = "intentosdeacceso")
    private Integer intentosdeacceso;
    @Column(name = "cantcambiopwd")
    private Integer cantcambiopwd;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String username, boolean activo, Date audFecalta, int audIdusuarioalta, ETipoEstadoUsuario tipoestado, Integer diasvencpwd, Integer intentosdeacceso, Integer cantcambiopwd) {
        this.idusuario = idusuario;
        this.username = username;
        this.activo = activo;
        this.audFecalta = audFecalta;
        this.audIdusuarioalta = audIdusuarioalta;
        this.tipoestado = tipoestado;
        this.diasvencpwd = diasvencpwd;
        this.intentosdeacceso = intentosdeacceso;
        this.cantcambiopwd = cantcambiopwd;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getAudFecmodif() {
        return audFecmodif;
    }

    public void setAudFecmodif(Date audFecmodif) {
        this.audFecmodif = audFecmodif;
    }

    public int getAudIdusuarioalta() {
        return audIdusuarioalta;
    }

    public void setAudIdusuarioalta(int audIdusuarioalta) {
        this.audIdusuarioalta = audIdusuarioalta;
    }

    public Integer getAudIdusuariomodif() {
        return audIdusuariomodif;
    }

    public void setAudIdusuariomodif(Integer audIdusuariomodif) {
        this.audIdusuariomodif = audIdusuariomodif;
    }

    public Date getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(Date lastaccess) {
        this.lastaccess = lastaccess;
    }

    public BigInteger getIdusuariodocumenta() {
        return idusuariodocumenta;
    }

    public void setIdusuariodocumenta(BigInteger idusuariodocumenta) {
        this.idusuariodocumenta = idusuariodocumenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public Date getUltimocambiopwd() {
        return ultimocambiopwd;
    }

    public void setUltimocambiopwd(Date ultimocambiopwd) {
        this.ultimocambiopwd = ultimocambiopwd;
    }


    public Date getFechabloqueoacceso() {
        return fechabloqueoacceso;
    }

    public void setFechabloqueoacceso(Date fechabloqueoacceso) {
        this.fechabloqueoacceso = fechabloqueoacceso;
    }


    public String getNrocel() {
        return nrocel;
    }

    public void setNrocel(String nrocel) {
        this.nrocel = nrocel;
    }

    public Date getFecbaja() {
        return fecbaja;
    }

    public void setFecbaja(Date fecbaja) {
        this.fecbaja = fecbaja;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovilejb.ejb.movil.Usuario[ idusuario=" + idusuario + " ]";
    }

    public ETipoEstadoUsuario getTipoestado() {
        return tipoestado;
    }

    public void setTipoestado(ETipoEstadoUsuario tipoestado) {
        this.tipoestado = tipoestado;
    }

    public Integer getDiasvencpwd() {
        return diasvencpwd;
    }

    public void setDiasvencpwd(Integer diasvencpwd) {
        this.diasvencpwd = diasvencpwd;
    }

    public Integer getIntentosdeacceso() {
        return intentosdeacceso;
    }

    public void setIntentosdeacceso(Integer intentosdeacceso) {
        this.intentosdeacceso = intentosdeacceso;
    }

    public Integer getCantcambiopwd() {
        return cantcambiopwd;
    }

    public void setCantcambiopwd(Integer cantcambiopwd) {
        this.cantcambiopwd = cantcambiopwd;
    }
}
