package com.cts.day1.services;

import com.cts.day1.model.Product;
import com.cts.day1.dao.ProductJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImple implements ProductService {

    @Autowired
    private ProductJPARepository productRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> readAllProducts() {
        return productRepository.findAll();
    }
}
