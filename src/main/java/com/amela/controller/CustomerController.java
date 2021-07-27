package com.amela.controller;

import com.amela.exception.DuplicateLastNameException;
import com.amela.model.Customer;
import com.amela.model.Items;
import com.amela.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) throws DuplicateLastNameException{

        /*try {
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("/customer/create");
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("message", "New customer created successfully");
            return modelAndView;
        } catch (DuplicateLastNameException e) {
            return new ModelAndView("/customer/inputs-not-acceptable");
        }*/

        customerService.saveCustomer(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;

    }

    @GetMapping("")
    public ModelAndView listCustomers(HttpSession session) {

        List<Items> cart = (List<Items>) session.getAttribute("cart");


        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @ExceptionHandler(DuplicateLastNameException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/customer/inputs-not-acceptable");
    }
}
