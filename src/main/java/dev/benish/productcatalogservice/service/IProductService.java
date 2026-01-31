package dev.benish.productcatalogservice.service;

import dev.benish.productcatalogservice.model.Product;

import java.util.List;

/*
 * we have no database so we are going to use
 * Fake store Api to get create product
 */
public interface IProductService {

    Product getProductById(Long id);

    List<Product> getProduct();

    void createProduct();
}
