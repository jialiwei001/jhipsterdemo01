package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.ExTransport;
import com.mycompany.myapp.repository.ExTransportRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ExTransport.
 */
@RestController
@RequestMapping("/api")
public class ExTransportResource {

    private final Logger log = LoggerFactory.getLogger(ExTransportResource.class);

    private static final String ENTITY_NAME = "exTransport";

    private ExTransportRepository exTransportRepository;

    public ExTransportResource(ExTransportRepository exTransportRepository) {
        this.exTransportRepository = exTransportRepository;
    }

    /**
     * POST  /ex-transports : Create a new exTransport.
     *
     * @param exTransport the exTransport to create
     * @return the ResponseEntity with status 201 (Created) and with body the new exTransport, or with status 400 (Bad Request) if the exTransport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ex-transports")
    @Timed
    public ResponseEntity<ExTransport> createExTransport(@Valid @RequestBody ExTransport exTransport) throws URISyntaxException {
        log.debug("REST request to save ExTransport : {}", exTransport);
        if (exTransport.getId() != null) {
            throw new BadRequestAlertException("A new exTransport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExTransport result = exTransportRepository.save(exTransport);
        return ResponseEntity.created(new URI("/api/ex-transports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ex-transports : Updates an existing exTransport.
     *
     * @param exTransport the exTransport to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated exTransport,
     * or with status 400 (Bad Request) if the exTransport is not valid,
     * or with status 500 (Internal Server Error) if the exTransport couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ex-transports")
    @Timed
    public ResponseEntity<ExTransport> updateExTransport(@Valid @RequestBody ExTransport exTransport) throws URISyntaxException {
        log.debug("REST request to update ExTransport : {}", exTransport);
        if (exTransport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExTransport result = exTransportRepository.save(exTransport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, exTransport.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ex-transports : get all the exTransports.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of exTransports in body
     */
    @GetMapping("/ex-transports")
    @Timed
    public ResponseEntity<List<ExTransport>> getAllExTransports(Pageable pageable) {
        log.debug("REST request to get a page of ExTransports");
        Page<ExTransport> page = exTransportRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ex-transports");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ex-transports/:id : get the "id" exTransport.
     *
     * @param id the id of the exTransport to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the exTransport, or with status 404 (Not Found)
     */
    @GetMapping("/ex-transports/{id}")
    @Timed
    public ResponseEntity<ExTransport> getExTransport(@PathVariable Long id) {
        log.debug("REST request to get ExTransport : {}", id);
        Optional<ExTransport> exTransport = exTransportRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(exTransport);
    }

    /**
     * DELETE  /ex-transports/:id : delete the "id" exTransport.
     *
     * @param id the id of the exTransport to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ex-transports/{id}")
    @Timed
    public ResponseEntity<Void> deleteExTransport(@PathVariable Long id) {
        log.debug("REST request to delete ExTransport : {}", id);

        exTransportRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
