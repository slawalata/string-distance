package com.example.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SearchController {

    @RequestMapping(value = "/similars",method = GET)
    public Result searchSimiliars(@RequestBody Params params) {

        List<String> words = asList(params.getNotebookEntry().split(" "));

        if (words.isEmpty()) return new Result();

        return new Result(params.getKeyword(),1,words);
    }
}
