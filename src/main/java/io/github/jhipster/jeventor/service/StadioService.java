package io.github.jhipster.jeventor.service;

import io.github.jhipster.jeventor.domain.Stadio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Stadio.
 */
public interface StadioService {

    /**
     * Save a stadio.
     *
     * @param stadio the entity to save
     * @return the persisted entity
     */
    Stadio save(Stadio stadio);

    /**
     * Get all the stadios.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Stadio> findAll(Pageable pageable);


    /**
     * Get the "id" stadio.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Stadio> findOne(Long id);

    /**
     * Delete the "id" stadio.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
