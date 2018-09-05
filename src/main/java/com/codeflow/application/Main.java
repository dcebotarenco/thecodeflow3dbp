package com.codeflow.application;

import com.codeflow.domain.algorithm.AlgorithmService;
import com.codeflow.domain.algorithm.airforce.AirforceAlgorithm;
import com.codeflow.domain.article.Article;
import com.codeflow.domain.article.ArticleFactory;
import com.codeflow.domain.container.Container;
import com.codeflow.domain.container.ContainerFactory;
import com.codeflow.infrastructure.filereader.FileReader;
import com.codeflow.infrastructure.filereader.InputDTOAssembler;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(new InputDTOAssembler());
        InputDTO inputDTO = fileReader.read(Paths.get("D:\\personal\\3dbp\\projects\\3d-bin-pack-master\\test\\dpp01.txt"));

        ArticleFactory articleFactory = new ArticleFactory();
        ContainerFactory containerFactory = new ContainerFactory();

        List<Article> articles = inputDTO.getArticleDTOList().stream().map(dto -> articleFactory.createArticle(dto.getId(),
                dto.getWidth(),
                dto.getLength(),
                dto.getHeight())).collect(Collectors.toList());
        Container container = containerFactory.createContainer(inputDTO.getContainerDTO().getId(),
                inputDTO.getContainerDTO().getWidth(),
                inputDTO.getContainerDTO().getLength(),
                inputDTO.getContainerDTO().getHeight());

        AlgorithmService algorithmService = new AlgorithmService();
        algorithmService.execute(new AirforceAlgorithm(), container, articles);


    }
}
