package io.github.jhipster.jeventor.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.jeventor.domain.Transizioni;
import io.github.jhipster.jeventor.service.TransizioniService;
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
 * REST controller for managing Transizioni.
 */
@RestController
@RequestMapping("/api")
public class TransizioniResource {

    private final Logger log = LoggerFactory.getLogger(TransizioniResource.class);

    private static final String ENTITY_NAME = "transizioni";

    private final TransizioniService transizioniService;

    public TransizioniResource(TransizioniService transizioniService) {
        this.transizioniService = transizioniService;
    }

    /**
     * POST  /transizionis : Create a new transizioni.
     *
     * @param transizioni the transizioni to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transizioni, or with status 400 (Bad Request) if the transizioni has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transizionis")
    @Timed
    public ResponseEntity<Transizioni> createTransizioni(@RequestBody Transizioni transizioni) throws URISyntaxException {
        log.debug("REST request to save Transizioni : {}", transizioni);
        if (transizioni.getId() != null) {
            throw new BadRequestAlertException("A new transizioni cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transizioni result = transizioniService.save(transizioni);
        return ResponseEntity.created(new URI("/api/transizionis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transizionis : Updates an existing transizioni.
     *
     * @param transizioni the transizioni to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transizioni,
     * or with status 400 (Bad Request) if the transizioni is not valid,
     * or with status 500 (Internal Server Error) if the transizioni couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transizionis")
    @Timed
    public ResponseEntity<Transizioni> updateTransizioni(@RequestBody Transizioni transizioni) throws URISyntaxException {
        log.debug("REST request to update Transizioni : {}", transizioni);
        if (transizioni.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Transizioni result = transizioniService.save(transizioni);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transizioni.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transizionis : get all the transizionis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of transizionis in body
     */
    @GetMapping("/transizionis")
    @Timed
    public ResponseEntity<List<Transizioni>> getAllTransizionis(Pageable pageable) {
        log.debug("REST request to get a page of Transizionis");
        Page<Transizioni> page = transizioniService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transizionis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /transizionis/:id : get the "id" transizioni.
     *
     * @param id the id of the transizioni to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transizioni, or with status 404 (Not Found)
     */
    @GetMapping("/transizionis/{id}")
    @Timed
    public ResponseEntity<Transizioni> getTransizioni(@PathVariable Long id) {
        log.debug("REST request to get Transizioni : {}", id);
        Optional<Transizioni> transizioni = transizioniService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transizioni);
    }

    /**
     * DELETE  /transizionis/:id : delete the "id" transizioni.
     *
     * @param id the id of the transizioni to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transizionis/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransizioni(@PathVariable Long id) {
        log.debug("REST request to delete Transizioni : {}", id);
        transizioniService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
