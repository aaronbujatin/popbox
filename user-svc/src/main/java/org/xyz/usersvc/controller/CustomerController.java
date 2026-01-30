package org.xyz.usersvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xyz.usersvc.dto.CustomerCreateReq;
import org.xyz.usersvc.dto.CustomerInfoResp;
import org.xyz.usersvc.dto.CustomerResponse;
import org.xyz.usersvc.dto.RegisterCustomerRequest;
import org.xyz.usersvc.exception.ResourceNotFoundException;
import org.xyz.usersvc.repository.CustomerRepository;
import org.xyz.usersvc.service.customer.CustomerService;
import org.xyz.usersvc.util.ApiLogger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Valid RegisterCustomerRequest registerCustomerRequest,
            HttpServletRequest request
    ) {


        return ResponseEntity.ok(customerService.createCustomer(registerCustomerRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerInfo(@PathVariable("id") Long id) {

        return ResponseEntity.ok(customerService.getUserById(id));
    }

}
