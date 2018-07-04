package io.github.jhipster.jeventor.web.rest;

import io.github.jhipster.jeventor.JeventorMsApp;

import io.github.jhipster.jeventor.domain.Produttore;
import io.github.jhipster.jeventor.repository.ProduttoreRepository;
import io.github.jhipster.jeventor.service.ProduttoreService;
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
 * Test class for the ProduttoreResource REST controller.
 *
 * @see ProduttoreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JeventorMsApp.class)
public class ProduttoreResourceIntTest {

    private static final Long DEFAULT_ID_PRODUTTORE = 1L;
    private static final Long UPDATED_ID_PRODUTTORE = 2L;

    private static final String DEFAULT_DS_PRODUTTORE = "AAAAAAAAAA";
    private static final String UPDATED_DS_PRODUTTORE = "BBBBBBBBBB";

    @Autowired
    private ProduttoreRepository produttoreRepository;

    

    @Autowired
    private ProduttoreService produttoreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProduttoreMockMvc;

    private Produttore produttore;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProduttoreResource produttoreResource = new ProduttoreResource(produttoreService);
        this.restProduttoreMockMvc = MockMvcBuilders.standaloneSetup(produttoreResource)
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
    public static Produttore createEntity(EntityManager em) {
        Produttore produttore = new Produttore()
            .idProduttore(DEFAULT_ID_PRODUTTORE)
            .dsProduttore(DEFAULT_DS_PRODUTTORE);
        return produttore;
    }

    @Before
    public void initTest() {
        produttore = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduttore() throws Exception {
        int databaseSizeBeforeCreate = produttoreRepository.findAll().size();

        // Create the Produttore
        restProduttoreMockMvc.perform(post("/api/produttores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produttore)))
            .andExpect(status().isCreated());

        // Validate the Produttore in the database
        List<Produttore> produttoreList = produttoreRepository.findAll();
        assertThat(produttoreList).hasSize(databaseSizeBeforeCreate + 1);
        Produttore testProduttore = produttoreList.get(produttoreList.size() - 1);
        assertThat(testProduttore.getIdProduttore()).isEqualTo(DEFAULT_ID_PRODUTTORE);
        assertThat(testProduttore.getDsProduttore()).isEqualTo(DEFAULT_DS_PRODUTTORE);
    }

    @Test
    @Transactional
    public void createProduttoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = produttoreRepository.findAll().size();

        // Create the Produttore with an existing ID
        produttore.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProduttoreMockMvc.perform(post("/api/produttores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produttore)))
            .andExpect(status().isBadRequest());

        // Validate the Produttore in the database
        List<Produttore> produttoreList = produttoreRepository.findAll();
        assertThat(produttoreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProduttores() throws Exception {
        // Initialize the database
        produttoreRepository.saveAndFlush(produttore);

        // Get all the produttoreList
        restProduttoreMockMvc.perform(get("/api/produttores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produttore.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProduttore").value(hasItem(DEFAULT_ID_PRODUTTORE.intValue())))
            .andExpect(jsonPath("$.[*].dsProduttore").value(hasItem(DEFAULT_DS_PRODUTTORE.toString())));
    }
    

    @Test
    @Transactional
    public void getProduttore() throws Exception {
        // Initialize the database
        produttoreRepository.saveAndFlush(produttore);

        // Get the produttore
        restProduttoreMockMvc.perform(get("/api/produttores/{id}", produttore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(produttore.getId().intValue()))
            .andExpect(jsonPath("$.idProduttore").value(DEFAULT_ID_PRODUTTORE.intValue()))
            .andExpect(jsonPath("$.dsProduttore").value(DEFAULT_DS_PRODUTTORE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProduttore() throws Exception {
        // Get the produttore
        restProduttoreMockMvc.perform(get("/api/produttores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduttore() throws Exception {
        // Initialize the database
        produttoreService.save(produttore);

        int databaseSizeBeforeUpdate = produttoreRepository.findAll().size();

        // Update the produttore
        Produttore updatedProduttore = produttoreRepository.findById(produttore.getId()).get();
        // Disconnect from session so that the updates on updatedProduttore are not directly saved in db
        em.detach(updatedProduttore);
        updatedProduttore
            .idProduttore(UPDATED_ID_PRODUTTORE)
            .dsProduttore(UPDATED_DS_PRODUTTORE);

        restProduttoreMockMvc.perform(put("/api/produttores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduttore)))
            .andExpect(status().isOk());

        // Validate the Produttore in the database
        List<Produttore> produttoreList = produttoreRepository.findAll();
        assertThat(produttoreList).hasSize(databaseSizeBeforeUpdate);
        Produttore testProduttore = produttoreList.get(produttoreList.size() - 1);
        assertThat(testProduttore.getIdProduttore()).isEqualTo(UPDATED_ID_PRODUTTORE);
        assertThat(testProduttore.getDsProduttore()).isEqualTo(UPDATED_DS_PRODUTTORE);
    }

    @Test
    @Transactional
    public void updateNonExistingProduttore() throws Exception {
        int databaseSizeBeforeUpdate = produttoreRepository.findAll().size();

        // Create the Produttore

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProduttoreMockMvc.perform(put("/api/produttores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produttore)))
            .andExpect(status().isBadRequest());

        // Validate the Produttore in the database
        List<Produttore> produttoreList = produttoreRepository.findAll();
        assertThat(produttoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduttore() throws Exception {
        // Initialize the database
        produttoreService.save(produttore);

        int databaseSizeBeforeDelete = produttoreRepository.findAll().size();

        // Get the produttore
        restProduttoreMockMvc.perform(delete("/api/produttores/{id}", produttore.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Produttore> produttoreList = produttoreRepository.findAll();
        assertThat(produttoreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Produttore.class);
        Produttore produttore1 = new Produttore();
        produttore1.setId(1L);
        Produttore produttore2 = new Produttore();
        produttore2.setId(produttore1.getId());
        assertThat(produttore1).isEqualTo(produttore2);
        produttore2.setId(2L);
        assertThat(produttore1).isNotEqualTo(produttore2);
        produttore1.setId(null);
        assertThat(produttore1).isNotEqualTo(produttore2);
    }
}
