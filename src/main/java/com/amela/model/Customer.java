package com.amela.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findCustomerWithId",
        query = "select c from Customer c where  c.id=:id")
@NamedQuery(
        name = "findAllCustomers",
        query = "SELECT c FROM Customer c")
@NamedStoredProcedureQuery(
        name = "addCustomer",
        procedureName = "sp_AddCustomer",
        parameters = {
                @StoredProcedureParameter(name = "firstName", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "lastName", mode = ParameterMode.IN, type = String.class)
        }
)
@NamedStoredProcedureQuery(
        name = "getCustomers",
        procedureName = "sp_GetCustomer"
)
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;

    @Column(unique = true)
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
