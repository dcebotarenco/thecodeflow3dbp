package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.orientation.Orientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;

/**
 * Service creates a {@link Layer} thickness list.
 * <p>
 * This {@link List} contains every unique {@link Layer#getHeight()} of the boxes less than the {@link ContainerType#getHeight()}
 * dimension of the current {@link Orientation} of the {@link ContainerType} with their individual {@link Layer#getEvaluationValue}.
 * The {@link List} is created for each {@link Orientation} of the {@link ContainerType}.
 * Each entry is a possible {@link Layer} thickness value for iterations with the current {@link Orientation}
 * of the {@link ContainerType} to start the packing.
 * </p>
 * <p>The {@link Layer#getEvaluationValue} represents how close all other boxes are to this layer height if we selected
 * this value as a layer thickness for the packing. The model calculates these {@link Layer#getEvaluationValue} as follows:
 * </p>
 * <p>
 * Retrieve an {@link ArticleType} and one of its dimensions.
 * </p>
 * <p>
 * Examine the previously set {@link Layer#getHeight} values in the {@link List}.
 * If this is a different length and less than the {@link ContainerType#getHeight()} dimension of
 * the current {@link Orientation} of the {@link ContainerType}, store the length in a new {@link Layer} in the {@link List}.
 * </p>
 * <p>
 * Then it goes through every other {@link ArticleType} retrieving its dimension closest to the {@link Layer#getHeight}
 * value, and adds up the absolute value of the differences between that dimension and the
 * {@link Layer#getHeight}. {@link Layer#getHeight} with the smallest {@link Layer#getEvaluationValue} is the most
 * suitable layer thickness value.
 * </p>
 * <p>Since the smallest {@link Layer#getEvaluationValue} value potentially may be the most suitable layer
 * thickness value, having that list sorted and starting to act from the most promising layer
 * thickness values would be an important factor to reduce the solution time, especially if
 * we consider packing a large number of different {@link ArticleType} types. However, this greedy
 * approach does not always hold. Sometimes an iteration starting with a larger {@link Layer#getHeight}
 * value yields the best solution.
 * </p>
 */
public class LayerServiceImpl implements LayerService {

    static final Logger LOGGER = LoggerFactory.getLogger(LayerServiceImpl.class);

