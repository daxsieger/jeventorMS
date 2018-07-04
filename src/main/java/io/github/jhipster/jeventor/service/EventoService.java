package io.github.jhipster.jeventor.service;

import io.github.jhipster.jeventor.domain.Evento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Evento.
 */
public interface EventoService {

    /**
     * Save a evento.
     *
     * @param evento the entity to save
     * @return the persisted entity
     */
    Evento save(Evento evento);

    /**
     * Get all the eventos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Evento> findAll(Pageable pageable);

    /**
     * Get all the Evento with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Evento> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" evento.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Evento> findOne(Long id);

    /**
     * Delete the "id" evento.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
