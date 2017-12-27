package com.cts.day1.services;

import com.cts.day1.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> readAllProducts();

    void addProduct(Product product);

    Product getProductById(long expectedProductID);
}
