package io.github.jhipster.jeventor.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.jeventor.domain.Processo;
import io.github.jhipster.jeventor.service.ProcessoService;
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
 * REST controller for managing Processo.
 */
@RestController
@RequestMapping("/api")
public class ProcessoResource {

    private final Logger log = LoggerFactory.getLogger(ProcessoResource.class);

    private static final String ENTITY_NAME = "processo";

    private final ProcessoService processoService;

    public ProcessoResource(ProcessoService processoService) {
        this.processoService = processoService;
    }

    /**
     * POST  /processos : Create a new processo.
     *
     * @param processo the processo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new processo, or with status 400 (Bad Request) if the processo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/processos")
    @Timed
    public ResponseEntity<Processo> createProcesso(@RequestBody Processo processo) throws URISyntaxException {
        log.debug("REST request to save Processo : {}", processo);
        if (processo.getId() != null) {
            throw new BadRequestAlertException("A new processo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Processo result = processoService.save(processo);
        return ResponseEntity.created(new URI("/api/processos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /processos : Updates an existing processo.
     *
     * @param processo the processo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated processo,
     * or with status 400 (Bad Request) if the processo is not valid,
     * or with status 500 (Internal Server Error) if the processo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/processos")
    @Timed
    public ResponseEntity<Processo> updateProcesso(@RequestBody Processo processo) throws URISyntaxException {
        log.debug("REST request to update Processo : {}", processo);
        if (processo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Processo result = processoService.save(processo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, processo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /processos : get all the processos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of processos in body
     */
    @GetMapping("/processos")
    @Timed
    public ResponseEntity<List<Processo>> getAllProcessos(Pageable pageable) {
        log.debug("REST request to get a page of Processos");
        Page<Processo> page = processoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/processos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /processos/:id : get the "id" processo.
     *
     * @param id the id of the processo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the processo, or with status 404 (Not Found)
     */
    @GetMapping("/processos/{id}")
    @Timed
    public ResponseEntity<Processo> getProcesso(@PathVariable Long id) {
        log.debug("REST request to get Processo : {}", id);
        Optional<Processo> processo = processoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(processo);
    }

    /**
     * DELETE  /processos/:id : delete the "id" processo.
     *
     * @param id the id of the processo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/processos/{id}")
    @Timed
    public ResponseEntity<Void> deleteProcesso(@PathVariable Long id) {
        log.debug("REST request to delete Processo : {}", id);
        processoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
