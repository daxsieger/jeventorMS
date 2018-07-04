package io.github.jhipster.jeventor.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.jeventor.domain.Stadio;
import io.github.jhipster.jeventor.service.StadioService;
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
 * REST controller for managing Stadio.
 */
@RestController
@RequestMapping("/api")
public class StadioResource {

    private final Logger log = LoggerFactory.getLogger(StadioResource.class);

    private static final String ENTITY_NAME = "stadio";

    private final StadioService stadioService;

    public StadioResource(StadioService stadioService) {
        this.stadioService = stadioService;
    }

    /**
     * POST  /stadios : Create a new stadio.
     *
     * @param stadio the stadio to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stadio, or with status 400 (Bad Request) if the stadio has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stadios")
    @Timed
    public ResponseEntity<Stadio> createStadio(@RequestBody Stadio stadio) throws URISyntaxException {
        log.debug("REST request to save Stadio : {}", stadio);
        if (stadio.getId() != null) {
            throw new BadRequestAlertException("A new stadio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Stadio result = stadioService.save(stadio);
        return ResponseEntity.created(new URI("/api/stadios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stadios : Updates an existing stadio.
     *
     * @param stadio the stadio to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stadio,
     * or with status 400 (Bad Request) if the stadio is not valid,
     * or with status 500 (Internal Server Error) if the stadio couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stadios")
    @Timed
    public ResponseEntity<Stadio> updateStadio(@RequestBody Stadio stadio) throws URISyntaxException {
        log.debug("REST request to update Stadio : {}", stadio);
        if (stadio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Stadio result = stadioService.save(stadio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stadio.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stadios : get all the stadios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stadios in body
     */
    @GetMapping("/stadios")
    @Timed
    public ResponseEntity<List<Stadio>> getAllStadios(Pageable pageable) {
        log.debug("REST request to get a page of Stadios");
        Page<Stadio> page = stadioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stadios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /stadios/:id : get the "id" stadio.
     *
     * @param id the id of the stadio to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stadio, or with status 404 (Not Found)
     */
    @GetMapping("/stadios/{id}")
    @Timed
    public ResponseEntity<Stadio> getStadio(@PathVariable Long id) {
        log.debug("REST request to get Stadio : {}", id);
        Optional<Stadio> stadio = stadioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stadio);
    }

    /**
     * DELETE  /stadios/:id : delete the "id" stadio.
     *
     * @param id the id of the stadio to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stadios/{id}")
    @Timed
    public ResponseEntity<Void> deleteStadio(@PathVariable Long id) {
        log.debug("REST request to delete Stadio : {}", id);
        stadioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
