package com.kata.controller;

import com.kata.service.FooBarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FooBarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FooBarService fooBarService;

    @Test
    public void testFooBarQuix_Success() throws Exception {
        when(fooBarService.checkNumber(15)).thenReturn("FOOBARBAR");

        mockMvc.perform(get("/foobar").param("number", "15")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("FOOBARBAR")))
        ;
    }

    @Test
    public void testFooBarQuix_Too_High() throws Exception {

        when(fooBarService.checkNumber(105)).thenReturn("105");

            mockMvc.perform(get("/foobar")
                .param("number", "105")
                .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("105")));
    }

}