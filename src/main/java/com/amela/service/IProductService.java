package com.amela.service;

import com.amela.model.Product;

public interface IProductService extends IGeneralService<Product>{

    void saveProduct(Product product);

    Product findById(Long id);
}
