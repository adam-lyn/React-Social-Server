package com.revature.search;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TestSearchControllerIntegration {

    private MockMvc mockMvc;
    private final WebApplicationContext context;
    private final ObjectMapper mapper;

    @Autowired
    public TestSearchControllerIntegration(WebApplicationContext context, ObjectMapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testQueryByString() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/search").contentType("application/json").content("query"))
                .andDo(print())
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    public void testQuery2ByString() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/search/2").contentType("application/json").content("query"))
                .andDo(print())
                .andExpect(status().is(400))
                .andReturn();
    }
}
