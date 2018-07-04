package io.github.jhipster.jeventor.web.rest;

import io.github.jhipster.jeventor.JeventorMsApp;

import io.github.jhipster.jeventor.domain.Gestore;
import io.github.jhipster.jeventor.repository.GestoreRepository;
import io.github.jhipster.jeventor.service.GestoreService;
import io.github.jhipster.jeventor.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.jeventor.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GestoreResource REST controller.
 *
 * @see GestoreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JeventorMsApp.class)
public class GestoreResourceIntTest {

    private static final Long DEFAULT_ID_GESTORE = 1L;
    private static final Long UPDATED_ID_GESTORE = 2L;

    @Autowired
    private GestoreRepository gestoreRepository;

    

    @Autowired
    private GestoreService gestoreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGestoreMockMvc;

    private Gestore gestore;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GestoreResource gestoreResource = new GestoreResource(gestoreService);
        this.restGestoreMockMvc = MockMvcBuilders.standaloneSetup(gestoreResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gestore createEntity(EntityManager em) {
        Gestore gestore = new Gestore()
            .idGestore(DEFAULT_ID_GESTORE);
        return gestore;
    }

    @Before
    public void initTest() {
        gestore = createEntity(em);
    }

    @Test
    @Transactional
    public void createGestore() throws Exception {
        int databaseSizeBeforeCreate = gestoreRepository.findAll().size();

        // Create the Gestore
        restGestoreMockMvc.perform(post("/api/gestores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gestore)))
            .andExpect(status().isCreated());

        // Validate the Gestore in the database
        List<Gestore> gestoreList = gestoreRepository.findAll();
        assertThat(gestoreList).hasSize(databaseSizeBeforeCreate + 1);
        Gestore testGestore = gestoreList.get(gestoreList.size() - 1);
        assertThat(testGestore.getIdGestore()).isEqualTo(DEFAULT_ID_GESTORE);
    }

    @Test
    @Transactional
    public void createGestoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gestoreRepository.findAll().size();

        // Create the Gestore with an existing ID
        gestore.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGestoreMockMvc.perform(post("/api/gestores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gestore)))
            .andExpect(status().isBadRequest());

        // Validate the Gestore in the database
        List<Gestore> gestoreList = gestoreRepository.findAll();
        assertThat(gestoreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGestores() throws Exception {
        // Initialize the database
        gestoreRepository.saveAndFlush(gestore);

        // Get all the gestoreList
        restGestoreMockMvc.perform(get("/api/gestores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gestore.getId().intValue())))
            .andExpect(jsonPath("$.[*].idGestore").value(hasItem(DEFAULT_ID_GESTORE.intValue())));
    }
    

    @Test
    @Transactional
    public void getGestore() throws Exception {
        // Initialize the database
        gestoreRepository.saveAndFlush(gestore);

        // Get the gestore
        restGestoreMockMvc.perform(get("/api/gestores/{id}", gestore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gestore.getId().intValue()))
            .andExpect(jsonPath("$.idGestore").value(DEFAULT_ID_GESTORE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingGestore() throws Exception {
        // Get the gestore
        restGestoreMockMvc.perform(get("/api/gestores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGestore() throws Exception {
        // Initialize the database
        gestoreService.save(gestore);

        int databaseSizeBeforeUpdate = gestoreRepository.findAll().size();

        // Update the gestore
        Gestore updatedGestore = gestoreRepository.findById(gestore.getId()).get();
        // Disconnect from session so that the updates on updatedGestore are not directly saved in db
        em.detach(updatedGestore);
        updatedGestore
            .idGestore(UPDATED_ID_GESTORE);

        restGestoreMockMvc.perform(put("/api/gestores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGestore)))
            .andExpect(status().isOk());

        // Validate the Gestore in the database
        List<Gestore> gestoreList = gestoreRepository.findAll();
        assertThat(gestoreList).hasSize(databaseSizeBeforeUpdate);
        Gestore testGestore = gestoreList.get(gestoreList.size() - 1);
        assertThat(testGestore.getIdGestore()).isEqualTo(UPDATED_ID_GESTORE);
    }

    @Test
    @Transactional
    public void updateNonExistingGestore() throws Exception {
        int databaseSizeBeforeUpdate = gestoreRepository.findAll().size();

        // Create the Gestore

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGestoreMockMvc.perform(put("/api/gestores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gestore)))
            .andExpect(status().isBadRequest());

        // Validate the Gestore in the database
        List<Gestore> gestoreList = gestoreRepository.findAll();
        assertThat(gestoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGestore() throws Exception {
        // Initialize the database
        gestoreService.save(gestore);

        int databaseSizeBeforeDelete = gestoreRepository.findAll().size();

        // Get the gestore
        restGestoreMockMvc.perform(delete("/api/gestores/{id}", gestore.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Gestore> gestoreList = gestoreRepository.findAll();
        assertThat(gestoreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gestore.class);
        Gestore gestore1 = new Gestore();
        gestore1.setId(1L);
        Gestore gestore2 = new Gestore();
        gestore2.setId(gestore1.getId());
        assertThat(gestore1).isEqualTo(gestore2);
        gestore2.setId(2L);
        assertThat(gestore1).isNotEqualTo(gestore2);
        gestore1.setId(null);
        assertThat(gestore1).isNotEqualTo(gestore2);
    }
}
