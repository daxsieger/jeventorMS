package io.github.jhipster.jeventor.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.jeventor.domain.Produttore;
import io.github.jhipster.jeventor.service.ProduttoreService;
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
 * REST controller for managing Produttore.
 */
@RestController
@RequestMapping("/api")
public class ProduttoreResource {

    private final Logger log = LoggerFactory.getLogger(ProduttoreResource.class);

    private static final String ENTITY_NAME = "produttore";

    private final ProduttoreService produttoreService;

    public ProduttoreResource(ProduttoreService produttoreService) {
        this.produttoreService = produttoreService;
    }

    /**
     * POST  /produttores : Create a new produttore.
     *
     * @param produttore the produttore to create
     * @return the ResponseEntity with status 201 (Created) and with body the new produttore, or with status 400 (Bad Request) if the produttore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/produttores")
    @Timed
    public ResponseEntity<Produttore> createProduttore(@RequestBody Produttore produttore) throws URISyntaxException {
        log.debug("REST request to save Produttore : {}", produttore);
        if (produttore.getId() != null) {
            throw new BadRequestAlertException("A new produttore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Produttore result = produttoreService.save(produttore);
        return ResponseEntity.created(new URI("/api/produttores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /produttores : Updates an existing produttore.
     *
     * @param produttore the produttore to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated produttore,
     * or with status 400 (Bad Request) if the produttore is not valid,
     * or with status 500 (Internal Server Error) if the produttore couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/produttores")
    @Timed
    public ResponseEntity<Produttore> updateProduttore(@RequestBody Produttore produttore) throws URISyntaxException {
        log.debug("REST request to update Produttore : {}", produttore);
        if (produttore.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Produttore result = produttoreService.save(produttore);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, produttore.getId().toString()))
            .body(result);
    }

    /**
     * GET  /produttores : get all the produttores.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of produttores in body
     */
    @GetMapping("/produttores")
    @Timed
    public ResponseEntity<List<Produttore>> getAllProduttores(Pageable pageable) {
        log.debug("REST request to get a page of Produttores");
        Page<Produttore> page = produttoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/produttores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /produttores/:id : get the "id" produttore.
     *
     * @param id the id of the produttore to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the produttore, or with status 404 (Not Found)
     */
    @GetMapping("/produttores/{id}")
    @Timed
    public ResponseEntity<Produttore> getProduttore(@PathVariable Long id) {
        log.debug("REST request to get Produttore : {}", id);
        Optional<Produttore> produttore = produttoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produttore);
    }

    /**
     * DELETE  /produttores/:id : delete the "id" produttore.
     *
     * @param id the id of the produttore to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/produttores/{id}")
    @Timed
    public ResponseEntity<Void> deleteProduttore(@PathVariable Long id) {
        log.debug("REST request to delete Produttore : {}", id);
        produttoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
