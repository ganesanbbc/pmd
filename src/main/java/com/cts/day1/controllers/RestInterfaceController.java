package com.cts.day1.controllers;


import com.cts.day1.exception.ProductException;
import com.cts.day1.model.Product;
import com.cts.day1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RestInterfaceController {

    public static final String GET_PRODUCTS = "/getProducts";
    public static final String ADD_PRODUCT = "/addProduct";
    public static final String GET_PRODUCT = "/getProduct/{id}";



    @Autowired
    private ProductService productService;

    @RequestMapping(path = GET_PRODUCTS, method = GET)
    public @ResponseBody
    ResponseEntity<List<Product>> getItems() {
        return new ResponseEntity<List<Product>>(productService.readAllProducts(), OK);
    }


    @RequestMapping(path = GET_PRODUCT, method = GET)
    public @ResponseBody
    ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws ProductException {
        Product productById = productService.getProductById(id);

        if (productById == null) {
            throw new ProductException("Product is not found");
        }

        return new ResponseEntity<Product>(productById, OK);
    }


    @RequestMapping(path = ADD_PRODUCT, method = POST)
    public List<Product> updateItemById(@RequestBody Product product) throws ProductException {
        productService.addProduct(product);
        return productService.readAllProducts();
    }


}