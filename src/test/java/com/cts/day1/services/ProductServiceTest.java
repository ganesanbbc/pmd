package com.cts.day1.services;

import com.cts.day1.dao.ProductJPARepository;
import com.cts.day1.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.totallylazy.matchers.NumberMatcher.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class ProductServiceTest {


    @InjectMocks
    private ProductServiceImple service;

    @Mock
    private ProductJPARepository productRepository;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void thatGetAllProducts() {

        int expectedSize = 2;
        //Setup - Mock
        pushMockProducts();

        //Action
        List<Product> results = service.readAllProducts();

        //Output
        int actualSize = results.size();
        assertThat(actualSize, is(expectedSize));

    }


    @Test
    public void thatGetTheTargetedProductWhenPassingProductID() {

        long expectedProductID = 2;
        pushMockProducts();
        Product productById = service.getProductById(expectedProductID);
        long actualProductID = productById.getId();
        assertThat(actualProductID, is(expectedProductID));


    }


    private List<Product> pushMockProducts() {
        List<Product> mockData = new ArrayList<>();
        mockData.add(new Product(1l, "demo"));
        mockData.add(new Product(2l, "demo"));
        when(productRepository.findAll()).thenReturn(mockData);
        return mockData;
    }

}