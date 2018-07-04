package io.github.jhipster.jeventor.service.impl;

import io.github.jhipster.jeventor.service.ProduttoreService;
import io.github.jhipster.jeventor.domain.Produttore;
import io.github.jhipster.jeventor.repository.ProduttoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Produttore.
 */
@Service
@Transactional
public class ProduttoreServiceImpl implements ProduttoreService {

    private final Logger log = LoggerFactory.getLogger(ProduttoreServiceImpl.class);

    private final ProduttoreRepository produttoreRepository;

    public ProduttoreServiceImpl(ProduttoreRepository produttoreRepository) {
        this.produttoreRepository = produttoreRepository;
    }

    /**
     * Save a produttore.
     *
     * @param produttore the entity to save
     * @return the persisted entity
     */
    @Override
    public Produttore save(Produttore produttore) {
        log.debug("Request to save Produttore : {}", produttore);        return produttoreRepository.save(produttore);
    }

    /**
     * Get all the produttores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Produttore> findAll(Pageable pageable) {
        log.debug("Request to get all Produttores");
        return produttoreRepository.findAll(pageable);
    }


    /**
     * Get one produttore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Produttore> findOne(Long id) {
        log.debug("Request to get Produttore : {}", id);
        return produttoreRepository.findById(id);
    }

    /**
     * Delete the produttore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Produttore : {}", id);
        produttoreRepository.deleteById(id);
    }
}
