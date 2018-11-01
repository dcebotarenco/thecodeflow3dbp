package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.searching.SearchResult;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingServiceImpl;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Iteration {
    private final LayerService layerService;


    private final Layer layer;
    private final ContainerOrientation containerOrientation;
    private Map<Position, ArticleOrientation> packedArticles;
    private Map<ArticleType, Long> remainingToPack;
    private double packedVolume;
    private boolean hundredPercentPacked;
    private boolean packing;

    public Iteration(Map<ArticleType, Long> articlesToPack,
                     Map<Position, ArticleOrientation> packedArticles,
                     Layer layer,
                     ContainerOrientation containerOrientation,
                     LayerService layerService) {
        this.packedArticles = packedArticles;
        this.remainingToPack = articlesToPack;
        this.packedVolume = 0;
        this.hundredPercentPacked = false;
        this.layer = layer;
        this.containerOrientation = containerOrientation;
        this.layerService = layerService;
    }

    public void execute() {

        double layerThickness = layer.getHeight();
        double remainpz = containerOrientation.getLength();
        double remainpy = containerOrientation.getHeight();
        double packedy = 0;
        double prepackedy;
        double preremainpy;
        this.packing = true;

        Double totalArticlesVolume = totalVolume(getRemainingToPack());


        do {
            PackLayerAttemptInput packLayerAttemptInput = new PackLayerAttemptInput(this,
                    layerThickness,
                    remainpz,
                    remainpy,
                    packedy,
                    packedVolume,
                    containerOrientation);
            PackLayerAttempt packLayerAttempt = new PackLayerAttempt(packLayerAttemptInput);
            PackLayerAttemptResult attemptResult = packLayerAttempt.start();
            analyzePackLayerResult(attemptResult, totalArticlesVolume);

            packedy = packedy + attemptResult.layerThickness;
            remainpy = containerOrientation.getHeight() - packedy;

            if (attemptResult.layerinlayer != 0) {
                prepackedy = packedy;
                preremainpy = remainpy;
                remainpy = attemptResult.layerThickness - attemptResult.prelayer;
                packedy = packedy - attemptResult.layerThickness + attemptResult.prelayer;
                remainpz = attemptResult.lilz;
                layerThickness = attemptResult.layerinlayer;
                PackLayerAttemptInput attemptInput = new PackLayerAttemptInput(this, layerThickness, remainpz, remainpy, packedy, packedVolume, containerOrientation);
                PackLayerAttempt layerAttempt = new PackLayerAttempt(attemptInput);
                analyzePackLayerResult(layerAttempt.start(), totalArticlesVolume);

                packedy = prepackedy;
                remainpy = preremainpy;
                remainpz = containerOrientation.getLength();
            }


            Optional<Layer> foundLayer = layerService.findLayer(containerOrientation, remainpy, remainingToPack);
            if (!foundLayer.isPresent()) {
                System.out.println(remainpy + "FOUND LAYER:" + 0.);
                break;
            } else {
                layerThickness = foundLayer.get().getHeight();
                System.out.println(remainpy + "FOUND LAYER:" + layerThickness);
                if (layerThickness > remainpy) {
                    break;
                }
            }

        } while (packing);
    }

    private void analyzePackLayerResult(PackLayerAttemptResult layerAttemptResult, Double totalVolume) {
        packedVolume = layerAttemptResult.packedVolume;

        if (layerAttemptResult.packedVolume == containerOrientation.getVolume() || layerAttemptResult.packedVolume == totalVolume) {
            packing = false;
            hundredPercentPacked = true;
        }
    }

    public Double totalVolume(Map<ArticleType, Long> articles) {
        return articles.entrySet().stream().map(t -> t.getKey().getVolume() * t.getValue()).reduce((v1, v2) -> v1 + v2).orElse(0D);
    }


    public void pack(ArticleOrientation articleOrientation, Position position) {
        ArticleType articleType = articleOrientation.getBoxType();
        Long sizeToPack = this.remainingToPack.get(articleType);
        if (sizeToPack > 0) {
            long newSize = sizeToPack - 1;
            if (newSize > 0) {
                this.remainingToPack.put(articleType, newSize);
            } else {
                this.remainingToPack.remove(articleType);
            }
        } else {
            throw new IllegalStateException("Cannot pack");
        }
        packedArticles.put(position, articleOrientation);
        System.out.println(String.format("PACK: %s %s %s %s %s %s", position.getX(), position.getY(), position.getZ(), articleOrientation.getWidth(), articleOrientation.getHeight(), articleOrientation.getLength()));

    }

    public Map<Position, ArticleOrientation> getPackedArticles() {
        return new LinkedHashMap<>(packedArticles);
    }

    public List<ArticleType> unpackedTypes() {
        return remainingToPack.entrySet().stream().filter(e -> e.getValue() > 0).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public Map<ArticleType, Long> getRemainingToPack() {
        return remainingToPack;
    }

    public double getPackedVolume() {
        return packedVolume;
    }

    public boolean isHundredPercentPacked() {
        return hundredPercentPacked;
    }


    public SearchResult findBoxTypes(List<ArticleType> articleTypes, Gap requiredGapImpl, Gap maxGapImpl) {
        SearchingService searchingService = new SearchingServiceImpl(articleTypes);
        return searchingService.findBoxTypes(requiredGapImpl, maxGapImpl);
    }

    public Iteration translate() {
        Map<Position, ArticleOrientation> packed = new LinkedHashMap<>();
        for (Map.Entry<Position, ArticleOrientation> entry : this.getPackedArticles().entrySet()) {
            Position pp = entry.getKey();
            ArticleOrientation aa = entry.getValue();
            Position p = containerOrientation.getTranslator().translate(pp);
            ArticleOrientation a = containerOrientation.getTranslator().translate(aa);
            packed.put(p, a);
//            ArticleType boxType = a.getBoxType();
//            System.out.printf("%.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f\n", boxType.getWidth(), boxType.getHeight(), boxType.getLength(), p.getX(), p.getY(), p.getZ(), a.getWidth(), a.getHeight(), a.getLength());
//            i++;
        }
        return new Iteration(remainingToPack, packed, layer, this.containerOrientation, layerService);
    }
}
