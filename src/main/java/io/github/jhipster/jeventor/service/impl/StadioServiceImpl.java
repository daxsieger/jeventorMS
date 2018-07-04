package io.github.jhipster.jeventor.service.impl;

import io.github.jhipster.jeventor.service.StadioService;
import io.github.jhipster.jeventor.domain.Stadio;
import io.github.jhipster.jeventor.repository.StadioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Stadio.
 */
@Service
@Transactional
public class StadioServiceImpl implements StadioService {

    private final Logger log = LoggerFactory.getLogger(StadioServiceImpl.class);

    private final StadioRepository stadioRepository;

    public StadioServiceImpl(StadioRepository stadioRepository) {
        this.stadioRepository = stadioRepository;
    }

    /**
     * Save a stadio.
     *
     * @param stadio the entity to save
     * @return the persisted entity
     */
    @Override
    public Stadio save(Stadio stadio) {
        log.debug("Request to save Stadio : {}", stadio);        return stadioRepository.save(stadio);
    }

    /**
     * Get all the stadios.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Stadio> findAll(Pageable pageable) {
        log.debug("Request to get all Stadios");
        return stadioRepository.findAll(pageable);
    }


    /**
     * Get one stadio by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Stadio> findOne(Long id) {
        log.debug("Request to get Stadio : {}", id);
        return stadioRepository.findById(id);
    }

    /**
     * Delete the stadio by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stadio : {}", id);
        stadioRepository.deleteById(id);
    }
}
