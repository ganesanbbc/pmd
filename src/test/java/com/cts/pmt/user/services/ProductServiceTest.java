package com.cts.pmt.user.services;

import com.cts.pmt.user.dao.UserRepository;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {


    @InjectMocks
    private UserServiceImple service;

    @Mock
    private UserRepository productRepository;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }



}