package io.github.jhipster.jeventor.service.impl;

import io.github.jhipster.jeventor.service.TipoEventoService;
import io.github.jhipster.jeventor.domain.TipoEvento;
import io.github.jhipster.jeventor.repository.TipoEventoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing TipoEvento.
 */
@Service
@Transactional
public class TipoEventoServiceImpl implements TipoEventoService {

    private final Logger log = LoggerFactory.getLogger(TipoEventoServiceImpl.class);

    private final TipoEventoRepository tipoEventoRepository;

    public TipoEventoServiceImpl(TipoEventoRepository tipoEventoRepository) {
        this.tipoEventoRepository = tipoEventoRepository;
    }

    /**
     * Save a tipoEvento.
     *
     * @param tipoEvento the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoEvento save(TipoEvento tipoEvento) {
        log.debug("Request to save TipoEvento : {}", tipoEvento);        return tipoEventoRepository.save(tipoEvento);
    }

    /**
     * Get all the tipoEventos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TipoEvento> findAll() {
        log.debug("Request to get all TipoEventos");
        return tipoEventoRepository.findAll();
    }


    /**
     * Get one tipoEvento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoEvento> findOne(Long id) {
        log.debug("Request to get TipoEvento : {}", id);
        return tipoEventoRepository.findById(id);
    }

    /**
     * Delete the tipoEvento by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoEvento : {}", id);
        tipoEventoRepository.deleteById(id);
    }
}
