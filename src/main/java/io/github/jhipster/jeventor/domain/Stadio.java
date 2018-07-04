package io.github.jhipster.jeventor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Stadio.
 */
@Entity
@Table(name = "stadio")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stadio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_stadio")
    private Long idStadio;

    @Column(name = "ds_stadio")
    private String dsStadio;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Processo processo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStadio() {
        return idStadio;
    }

    public Stadio idStadio(Long idStadio) {
        this.idStadio = idStadio;
        return this;
    }

    public void setIdStadio(Long idStadio) {
        this.idStadio = idStadio;
    }

    public String getDsStadio() {
        return dsStadio;
    }

    public Stadio dsStadio(String dsStadio) {
        this.dsStadio = dsStadio;
        return this;
    }

    public void setDsStadio(String dsStadio) {
        this.dsStadio = dsStadio;
    }

    public Processo getProcesso() {
        return processo;
    }

    public Stadio processo(Processo processo) {
        this.processo = processo;
        return this;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
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
        Stadio stadio = (Stadio) o;
        if (stadio.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stadio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Stadio{" +
            "id=" + getId() +
            ", idStadio=" + getIdStadio() +
            ", dsStadio='" + getDsStadio() + "'" +
            "}";
    }
}
