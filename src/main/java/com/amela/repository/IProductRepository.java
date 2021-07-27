package com.amela.repository;

import com.amela.model.Product;

public interface IProductRepository extends IGeneralRepository<Product>{


    Product findById(Long id);
}
