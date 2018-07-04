package io.github.jhipster.jeventor.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Produttore.
 */
@Entity
@Table(name = "produttore")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Produttore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_produttore")
    private Long idProduttore;

    @Column(name = "ds_produttore")
    private String dsProduttore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduttore() {
        return idProduttore;
    }

    public Produttore idProduttore(Long idProduttore) {
        this.idProduttore = idProduttore;
        return this;
    }

    public void setIdProduttore(Long idProduttore) {
        this.idProduttore = idProduttore;
    }

    public String getDsProduttore() {
        return dsProduttore;
    }

    public Produttore dsProduttore(String dsProduttore) {
        this.dsProduttore = dsProduttore;
        return this;
    }

    public void setDsProduttore(String dsProduttore) {
        this.dsProduttore = dsProduttore;
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
        Produttore produttore = (Produttore) o;
        if (produttore.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produttore.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Produttore{" +
            "id=" + getId() +
            ", idProduttore=" + getIdProduttore() +
            ", dsProduttore='" + getDsProduttore() + "'" +
            "}";
    }
}
