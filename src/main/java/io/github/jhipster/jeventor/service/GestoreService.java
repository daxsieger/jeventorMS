package io.github.jhipster.jeventor.service;

import io.github.jhipster.jeventor.domain.Gestore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Gestore.
 */
public interface GestoreService {

    /**
     * Save a gestore.
     *
     * @param gestore the entity to save
     * @return the persisted entity
     */
    Gestore save(Gestore gestore);

    /**
     * Get all the gestores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Gestore> findAll(Pageable pageable);


    /**
     * Get the "id" gestore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Gestore> findOne(Long id);

    /**
     * Delete the "id" gestore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
