package com.amela.repository;

import com.amela.model.Customer;

public interface ICustomerRepository extends IGeneralRepository<Customer>{
    Customer findById(Long id);
}
