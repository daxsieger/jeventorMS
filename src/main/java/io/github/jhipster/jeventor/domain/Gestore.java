package io.github.jhipster.jeventor.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Gestore.
 */
@Entity
@Table(name = "gestore")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Gestore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_gestore")
    private Long idGestore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGestore() {
        return idGestore;
    }

    public Gestore idGestore(Long idGestore) {
        this.idGestore = idGestore;
        return this;
    }

    public void setIdGestore(Long idGestore) {
        this.idGestore = idGestore;
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
        Gestore gestore = (Gestore) o;
        if (gestore.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gestore.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Gestore{" +
            "id=" + getId() +
            ", idGestore=" + getIdGestore() +
            "}";
    }
}
