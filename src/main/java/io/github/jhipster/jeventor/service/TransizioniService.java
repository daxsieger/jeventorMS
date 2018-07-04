package io.github.jhipster.jeventor.service;

import io.github.jhipster.jeventor.domain.Transizioni;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Transizioni.
 */
public interface TransizioniService {

    /**
     * Save a transizioni.
     *
     * @param transizioni the entity to save
     * @return the persisted entity
     */
    Transizioni save(Transizioni transizioni);

    /**
     * Get all the transizionis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Transizioni> findAll(Pageable pageable);


    /**
     * Get the "id" transizioni.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Transizioni> findOne(Long id);

    /**
     * Delete the "id" transizioni.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
