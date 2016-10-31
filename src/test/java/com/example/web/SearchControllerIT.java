package com.example.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerIT {

    private MediaType jsonContentType =
            new MediaType(APPLICATION_JSON.getType(), APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter =
                asList(converters)
                        .stream()
                        .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                        .findAny()
                        .get();

        assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void testSearchSimilarsWithValidRequestParameters() throws Exception {
        this.mockMvc
                .perform(get("/similars")
                        .contentType(jsonContentType)
                        .content(this.json(new Params("One Two", "world")))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keyword", is("world")))
                .andExpect(jsonPath("$.frequency", is(0)))
                .andExpect(jsonPath("$.similar_words", hasSize(0)));
    }

    @Test
    public void testSearchSimilarsWithMissingMandatoryParameters() throws Exception {
        this.mockMvc
                .perform(get("/similars").contentType(jsonContentType)
                        .content(this.json(new Params(null, null))))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testSearchSimilarsWithoutRequestBody() throws Exception {
        this.mockMvc
                .perform(get("/similars").contentType(jsonContentType))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchSimilarsWith1ExactAnd3Similarities() throws Exception {
        this.mockMvc
                .perform(
                        get("/similars")
                                .content(this.json(new Params("Word Words Wor word", "Word")))
                                .contentType(jsonContentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keyword", is("Word")))
                .andExpect(jsonPath("$.frequency", is(1)))
                .andExpect(jsonPath("$.similar_words", hasSize(3)))
                .andExpect(jsonPath("$.similar_words.[0]", is("Words")))
                .andExpect(jsonPath("$.similar_words.[1]", is("Wor")))
                .andExpect(jsonPath("$.similar_words.[2]", is("word")));

    }


    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}