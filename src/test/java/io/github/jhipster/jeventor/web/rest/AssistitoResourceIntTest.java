package io.github.jhipster.jeventor.web.rest;

import io.github.jhipster.jeventor.JeventorMsApp;

import io.github.jhipster.jeventor.domain.Assistito;
import io.github.jhipster.jeventor.repository.AssistitoRepository;
import io.github.jhipster.jeventor.service.AssistitoService;
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
 * Test class for the AssistitoResource REST controller.
 *
 * @see AssistitoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JeventorMsApp.class)
public class AssistitoResourceIntTest {

    private static final Long DEFAULT_ID_ASSISTITO = 1L;
    private static final Long UPDATED_ID_ASSISTITO = 2L;

    @Autowired
    private AssistitoRepository assistitoRepository;

    

    @Autowired
    private AssistitoService assistitoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssistitoMockMvc;

    private Assistito assistito;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssistitoResource assistitoResource = new AssistitoResource(assistitoService);
        this.restAssistitoMockMvc = MockMvcBuilders.standaloneSetup(assistitoResource)
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
    public static Assistito createEntity(EntityManager em) {
        Assistito assistito = new Assistito()
            .idAssistito(DEFAULT_ID_ASSISTITO);
        return assistito;
    }

    @Before
    public void initTest() {
        assistito = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssistito() throws Exception {
        int databaseSizeBeforeCreate = assistitoRepository.findAll().size();

        // Create the Assistito
        restAssistitoMockMvc.perform(post("/api/assistitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assistito)))
            .andExpect(status().isCreated());

        // Validate the Assistito in the database
        List<Assistito> assistitoList = assistitoRepository.findAll();
        assertThat(assistitoList).hasSize(databaseSizeBeforeCreate + 1);
        Assistito testAssistito = assistitoList.get(assistitoList.size() - 1);
        assertThat(testAssistito.getIdAssistito()).isEqualTo(DEFAULT_ID_ASSISTITO);
    }

    @Test
    @Transactional
    public void createAssistitoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assistitoRepository.findAll().size();

        // Create the Assistito with an existing ID
        assistito.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssistitoMockMvc.perform(post("/api/assistitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assistito)))
            .andExpect(status().isBadRequest());

        // Validate the Assistito in the database
        List<Assistito> assistitoList = assistitoRepository.findAll();
        assertThat(assistitoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAssistitos() throws Exception {
        // Initialize the database
        assistitoRepository.saveAndFlush(assistito);

        // Get all the assistitoList
        restAssistitoMockMvc.perform(get("/api/assistitos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assistito.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAssistito").value(hasItem(DEFAULT_ID_ASSISTITO.intValue())));
    }
    

    @Test
    @Transactional
    public void getAssistito() throws Exception {
        // Initialize the database
        assistitoRepository.saveAndFlush(assistito);

        // Get the assistito
        restAssistitoMockMvc.perform(get("/api/assistitos/{id}", assistito.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(assistito.getId().intValue()))
            .andExpect(jsonPath("$.idAssistito").value(DEFAULT_ID_ASSISTITO.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAssistito() throws Exception {
        // Get the assistito
        restAssistitoMockMvc.perform(get("/api/assistitos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssistito() throws Exception {
        // Initialize the database
        assistitoService.save(assistito);

        int databaseSizeBeforeUpdate = assistitoRepository.findAll().size();

        // Update the assistito
        Assistito updatedAssistito = assistitoRepository.findById(assistito.getId()).get();
        // Disconnect from session so that the updates on updatedAssistito are not directly saved in db
        em.detach(updatedAssistito);
        updatedAssistito
            .idAssistito(UPDATED_ID_ASSISTITO);

        restAssistitoMockMvc.perform(put("/api/assistitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssistito)))
            .andExpect(status().isOk());

        // Validate the Assistito in the database
        List<Assistito> assistitoList = assistitoRepository.findAll();
        assertThat(assistitoList).hasSize(databaseSizeBeforeUpdate);
        Assistito testAssistito = assistitoList.get(assistitoList.size() - 1);
        assertThat(testAssistito.getIdAssistito()).isEqualTo(UPDATED_ID_ASSISTITO);
    }

    @Test
    @Transactional
    public void updateNonExistingAssistito() throws Exception {
        int databaseSizeBeforeUpdate = assistitoRepository.findAll().size();

        // Create the Assistito

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssistitoMockMvc.perform(put("/api/assistitos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assistito)))
            .andExpect(status().isBadRequest());

        // Validate the Assistito in the database
        List<Assistito> assistitoList = assistitoRepository.findAll();
        assertThat(assistitoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssistito() throws Exception {
        // Initialize the database
        assistitoService.save(assistito);

        int databaseSizeBeforeDelete = assistitoRepository.findAll().size();

        // Get the assistito
        restAssistitoMockMvc.perform(delete("/api/assistitos/{id}", assistito.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Assistito> assistitoList = assistitoRepository.findAll();
        assertThat(assistitoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Assistito.class);
        Assistito assistito1 = new Assistito();
        assistito1.setId(1L);
        Assistito assistito2 = new Assistito();
        assistito2.setId(assistito1.getId());
        assertThat(assistito1).isEqualTo(assistito2);
        assistito2.setId(2L);
        assertThat(assistito1).isNotEqualTo(assistito2);
        assistito1.setId(null);
        assertThat(assistito1).isNotEqualTo(assistito2);
    }
}
