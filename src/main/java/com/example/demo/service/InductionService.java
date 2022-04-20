package com.example.demo.service;

import com.example.demo.data.SmartValueDTO;
import com.example.demo.domain.SmartValue;
import com.example.demo.domain.SmartValuesDomain;
import com.example.demo.security.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InductionService {
    private JdbcTemplate jdbcTemplate;

    public SmartValueDTO createSmartValue(SmartValueDTO smartValueDTO) {
        // use domain builder to ensure data integrity is maintained
        SmartValuesDomain domain = new SmartValuesDomain.SmartValuesDomainBuilder()
                .withDTO(smartValueDTO).build();
        SmartValue smartValue = domain.toEntity();
        // save value to database
        return smartValue.toDTO();
    }

    public List<SmartValueDTO> getSmartValues() {
        return new SmartValuesDomain.SmartValuesDomainBuilder().getAllSmartValues();
    }

    public void dummyMethod(SmartValueDTO smartValueDTO) {
        // use domain builder to ensure data integrity is maintained
        SmartValuesDomain domain = new SmartValuesDomain.SmartValuesDomainBuilder()
                .withDTO(smartValueDTO).build();
        SmartValue smartValue = domain.toEntity();
        // save value to database
        // close issue #4
        // test issue close #5
    }
}
