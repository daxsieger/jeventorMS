package io.github.jhipster.jeventor.service.impl;

import io.github.jhipster.jeventor.service.TransizioniService;
import io.github.jhipster.jeventor.domain.Transizioni;
import io.github.jhipster.jeventor.repository.TransizioniRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Transizioni.
 */
@Service
@Transactional
public class TransizioniServiceImpl implements TransizioniService {

    private final Logger log = LoggerFactory.getLogger(TransizioniServiceImpl.class);

    private final TransizioniRepository transizioniRepository;

    public TransizioniServiceImpl(TransizioniRepository transizioniRepository) {
        this.transizioniRepository = transizioniRepository;
    }

    /**
     * Save a transizioni.
     *
     * @param transizioni the entity to save
     * @return the persisted entity
     */
    @Override
    public Transizioni save(Transizioni transizioni) {
        log.debug("Request to save Transizioni : {}", transizioni);        return transizioniRepository.save(transizioni);
    }

    /**
     * Get all the transizionis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Transizioni> findAll(Pageable pageable) {
        log.debug("Request to get all Transizionis");
        return transizioniRepository.findAll(pageable);
    }


    /**
     * Get one transizioni by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Transizioni> findOne(Long id) {
        log.debug("Request to get Transizioni : {}", id);
        return transizioniRepository.findById(id);
    }

    /**
     * Delete the transizioni by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transizioni : {}", id);
        transizioniRepository.deleteById(id);
    }
}
