package io.github.jhipster.jeventor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Transizioni.
 */
@Entity
@Table(name = "transizioni")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transizioni implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_transizione")
    private Long idTransizione;

    @Column(name = "ds_transizione")
    private String dsTransizione;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Processo processo;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Stadio stadioIniziale;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Stadio stadioFinale;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTransizione() {
        return idTransizione;
    }

    public Transizioni idTransizione(Long idTransizione) {
        this.idTransizione = idTransizione;
        return this;
    }

    public void setIdTransizione(Long idTransizione) {
        this.idTransizione = idTransizione;
    }

    public String getDsTransizione() {
        return dsTransizione;
    }

    public Transizioni dsTransizione(String dsTransizione) {
        this.dsTransizione = dsTransizione;
        return this;
    }

    public void setDsTransizione(String dsTransizione) {
        this.dsTransizione = dsTransizione;
    }

    public Processo getProcesso() {
        return processo;
    }

    public Transizioni processo(Processo processo) {
        this.processo = processo;
        return this;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Stadio getStadioIniziale() {
        return stadioIniziale;
    }

    public Transizioni stadioIniziale(Stadio stadio) {
        this.stadioIniziale = stadio;
        return this;
    }

    public void setStadioIniziale(Stadio stadio) {
        this.stadioIniziale = stadio;
    }

    public Stadio getStadioFinale() {
        return stadioFinale;
    }

    public Transizioni stadioFinale(Stadio stadio) {
        this.stadioFinale = stadio;
        return this;
    }

    public void setStadioFinale(Stadio stadio) {
        this.stadioFinale = stadio;
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
        Transizioni transizioni = (Transizioni) o;
        if (transizioni.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transizioni.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transizioni{" +
            "id=" + getId() +
            ", idTransizione=" + getIdTransizione() +
            ", dsTransizione='" + getDsTransizione() + "'" +
            "}";
    }
}