    @Override
    public List<Layer> listCandidates(ContainerOrientation containerOrientation, Map<ArticleType, Long> articleTypes) {
        List<Layer> layers = new ArrayList<>();
        for (Map.Entry<ArticleType, Long> articleTypeEntry : articleTypes.entrySet()) {
            ArticleType articleType = articleTypeEntry.getKey();
            List<Double> dimensions = Stream.of(articleType.getWidth(), articleType.getHeight(), articleType.getLength()).filter(d -> d <= containerOrientation.getHeight()).collect(Collectors.toList());
            Map<ArticleType, Long> otherArticleTypes = articleTypes.entrySet().stream().filter(e -> !e.getKey().equals(articleTypeEntry.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            for (Double dimension : dimensions) {
                Double eval = 0D;
                for (Map.Entry<ArticleType, Long> otherArticleTypeEntry : otherArticleTypes.entrySet()) {
                    ArticleType otherArticleType = otherArticleTypeEntry.getKey();
                    Double minDiff = Stream.of(otherArticleType.getWidth(), otherArticleType.getHeight(), otherArticleType.getLength())
                            .map(d -> Math.abs(dimension - d))
                            .sorted().findFirst().get();
                    eval = eval + (minDiff * otherArticleTypeEntry.getValue());
                }
                layers.add(new LayerImpl(dimension, containerOrientation.getLength(), eval));
            }
        }
        layers.sort(Comparator.comparingDouble(Layer::getEvaluationValue).thenComparing(reverseOrder(Comparator.comparingDouble(Layer::getHeight))));
        return layers;
    }

    /**
     * Having up to 6 possible {@link Orientation} we try to run through all of them to see if current {@link ArticleType} have an {@link Orientation}
     * that might fit in empty {@link ContainerType}.
     *
     * @param a Current {@link ArticleType} checked
     * @return list of {@link Orientation} of in what current {@link ArticleType} can fil the {@link ContainerType}
     */


    private List<ArticleOrientation> getOrientationsOfArticleThatFitTheHeightAndContainer(ContainerOrientation containerOrientation, Double height, ArticleType a) {
        return a.getOrientations().stream().filter(articleOrientation ->
                articleOrientation.getWidth() <= containerOrientation.getWidth() &&
                        articleOrientation.getHeight() <= height &&
                        articleOrientation.getLength() <= containerOrientation.getLength()).collect(Collectors.toList());
    }

    private Double getSmallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions(Orientation orientationOfCurrentArticle, ArticleOrientation otherArticle) {
        Double smallestDiff;
        Double orientationHeightDiffAgainstArticleWidth = Math.abs(orientationOfCurrentArticle.getHeight() - otherArticle.getWidth());
        Double orientationHeightDiffAgainstArticleHeight = Math.abs(orientationOfCurrentArticle.getHeight() - otherArticle.getHeight());
        Double orientationHeightDiffAgainstArticleLength = Math.abs(orientationOfCurrentArticle.getHeight() - otherArticle.getLength());
        smallestDiff = orientationHeightDiffAgainstArticleWidth;
        if (orientationHeightDiffAgainstArticleHeight < smallestDiff) {
            smallestDiff = orientationHeightDiffAgainstArticleHeight;
        }
        if (orientationHeightDiffAgainstArticleLength < smallestDiff) {
            smallestDiff = orientationHeightDiffAgainstArticleLength;
        }
        return smallestDiff;
    }

    @Override
    public Optional<Layer> findLayer(ContainerOrientation containerOrientation, Double height, Map<ArticleType, Long> articleTypes) {
//        Set<Layer> layers = new HashSet<>();
//        for (Article currentArticle : articles) {
//            List<ArticleOrientation> orientationsOfArticleThatFixTheContainer = getOrientationsOfArticleThatFitTheHeightAndContainer(containerOrientation, height, currentArticle);
//            if (orientationsOfArticleThatFixTheContainer.size() > 0) {
//                List<Article> otherArticlesExceptCurrent = articles.stream().filter(otherArticle -> !otherArticle.equals(currentArticle)).collect(Collectors.toList());
//                for (Orientation orientationOfCurrentArticle : orientationsOfArticleThatFixTheContainer) {
//                    Double evaluationValue = 0.;
//                    for (Article otherArticle : otherArticlesExceptCurrent) {
//                        Double smallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions = getSmallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions(orientationOfCurrentArticle, otherArticle);
//                        evaluationValue = evaluationValue + smallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions;
//                    }
//                    Layer layer = new LayerImpl(orientationOfCurrentArticle.getHeight(), containerOrientation.getLength(), evaluationValue);
////                    LOGGER.info("layer {}", layer);
//                    layers.add(layer);
//                }
//            }
//        }

        Set<Layer> layers = new HashSet<>();
//        for (ArticleType articleType : articleTypes) {
//            List<ArticleOrientation> orientationsOfArticleThatFixTheContainer = getOrientationsOfArticleThatFitTheHeightAndContainer(containerOrientation, height, articleType);
//            if (orientationsOfArticleThatFixTheContainer.size() > 0) {
//                for (ArticleOrientation articleOrientation : orientationsOfArticleThatFixTheContainer) {
//                    List<ArticleOrientation> otherArticleOrientationsExceptCurrent = orientationsOfArticleThatFixTheContainer.stream().filter(otherArticle -> !otherArticle.equals(articleOrientation)).collect(Collectors.toList());
//                    Double evaluationValue = 0.;
//                    for (ArticleOrientation otherArticleOrientation : otherArticleOrientationsExceptCurrent) {
//                        Double smallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions = getSmallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions(articleOrientation, otherArticleOrientation);
//                        evaluationValue = evaluationValue + smallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions;
//                    }
//                    Layer layer = new LayerImpl(articleOrientation.getHeight(), containerOrientation.getLength(), evaluationValue);
//                    layers.add(layer);
//                }
//            }
//        }
        ArrayList<Layer> list = new ArrayList<>(layers);
//        list.sort(Comparator.comparingDouble(Layer::getEvaluationValue).thenComparing(reverseOrder(Comparator.comparingDouble(Layer::getHeight))));
        list.sort(Comparator.comparingDouble(Layer::getEvaluationValue).thenComparing(Layer::getHeight));
//        LOGGER.info("Size of list {}", list.size());
        return list.stream().findFirst();
    }
}
