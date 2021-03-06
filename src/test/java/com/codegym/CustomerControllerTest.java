package com.codegym;

import com.amela.controller.CustomerController;
import com.amela.model.Customer;
import com.amela.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebAppConfiguration
@SpringJUnitJupiterConfig(CustomerControllerTestConfig.class)
public class CustomerControllerTest {
    private MockMvc mockMvc;

    private CustomerService customerService = Mockito.mock(CustomerService.class);

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void findAllCustomer_Test() throws Exception{
        Customer first = new Customer(1L,"Nguyen", "Tung");
        Customer second = new Customer(2L,"Hoang", "Nam");
        when(customerService.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/list"))
                .andExpect(forwardedUrl("/customer/list"))
                //.andExpect(model().attribute("customers", hasSize(2)))
                .andExpect(model().attribute("customers", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Nguyen")),
                                hasProperty("lastName", is("Tung"))
                        )
                )))
                .andExpect(model().attribute("customers", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("firstName", is("Hoang")),
                                hasProperty("lastName", is("Nam"))
                        )
                )));
        verify(customerService, times(1)).findAll();
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void findById_EntryFound() throws Exception {

        Customer first = new Customer(1L,"Nguyen", "Tung");
        Customer second = new Customer(2L,"Hoang", "Nam");
        when(customerService.findById(2L)).thenReturn(second);

        mockMvc.perform(get("/customer/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/detail"))
                .andExpect(forwardedUrl("/customer/detail"))
                .andExpect(model().attribute("customer", hasProperty("id", is(2L))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is("Hoang"))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is("Nam"))));

        verify(customerService, times(1)).findById(2L);
        verifyNoMoreInteractions(customerService);
    }

}
