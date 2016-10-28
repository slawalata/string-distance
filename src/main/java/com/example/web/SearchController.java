package com.example.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static jersey.repackaged.com.google.common.collect.ImmutableList.of;

@RestController
public class SearchController {

    @RequestMapping("/search")
    public Result search() {
        return new Result("hello", 1, of("world"));
    }
}
