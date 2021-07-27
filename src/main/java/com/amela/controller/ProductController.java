package com.amela.controller;

import com.amela.model.Product;
import com.amela.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("")
    public ModelAndView listProducts() {

        List<Product> products = productService.findAll();

        ModelAndView modelAndView = new ModelAndView("/product/list", "products", products);
        return modelAndView;
    }
}