package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.boxes.Gap;
import com.codeflow.domain.boxes.Orientation;
import org.junit.Assert;
import org.junit.Test;

public class SearchingServiceTest_dpp01_all_searches extends SharedTest {

    @Test
    public void findBoxTypes1() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 96, 84);
        Gap requiredGap = gap(104, 24, 84);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        assertBestFitPosition(searchResult, 0, 0, 14);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
        assertBestFitMaxPosition(searchResult, 0, 24, 70);
    }

    @Test
    public void findBoxTypes2() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 96, 14);
        Gap requiredGap = gap(104, 24, 14);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
        assertBestFitMaxPosition(searchResult, 0, 24, 0);
    }

    @Test
    public void findBoxTypes3() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 96, 0);
        Gap requiredGap = gap(104, 48, 0);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes4() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 24, 70);
        Gap requiredGap = gap(104, 24, 70);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        assertBestFitPosition(searchResult, 0, 0, 0);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes5() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 24, 0);
        Gap requiredGap = gap(104, 24, 0);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes6() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 48, 84);
        Gap requiredGap = gap(104, 24, 84);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        assertBestFitPosition(searchResult, 0, 0, 14);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
        assertBestFitMaxPosition(searchResult, 0, 24, 70);
    }

    @Test
    public void findBoxTypes7() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 48, 14);
        Gap requiredGap = gap(104, 24, 14);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
        assertBestFitMaxPosition(searchResult, 0, 24, 0);
    }

    @Test
    public void findBoxTypes8() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 48, 0);
        Gap requiredGap = gap(104, 48, 0);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes9() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 24, 70);
        Gap requiredGap = gap(104, 24, 70);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        assertBestFitPosition(searchResult, 0, 0, 0);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes10() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 24, 0);
        Gap requiredGap = gap(104, 24, 0);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes11_best_i() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 96, 84);
        Gap requiredGap = gap(104, 24, 84);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        assertBestFitPosition(searchResult, 0, 0, 14);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
        assertBestFitMaxPosition(searchResult, 0, 24, 70);
    }

    @Test
    public void findBoxTypes12_best_i() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 96, 14);
        Gap requiredGap = gap(104, 24, 14);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
        assertBestFitMaxPosition(searchResult, 0, 24, 0);
    }

    @Test
    public void findBoxTypes13_best_id() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 96, 0);
        Gap requiredGap = gap(104, 48, 0);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes14_best_id() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 24, 70);
        Gap requiredGap = gap(104, 24, 70);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        assertBestFitPosition(searchResult, 0, 0, 0);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes15_best_id() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 24, 0);
        Gap requiredGap = gap(104, 24, 0);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes16_best_id() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 48, 84);
        Gap requiredGap = gap(104, 24, 84);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        assertBestFitPosition(searchResult, 0, 0, 14);
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
        assertBestFitMaxPosition(searchResult, 0, 24, 70);
    }

    @Test
    public void findBoxTypes17_best_id() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 48, 14);
        Gap requiredGap = gap(104, 24, 14);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
        assertBestFitMax(searchResult, 104, 48, 14);
        assertBestFitMaxPosition(searchResult, 0, 24, 0);
    }

    @Test
    public void findBoxTypes18_best_id() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 48, 0);
        Gap requiredGap = gap(104, 48, 0);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes19_best_id() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 24, 70);
        Gap requiredGap = gap(104, 24, 70);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
        assertBestFitRequired(searchResult, 104, 24, 70);
        assertBestFitPosition(searchResult, 0, 0, 0);
        Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
    }

    @Test
    public void findBoxTypes20_best_id() {
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        Gap maxGap = gap(104, 24, 0);
        Gap requiredGap = gap(104, 24, 0);
        SearchResult searchResult = searchingService.findBoxTypes(requiredGap, maxGap);
        Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
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