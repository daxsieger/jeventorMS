package io.github.jhipster.jeventor.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.jeventor.domain.TipoEvento;
import io.github.jhipster.jeventor.service.TipoEventoService;
import io.github.jhipster.jeventor.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.jeventor.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TipoEvento.
 */
@RestController
@RequestMapping("/api")
public class TipoEventoResource {

    private final Logger log = LoggerFactory.getLogger(TipoEventoResource.class);

    private static final String ENTITY_NAME = "tipoEvento";

    private final TipoEventoService tipoEventoService;

    public TipoEventoResource(TipoEventoService tipoEventoService) {
        this.tipoEventoService = tipoEventoService;
    }

    /**
     * POST  /tipo-eventos : Create a new tipoEvento.
     *
     * @param tipoEvento the tipoEvento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoEvento, or with status 400 (Bad Request) if the tipoEvento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-eventos")
    @Timed
    public ResponseEntity<TipoEvento> createTipoEvento(@RequestBody TipoEvento tipoEvento) throws URISyntaxException {
        log.debug("REST request to save TipoEvento : {}", tipoEvento);
        if (tipoEvento.getId() != null) {
            throw new BadRequestAlertException("A new tipoEvento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoEvento result = tipoEventoService.save(tipoEvento);
        return ResponseEntity.created(new URI("/api/tipo-eventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-eventos : Updates an existing tipoEvento.
     *
     * @param tipoEvento the tipoEvento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoEvento,
     * or with status 400 (Bad Request) if the tipoEvento is not valid,
     * or with status 500 (Internal Server Error) if the tipoEvento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-eventos")
    @Timed
    public ResponseEntity<TipoEvento> updateTipoEvento(@RequestBody TipoEvento tipoEvento) throws URISyntaxException {
        log.debug("REST request to update TipoEvento : {}", tipoEvento);
        if (tipoEvento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoEvento result = tipoEventoService.save(tipoEvento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoEvento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-eventos : get all the tipoEventos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoEventos in body
     */
    @GetMapping("/tipo-eventos")
    @Timed
    public List<TipoEvento> getAllTipoEventos() {
        log.debug("REST request to get all TipoEventos");
        return tipoEventoService.findAll();
    }

    /**
     * GET  /tipo-eventos/:id : get the "id" tipoEvento.
     *
     * @param id the id of the tipoEvento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoEvento, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-eventos/{id}")
    @Timed
    public ResponseEntity<TipoEvento> getTipoEvento(@PathVariable Long id) {
        log.debug("REST request to get TipoEvento : {}", id);
        Optional<TipoEvento> tipoEvento = tipoEventoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoEvento);
    }

    /**
     * DELETE  /tipo-eventos/:id : delete the "id" tipoEvento.
     *
     * @param id the id of the tipoEvento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-eventos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoEvento(@PathVariable Long id) {
        log.debug("REST request to delete TipoEvento : {}", id);
        tipoEventoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
