package io.github.jhipster.jeventor.web.rest;

import io.github.jhipster.jeventor.JeventorMsApp;

import io.github.jhipster.jeventor.domain.Stadio;
import io.github.jhipster.jeventor.repository.StadioRepository;
import io.github.jhipster.jeventor.service.StadioService;
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
 * Test class for the StadioResource REST controller.
 *
 * @see StadioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JeventorMsApp.class)
public class StadioResourceIntTest {

    private static final Long DEFAULT_ID_STADIO = 1L;
    private static final Long UPDATED_ID_STADIO = 2L;

    private static final String DEFAULT_DS_STADIO = "AAAAAAAAAA";
    private static final String UPDATED_DS_STADIO = "BBBBBBBBBB";

    @Autowired
    private StadioRepository stadioRepository;

    

    @Autowired
    private StadioService stadioService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStadioMockMvc;

    private Stadio stadio;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StadioResource stadioResource = new StadioResource(stadioService);
        this.restStadioMockMvc = MockMvcBuilders.standaloneSetup(stadioResource)
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
    public static Stadio createEntity(EntityManager em) {
        Stadio stadio = new Stadio()
            .idStadio(DEFAULT_ID_STADIO)
            .dsStadio(DEFAULT_DS_STADIO);
        return stadio;
    }

    @Before
    public void initTest() {
        stadio = createEntity(em);
    }

    @Test
    @Transactional
    public void createStadio() throws Exception {
        int databaseSizeBeforeCreate = stadioRepository.findAll().size();

        // Create the Stadio
        restStadioMockMvc.perform(post("/api/stadios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadio)))
            .andExpect(status().isCreated());

        // Validate the Stadio in the database
        List<Stadio> stadioList = stadioRepository.findAll();
        assertThat(stadioList).hasSize(databaseSizeBeforeCreate + 1);
        Stadio testStadio = stadioList.get(stadioList.size() - 1);
        assertThat(testStadio.getIdStadio()).isEqualTo(DEFAULT_ID_STADIO);
        assertThat(testStadio.getDsStadio()).isEqualTo(DEFAULT_DS_STADIO);
    }

    @Test
    @Transactional
    public void createStadioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stadioRepository.findAll().size();

        // Create the Stadio with an existing ID
        stadio.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStadioMockMvc.perform(post("/api/stadios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadio)))
            .andExpect(status().isBadRequest());

        // Validate the Stadio in the database
        List<Stadio> stadioList = stadioRepository.findAll();
        assertThat(stadioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStadios() throws Exception {
        // Initialize the database
        stadioRepository.saveAndFlush(stadio);

        // Get all the stadioList
        restStadioMockMvc.perform(get("/api/stadios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stadio.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStadio").value(hasItem(DEFAULT_ID_STADIO.intValue())))
            .andExpect(jsonPath("$.[*].dsStadio").value(hasItem(DEFAULT_DS_STADIO.toString())));
    }
    

    @Test
    @Transactional
    public void getStadio() throws Exception {
        // Initialize the database
        stadioRepository.saveAndFlush(stadio);

        // Get the stadio
        restStadioMockMvc.perform(get("/api/stadios/{id}", stadio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stadio.getId().intValue()))
            .andExpect(jsonPath("$.idStadio").value(DEFAULT_ID_STADIO.intValue()))
            .andExpect(jsonPath("$.dsStadio").value(DEFAULT_DS_STADIO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingStadio() throws Exception {
        // Get the stadio
        restStadioMockMvc.perform(get("/api/stadios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStadio() throws Exception {
        // Initialize the database
        stadioService.save(stadio);

        int databaseSizeBeforeUpdate = stadioRepository.findAll().size();

        // Update the stadio
        Stadio updatedStadio = stadioRepository.findById(stadio.getId()).get();
        // Disconnect from session so that the updates on updatedStadio are not directly saved in db
        em.detach(updatedStadio);
        updatedStadio
            .idStadio(UPDATED_ID_STADIO)
            .dsStadio(UPDATED_DS_STADIO);

        restStadioMockMvc.perform(put("/api/stadios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStadio)))
            .andExpect(status().isOk());

        // Validate the Stadio in the database
        List<Stadio> stadioList = stadioRepository.findAll();
        assertThat(stadioList).hasSize(databaseSizeBeforeUpdate);
        Stadio testStadio = stadioList.get(stadioList.size() - 1);
        assertThat(testStadio.getIdStadio()).isEqualTo(UPDATED_ID_STADIO);
        assertThat(testStadio.getDsStadio()).isEqualTo(UPDATED_DS_STADIO);
    }

    @Test
    @Transactional
    public void updateNonExistingStadio() throws Exception {
        int databaseSizeBeforeUpdate = stadioRepository.findAll().size();

        // Create the Stadio

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStadioMockMvc.perform(put("/api/stadios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stadio)))
            .andExpect(status().isBadRequest());

        // Validate the Stadio in the database
        List<Stadio> stadioList = stadioRepository.findAll();
        assertThat(stadioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStadio() throws Exception {
        // Initialize the database
        stadioService.save(stadio);

        int databaseSizeBeforeDelete = stadioRepository.findAll().size();

        // Get the stadio
        restStadioMockMvc.perform(delete("/api/stadios/{id}", stadio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Stadio> stadioList = stadioRepository.findAll();
        assertThat(stadioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Stadio.class);
        Stadio stadio1 = new Stadio();
        stadio1.setId(1L);
        Stadio stadio2 = new Stadio();
        stadio2.setId(stadio1.getId());
        assertThat(stadio1).isEqualTo(stadio2);
        stadio2.setId(2L);
        assertThat(stadio1).isNotEqualTo(stadio2);
        stadio1.setId(null);
        assertThat(stadio1).isNotEqualTo(stadio2);
    }
}
