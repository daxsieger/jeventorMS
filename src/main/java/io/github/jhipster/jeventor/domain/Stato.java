package io.github.jhipster.jeventor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Stato.
 */
@Entity
@Table(name = "stato")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_stadio")
    private Long idStadio;

    @Column(name = "ds_stadio")
    private String dsStadio;

    @Column(name = "ts_cambio_stato")
    private Instant tsCambioStato;

    @OneToOne
    @JoinColumn(unique = true)
    private Stadio stadio;

    @ManyToMany(mappedBy = "statis")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Evento> eventis = new HashSet<>();

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

    public Stato idStadio(Long idStadio) {
        this.idStadio = idStadio;
        return this;
    }

    public void setIdStadio(Long idStadio) {
        this.idStadio = idStadio;
    }

    public String getDsStadio() {
        return dsStadio;
    }

    public Stato dsStadio(String dsStadio) {
        this.dsStadio = dsStadio;
        return this;
    }

    public void setDsStadio(String dsStadio) {
        this.dsStadio = dsStadio;
    }

    public Instant getTsCambioStato() {
        return tsCambioStato;
    }

    public Stato tsCambioStato(Instant tsCambioStato) {
        this.tsCambioStato = tsCambioStato;
        return this;
    }

    public void setTsCambioStato(Instant tsCambioStato) {
        this.tsCambioStato = tsCambioStato;
    }

    public Stadio getStadio() {
        return stadio;
    }

    public Stato stadio(Stadio stadio) {
        this.stadio = stadio;
        return this;
    }

    public void setStadio(Stadio stadio) {
        this.stadio = stadio;
    }

    public Set<Evento> getEventis() {
        return eventis;
    }

    public Stato eventis(Set<Evento> eventos) {
        this.eventis = eventos;
        return this;
    }

    public Stato addEventi(Evento evento) {
        this.eventis.add(evento);
        evento.getStatis().add(this);
        return this;
    }

    public Stato removeEventi(Evento evento) {
        this.eventis.remove(evento);
        evento.getStatis().remove(this);
        return this;
    }

    public void setEventis(Set<Evento> eventos) {
        this.eventis = eventos;
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
        Stato stato = (Stato) o;
        if (stato.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Stato{" +
            "id=" + getId() +
            ", idStadio=" + getIdStadio() +
            ", dsStadio='" + getDsStadio() + "'" +
            ", tsCambioStato='" + getTsCambioStato() + "'" +
            "}";
    }
}
