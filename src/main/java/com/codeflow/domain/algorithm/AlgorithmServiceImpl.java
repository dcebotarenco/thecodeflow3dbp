package com.codeflow.domain.algorithm;

import com.codeflow.domain.algorithm.airforce.AlgorithmInputData;
import com.codeflow.domain.algorithm.airforce.AlgorithmInputDataBuilder;
import com.codeflow.domain.articletype.ArticleTypeRepository;
import com.codeflow.domain.containertype.ContainerRepository;


public class AlgorithmServiceImpl implements AlgorithmService {

    private ContainerRepository containerRepository;
    private ArticleTypeRepository articleTypeRepository;

    public AlgorithmServiceImpl(ContainerRepository containerRepository,
                                ArticleTypeRepository articleTypeRepository) {
        this.containerRepository = containerRepository;
        this.articleTypeRepository = articleTypeRepository;
    }


    @Override
    public PackResult execute(Algorithm algorithm) {
        AlgorithmInputData algorithmInputData = new AlgorithmInputDataBuilder()
                .setContainerType(containerRepository.container())
                .setArticleTypes(articleTypeRepository.receivedArticleTypes())
                .createAlgorithmInputData();
        return algorithm.run(algorithmInputData);
    }
}
