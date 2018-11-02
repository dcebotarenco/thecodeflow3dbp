package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.ArticleTypeImpl;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.ContainerTypeImpl;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.orientation.Orientation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SearchingServiceTest_dpp02_searches extends SharedTest {

    List<ArticleType> articleTypes;
    ContainerType container;

    @Before
    public void clearRepos() {
        articleTypes = new ArrayList<>();
    }

    void articleTypes(Integer w, Integer h, Integer l) {
        articleTypes.add(new ArticleTypeImpl(w, h, l));
    }

    ContainerType container(int w, int h, int l) {
        container = new ContainerTypeImpl(w, h, l);
        return container;
    }
    
    
    @Test
    public void findBoxTypes1() {
        articleTypes(70, 50, 24);
        articleTypes(70, 54, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 96, 84);
        Gap requiredGap = gap(104, 50, 84);

        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 70, 50, 24);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 70, 54, 24);
    }

    @Test
    public void findBoxTypes2() {
        articleTypes(70, 50, 24);
        articleTypes(70, 54, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(34, 96, 84);
        Gap requiredGap = gap(34, 50, 24);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 24, 50, 70);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 24, 54, 70);
    }

    @Test
    public void findBoxTypes128() {
//        70.0,50.0,24.0
//        70.0,50.0,24.0
//        70.0,50.0,24.0
//        70.0,50.0,24.0
//        70.0,54.0,24.0
//        70.0,54.0,24.0
//        70.0,54.0,24.0
//        14.0,104.0,48.0
//        14.0,104.0,48.0

        articleTypes(70, 50, 24);
        articleTypes(70, 54, 24);
        articleTypes(14, 104, 48);
//        30.0,96.0,104.0,70.0,24.0
        Gap maxGap = gap(30.0, 96.0, 104.0);
        Gap requiredGap = gap(30.0, 70., 24.);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
//        24.0,70.0,50.0,6.0,0.0,26.0
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 24, 70, 50);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }


    private void assertBestFitMax(SearchResult searchResult, Integer w, Integer h, Integer l) {
        assertOrientation(searchResult.getBestFitBiggerThenRequired().get(), w, h, l);
    }


    private void assertBestFitRequired(SearchResult searchResult, Integer w, Integer h, Integer l) {
        assertOrientation(searchResult.getBestFitInRequired().get(), w, h, l);
    }

    private void assertOrientation(Orientation orientation, Integer w, Integer h, Integer l) {
        Double length = orientation.getLength();
        Double width = orientation.getWidth();
        Double height = orientation.getHeight();
        Assert.assertEquals(new Double(w), width);
        Assert.assertEquals(new Double(h), height);
        Assert.assertEquals(new Double(l), length);
    }

}