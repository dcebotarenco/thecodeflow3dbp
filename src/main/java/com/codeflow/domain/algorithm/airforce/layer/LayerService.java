package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.stock.Stock;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LayerService {

    List<Layer> listCandidates(ContainerOrientation containerOrientation, Collection<Stock> stock);

    Optional<Layer> findLayer(ContainerOrientation containerOrientation, Double height, Collection<Stock> stock);
}
