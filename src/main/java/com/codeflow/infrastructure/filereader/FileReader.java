package com.codeflow.infrastructure.filereader;


import com.codeflow.application.client.Input;
import com.codeflow.application.client.container.Container;
import com.codeflow.application.client.stock.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileReader {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);
    private InputDTOAssembler inputDTOAssembler;

    public FileReader(InputDTOAssembler inputDTOAssembler) {
        this.inputDTOAssembler = inputDTOAssembler;
    }

    public Input read(Path path) throws IOException {
        LOGGER.info("Reading {}", path);
        List<String> allLines = Files.lines(path).collect(Collectors.toList());
        Optional<String> firstLine = allLines.stream().findFirst();
        if (firstLine.isPresent()) {
            List<String> containerLine = Arrays.asList(firstLine.get().split(","));
            List<List<String>> articlesLines = allLines.stream()
                    .filter(l -> allLines.indexOf(l) != 0)
                    .map(l -> Arrays.asList(l.split("[.|,]")))
                    .collect(Collectors.toList());

            List<Stock> stock = inputDTOAssembler.createStock(articlesLines);
            Container container = inputDTOAssembler.createContainer(containerLine);
            return new Input(container, stock);
        } else {
            throw new IllegalStateException("No lines found in the file");
        }
    }
}
