package io.github.jhipster.jeventor.service;

import io.github.jhipster.jeventor.domain.Assistito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Assistito.
 */
public interface AssistitoService {

    /**
     * Save a assistito.
     *
     * @param assistito the entity to save
     * @return the persisted entity
     */
    Assistito save(Assistito assistito);

    /**
     * Get all the assistitos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Assistito> findAll(Pageable pageable);


    /**
     * Get the "id" assistito.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Assistito> findOne(Long id);

    /**
     * Delete the "id" assistito.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
