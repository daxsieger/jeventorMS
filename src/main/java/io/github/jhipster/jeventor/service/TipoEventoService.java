package io.github.jhipster.jeventor.service;

import io.github.jhipster.jeventor.domain.TipoEvento;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoEvento.
 */
public interface TipoEventoService {

    /**
     * Save a tipoEvento.
     *
     * @param tipoEvento the entity to save
     * @return the persisted entity
     */
    TipoEvento save(TipoEvento tipoEvento);

    /**
     * Get all the tipoEventos.
     *
     * @return the list of entities
     */
    List<TipoEvento> findAll();


    /**
     * Get the "id" tipoEvento.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoEvento> findOne(Long id);

    /**
     * Delete the "id" tipoEvento.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
