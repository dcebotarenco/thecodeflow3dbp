package com.codeflow.infrastructure.filereader;

import com.codeflow.application.client.ArticleType;
import com.codeflow.application.client.container.Container;
import com.codeflow.application.client.stock.Stock;

import java.util.List;
import java.util.stream.Collectors;

public class InputDTOAssembler {
    Container createContainer(List<String> containerLine) {
        return new Container(Double.valueOf(containerLine.get(0)),
                Double.valueOf(containerLine.get(1)),
                Double.valueOf(containerLine.get(2)));
    }


    List<Stock> createStock(List<List<String>> articleLines) {
        return articleLines.stream().map(line -> {
            Double w = Double.valueOf(line.get(1));
            Double h = Double.valueOf(line.get(2));
            Double l = Double.valueOf(line.get(3));
            Long number = Long.valueOf(line.get(4).trim());
            return new Stock(new ArticleType(w, h, l), number);
        }).collect(Collectors.toList());
    }
}
