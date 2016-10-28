package com.example.web;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(value = "/similars",method = GET)
    public Similarity searchSimiliars(@RequestBody Params params) {

        if(params.getKeyword().isEmpty() || params.getNotebookEntry().isEmpty()) throw new IllegalArgumentException();

        List<String> words = asList(params.getNotebookEntry().split(" "));

        return new Similarity(params.getKeyword(),1,words);
    }

    @ResponseStatus(value= UNPROCESSABLE_ENTITY,reason="Parameters can't be empty or null")  // 422
    @ExceptionHandler(IllegalArgumentException.class)
    public void missingMandatoryParameters(){
    }
}
