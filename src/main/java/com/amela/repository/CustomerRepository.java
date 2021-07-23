package com.amela.repository;

import com.amela.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Transactional
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    private EntityManager em;

    /*@Override
    public List<Customer> findAll() {
        //TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        //return query.getResultList();

        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomers", Customer.class);
        List<Customer> customers = query.getResultList();
        return customers;
    }*/

    /*@Override
    public void save(Customer customer) {
        if (customer.getId() != null) {
            em.merge(customer);
        } else {
            em.persist(customer);
        }
    }*/

    // demo store procedure
    @Override
    public void save(Customer customer) {

        StoredProcedureQuery spAddCustomer = em.createNamedStoredProcedureQuery("addCustomer");
        spAddCustomer.setParameter("firstName", customer.getFirstName());
        spAddCustomer.setParameter("lastName", customer.getLastName());
        spAddCustomer.execute();
    }

    // demo store procedure
    @Override
    public List<Customer> findAll() {
        StoredProcedureQuery getAllCustomers = em.createNamedStoredProcedureQuery("getCustomers");
        getAllCustomers.execute();
        List<Customer> customers = getAllCustomers.getResultList();

        return customers;
    }

}
