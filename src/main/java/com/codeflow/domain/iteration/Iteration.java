package com.codeflow.domain.iteration;

import com.codeflow.domain.algorithm.airforce.PackLayerAttempt;
import com.codeflow.domain.algorithm.airforce.PackLayerAttemptInput;
import com.codeflow.domain.algorithm.airforce.PackLayerAttemptResult;
import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
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
    private final LayerService layerService;
    private final Layer layer;
    private final ContainerOrientation containerOrientation;
    private final IterationStock iterationStock;
    private boolean hundredPercentPacked;

    //Mutable
    private boolean packing;

    public Iteration(IterationStock iterationStock,
                     Layer layer,
                     ContainerOrientation containerOrientation,
                     LayerService layerService) {
        this.iterationStock = iterationStock;
        this.hundredPercentPacked = false;
        this.layer = layer;
        this.containerOrientation = containerOrientation;
        this.layerService = layerService;
    }

    public void execute() {

        double layerThickness = layer.getHeight();
        double containerRemainingLength = containerOrientation.getLength();
        double containerRemainingHeight = containerOrientation.getHeight();
        double packedHeight = 0;
        double prepackedy;
        double preremainpy;
        this.packing = true;

        Double totalArticlesVolume = iterationStock.totalVolume();

        do {
            PackLayerAttemptInput packLayerAttemptInput = new PackLayerAttemptInput(this,
                    layerThickness,
                    containerRemainingLength,
                    containerRemainingHeight,
                    packedHeight,
                    iterationStock.getPackedVolume(),
                    containerOrientation);
            PackLayerAttempt packLayerAttempt = new PackLayerAttempt(packLayerAttemptInput);
            PackLayerAttemptResult attemptResult = packLayerAttempt.start();
            analyzePackLayerResult(attemptResult, totalArticlesVolume);

            packedHeight = packedHeight + attemptResult.layerThickness;
            containerRemainingHeight = containerOrientation.getHeight() - packedHeight;

            if (attemptResult.layerinlayer != 0) {
                prepackedy = packedHeight;
                preremainpy = containerRemainingHeight;
                containerRemainingHeight = attemptResult.layerThickness - attemptResult.prelayer;
                packedHeight = packedHeight - attemptResult.layerThickness + attemptResult.prelayer;
                layerThickness = attemptResult.layerinlayer;
                PackLayerAttemptInput attemptInput = new PackLayerAttemptInput(this,
                        layerThickness,
                        attemptResult.lilz,
                        containerRemainingHeight,
                        packedHeight,
                        iterationStock.getPackedVolume(), containerOrientation);
                PackLayerAttempt layerAttempt = new PackLayerAttempt(attemptInput);
                analyzePackLayerResult(layerAttempt.start(), totalArticlesVolume);
                packedHeight = prepackedy;
                containerRemainingHeight = preremainpy;
            }


            Optional<Layer> foundLayer = layerService.findLayer(containerOrientation, containerRemainingHeight, iterationStock.findAll().values());
            if (!foundLayer.isPresent()) {
                System.out.println(containerRemainingHeight + "FOUND LAYER:" + 0.);
                break;
            } else {
                layerThickness = foundLayer.get().getHeight();
                System.out.println(containerRemainingHeight + "FOUND LAYER:" + layerThickness);
                if (layerThickness > containerRemainingHeight) {
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
        System.out.println(String.format("PACK: %s %s %s %s %s %s", position.getX(), position.getY(), position.getZ(), foundArticle.getWidth(), foundArticle.getHeight(), foundArticle.getLength()));
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
