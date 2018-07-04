package io.github.jhipster.jeventor.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.jeventor.domain.Stato;
import io.github.jhipster.jeventor.service.StatoService;
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
 * REST controller for managing Stato.
 */
@RestController
@RequestMapping("/api")
public class StatoResource {

    private final Logger log = LoggerFactory.getLogger(StatoResource.class);

    private static final String ENTITY_NAME = "stato";

    private final StatoService statoService;

    public StatoResource(StatoService statoService) {
        this.statoService = statoService;
    }

    /**
     * POST  /statoes : Create a new stato.
     *
     * @param stato the stato to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stato, or with status 400 (Bad Request) if the stato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/statoes")
    @Timed
    public ResponseEntity<Stato> createStato(@RequestBody Stato stato) throws URISyntaxException {
        log.debug("REST request to save Stato : {}", stato);
        if (stato.getId() != null) {
            throw new BadRequestAlertException("A new stato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Stato result = statoService.save(stato);
        return ResponseEntity.created(new URI("/api/statoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /statoes : Updates an existing stato.
     *
     * @param stato the stato to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stato,
     * or with status 400 (Bad Request) if the stato is not valid,
     * or with status 500 (Internal Server Error) if the stato couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/statoes")
    @Timed
    public ResponseEntity<Stato> updateStato(@RequestBody Stato stato) throws URISyntaxException {
        log.debug("REST request to update Stato : {}", stato);
        if (stato.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Stato result = statoService.save(stato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stato.getId().toString()))
            .body(result);
    }

    /**
     * GET  /statoes : get all the statoes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of statoes in body
     */
    @GetMapping("/statoes")
    @Timed
    public ResponseEntity<List<Stato>> getAllStatoes(Pageable pageable) {
        log.debug("REST request to get a page of Statoes");
        Page<Stato> page = statoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/statoes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /statoes/:id : get the "id" stato.
     *
     * @param id the id of the stato to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stato, or with status 404 (Not Found)
     */
    @GetMapping("/statoes/{id}")
    @Timed
    public ResponseEntity<Stato> getStato(@PathVariable Long id) {
        log.debug("REST request to get Stato : {}", id);
        Optional<Stato> stato = statoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stato);
    }

    /**
     * DELETE  /statoes/:id : delete the "id" stato.
     *
     * @param id the id of the stato to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/statoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteStato(@PathVariable Long id) {
        log.debug("REST request to delete Stato : {}", id);
        statoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
