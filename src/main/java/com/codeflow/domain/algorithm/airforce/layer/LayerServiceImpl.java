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

    /**
     * Having up to 6 possible {@link Orientation} we try to run through all of them to see if current {@link ArticleType} have an {@link Orientation}
     * that might fit in empty {@link ContainerType}.
     *
     * @return list of {@link Orientation} of in what current {@link ArticleType} can fil the {@link ContainerType}
     */
    public List<Layer> listCandidates(ContainerOrientation containerOrientation, Map<ArticleType, Long> articleTypes) {
        List<Layer> layers = new ArrayList<>();
        List<Double> distinctHeights = articleTypes.entrySet().stream().flatMap(e -> e.getKey().getOrientations().stream())
                .filter(containerOrientation::fit)
                .map(Orientation::getHeight).distinct()
                .collect(Collectors.toList());

        for (Double height : distinctHeights) {
            Double eval = articleTypes.entrySet().stream().map(entry -> Stream.of(entry.getKey().getWidth(), entry.getKey().getHeight(), entry.getKey().getLength())
                    .map(d -> Math.abs(height - d))
                    .sorted().findFirst().get() * entry.getValue()).reduce((d1, d2) -> d1 + d2).orElse(0D);
            layers.add(new LayerImpl(height, containerOrientation.getLength(), eval));
        }
        layers.sort(Comparator.comparingDouble(Layer::getEvaluationValue).thenComparing(reverseOrder(Comparator.comparingDouble(Layer::getHeight))));
        return layers;
    }


    @Override
    public Optional<Layer> findLayer(ContainerOrientation containerOrientation, Double requiredHeight, Map<ArticleType, Long> articleTypes) {
        //TODO: Find layer is influenced by order of article types in the file. Preferable to remove Linked Sets.
        List<Layer> layers = new ArrayList<>();

        List<Double> collect = articleTypes.keySet().stream().flatMap(a -> Stream.of(a.getWidth(), a.getHeight(), a.getLength())).distinct().collect(Collectors.toList());

        List<ArticleOrientation> articleOrientations = articleTypes.entrySet().stream().flatMap(e -> e.getKey().getOrientations().stream())
                .filter(o -> o.getWidth() <= containerOrientation.getWidth() &&
                        o.getHeight() <= requiredHeight &&
                        o.getLength() <= containerOrientation.getLength()).collect(Collectors.toList());

        List<Double> distinctHeights = articleOrientations.stream().map(Orientation::getHeight).distinct()
                .collect(Collectors.toList());

        for (Double height : distinctHeights) {
            Double eval = articleTypes.entrySet().stream()
                    .map(entry -> {
                        double v = Stream.of(entry.getKey().getWidth(), entry.getKey().getHeight(), entry.getKey().getLength())
                                .map(d -> Math.abs(height - d))
                                .sorted().findFirst().get();
                        return v * entry.getValue();
                    }).reduce((d1, d2) -> d1 + d2).orElse(0D);
            layers.add(new LayerImpl(height, containerOrientation.getLength(), eval));
        }
        layers.sort(Comparator.comparingDouble(Layer::getEvaluationValue).thenComparingDouble(l -> collect.indexOf(l.getHeight())));
        return layers.stream().findFirst();
    }
}
