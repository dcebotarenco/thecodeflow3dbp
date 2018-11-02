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

public class SearchingServiceTest_dpp01_all_searches extends SharedTest {

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
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 96, 84);
        Gap requiredGap = gap(104, 24, 84);
        SearchingServiceImpl searchingService = new SearchingServiceImpl(articleTypes);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
    }

    @Test
    public void findBoxTypes2() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 96, 14);
        Gap requiredGap = gap(104, 24, 14);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
    }

    @Test
    public void findBoxTypes3() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 96, 0);
        Gap requiredGap = gap(104, 48, 0);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes4() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 24, 70);
        Gap requiredGap = gap(104, 24, 70);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes5() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 24, 0);
        Gap requiredGap = gap(104, 24, 0);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes6() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 48, 84);
        Gap requiredGap = gap(104, 24, 84);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
    }

    @Test
    public void findBoxTypes7() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 48, 14);
        Gap requiredGap = gap(104, 24, 14);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
    }

    @Test
    public void findBoxTypes8() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 48, 0);
        Gap requiredGap = gap(104, 48, 0);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes9() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 24, 70);
        Gap requiredGap = gap(104, 24, 70);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes10() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 24, 0);
        Gap requiredGap = gap(104, 24, 0);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes11_best_i() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 96, 84);
        Gap requiredGap = gap(104, 24, 84);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
    }

    @Test
    public void findBoxTypes12_best_i() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 96, 14);
        Gap requiredGap = gap(104, 24, 14);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
    }

    @Test
    public void findBoxTypes13_best_id() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 96, 0);
        Gap requiredGap = gap(104, 48, 0);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes14_best_id() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 24, 70);
        Gap requiredGap = gap(104, 24, 70);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes15_best_id() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 24, 0);
        Gap requiredGap = gap(104, 24, 0);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes16_best_id() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 48, 84);
        Gap requiredGap = gap(104, 24, 84);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
    }

    @Test
    public void findBoxTypes17_best_id() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 48, 14);
        Gap requiredGap = gap(104, 24, 14);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
    }

    @Test
    public void findBoxTypes18_best_id() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 48, 0);
        Gap requiredGap = gap(104, 48, 0);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes19_best_id() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 24, 70);
        Gap requiredGap = gap(104, 24, 70);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes20_best_id() {
        articleTypes(70, 104, 24);
        articleTypes(14, 104, 48);
        Gap maxGap = gap(104, 24, 0);
        Gap requiredGap = gap(104, 24, 0);
        SearchResult searchResult = new SearchingServiceImpl(articleTypes).findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
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