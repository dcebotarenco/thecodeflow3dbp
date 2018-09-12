package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.boxes.Gap;
import com.codeflow.domain.boxes.Orientation;
import org.junit.Assert;
import org.junit.Test;

public class SearchingServiceTest_dpp02_searches extends SharedTest {

    @Test
    public void findBoxTypes1() {
        articles(70, 50, 24, 4);
        articles(70, 54, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 96, 84);
        Gap requiredGap = gap(104, 50, 84);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 70, 50, 24);
        assertBestFitPosition(searchResult, 34, 0, 60);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 70, 54, 24);
        assertBestFitMaxPosition(searchResult, 34, 4, 60);
    }

    @Test
    public void findBoxTypes2() {
        articles(70, 50, 24, 4);
        articles(70, 54, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(34, 96, 84);
        Gap requiredGap = gap(34, 50, 24);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 24, 50, 70);
        assertBestFitPosition(searchResult, 10, 0, 46);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 24, 54, 70);
        assertBestFitMaxPosition(searchResult, 10, 4, 46);
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

        articles(70, 50, 24, 4);
        articles(70, 54, 24, 3);
        articles(14, 104, 48, 2);
//        30.0,96.0,104.0,70.0,24.0
        Gap maxGap = gap(30.0, 96.0, 104.0);
        Gap requiredGap = gap(30.0, 70., 24.);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
//        24.0,70.0,50.0,6.0,0.0,26.0
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 24, 70, 50);
        assertBestFitPosition(searchResult, 6, 0, 26);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }


    private void assertBestFitMaxPosition(SearchResult searchResult, Integer x, Integer y, Integer z) {
        BestFitBiggerThenRequired bestFitBiggerThenRequired = searchResult.getBestFitBiggerThenRequired().get();
        assertPosition(bestFitBiggerThenRequired.getPosition(), x, y, z);
    }

    private void assertBestFitMax(SearchResult searchResult, Integer w, Integer h, Integer l) {
        BestFitBiggerThenRequired bestFitBiggerThenRequired = searchResult.getBestFitBiggerThenRequired().get();
        assertOrientation(bestFitBiggerThenRequired.getOrientation(), w, h, l);
    }

    private void assertBestFitPosition(SearchResult searchResult, Integer x, Integer y, Integer z) {
        BestFitInRequired bestFitInRequired = searchResult.getBestFitInRequired().get();
        assertPosition(bestFitInRequired.getPosition(), x, y, z);
    }

    private void assertBestFitRequired(SearchResult searchResult, Integer w, Integer h, Integer l) {
        BestFitInRequired bestFitInRequired = searchResult.getBestFitInRequired().get();
        assertOrientation(bestFitInRequired.getOrientation(), w, h, l);
    }

    private void assertOrientation(Orientation orientation, Integer w, Integer h, Integer l) {
        Double length = orientation.getLength();
        Double width = orientation.getWidth();
        Double height = orientation.getHeight();
        Assert.assertEquals(new Double(w), width);
        Assert.assertEquals(new Double(h), height);
        Assert.assertEquals(new Double(l), length);
    }

    private void assertPosition(Position position, Integer x, Integer y, Integer z) {
        Double x1 = position.getX();
        Double y1 = position.getY();
        Double z1 = position.getZ();
        Assert.assertEquals(new Double(x), x1);
        Assert.assertEquals(new Double(y), y1);
        Assert.assertEquals(new Double(z), z1);

    }
}