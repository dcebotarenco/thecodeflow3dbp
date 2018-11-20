package com.codeflow.domain.iteration;

import com.codeflow.domain.algorithm.airforce.PackLayerAttempt;
import com.codeflow.domain.algorithm.airforce.PackLayerAttemptInput;
import com.codeflow.domain.algorithm.airforce.PackLayerAttemptResult;
import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.searching.SearchResult;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingServiceImpl;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.iteration.stock.IterationStock;
import com.codeflow.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Iteration {
    private final Layer layer;
    private final ContainerOrientation containerOrientation;
    private final IterationStock iterationStock;
    private boolean hundredPercentPacked;

    //Mutable
    private boolean packing;

    public Iteration(IterationStock iterationStock,
                     Layer layer,
                     ContainerOrientation containerOrientation) {
        this.iterationStock = iterationStock;
        this.hundredPercentPacked = false;
        this.layer = layer;
        this.containerOrientation = containerOrientation;
    }

    public void execute() {

        double requiredLayerThicknessToPack = layer.getHeight();
        this.packing = true;

        Double totalArticlesVolume = iterationStock.totalVolume();

        do {
            PackLayerAttemptInput packLayerAttemptInput = new PackLayerAttemptInput(this,
                    requiredLayerThicknessToPack,
                    layer.getLength(),
                    containerOrientation.getRemainHeight(),
                    containerOrientation.getPackedHeight(),
                    iterationStock.getPackedVolume(),
                    containerOrientation);
            PackLayerAttempt packLayerAttempt = new PackLayerAttempt(packLayerAttemptInput);
            PackLayerAttemptResult attemptResult = packLayerAttempt.start();
            analyzePackLayerResult(attemptResult, totalArticlesVolume);

            containerOrientation.pack(attemptResult.foundArticleHeightBiggerThenRequired);

            if (attemptResult.layerinlayer != 0) {
                double containerRemainingHeight = attemptResult.foundArticleHeightBiggerThenRequired - requiredLayerThicknessToPack;
                double containerPackedHeight = containerOrientation.getPackedHeight() - attemptResult.foundArticleHeightBiggerThenRequired + requiredLayerThicknessToPack;
                PackLayerAttemptInput attemptInput = new PackLayerAttemptInput(this,
                        attemptResult.layerinlayer,
                        attemptResult.lilz,
                        containerRemainingHeight,
                        containerPackedHeight,
                        iterationStock.getPackedVolume(), containerOrientation);
                PackLayerAttempt layerAttempt = new PackLayerAttempt(attemptInput);
                analyzePackLayerResult(layerAttempt.start(), totalArticlesVolume);
            }

            Optional<Layer> foundLayer = iterationStock.findNextLayer(containerOrientation, containerOrientation.getRemainHeight());
            if (!foundLayer.isPresent()) {
//                System.out.println(containerOrientation.getRemainHeight() + "FOUND LAYER:" + 0.);
                break;
            } else {
                requiredLayerThicknessToPack = foundLayer.get().getHeight();
//                System.out.println(containerOrientation.getRemainHeight() + "FOUND LAYER:" + requiredLayerThicknessToPack);
                if (requiredLayerThicknessToPack > containerOrientation.getRemainHeight()) {
                    break;
                }
            }

        } while (packing);
    }

    private void analyzePackLayerResult(PackLayerAttemptResult layerAttemptResult, Double totalVolume) {
        if (layerAttemptResult.packedVolume == containerOrientation.getVolume() || layerAttemptResult.packedVolume == totalVolume) {
            packing = false;
            hundredPercentPacked = true;
        }
    }

    public void pack(ArticleOrientation foundArticle, Position position) {
        iterationStock.pack(foundArticle, position);
//        System.out.println(String.format("PACK: %s %s %s %s %s %s", position.getX(), position.getY(), position.getZ(), foundArticle.getWidth(), foundArticle.getHeight(), foundArticle.getLength()));
    }

    public Map<Position, ArticleOrientation> getPackedArticles() {
        return iterationStock.translate(containerOrientation.getTranslator());
    }

    public double getPackedVolume() {
        return iterationStock.getPackedVolume();
    }

    public boolean isHundredPercentPacked() {
        return hundredPercentPacked;
    }


    public SearchResult findBoxTypes(List<ArticleType> articleTypes, Gap requiredGapImpl, Gap maxGapImpl) {
        SearchingService searchingService = new SearchingServiceImpl(articleTypes);
        return searchingService.findBoxTypes(requiredGapImpl, maxGapImpl);
    }

    public IterationStock getIterationStock() {
        return iterationStock;
    }
}
