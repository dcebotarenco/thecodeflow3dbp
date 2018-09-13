package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.article.Article;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.orientation.Orientation;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchingBasedOnFiles extends SharedTest {

    @Test
    public void testFiles() throws URISyntaxException {
        URL searchFolder = Thread.currentThread().getContextClassLoader().getResource("./searches");
        Path searchFolderPath = Paths.get(Objects.requireNonNull(searchFolder).toURI());

        File[] tests = Objects.requireNonNull(searchFolderPath.toFile().listFiles());
        List<Input> inputs = new ArrayList<>();
        for (File test : tests) {
            Map<Integer, List<String>> givenData = new HashMap<>();
            Map<Integer, List<String>> thenData = new HashMap<>();
            Map<Integer, List<String>> articlesData = new HashMap<>();
            Path given = test.toPath().resolve("given");
            addData(givenData, given);
            Path then = test.toPath().resolve("then");
            addData(thenData, then);
            Path articles = test.toPath().resolve("articles");
            addData(articlesData, articles);
            inputs.add(new Input(test, articlesData, givenData, thenData));
        }

        for (Input input : inputs) {
            for (Map.Entry<Integer, List<String>> givenEntry : input.getGivenData().entrySet()) {
                Integer n = givenEntry.getKey();

                List<String> articleLines = input.getArticles().get(n);
                config.getArticleRepository().clear();
                for (String articleLine : articleLines) {
                    Long id = (long) articleLines.indexOf(articleLine);
                    List<Double> aValues = Arrays.stream(articleLine.split(",")).map(Double::valueOf).collect(Collectors.toList());
                    Article article = config.getArticleFactory().create(id, aValues.get(0), aValues.get(1), aValues.get(2));
                    config.getArticleRepository().saveReceived(article);
                }

                System.out.println("Processing " + n);
                List<Double> givenValues = Arrays.stream(givenEntry.getValue().get(0).split(",")).map(Double::valueOf).collect(Collectors.toList());

                Gap maxGapImpl = gap(givenValues.get(0), givenValues.get(1), givenValues.get(2));
                Gap requiredGapImpl = gap(givenValues.get(0), givenValues.get(3), givenValues.get(4));
                SearchResult searchResult = config.getSearchingService().findBoxTypes(requiredGapImpl, maxGapImpl);
                List<String> then = input.getThenData().get(n);
                assertBestFit(then.get(0), searchResult);
                assertBestFitBiggerThenReq(then.get(1), searchResult);
            }

        }


    }

    private void assertBestFitBiggerThenReq(String s, SearchResult searchResult) {
        if (s.contains("notFound")) {
            Assert.assertFalse(searchResult.getBestFitBiggerThenRequired().isPresent());
        } else {
            List<Double> thenValues = Arrays.stream(s.split(",")).map(Double::valueOf).collect(Collectors.toList());
            Assert.assertTrue(searchResult.getBestFitBiggerThenRequired().isPresent());
            assertBestFitBiggerThenReq(searchResult, thenValues.get(0), thenValues.get(1), thenValues.get(2));
            assertBestFitMaxPosition(searchResult, thenValues.get(3), thenValues.get(4), thenValues.get(5));
        }
    }

    private void assertBestFit(String s, SearchResult searchResult) {
        if (s.contains("notFound")) {
            Assert.assertFalse(searchResult.getBestFitInRequired().isPresent());
        } else {
            List<Double> thenValues = Arrays.stream(s.split(",")).map(Double::valueOf).collect(Collectors.toList());
            Assert.assertTrue(searchResult.getBestFitInRequired().isPresent());
            assertBestFitRequired(searchResult, thenValues.get(0), thenValues.get(1), thenValues.get(2));
            assertBestFitPosition(searchResult, thenValues.get(3), thenValues.get(4), thenValues.get(5));
        }

    }

    private void addData(Map<Integer, List<String>> data, Path given) {
        Stream.of(Objects.requireNonNull(given.toFile().listFiles())).forEach(f -> {
            try {
                List<String> givenLines = Files.readAllLines(f.toPath());
                Integer n = Integer.valueOf(f.getName().split("[_.]")[1]);
                data.put(n, givenLines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void assertBestFitMaxPosition(SearchResult searchResult, Double x, Double y, Double z) {
        BestFitBiggerThenRequired bestFitBiggerThenRequired = searchResult.getBestFitBiggerThenRequired().get();
        assertPosition(bestFitBiggerThenRequired.getPosition(), x, y, z);
    }

    private void assertBestFitBiggerThenReq(SearchResult searchResult, Double w, Double h, Double l) {
        BestFitBiggerThenRequired bestFitBiggerThenRequired = searchResult.getBestFitBiggerThenRequired().get();
        assertOrientation(bestFitBiggerThenRequired.getOrientation(), w, h, l);
    }

    private void assertBestFitPosition(SearchResult searchResult, Double x, Double y, Double z) {
        BestFitInRequired bestFitInRequired = searchResult.getBestFitInRequired().get();
        assertPosition(bestFitInRequired.getPosition(), x, y, z);
    }

    private void assertBestFitRequired(SearchResult searchResult, Double w, Double h, Double l) {
        BestFitInRequired bestFitInRequired = searchResult.getBestFitInRequired().get();
        assertOrientation(bestFitInRequired.getOrientation(), w, h, l);
    }

    private void assertOrientation(Orientation orientation, Double w, Double h, Double l) {
        Double length = orientation.getLength();
        Double width = orientation.getWidth();
        Double height = orientation.getHeight();
        Assert.assertEquals(w, width);
        Assert.assertEquals(h, height);
        Assert.assertEquals(l, length);
    }

    private void assertPosition(Position position, Double x, Double y, Double z) {
        Double x1 = position.getX();
        Double y1 = position.getY();
        Double z1 = position.getZ();
        Assert.assertEquals(x, x1);
        Assert.assertEquals(y, y1);
        Assert.assertEquals(z, z1);

    }

}
