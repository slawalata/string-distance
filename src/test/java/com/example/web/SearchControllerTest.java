package com.example.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getVehicleShouldReturnMakeAndModel() throws Exception {
        this.mockMvc
                .perform(get("/search").accept(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keyword", is("hello")))
                .andExpect(jsonPath("$.frequency", is(1)))
                .andExpect(jsonPath("$.similar_words", hasSize(1)))
                .andExpect(jsonPath("$.similar_words.[0]", is("world")));
    }


}