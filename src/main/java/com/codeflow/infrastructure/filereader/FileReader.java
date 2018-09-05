package com.codeflow.infrastructure.filereader;


import com.codeflow.application.ArticleDTO;
import com.codeflow.application.ContainerDTO;
import com.codeflow.application.InputDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileReader {

    private InputDTOAssembler inputDTOAssembler;

    public FileReader(InputDTOAssembler inputDTOAssembler) {
        this.inputDTOAssembler = inputDTOAssembler;
    }

    public InputDTO read(Path path) throws IOException {
        List<String> allLines = Files.lines(path).collect(Collectors.toList());
        Optional<String> firstLine = allLines.stream().findFirst();
        if (firstLine.isPresent()) {
            List<String> containerLine = Arrays.asList(firstLine.get().split(","));
            List<List<String>> articlesLines = allLines.stream()
                    .filter(l -> allLines.indexOf(l) != 0)
                    .map(l -> Arrays.asList(l.split("[.|,]")))
                    .collect(Collectors.toList());

            List<ArticleDTO> articles = inputDTOAssembler.createArticles(articlesLines);
            ContainerDTO container = inputDTOAssembler.createContainer(999L, containerLine);
            return new InputDTO(container, articles);
        } else {
            throw new IllegalStateException("No lines found in the file");
        }
    }
}
