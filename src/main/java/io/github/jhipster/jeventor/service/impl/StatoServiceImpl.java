package io.github.jhipster.jeventor.service.impl;

import io.github.jhipster.jeventor.service.StatoService;
import io.github.jhipster.jeventor.domain.Stato;
import io.github.jhipster.jeventor.repository.StatoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Stato.
 */
@Service
@Transactional
public class StatoServiceImpl implements StatoService {

    private final Logger log = LoggerFactory.getLogger(StatoServiceImpl.class);

    private final StatoRepository statoRepository;

    public StatoServiceImpl(StatoRepository statoRepository) {
        this.statoRepository = statoRepository;
    }

    /**
     * Save a stato.
     *
     * @param stato the entity to save
     * @return the persisted entity
     */
    @Override
    public Stato save(Stato stato) {
        log.debug("Request to save Stato : {}", stato);        return statoRepository.save(stato);
    }

    /**
     * Get all the statoes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Stato> findAll(Pageable pageable) {
        log.debug("Request to get all Statoes");
        return statoRepository.findAll(pageable);
    }


    /**
     * Get one stato by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Stato> findOne(Long id) {
        log.debug("Request to get Stato : {}", id);
        return statoRepository.findById(id);
    }

    /**
     * Delete the stato by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stato : {}", id);
        statoRepository.deleteById(id);
    }
}
