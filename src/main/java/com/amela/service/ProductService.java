package com.amela.service;

import com.amela.model.Product;
import com.amela.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService implements IProductService {


    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {

        return productRepository.findAll();

    }

    @Override
    public void saveProduct(Product product){

    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

}

