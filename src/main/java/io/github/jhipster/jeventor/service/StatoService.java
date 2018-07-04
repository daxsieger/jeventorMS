package io.github.jhipster.jeventor.service;

import io.github.jhipster.jeventor.domain.Stato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Stato.
 */
public interface StatoService {

    /**
     * Save a stato.
     *
     * @param stato the entity to save
     * @return the persisted entity
     */
    Stato save(Stato stato);

    /**
     * Get all the statoes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Stato> findAll(Pageable pageable);


    /**
     * Get the "id" stato.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Stato> findOne(Long id);

    /**
     * Delete the "id" stato.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
