package io.github.jhipster.jeventor.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.jeventor.domain.Assistito;
import io.github.jhipster.jeventor.service.AssistitoService;
import io.github.jhipster.jeventor.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.jeventor.web.rest.util.HeaderUtil;
import io.github.jhipster.jeventor.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Assistito.
 */
@RestController
@RequestMapping("/api")
public class AssistitoResource {

    private final Logger log = LoggerFactory.getLogger(AssistitoResource.class);

    private static final String ENTITY_NAME = "assistito";

    private final AssistitoService assistitoService;

    public AssistitoResource(AssistitoService assistitoService) {
        this.assistitoService = assistitoService;
    }

    /**
     * POST  /assistitos : Create a new assistito.
     *
     * @param assistito the assistito to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assistito, or with status 400 (Bad Request) if the assistito has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/assistitos")
    @Timed
    public ResponseEntity<Assistito> createAssistito(@RequestBody Assistito assistito) throws URISyntaxException {
        log.debug("REST request to save Assistito : {}", assistito);
        if (assistito.getId() != null) {
            throw new BadRequestAlertException("A new assistito cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Assistito result = assistitoService.save(assistito);
        return ResponseEntity.created(new URI("/api/assistitos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /assistitos : Updates an existing assistito.
     *
     * @param assistito the assistito to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assistito,
     * or with status 400 (Bad Request) if the assistito is not valid,
     * or with status 500 (Internal Server Error) if the assistito couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/assistitos")
    @Timed
    public ResponseEntity<Assistito> updateAssistito(@RequestBody Assistito assistito) throws URISyntaxException {
        log.debug("REST request to update Assistito : {}", assistito);
        if (assistito.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Assistito result = assistitoService.save(assistito);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assistito.getId().toString()))
            .body(result);
    }

    /**
     * GET  /assistitos : get all the assistitos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assistitos in body
     */
    @GetMapping("/assistitos")
    @Timed
    public ResponseEntity<List<Assistito>> getAllAssistitos(Pageable pageable) {
        log.debug("REST request to get a page of Assistitos");
        Page<Assistito> page = assistitoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/assistitos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /assistitos/:id : get the "id" assistito.
     *
     * @param id the id of the assistito to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assistito, or with status 404 (Not Found)
     */
    @GetMapping("/assistitos/{id}")
    @Timed
    public ResponseEntity<Assistito> getAssistito(@PathVariable Long id) {
        log.debug("REST request to get Assistito : {}", id);
        Optional<Assistito> assistito = assistitoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assistito);
    }

    /**
     * DELETE  /assistitos/:id : delete the "id" assistito.
     *
     * @param id the id of the assistito to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/assistitos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssistito(@PathVariable Long id) {
        log.debug("REST request to delete Assistito : {}", id);
        assistitoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
