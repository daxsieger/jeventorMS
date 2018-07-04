package io.github.jhipster.jeventor.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Processo.
 */
@Entity
@Table(name = "processo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Processo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_processo")
    private Long idProcesso;

    @Column(name = "ds_processo")
    private String dsProcesso;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProcesso() {
        return idProcesso;
    }

    public Processo idProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
        return this;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getDsProcesso() {
        return dsProcesso;
    }

    public Processo dsProcesso(String dsProcesso) {
        this.dsProcesso = dsProcesso;
        return this;
    }

    public void setDsProcesso(String dsProcesso) {
        this.dsProcesso = dsProcesso;
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
        Processo processo = (Processo) o;
        if (processo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), processo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Processo{" +
            "id=" + getId() +
            ", idProcesso=" + getIdProcesso() +
            ", dsProcesso='" + getDsProcesso() + "'" +
            "}";
    }
}
