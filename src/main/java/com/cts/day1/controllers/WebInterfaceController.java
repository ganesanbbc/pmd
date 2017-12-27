package com.cts.day1.controllers;

import com.cts.day1.services.ProductServiceImple;
import com.cts.day1.model.Product;
import com.cts.day1.dao.ProductJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class WebInterfaceController extends WebMvcConfigurerAdapter {


    public static final String INDEX_PAGE = "index";
    public static final String TARGET_PAGE = "success";
    public static final String ROOT = "/";

    @Autowired
    ProductJPARepository respository;


    @Autowired
    ProductServiceImple productService;



    @GetMapping(ROOT)
    public String loadIndexPage(Product product) {
        return INDEX_PAGE;
    }

    @PostMapping(ROOT)
    public String addProduct(@Valid Product product,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return INDEX_PAGE;
        }

        productService.addProduct(product);

        return TARGET_PAGE;
    }


}
