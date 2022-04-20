package com.example.demo.domain;

import com.example.demo.data.SmartValueDTO;
import com.example.demo.domain.exception.DomainException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmartValuesDomain {
    public static final String domainName = "SmartValues";
    private final SmartValueDTO smartValueDTO;

    public SmartValuesDomain(SmartValuesDomainBuilder builder) {
        smartValueDTO = builder.smartValueDTO;
    }

    public SmartValue toEntity() {
        SmartValue smartValue = new SmartValue();
        smartValue.setValue(smartValueDTO.getValue());
        smartValue.setTagLine(smartValueDTO.getTagLine());
        return smartValue;
    }

    public String teachSmartValue() {
        return smartValueDTO.getValue() + " - " + smartValueDTO.getTagLine();
    }

    public static class SmartValuesDomainBuilder {
        private SmartValueDTO smartValueDTO;
        private final List<String> acceptableSmartValues = Arrays.asList("Courage", "Don't Settle",
                "Self Love", "Faith", "Commitment", "Change", "Ask, Receive");

        public SmartValuesDomainBuilder() {
        }

        public SmartValuesDomainBuilder withDTO(SmartValueDTO smartValueDTO) {
            this.smartValueDTO = smartValueDTO;
            return this;
        }

        public List<SmartValueDTO> getAllSmartValues() {
            ArrayList<SmartValueDTO> values = new ArrayList<>();
            values.add(new SmartValueDTO("Courage", "If it frightens you, do it"));
            values.add(new SmartValueDTO("Don't Settle", "Every time you settle, you get exactly what you\n" +
                    "settled for"));
            values.add(new SmartValueDTO("Self Love", "Put yourself first"));
            values.add(new SmartValueDTO("Faith", "No matter what happens, you will handle it"));
            values.add(new SmartValueDTO("Commitment", "Whatever you do, do it 100%"));
            values.add(new SmartValueDTO("Change", "If you do what you have always done, you will get what you have\n" +
                    "always got"));
            values.add(new SmartValueDTO("Ask, Receive", "Ask for what you want"));
            return values;
        }

        private boolean smartValueIsAcceptable() {
            if (this.smartValueDTO == null) {
                throw DomainException.throwDomainException(domainName, "SmartValue is required");
            }

            if (this.smartValueDTO.getValue() == null) {
                throw DomainException.throwDomainException(domainName, "Value is required");
            }

            if (this.smartValueDTO.getTagLine() == null) {
                throw DomainException.throwDomainException(domainName, "TagLine is required");
            }

            if (!this.acceptableSmartValues.contains(this.smartValueDTO.getValue())) {
                throw DomainException.throwDomainException(domainName, "Value is not a smart value");
            }

            return true;
        }

        public SmartValuesDomain build() {
            if (smartValueIsAcceptable()) {
                return new SmartValuesDomain(this);
            }
            throw DomainException.throwDomainException(domainName, "Cannot withDTO a smart value with invalid data");
        }

    }
}
