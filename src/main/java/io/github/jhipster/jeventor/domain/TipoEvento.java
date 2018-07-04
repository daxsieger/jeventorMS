package io.github.jhipster.jeventor.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TipoEvento.
 */
@Entity
@Table(name = "tipo_evento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_tipo_evento")
    private Long idTipoEvento;

    @Column(name = "ds_tipo_evento")
    private String dsTipoEvento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTipoEvento() {
        return idTipoEvento;
    }

    public TipoEvento idTipoEvento(Long idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
        return this;
    }

    public void setIdTipoEvento(Long idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public String getDsTipoEvento() {
        return dsTipoEvento;
    }

    public TipoEvento dsTipoEvento(String dsTipoEvento) {
        this.dsTipoEvento = dsTipoEvento;
        return this;
    }

    public void setDsTipoEvento(String dsTipoEvento) {
        this.dsTipoEvento = dsTipoEvento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TipoEvento tipoEvento = (TipoEvento) o;
        if (tipoEvento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoEvento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoEvento{" +
            "id=" + getId() +
            ", idTipoEvento=" + getIdTipoEvento() +
            ", dsTipoEvento='" + getDsTipoEvento() + "'" +
            "}";
    }
}
