package io.github.jhipster.jeventor.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.jeventor.domain.Gestore;
import io.github.jhipster.jeventor.service.GestoreService;
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
 * REST controller for managing Gestore.
 */
@RestController
@RequestMapping("/api")
public class GestoreResource {

    private final Logger log = LoggerFactory.getLogger(GestoreResource.class);

    private static final String ENTITY_NAME = "gestore";

    private final GestoreService gestoreService;

    public GestoreResource(GestoreService gestoreService) {
        this.gestoreService = gestoreService;
    }

    /**
     * POST  /gestores : Create a new gestore.
     *
     * @param gestore the gestore to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gestore, or with status 400 (Bad Request) if the gestore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gestores")
    @Timed
    public ResponseEntity<Gestore> createGestore(@RequestBody Gestore gestore) throws URISyntaxException {
        log.debug("REST request to save Gestore : {}", gestore);
        if (gestore.getId() != null) {
            throw new BadRequestAlertException("A new gestore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gestore result = gestoreService.save(gestore);
        return ResponseEntity.created(new URI("/api/gestores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gestores : Updates an existing gestore.
     *
     * @param gestore the gestore to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gestore,
     * or with status 400 (Bad Request) if the gestore is not valid,
     * or with status 500 (Internal Server Error) if the gestore couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gestores")
    @Timed
    public ResponseEntity<Gestore> updateGestore(@RequestBody Gestore gestore) throws URISyntaxException {
        log.debug("REST request to update Gestore : {}", gestore);
        if (gestore.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Gestore result = gestoreService.save(gestore);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gestore.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gestores : get all the gestores.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gestores in body
     */
    @GetMapping("/gestores")
    @Timed
    public ResponseEntity<List<Gestore>> getAllGestores(Pageable pageable) {
        log.debug("REST request to get a page of Gestores");
        Page<Gestore> page = gestoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gestores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /gestores/:id : get the "id" gestore.
     *
     * @param id the id of the gestore to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gestore, or with status 404 (Not Found)
     */
    @GetMapping("/gestores/{id}")
    @Timed
    public ResponseEntity<Gestore> getGestore(@PathVariable Long id) {
        log.debug("REST request to get Gestore : {}", id);
        Optional<Gestore> gestore = gestoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gestore);
    }

    /**
     * DELETE  /gestores/:id : delete the "id" gestore.
     *
     * @param id the id of the gestore to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gestores/{id}")
    @Timed
    public ResponseEntity<Void> deleteGestore(@PathVariable Long id) {
        log.debug("REST request to delete Gestore : {}", id);
        gestoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
