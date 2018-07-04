package io.github.jhipster.jeventor.service.impl;

import io.github.jhipster.jeventor.service.AssistitoService;
import io.github.jhipster.jeventor.domain.Assistito;
import io.github.jhipster.jeventor.repository.AssistitoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Assistito.
 */
@Service
@Transactional
public class AssistitoServiceImpl implements AssistitoService {

    private final Logger log = LoggerFactory.getLogger(AssistitoServiceImpl.class);

    private final AssistitoRepository assistitoRepository;

    public AssistitoServiceImpl(AssistitoRepository assistitoRepository) {
        this.assistitoRepository = assistitoRepository;
    }

    /**
     * Save a assistito.
     *
     * @param assistito the entity to save
     * @return the persisted entity
     */
    @Override
    public Assistito save(Assistito assistito) {
        log.debug("Request to save Assistito : {}", assistito);        return assistitoRepository.save(assistito);
    }

    /**
     * Get all the assistitos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Assistito> findAll(Pageable pageable) {
        log.debug("Request to get all Assistitos");
        return assistitoRepository.findAll(pageable);
    }


    /**
     * Get one assistito by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Assistito> findOne(Long id) {
        log.debug("Request to get Assistito : {}", id);
        return assistitoRepository.findById(id);
    }

    /**
     * Delete the assistito by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Assistito : {}", id);
        assistitoRepository.deleteById(id);
    }
}
