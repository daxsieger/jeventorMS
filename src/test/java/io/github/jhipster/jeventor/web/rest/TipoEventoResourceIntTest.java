package io.github.jhipster.jeventor.web.rest;

import io.github.jhipster.jeventor.JeventorMsApp;

import io.github.jhipster.jeventor.domain.TipoEvento;
import io.github.jhipster.jeventor.repository.TipoEventoRepository;
import io.github.jhipster.jeventor.service.TipoEventoService;
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
 * Test class for the TipoEventoResource REST controller.
 *
 * @see TipoEventoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JeventorMsApp.class)
public class TipoEventoResourceIntTest {

    private static final Long DEFAULT_ID_TIPO_EVENTO = 1L;
    private static final Long UPDATED_ID_TIPO_EVENTO = 2L;

    private static final String DEFAULT_DS_TIPO_EVENTO = "AAAAAAAAAA";
    private static final String UPDATED_DS_TIPO_EVENTO = "BBBBBBBBBB";

    @Autowired
    private TipoEventoRepository tipoEventoRepository;

    

    @Autowired
    private TipoEventoService tipoEventoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoEventoMockMvc;

    private TipoEvento tipoEvento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoEventoResource tipoEventoResource = new TipoEventoResource(tipoEventoService);
        this.restTipoEventoMockMvc = MockMvcBuilders.standaloneSetup(tipoEventoResource)
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
    public static TipoEvento createEntity(EntityManager em) {
        TipoEvento tipoEvento = new TipoEvento()
            .idTipoEvento(DEFAULT_ID_TIPO_EVENTO)
            .dsTipoEvento(DEFAULT_DS_TIPO_EVENTO);
        return tipoEvento;
    }

    @Before
    public void initTest() {
        tipoEvento = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoEvento() throws Exception {
        int databaseSizeBeforeCreate = tipoEventoRepository.findAll().size();

        // Create the TipoEvento
        restTipoEventoMockMvc.perform(post("/api/tipo-eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoEvento)))
            .andExpect(status().isCreated());

        // Validate the TipoEvento in the database
        List<TipoEvento> tipoEventoList = tipoEventoRepository.findAll();
        assertThat(tipoEventoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoEvento testTipoEvento = tipoEventoList.get(tipoEventoList.size() - 1);
        assertThat(testTipoEvento.getIdTipoEvento()).isEqualTo(DEFAULT_ID_TIPO_EVENTO);
        assertThat(testTipoEvento.getDsTipoEvento()).isEqualTo(DEFAULT_DS_TIPO_EVENTO);
    }

    @Test
    @Transactional
    public void createTipoEventoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoEventoRepository.findAll().size();

        // Create the TipoEvento with an existing ID
        tipoEvento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoEventoMockMvc.perform(post("/api/tipo-eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoEvento)))
            .andExpect(status().isBadRequest());

        // Validate the TipoEvento in the database
        List<TipoEvento> tipoEventoList = tipoEventoRepository.findAll();
        assertThat(tipoEventoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoEventos() throws Exception {
        // Initialize the database
        tipoEventoRepository.saveAndFlush(tipoEvento);

        // Get all the tipoEventoList
        restTipoEventoMockMvc.perform(get("/api/tipo-eventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoEvento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTipoEvento").value(hasItem(DEFAULT_ID_TIPO_EVENTO.intValue())))
            .andExpect(jsonPath("$.[*].dsTipoEvento").value(hasItem(DEFAULT_DS_TIPO_EVENTO.toString())));
    }
    

    @Test
    @Transactional
    public void getTipoEvento() throws Exception {
        // Initialize the database
        tipoEventoRepository.saveAndFlush(tipoEvento);

        // Get the tipoEvento
        restTipoEventoMockMvc.perform(get("/api/tipo-eventos/{id}", tipoEvento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoEvento.getId().intValue()))
            .andExpect(jsonPath("$.idTipoEvento").value(DEFAULT_ID_TIPO_EVENTO.intValue()))
            .andExpect(jsonPath("$.dsTipoEvento").value(DEFAULT_DS_TIPO_EVENTO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTipoEvento() throws Exception {
        // Get the tipoEvento
        restTipoEventoMockMvc.perform(get("/api/tipo-eventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoEvento() throws Exception {
        // Initialize the database
        tipoEventoService.save(tipoEvento);

        int databaseSizeBeforeUpdate = tipoEventoRepository.findAll().size();

        // Update the tipoEvento
        TipoEvento updatedTipoEvento = tipoEventoRepository.findById(tipoEvento.getId()).get();
        // Disconnect from session so that the updates on updatedTipoEvento are not directly saved in db
        em.detach(updatedTipoEvento);
        updatedTipoEvento
            .idTipoEvento(UPDATED_ID_TIPO_EVENTO)
            .dsTipoEvento(UPDATED_DS_TIPO_EVENTO);

        restTipoEventoMockMvc.perform(put("/api/tipo-eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoEvento)))
            .andExpect(status().isOk());

        // Validate the TipoEvento in the database
        List<TipoEvento> tipoEventoList = tipoEventoRepository.findAll();
        assertThat(tipoEventoList).hasSize(databaseSizeBeforeUpdate);
        TipoEvento testTipoEvento = tipoEventoList.get(tipoEventoList.size() - 1);
        assertThat(testTipoEvento.getIdTipoEvento()).isEqualTo(UPDATED_ID_TIPO_EVENTO);
        assertThat(testTipoEvento.getDsTipoEvento()).isEqualTo(UPDATED_DS_TIPO_EVENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoEvento() throws Exception {
        int databaseSizeBeforeUpdate = tipoEventoRepository.findAll().size();

        // Create the TipoEvento

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoEventoMockMvc.perform(put("/api/tipo-eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoEvento)))
            .andExpect(status().isBadRequest());

        // Validate the TipoEvento in the database
        List<TipoEvento> tipoEventoList = tipoEventoRepository.findAll();
        assertThat(tipoEventoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoEvento() throws Exception {
        // Initialize the database
        tipoEventoService.save(tipoEvento);

        int databaseSizeBeforeDelete = tipoEventoRepository.findAll().size();

        // Get the tipoEvento
        restTipoEventoMockMvc.perform(delete("/api/tipo-eventos/{id}", tipoEvento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoEvento> tipoEventoList = tipoEventoRepository.findAll();
        assertThat(tipoEventoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoEvento.class);
        TipoEvento tipoEvento1 = new TipoEvento();
        tipoEvento1.setId(1L);
        TipoEvento tipoEvento2 = new TipoEvento();
        tipoEvento2.setId(tipoEvento1.getId());
        assertThat(tipoEvento1).isEqualTo(tipoEvento2);
        tipoEvento2.setId(2L);
        assertThat(tipoEvento1).isNotEqualTo(tipoEvento2);
        tipoEvento1.setId(null);
        assertThat(tipoEvento1).isNotEqualTo(tipoEvento2);
    }
}
