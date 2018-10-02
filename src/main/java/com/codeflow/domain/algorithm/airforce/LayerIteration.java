package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.articletype.ArticleService;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.orientation.Orientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Algorithm iteration that act 1 {@link Orientation} and 1 {@link Layer}.
 * <p>
 * {@link LayerIteration} tie closely to the six possible {@link Orientation} of a {@link ContainerType}.
 * </p>
 * During iterations, all six {@link Orientation} of the {@link ContainerType} are packed.
 * Each unique {@link Orientation} of the {@link ContainerType} is treated as a {@link ContainerType} to act.
 * Obviously, if a {@link ContainerType} has three identical dimensions,
 * it has only one {@link Orientation}. In general, we have 1,2 or 6 {@link Orientation}s for 1,2 or 3 unique
 * dimensions, respectively. In each {@link LayerIteration}, each {@link Orientation}
 * of the {@link ContainerType} is packed once for each {@link Layer}.
 * <p>
 * Each {@link LayerIteration} begins packing with an initial {@link Layer} thickness taken from {@link Layer#getHeight()} value.
 * Thus, if we have 7 different dimensions values in our {@link java.util.List}s of {@link Layer} and
 * the {@link ContainerType} has 3 unique dimensions, the
 * program potentially performs 6*7=42 {@link LayerIteration}s
 * </p>
 */
public class LayerIteration {

    private static final Logger LOGGER = LoggerFactory.getLogger(LayerIteration.class);
    private Layer layer;
    private ActionService actionService;
    private ArticleService articleService;

    LayerIteration(Layer layer,
                   ActionService actionService,
                   ArticleService articleService) {
        this.layer = layer;
        this.actionService = actionService;
        this.articleService = articleService;
    }

    void run(ContainerOrientation containerOrientation) {
        LOGGER.info("START_LAYER:{},{},{}", layer.getHeight(), layer.getLength(), layer.getEvaluationValue());
        while (!layer.isDone() && !containerOrientation.allVolumePacked() && !articleService.allPacked()) {
//            Corner cornerWithSmallestLength = topologyService.findCornerWithSmallestLength();
//            LOGGER.info("FIND_CORNER:{},{},{}:", cornerWithSmallestLength.getWidth(), cornerWithSmallestLength.getLength(), topologyService.print());
//            Situation topologySituation = topologyService.analyzeTopology(cornerWithSmallestLength);
//            actionService.act(topologySituation, cornerWithSmallestLength, containerOrientation, layer);
        }
        containerOrientation.pack(layer);
    }

    public Layer getLayer() {
        return layer;
    }
}

