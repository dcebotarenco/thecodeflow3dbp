package com.codeflow.domain.algorithm;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.iteration.IterationResult;
import com.codeflow.domain.position.Position;

import java.util.Map;
import java.util.Set;

import static com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text.NEW_LINE;

public class Result {
    private IterationResult translateResult;

    public Result(IterationResult translateResult) {

        this.translateResult = translateResult;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<Position, ArticleOrientation>> entries = translateResult.getPacked().entrySet();
        for (Map.Entry<Position, ArticleOrientation> entry : entries) {
            ArticleOrientation a = entry.getValue();
            Position p = entry.getKey();
            ArticleType boxType = a.getBoxType();
            String line = String.format("%.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f\n", boxType.getWidth(), boxType.getHeight(), boxType.getLength(), p.getX(), p.getY(), p.getZ(), a.getWidth(), a.getHeight(), a.getLength());
            builder.append(line).append(NEW_LINE);
        }
        return builder.toString();
    }
}
