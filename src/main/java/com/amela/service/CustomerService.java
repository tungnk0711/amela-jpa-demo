package com.amela.service;

import com.amela.exception.DuplicateLastNameException;
import com.amela.model.Customer;
import com.amela.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService implements ICustomerService {

    public int count = 10;

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {

        //business logic -- upload file
        System.out.println("Method findAll() called");

        return customerRepository.findAll();

    }

    @Override
    public void saveCustomer(Customer customer) throws DuplicateLastNameException{

        try {
            System.out.println("Method save() called");
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new DuplicateLastNameException();
        }


    }

}
