package io.github.jhipster.jeventor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Evento.
 */
@Entity
@Table(name = "evento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_evento")
    private Long idEvento;

    @Column(name = "ts_evento")
    private Instant tsEvento;

    @Column(name = "note")
    private String note;

    @OneToOne
    @JoinColumn(unique = true)
    private Assistito assistito;

    @OneToOne
    @JoinColumn(unique = true)
    private TipoEvento tipo;

    @OneToOne
    @JoinColumn(unique = true)
    private Gestore gestore;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Produttore origine;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "evento_stati",
               joinColumns = @JoinColumn(name = "eventos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "statis_id", referencedColumnName = "id"))
    private Set<Stato> statis = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public Evento idEvento(Long idEvento) {
        this.idEvento = idEvento;
        return this;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Instant getTsEvento() {
        return tsEvento;
    }

    public Evento tsEvento(Instant tsEvento) {
        this.tsEvento = tsEvento;
        return this;
    }

    public void setTsEvento(Instant tsEvento) {
        this.tsEvento = tsEvento;
    }

    public String getNote() {
        return note;
    }

    public Evento note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Assistito getAssistito() {
        return assistito;
    }

    public Evento assistito(Assistito assistito) {
        this.assistito = assistito;
        return this;
    }

    public void setAssistito(Assistito assistito) {
        this.assistito = assistito;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public Evento tipo(TipoEvento tipoEvento) {
        this.tipo = tipoEvento;
        return this;
    }

    public void setTipo(TipoEvento tipoEvento) {
        this.tipo = tipoEvento;
    }

    public Gestore getGestore() {
        return gestore;
    }

    public Evento gestore(Gestore gestore) {
        this.gestore = gestore;
        return this;
    }

    public void setGestore(Gestore gestore) {
        this.gestore = gestore;
    }

    public Produttore getOrigine() {
        return origine;
    }

    public Evento origine(Produttore produttore) {
        this.origine = produttore;
        return this;
    }

    public void setOrigine(Produttore produttore) {
        this.origine = produttore;
    }

    public Set<Stato> getStatis() {
        return statis;
    }

    public Evento statis(Set<Stato> statoes) {
        this.statis = statoes;
        return this;
    }

    public Evento addStati(Stato stato) {
        this.statis.add(stato);
        stato.getEventis().add(this);
        return this;
    }

    public Evento removeStati(Stato stato) {
        this.statis.remove(stato);
        stato.getEventis().remove(this);
        return this;
    }

    public void setStatis(Set<Stato> statoes) {
        this.statis = statoes;
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
        Evento evento = (Evento) o;
        if (evento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + getId() +
            ", idEvento=" + getIdEvento() +
            ", tsEvento='" + getTsEvento() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
