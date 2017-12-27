package com.cts.day1.user.controllers;


import com.cts.day1.ProjectManagementApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectManagementApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestInterfaceControllerTest {


    public static final String GET_PRODUCTS = "/getProducts";
    public static final String GET_PRODUCT = "/getProduct/";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void thatGetProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GET_PRODUCTS).accept(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    public void thatGetProductById() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(GET_PRODUCT + "1")
                .accept(APPLICATION_JSON);

        ResultMatcher expect1 = jsonPath(".id").exists();
        ResultMatcher expect2 = jsonPath(".id").value(1);

        mockMvc.perform(mockRequest)
                .andExpect(expect1)
                .andExpect(expect2)
                .andDo(print());
    }

    @Test
    public void thatGetErrorResponseMessageWhenPassingInvalidProductId() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(GET_PRODUCT + "10")
                .accept(APPLICATION_JSON);

        ResultMatcher expect1 = jsonPath(".errorCode").value(404);
        mockMvc.perform(mockRequest)
                .andExpect(expect1)
                .andDo(print());
    }

}