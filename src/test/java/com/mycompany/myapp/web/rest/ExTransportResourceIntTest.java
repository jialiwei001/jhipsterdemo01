package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Jhipsterdemo01App;

import com.mycompany.myapp.domain.ExTransport;
import com.mycompany.myapp.repository.ExTransportRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

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


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExTransportResource REST controller.
 *
 * @see ExTransportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jhipsterdemo01App.class)
public class ExTransportResourceIntTest {

    private static final Long DEFAULT_EX_EXTRANSPORT = 1L;
    private static final Long UPDATED_EX_EXTRANSPORT = 2L;

    private static final Long DEFAULT_EX_EXTRANSPORTA = 1L;
    private static final Long UPDATED_EX_EXTRANSPORTA = 2L;

    @Autowired
    private ExTransportRepository exTransportRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restExTransportMockMvc;

    private ExTransport exTransport;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExTransportResource exTransportResource = new ExTransportResource(exTransportRepository);
        this.restExTransportMockMvc = MockMvcBuilders.standaloneSetup(exTransportResource)
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
    public static ExTransport createEntity(EntityManager em) {
        ExTransport exTransport = new ExTransport()
            .ex_extransport(DEFAULT_EX_EXTRANSPORT)
            .ex_extransporta(DEFAULT_EX_EXTRANSPORTA);
        return exTransport;
    }

    @Before
    public void initTest() {
        exTransport = createEntity(em);
    }

    @Test
    @Transactional
    public void createExTransport() throws Exception {
        int databaseSizeBeforeCreate = exTransportRepository.findAll().size();

        // Create the ExTransport
        restExTransportMockMvc.perform(post("/api/ex-transports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exTransport)))
            .andExpect(status().isCreated());

        // Validate the ExTransport in the database
        List<ExTransport> exTransportList = exTransportRepository.findAll();
        assertThat(exTransportList).hasSize(databaseSizeBeforeCreate + 1);
        ExTransport testExTransport = exTransportList.get(exTransportList.size() - 1);
        assertThat(testExTransport.getEx_extransport()).isEqualTo(DEFAULT_EX_EXTRANSPORT);
        assertThat(testExTransport.getEx_extransporta()).isEqualTo(DEFAULT_EX_EXTRANSPORTA);
    }

    @Test
    @Transactional
    public void createExTransportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exTransportRepository.findAll().size();

        // Create the ExTransport with an existing ID
        exTransport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExTransportMockMvc.perform(post("/api/ex-transports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exTransport)))
            .andExpect(status().isBadRequest());

        // Validate the ExTransport in the database
        List<ExTransport> exTransportList = exTransportRepository.findAll();
        assertThat(exTransportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEx_extransportIsRequired() throws Exception {
        int databaseSizeBeforeTest = exTransportRepository.findAll().size();
        // set the field null
        exTransport.setEx_extransport(null);

        // Create the ExTransport, which fails.

        restExTransportMockMvc.perform(post("/api/ex-transports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exTransport)))
            .andExpect(status().isBadRequest());

        List<ExTransport> exTransportList = exTransportRepository.findAll();
        assertThat(exTransportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEx_extransportaIsRequired() throws Exception {
        int databaseSizeBeforeTest = exTransportRepository.findAll().size();
        // set the field null
        exTransport.setEx_extransporta(null);

        // Create the ExTransport, which fails.

        restExTransportMockMvc.perform(post("/api/ex-transports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exTransport)))
            .andExpect(status().isBadRequest());

        List<ExTransport> exTransportList = exTransportRepository.findAll();
        assertThat(exTransportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExTransports() throws Exception {
        // Initialize the database
        exTransportRepository.saveAndFlush(exTransport);

        // Get all the exTransportList
        restExTransportMockMvc.perform(get("/api/ex-transports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exTransport.getId().intValue())))
            .andExpect(jsonPath("$.[*].ex_extransport").value(hasItem(DEFAULT_EX_EXTRANSPORT.intValue())))
            .andExpect(jsonPath("$.[*].ex_extransporta").value(hasItem(DEFAULT_EX_EXTRANSPORTA.intValue())));
    }
    
    @Test
    @Transactional
    public void getExTransport() throws Exception {
        // Initialize the database
        exTransportRepository.saveAndFlush(exTransport);

        // Get the exTransport
        restExTransportMockMvc.perform(get("/api/ex-transports/{id}", exTransport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(exTransport.getId().intValue()))
            .andExpect(jsonPath("$.ex_extransport").value(DEFAULT_EX_EXTRANSPORT.intValue()))
            .andExpect(jsonPath("$.ex_extransporta").value(DEFAULT_EX_EXTRANSPORTA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingExTransport() throws Exception {
        // Get the exTransport
        restExTransportMockMvc.perform(get("/api/ex-transports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExTransport() throws Exception {
        // Initialize the database
        exTransportRepository.saveAndFlush(exTransport);

        int databaseSizeBeforeUpdate = exTransportRepository.findAll().size();

        // Update the exTransport
        ExTransport updatedExTransport = exTransportRepository.findById(exTransport.getId()).get();
        // Disconnect from session so that the updates on updatedExTransport are not directly saved in db
        em.detach(updatedExTransport);
        updatedExTransport
            .ex_extransport(UPDATED_EX_EXTRANSPORT)
            .ex_extransporta(UPDATED_EX_EXTRANSPORTA);

        restExTransportMockMvc.perform(put("/api/ex-transports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExTransport)))
            .andExpect(status().isOk());

        // Validate the ExTransport in the database
        List<ExTransport> exTransportList = exTransportRepository.findAll();
        assertThat(exTransportList).hasSize(databaseSizeBeforeUpdate);
        ExTransport testExTransport = exTransportList.get(exTransportList.size() - 1);
        assertThat(testExTransport.getEx_extransport()).isEqualTo(UPDATED_EX_EXTRANSPORT);
        assertThat(testExTransport.getEx_extransporta()).isEqualTo(UPDATED_EX_EXTRANSPORTA);
    }

    @Test
    @Transactional
    public void updateNonExistingExTransport() throws Exception {
        int databaseSizeBeforeUpdate = exTransportRepository.findAll().size();

        // Create the ExTransport

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExTransportMockMvc.perform(put("/api/ex-transports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exTransport)))
            .andExpect(status().isBadRequest());

        // Validate the ExTransport in the database
        List<ExTransport> exTransportList = exTransportRepository.findAll();
        assertThat(exTransportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExTransport() throws Exception {
        // Initialize the database
        exTransportRepository.saveAndFlush(exTransport);

        int databaseSizeBeforeDelete = exTransportRepository.findAll().size();

        // Get the exTransport
        restExTransportMockMvc.perform(delete("/api/ex-transports/{id}", exTransport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExTransport> exTransportList = exTransportRepository.findAll();
        assertThat(exTransportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExTransport.class);
        ExTransport exTransport1 = new ExTransport();
        exTransport1.setId(1L);
        ExTransport exTransport2 = new ExTransport();
        exTransport2.setId(exTransport1.getId());
        assertThat(exTransport1).isEqualTo(exTransport2);
        exTransport2.setId(2L);
        assertThat(exTransport1).isNotEqualTo(exTransport2);
        exTransport1.setId(null);
        assertThat(exTransport1).isNotEqualTo(exTransport2);
    }
}
