package io.github.jhipster.jeventor.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Assistito.
 */
@Entity
@Table(name = "assistito")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Assistito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_assistito")
    private Long idAssistito;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAssistito() {
        return idAssistito;
    }

    public Assistito idAssistito(Long idAssistito) {
        this.idAssistito = idAssistito;
        return this;
    }

    public void setIdAssistito(Long idAssistito) {
        this.idAssistito = idAssistito;
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
        Assistito assistito = (Assistito) o;
        if (assistito.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assistito.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Assistito{" +
            "id=" + getId() +
            ", idAssistito=" + getIdAssistito() +
            "}";
    }
}
