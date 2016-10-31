package com.example.web;

import com.example.utils.StringDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SearchController {

    private StringDistance stringDistance;

    @Autowired
    public SearchController(StringDistance  stringDistance) {
        this.stringDistance = stringDistance;
    }

    @RequestMapping(value = "/similars", method = GET)
    public Similarity searchSimiliars(@RequestBody Params params) {

        checkMandatoryParameters(params);

        List<String> words = asList(params.getNotebookEntry().split(" "));
        String keyword = params.getKeyword();

        Map<Integer, List<String>> similarites =
                words
                        .stream()
                        .parallel()
                        .collect(
                                groupingBy(w -> stringDistance.getLevenshteinDistance(w, keyword)));

        List<String> wordsWithSimilarity =
                similarites
                        .entrySet()
                        .stream()
                        .parallel()
                        .filter(e -> e.getKey() == 1)
                        .flatMap(e -> e.getValue().stream())
                        .collect(toList());

        return new Similarity(params.getKeyword(), similarites.getOrDefault(0, emptyList()).size(), wordsWithSimilarity);
    }

    private void checkMandatoryParameters(@RequestBody Params params) {
        String notebookEntry = params.getNotebookEntry();
        String keyword = params.getKeyword();
        if (keyword == null || keyword.isEmpty() || notebookEntry == null || notebookEntry.isEmpty())
            throw new IllegalArgumentException();
    }

    // HTTP 422
    @ResponseStatus(value = UNPROCESSABLE_ENTITY, reason = "Parameters [notebook_entry | keyword] can't be empty or null")
    @ExceptionHandler(IllegalArgumentException.class)
    public void missingMandatoryParameters() {
    }
}
