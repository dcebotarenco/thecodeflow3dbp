package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.iteration.Iteration;
import com.codeflow.domain.iteration.stock.IterationStockImpl;
import com.codeflow.domain.iteration.stock.IterationStockRepositoryImpl;
import com.codeflow.domain.stock.StockRepository;

import java.util.LinkedList;
import java.util.List;

public class Run {

    private final LayerService layerService;
    private final ContainerType containerType;
    private StockRepository stockRepository;
    private List<Iteration> iterations;

    public Run(ContainerType containerType,
               StockRepository stockRepository,
               LayerService layerService) {
        this.containerType = containerType;
        this.stockRepository = stockRepository;
        this.iterations = new LinkedList<>();
        this.layerService = layerService;
    }


    public void start() {
        boolean hundredPercentPackedPerSearch = false;
        for (ContainerOrientation containerOrientation : containerType.getOrientations()) {
            List<Layer> layers = layerService.listCandidates(containerOrientation, stockRepository.findAll().values());
            for (Layer layer : layers) {
                Iteration iteration = new Iteration(
                        new IterationStockImpl(new IterationStockRepositoryImpl(stockRepository)),
                        layer,
                        containerOrientation,
                        layerService);
                iteration.execute();
                iterations.add(iteration);
                if (iteration.isHundredPercentPacked()) {
                    hundredPercentPackedPerSearch = true;
                    break;
                }
            }
            if (hundredPercentPackedPerSearch) {
                break;
            }
        }
    }

    public List<Iteration> getIterations() {
        return iterations;
    }
}
