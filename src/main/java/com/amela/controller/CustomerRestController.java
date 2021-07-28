package com.amela.controller;

import com.amela.exception.DuplicateLastNameException;
import com.amela.model.Customer;
import com.amela.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomer() {
        List<Customer> customers = (List<Customer>) customerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) throws DuplicateLastNameException {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
