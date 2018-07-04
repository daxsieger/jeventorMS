package io.github.jhipster.jeventor.service.impl;

import io.github.jhipster.jeventor.service.ProcessoService;
import io.github.jhipster.jeventor.domain.Processo;
import io.github.jhipster.jeventor.repository.ProcessoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Processo.
 */
@Service
@Transactional
public class ProcessoServiceImpl implements ProcessoService {

    private final Logger log = LoggerFactory.getLogger(ProcessoServiceImpl.class);

    private final ProcessoRepository processoRepository;

    public ProcessoServiceImpl(ProcessoRepository processoRepository) {
        this.processoRepository = processoRepository;
    }

    /**
     * Save a processo.
     *
     * @param processo the entity to save
     * @return the persisted entity
     */
    @Override
    public Processo save(Processo processo) {
        log.debug("Request to save Processo : {}", processo);        return processoRepository.save(processo);
    }

    /**
     * Get all the processos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Processo> findAll(Pageable pageable) {
        log.debug("Request to get all Processos");
        return processoRepository.findAll(pageable);
    }


    /**
     * Get one processo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Processo> findOne(Long id) {
        log.debug("Request to get Processo : {}", id);
        return processoRepository.findById(id);
    }

    /**
     * Delete the processo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Processo : {}", id);
        processoRepository.deleteById(id);
    }
}
