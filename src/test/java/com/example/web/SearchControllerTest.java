package com.example.web;

import com.example.utils.StringDistance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class SearchControllerTest {

    SearchController controller;

    @Before
    public void setUp(){
        controller = new SearchController(new StringDistance());
    }

    @Test
    public void testWordWithNoSimilarities() throws Exception {
        // given
        Params params = new Params("test nothing similiart","word");
        Similarity expected = new Similarity("word", 0, Collections.emptyList());

        // when
        Similarity current = controller.searchSimiliars(params);

        // then
        assertThat(current,is(expected));
    }

    @Test
    public void test1WordWithSimilarities() throws Exception {
        // given
        Params params = new Params("Word Words Wor word","Word");
        Similarity expected = new Similarity("Word", 1, asList("Words", "Wor", "word"));

        // when
        Similarity current = controller.searchSimiliars(params);

        // then
        assertThat(current,is(expected));
    }

    @Test
    public void test1WordWith3Similarities() throws Exception {
        // given
        Params params = new Params("Word Word Word word","Word");
        Similarity expected = new Similarity("Word", 3, asList("word"));

        // when
        Similarity current = controller.searchSimiliars(params);

        // then
        assertThat(current,is(expected));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMissingMandatoryParameters() throws Exception {
        // given
        Params params = new Params("","");

        // when
        controller.searchSimiliars(params);

    }
}