package com.codeflow.application;

import com.codeflow.domain.algorithm.AlgorithmService;
import com.codeflow.domain.algorithm.airforce.AirForceAlgorithm;
import com.codeflow.domain.boxes.*;
import com.codeflow.domain.orientation.OrientationFactory;
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

        Dimensions3DFactory dimensions3DFactory = new Dimensions3DFactory();
        ArticleFactory articleFactory = new ArticleFactory(dimensions3DFactory);

        OrientationService orientationService = new OrientationService(new OrientationFactory(dimensions3DFactory));
        ContainerFactory containerFactory = new ContainerFactory(dimensions3DFactory, orientationService);

        List<Article> articles = inputDTO.getArticleDTOList().stream().map(dto -> articleFactory.create(dto.getId(),
                dto.getWidth(),
                dto.getLength(),
                dto.getHeight())).collect(Collectors.toList());
        Container container = containerFactory.create(inputDTO.getContainerDTO().getId(),
                inputDTO.getContainerDTO().getWidth(),
                inputDTO.getContainerDTO().getLength(),
                inputDTO.getContainerDTO().getHeight());

        AlgorithmService algorithmService = new AlgorithmService();
        algorithmService.execute(new AirForceAlgorithm(), container, articles);


    }
}
