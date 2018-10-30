package com.codeflow.application.client;

import com.codeflow.domain.algorithm.PackResult;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultMapper {

    Result map(PackResult packResult) {
        Map<Position, ArticleOrientation> packed = packResult.getPacked();
        List<Placement> collect = packed.entrySet().stream().map(e -> {
            Position position = e.getKey();
            ArticleOrientation orientation = e.getValue();
            com.codeflow.domain.articletype.ArticleType boxType = e.getValue().getBoxType();
            return new Placement(new ArticleType(boxType.getWidth(), boxType.getHeight(), boxType.getLength(), 0L),
                    new com.codeflow.application.client.Position(position.getX(), position.getY(), position.getZ()),
                    new Orientation(orientation.getWidth(), orientation.getHeight(), orientation.getLength()));
        }).collect(Collectors.toList());
        return new Result(collect);
    }
}
