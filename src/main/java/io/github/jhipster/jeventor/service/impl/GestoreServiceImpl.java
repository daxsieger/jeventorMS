package io.github.jhipster.jeventor.service.impl;

import io.github.jhipster.jeventor.service.GestoreService;
import io.github.jhipster.jeventor.domain.Gestore;
import io.github.jhipster.jeventor.repository.GestoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Gestore.
 */
@Service
@Transactional
public class GestoreServiceImpl implements GestoreService {

    private final Logger log = LoggerFactory.getLogger(GestoreServiceImpl.class);

    private final GestoreRepository gestoreRepository;

    public GestoreServiceImpl(GestoreRepository gestoreRepository) {
        this.gestoreRepository = gestoreRepository;
    }

    /**
     * Save a gestore.
     *
     * @param gestore the entity to save
     * @return the persisted entity
     */
    @Override
    public Gestore save(Gestore gestore) {
        log.debug("Request to save Gestore : {}", gestore);        return gestoreRepository.save(gestore);
    }

    /**
     * Get all the gestores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Gestore> findAll(Pageable pageable) {
        log.debug("Request to get all Gestores");
        return gestoreRepository.findAll(pageable);
    }


    /**
     * Get one gestore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Gestore> findOne(Long id) {
        log.debug("Request to get Gestore : {}", id);
        return gestoreRepository.findById(id);
    }

    /**
     * Delete the gestore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gestore : {}", id);
        gestoreRepository.deleteById(id);
    }
}
