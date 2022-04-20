package com.example.demo.web;

import com.example.demo.data.SmartValueDTO;
import com.example.demo.domain.SmartValuesDomain;
import com.example.demo.domain.exception.DomainException;
import com.example.demo.service.InductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/values")
public class InductionController {
    private final InductionService inductionService;
    //work hard, work smart, be true test issue tag tracker
    //test in-built commands to close an issue
    public InductionController(InductionService inductionService) {
        this.inductionService = inductionService;
    }

    @GetMapping
    public ResponseEntity<List<String>> teachSmartValues() {
        List<String> smartValues = new SmartValuesDomain.SmartValuesDomainBuilder().getAllSmartValues()
                .stream().map(it -> new SmartValuesDomain.SmartValuesDomainBuilder()
                        .withDTO(it).build().teachSmartValue())
                .collect(Collectors.toList());
        return ResponseEntity.ok(smartValues);
    }

    @PostMapping
    public ResponseEntity<SmartValueDTO> createSmartValue(@RequestBody SmartValueDTO smartValueDTO) {
        SmartValueDTO result = inductionService.createSmartValue(smartValueDTO);
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(DomainException.class)
    public Exception handleError(HttpServletRequest req, Exception ex) {
        return ex;
    }
}
