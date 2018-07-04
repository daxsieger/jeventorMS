package io.github.jhipster.jeventor.web.rest;

import io.github.jhipster.jeventor.JeventorMsApp;

import io.github.jhipster.jeventor.domain.Transizioni;
import io.github.jhipster.jeventor.repository.TransizioniRepository;
import io.github.jhipster.jeventor.service.TransizioniService;
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
 * Test class for the TransizioniResource REST controller.
 *
 * @see TransizioniResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JeventorMsApp.class)
public class TransizioniResourceIntTest {

    private static final Long DEFAULT_ID_TRANSIZIONE = 1L;
    private static final Long UPDATED_ID_TRANSIZIONE = 2L;

    private static final String DEFAULT_DS_TRANSIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DS_TRANSIZIONE = "BBBBBBBBBB";

    @Autowired
    private TransizioniRepository transizioniRepository;

    

    @Autowired
    private TransizioniService transizioniService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransizioniMockMvc;

    private Transizioni transizioni;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransizioniResource transizioniResource = new TransizioniResource(transizioniService);
        this.restTransizioniMockMvc = MockMvcBuilders.standaloneSetup(transizioniResource)
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
    public static Transizioni createEntity(EntityManager em) {
        Transizioni transizioni = new Transizioni()
            .idTransizione(DEFAULT_ID_TRANSIZIONE)
            .dsTransizione(DEFAULT_DS_TRANSIZIONE);
        return transizioni;
    }

    @Before
    public void initTest() {
        transizioni = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransizioni() throws Exception {
        int databaseSizeBeforeCreate = transizioniRepository.findAll().size();

        // Create the Transizioni
        restTransizioniMockMvc.perform(post("/api/transizionis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transizioni)))
            .andExpect(status().isCreated());

        // Validate the Transizioni in the database
        List<Transizioni> transizioniList = transizioniRepository.findAll();
        assertThat(transizioniList).hasSize(databaseSizeBeforeCreate + 1);
        Transizioni testTransizioni = transizioniList.get(transizioniList.size() - 1);
        assertThat(testTransizioni.getIdTransizione()).isEqualTo(DEFAULT_ID_TRANSIZIONE);
        assertThat(testTransizioni.getDsTransizione()).isEqualTo(DEFAULT_DS_TRANSIZIONE);
    }

    @Test
    @Transactional
    public void createTransizioniWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transizioniRepository.findAll().size();

        // Create the Transizioni with an existing ID
        transizioni.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransizioniMockMvc.perform(post("/api/transizionis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transizioni)))
            .andExpect(status().isBadRequest());

        // Validate the Transizioni in the database
        List<Transizioni> transizioniList = transizioniRepository.findAll();
        assertThat(transizioniList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTransizionis() throws Exception {
        // Initialize the database
        transizioniRepository.saveAndFlush(transizioni);

        // Get all the transizioniList
        restTransizioniMockMvc.perform(get("/api/transizionis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transizioni.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTransizione").value(hasItem(DEFAULT_ID_TRANSIZIONE.intValue())))
            .andExpect(jsonPath("$.[*].dsTransizione").value(hasItem(DEFAULT_DS_TRANSIZIONE.toString())));
    }
    

    @Test
    @Transactional
    public void getTransizioni() throws Exception {
        // Initialize the database
        transizioniRepository.saveAndFlush(transizioni);

        // Get the transizioni
        restTransizioniMockMvc.perform(get("/api/transizionis/{id}", transizioni.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transizioni.getId().intValue()))
            .andExpect(jsonPath("$.idTransizione").value(DEFAULT_ID_TRANSIZIONE.intValue()))
            .andExpect(jsonPath("$.dsTransizione").value(DEFAULT_DS_TRANSIZIONE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTransizioni() throws Exception {
        // Get the transizioni
        restTransizioniMockMvc.perform(get("/api/transizionis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransizioni() throws Exception {
        // Initialize the database
        transizioniService.save(transizioni);

        int databaseSizeBeforeUpdate = transizioniRepository.findAll().size();

        // Update the transizioni
        Transizioni updatedTransizioni = transizioniRepository.findById(transizioni.getId()).get();
        // Disconnect from session so that the updates on updatedTransizioni are not directly saved in db
        em.detach(updatedTransizioni);
        updatedTransizioni
            .idTransizione(UPDATED_ID_TRANSIZIONE)
            .dsTransizione(UPDATED_DS_TRANSIZIONE);

        restTransizioniMockMvc.perform(put("/api/transizionis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransizioni)))
            .andExpect(status().isOk());

        // Validate the Transizioni in the database
        List<Transizioni> transizioniList = transizioniRepository.findAll();
        assertThat(transizioniList).hasSize(databaseSizeBeforeUpdate);
        Transizioni testTransizioni = transizioniList.get(transizioniList.size() - 1);
        assertThat(testTransizioni.getIdTransizione()).isEqualTo(UPDATED_ID_TRANSIZIONE);
        assertThat(testTransizioni.getDsTransizione()).isEqualTo(UPDATED_DS_TRANSIZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransizioni() throws Exception {
        int databaseSizeBeforeUpdate = transizioniRepository.findAll().size();

        // Create the Transizioni

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTransizioniMockMvc.perform(put("/api/transizionis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transizioni)))
            .andExpect(status().isBadRequest());

        // Validate the Transizioni in the database
        List<Transizioni> transizioniList = transizioniRepository.findAll();
        assertThat(transizioniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransizioni() throws Exception {
        // Initialize the database
        transizioniService.save(transizioni);

        int databaseSizeBeforeDelete = transizioniRepository.findAll().size();

        // Get the transizioni
        restTransizioniMockMvc.perform(delete("/api/transizionis/{id}", transizioni.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Transizioni> transizioniList = transizioniRepository.findAll();
        assertThat(transizioniList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transizioni.class);
        Transizioni transizioni1 = new Transizioni();
        transizioni1.setId(1L);
        Transizioni transizioni2 = new Transizioni();
        transizioni2.setId(transizioni1.getId());
        assertThat(transizioni1).isEqualTo(transizioni2);
        transizioni2.setId(2L);
        assertThat(transizioni1).isNotEqualTo(transizioni2);
        transizioni1.setId(null);
        assertThat(transizioni1).isNotEqualTo(transizioni2);
    }
}
