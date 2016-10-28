package com.example.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class SearchController {

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public Result search(@RequestBody Request request) {

        List<String> words = asList(request.getNotebookEntry().split(" "));

        if (words.isEmpty()) return new Result();

        return new Result(request.getKeyword(),1,words);
    }
}
