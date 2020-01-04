/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.documenta.ntimovil.ejb.model.movil;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author tokio
 */
@Embeddable
public class PersonaPK implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_seq_gen")
    @SequenceGenerator(name = "persona_seq_gen", sequenceName = "movil.persona_idpersona_seq")
    @Column(name = "idpersona")
    private Integer idpersona;
    @Column(name = "idempresa")
    private int idempresa;

    public PersonaPK() {
    }

    public PersonaPK(Integer idpersona, int idempresa) {
        this.idpersona = idpersona;
        this.idempresa = idempresa;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idpersona;
        hash += (int) idempresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaPK)) {
            return false;
        }
        PersonaPK other = (PersonaPK) object;
        if (!Objects.equals(this.idpersona, other.idpersona)) {
            return false;
        }
        if (this.idempresa != other.idempresa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.documenta.ntimovilejb.ejb.movil.PersonaPK[ idpersona=" + idpersona + ", idempresa=" + idempresa + " ]";
    }

}
