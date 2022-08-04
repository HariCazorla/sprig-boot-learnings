package com.shreeharibi.beanvalidationexample.controller;

import com.shreeharibi.beanvalidationexample.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/app")
public class Controller {

    private final Validator validator;

    @PostMapping("auto")
    public ResponseEntity<?> automaticValidation(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customer);
    }

    @PostMapping("manual")
    public ResponseEntity<?> manualValidation(@RequestBody Customer customer) {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        if (violations.isEmpty()) {
            return ResponseEntity.ok(customer);
        }
        else {
            List<String> errors = violations.stream().map(v -> v.getMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
    }
}
