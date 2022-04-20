package com.example.demo.service;

import com.example.demo.data.SmartValueDTO;
import com.example.demo.domain.exception.DomainException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InductionServiceTest {
    private static final String DUMMY_VALUE = "value";
    private static final String DUMMY_TAG = "tag";
    private static final String ACCEPTABLE_VALUE = "Resilience";
    private static final String ACCEPTABLE_TAG = "Nothing can stop us, all the way up";

    @Autowired
    private InductionService inductionService;

    @Test
    void createSmartValueWithNullObject() {
        Exception exception = assertThrows(DomainException.class, () -> inductionService.createSmartValue(null));
        assertTrue(exception.getMessage().contains("SmartValue is required"));
    }

    @Test
    void createSmartValueWithNullValue() {
        SmartValueDTO smartValueDTO = new SmartValueDTO(null, DUMMY_TAG);
        Exception exception = assertThrows(DomainException.class, () -> inductionService.createSmartValue(smartValueDTO));
        assertTrue(exception.getMessage().contains("Value is required"));
    }

    @Test
    void createSmartValueWithNullTagline() {
        SmartValueDTO smartValueDTO = new SmartValueDTO(DUMMY_VALUE, null);
        Exception exception = assertThrows(DomainException.class, () -> inductionService.createSmartValue(smartValueDTO));
        assertTrue(exception.getMessage().contains("TagLine is required"));
    }

    @Test
    void createSmartValueWithNonAcceptableValue() {
        SmartValueDTO smartValueDTO = new SmartValueDTO(DUMMY_VALUE, DUMMY_TAG);
        Exception exception = assertThrows(DomainException.class, () -> inductionService.createSmartValue(smartValueDTO));
        assertTrue(exception.getMessage().contains("Value is not a smart value"));
    }

    @Test
    void createSmartValueWithAcceptableValueDoesNotThrowException() {
        SmartValueDTO smartValueDTO = new SmartValueDTO(ACCEPTABLE_VALUE, ACCEPTABLE_TAG);
        assertDoesNotThrow(() -> inductionService.createSmartValue(smartValueDTO));
    }

    @Test
    void createSmartValueWithAcceptableValueReturnsCorrectValues() {
        SmartValueDTO smartValueDTO = new SmartValueDTO(ACCEPTABLE_VALUE, ACCEPTABLE_TAG);
        SmartValueDTO result = inductionService.createSmartValue(smartValueDTO);
        assertEquals(smartValueDTO.getValue(), result.getValue());
        assertEquals(smartValueDTO.getTagLine(), result.getTagLine());
    }

    @Test
    void getSmartValuesReturnsCorrectNumberOfValues() {
        assertEquals(inductionService.getSmartValues().size(), 7);
    }
}