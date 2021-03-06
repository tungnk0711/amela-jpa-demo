package com.amela.service;

import com.amela.exception.DuplicateLastNameException;
import com.amela.model.Customer;

public interface ICustomerService extends IGeneralService<Customer> {

    Customer findById(Long id);

    void saveCustomer(Customer customer) throws DuplicateLastNameException;
}
