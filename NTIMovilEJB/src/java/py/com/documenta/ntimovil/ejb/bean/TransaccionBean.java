/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.bean;

import java.math.BigDecimal;

/**
 *
 * @author tokio
 */
public class TransaccionBean {  
    private BigDecimal idTransaccion;
    private String servicio;
    private String refConsulta;
    private String refPago;
    private BigDecimal gestion;
    private String fecha;
    private BigDecimal monto;
    private String anulado;
    private String tipoPago;
    private String estado;
    private String cobrador;

    public TransaccionBean() {
    }

    public BigDecimal getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(BigDecimal idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getRefConsulta() {
        return refConsulta;
    }

    public void setRefConsulta(String refConsulta) {
        this.refConsulta = refConsulta;
    }

    public String getRefPago() {
        return refPago;
    }

    public void setRefPago(String refPago) {
        this.refPago = refPago;
    }

    public BigDecimal getGestion() {
        return gestion;
    }

    public void setGestion(BigDecimal gestion) {
        this.gestion = gestion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCobrador() {
        return cobrador;
    }

    public void setCobrador(String cobrador) {
        this.cobrador = cobrador;
    }
}
