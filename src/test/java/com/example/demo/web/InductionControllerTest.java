package com.example.demo.web;

import com.example.demo.data.SmartValueDTO;
import com.example.demo.domain.exception.DomainException;
import com.example.demo.web.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InductionControllerTest {
    private static final String DUMMY_VALUE = "value";
    private static final String DUMMY_TAG = "tag";
    private static final String ACCEPTABLE_VALUE = "Resilience";
    private static final String ACCEPTABLE_TAG = "Nothing can stop us, all the way up";
    public static final String ENTITY_API_URL = "/api/values";

    @Autowired
    private MockMvc inductionMockMvc;

    @Test
    void createSmartValueWithNullObject() throws Exception {
        inductionMockMvc.perform(post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(null))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createSmartValueWithNullValue() throws Exception {
        SmartValueDTO smartValueDTO = new SmartValueDTO(null, DUMMY_TAG);
        inductionMockMvc
                .perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(smartValueDTO))
                )
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DomainException));
    }

    @Test
    void createSmartValueWithNullTagline() throws Exception {
        SmartValueDTO smartValueDTO = new SmartValueDTO(DUMMY_VALUE, null);
        inductionMockMvc
                .perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(smartValueDTO))
                )
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DomainException));
    }

    @Test
    void createSmartValueWithNonAcceptableValue() throws Exception {
        SmartValueDTO smartValueDTO = new SmartValueDTO(DUMMY_VALUE, DUMMY_TAG);
        inductionMockMvc
                .perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(smartValueDTO))
                )
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DomainException));
    }

    @Test
    void createSmartValueWithAcceptableValue() throws Exception {
        SmartValueDTO smartValueDTO = new SmartValueDTO(ACCEPTABLE_VALUE, ACCEPTABLE_TAG);
        inductionMockMvc
                .perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(smartValueDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.value").value(ACCEPTABLE_VALUE))
                .andExpect(jsonPath("$.tagLine").value(ACCEPTABLE_TAG));
    }

    @Test
    void getSmartValuesReturnsCorrectNumberOfValues() throws Exception {
        inductionMockMvc
                .perform(get(ENTITY_API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*]").value(hasItem(ACCEPTABLE_VALUE + " - " + ACCEPTABLE_TAG)));
    }

}