package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.article.Article;
import com.codeflow.domain.container.Container;
import com.codeflow.domain.orientation.Orientation;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

/**
 * Service creates a {@link LayerImpl} thickness list.
 * <p>
 * This {@link List} contains every unique {@link LayerImpl#dimension} of the boxes less than the {@link Container#getHeight()}
 * dimension of the current {@link Orientation} of the {@link Container} with their individual {@link LayerImpl#evaluationValue}.
 * The {@link List} is created for each {@link Orientation} of the {@link Container}.
 * Each entry is a possible {@link LayerImpl} thickness value for iterations with the current {@link Orientation}
 * of the {@link Container} to start the packing.
 * </p>
 * <p>The {@link LayerImpl#evaluationValue} represents how close all other boxes are to this layer height if we selected
 * this value as a layer thickness for the packing. The model calculates these {@link LayerImpl#evaluationValue} as follows:
 * </p>
 * <p>
 * Retrieve an {@link Article} and one of its dimensions.
 * </p>
 * <p>
 * Examine the previously set {@link LayerImpl#dimension} values in the {@link List}.
 * If this is a different length and less than the {@link Container#getHeight()} dimension of
 * the current {@link Orientation} of the {@link Container}, store the length in a new {@link LayerImpl} in the {@link List}.
 * </p>
 * <p>
 * Then it goes through every other {@link Article} retrieving its dimension closest to the {@link LayerImpl#dimension}
 * value, and adds up the absolute value of the differences between that dimension and the
 * {@link LayerImpl#dimension}. {@link LayerImpl#dimension} with the smallest {@link LayerImpl#evaluationValue} is the most
 * suitable layer thickness value.
 * </p>
 * <p>Since the smallest {@link LayerImpl#evaluationValue} value potentially may be the most suitable layer
 * thickness value, having that list sorted and starting to act from the most promising layer
 * thickness values would be an important factor to reduce the solution time, especially if
 * we consider packing a large number of different {@link Article} types. However, this greedy
 * approach does not always hold. Sometimes an iteration starting with a larger {@link LayerImpl#dimension}
 * value yields the best solution.
 * </p>
 */
class LayerServiceImpl implements LayerService {

    @Override
    public List<Layer> listCandidates(Orientation containerOrientation, List<Article> articleImpls) {
        Set<Layer> layers = new HashSet<>();
        for (Article currentArticle : articleImpls) {
            List<Orientation> orientationsOfArticleThatFixTheContainer = getOrientationsOfArticleThatFixTheContainer(containerOrientation, currentArticle);
            if (orientationsOfArticleThatFixTheContainer.size() > 0) {
                List<Article> otherArticlesExceptCurrent = articleImpls.stream().filter(otherArticle -> !otherArticle.equals(currentArticle)).collect(Collectors.toList());
                for (Orientation orientationOfCurrentArticle : orientationsOfArticleThatFixTheContainer) {
                    Double evaluationValue = 0.;
                    for (Article otherArticle : otherArticlesExceptCurrent) {
                        Double smallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions = getSmallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions(orientationOfCurrentArticle, otherArticle);
                        evaluationValue = evaluationValue + smallestDifferenceOfCurrentOrientationHeightAgainstArticleDimensions;
                    }
                    layers.add(new LayerImpl(orientationOfCurrentArticle.getHeight(), evaluationValue));
                }
            }
        }
        ArrayList<Layer> list = new ArrayList<>(layers);
        list.sort(Comparator.comparingDouble(Layer::getEvaluationValue).thenComparing(reverseOrder(Comparator.comparingDouble(Layer::getDimension))));
        return list;
    }

    /**
     * Having up to 6 possible {@link Orientation} we try to run through all of them to see if current {@link Article} have an {@link Orientation}
     * that might fit in empty {@link Container}.
     *
     * @param a Current {@link Article} checked
     * @return list of {@link Orientation} of in what current {@link Article} can fil the {@link Container}
     */
    private List<Orientation> getOrientationsOfArticleThatFixTheContainer(Orientation containerOrientation, Article a) {
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
