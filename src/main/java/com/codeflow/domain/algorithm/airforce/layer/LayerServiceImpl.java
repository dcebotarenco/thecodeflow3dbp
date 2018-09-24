package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.article.Article;
import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.container.Container;
import com.codeflow.domain.container.orientation.ContainerOrientation;
import com.codeflow.domain.orientation.Orientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

/**
 * Service creates a {@link Layer} thickness list.
 * <p>
 * This {@link List} contains every unique {@link Layer#getHeight()} of the boxes less than the {@link Container#getHeight()}
 * dimension of the current {@link Orientation} of the {@link Container} with their individual {@link Layer#getEvaluationValue}.
 * The {@link List} is created for each {@link Orientation} of the {@link Container}.
 * Each entry is a possible {@link Layer} thickness value for iterations with the current {@link Orientation}
 * of the {@link Container} to start the packing.
 * </p>
 * <p>The {@link Layer#getEvaluationValue} represents how close all other boxes are to this layer height if we selected
 * this value as a layer thickness for the packing. The model calculates these {@link Layer#getEvaluationValue} as follows:
 * </p>
 * <p>
 * Retrieve an {@link Article} and one of its dimensions.
 * </p>
 * <p>
 * Examine the previously set {@link Layer#getHeight} values in the {@link List}.
 * If this is a different length and less than the {@link Container#getHeight()} dimension of
 * the current {@link Orientation} of the {@link Container}, store the length in a new {@link Layer} in the {@link List}.
 * </p>
 * <p>
 * Then it goes through every other {@link Article} retrieving its dimension closest to the {@link Layer#getHeight}
 * value, and adds up the absolute value of the differences between that dimension and the
 * {@link Layer#getHeight}. {@link Layer#getHeight} with the smallest {@link Layer#getEvaluationValue} is the most
 * suitable layer thickness value.
 * </p>
 * <p>Since the smallest {@link Layer#getEvaluationValue} value potentially may be the most suitable layer
 * thickness value, having that list sorted and starting to act from the most promising layer
 * thickness values would be an important factor to reduce the solution time, especially if
 * we consider packing a large number of different {@link Article} types. However, this greedy
 * approach does not always hold. Sometimes an iteration starting with a larger {@link Layer#getHeight}
 * value yields the best solution.
 * </p>
 */
class LayerServiceImpl implements LayerService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LayerServiceImpl.class);
    private LayerFactory<Layer> layerFactory;

    LayerServiceImpl(LayerFactory<Layer> layerFactory) {
        this.layerFactory = layerFactory;
    }

    @Override
    public List<Layer> listCandidates(ContainerOrientation containerOrientation, List<Article> articles) {
        LOGGER.info("List candidates for containerOrientation {} and articles {}", containerOrientation, articles);
        Set<Layer> layers = new HashSet<>();
        for (Article currentArticle : articles) {
            List<ArticleOrientation> orientationsOfArticleThatFixTheContainer = getOrientationsOfArticleThatFixTheContainer(containerOrientation, currentArticle);
            if (orientationsOfArticleThatFixTheContainer.size() > 0) {
                List<Article> otherArticlesExceptCurrent = articles.stream().filter(otherArticle -> !otherArticle.equals(currentArticle)).collect(Collectors.toList());
                for (Orientation orientationOfCurrentArticle : orientationsOfArticleThatFixTheContainer) {
                    Double evaluationValue = 0.;
                    for (Article otherArticle : otherArticlesExceptCurrent) {
                        Double smallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions = getSmallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions(orientationOfCurrentArticle, otherArticle);
                        evaluationValue = evaluationValue + smallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions;
                    }
                    Layer layer = layerFactory.create(orientationOfCurrentArticle.getHeight(), containerOrientation.getLength(), evaluationValue);
                    LOGGER.info("layer {}", layer);
                    layers.add(layer);
                }
            }
        }
        ArrayList<Layer> list = new ArrayList<>(layers);
        list.sort(Comparator.comparingDouble(Layer::getEvaluationValue).thenComparing(reverseOrder(Comparator.comparingDouble(Layer::getHeight))));
        LOGGER.info("Size of list {}", list.size());
        return list;
    }

    /**
     * Having up to 6 possible {@link Orientation} we try to run through all of them to see if current {@link Article} have an {@link Orientation}
     * that might fit in empty {@link Container}.
     *
     * @param a Current {@link Article} checked
     * @return list of {@link Orientation} of in what current {@link Article} can fil the {@link Container}
     */
    private List<ArticleOrientation> getOrientationsOfArticleThatFixTheContainer(ContainerOrientation containerOrientation, Article a) {
        return a.getOrientations().stream().filter(containerOrientation::fit).collect(Collectors.toList());
    }

    private Double getSmallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions(Orientation orientationOfCurrentArticle, Article otherArticle) {
        Double smallestDiff;
        Double orientationHeightDiffAgainstArticleWidth = Math.abs(orientationOfCurrentArticle.getHeight() - otherArticle.getDimensions().getWidth());
        Double orientationHeightDiffAgainstArticleHeight = Math.abs(orientationOfCurrentArticle.getHeight() - otherArticle.getDimensions().getHeight());
        Double orientationHeightDiffAgainstArticleLength = Math.abs(orientationOfCurrentArticle.getHeight() - otherArticle.getDimensions().getLength());
        smallestDiff = orientationHeightDiffAgainstArticleWidth;
        if (orientationHeightDiffAgainstArticleHeight < smallestDiff) {
            smallestDiff = orientationHeightDiffAgainstArticleHeight;
        }
        if (orientationHeightDiffAgainstArticleLength < smallestDiff) {
            smallestDiff = orientationHeightDiffAgainstArticleLength;
        }
        return smallestDiff;
    }
}
