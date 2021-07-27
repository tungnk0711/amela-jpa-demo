package com.amela.repository;

import com.amela.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductRepository implements IProductRepository {
    @PersistenceContext
    private EntityManager em;

    // demo store procedure
    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = em.createNamedQuery("findAllProducts", Product.class);
        List<Product> products = query.getResultList();
        return products;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = em.createNamedQuery("findProductsWithId", Product.class);
        query.setParameter("id", id);
        Product product = query.getSingleResult();

        return product;
    }
}

