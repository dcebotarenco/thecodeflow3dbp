package com.codeflow.domain;

import com.codeflow.domain.articletype.*;
import com.codeflow.domain.containertype.ContainerRepository;
import com.codeflow.domain.containertype.ContainerRepositoryImpl;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.ContainerTypeImpl;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapImpl;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.List;

public class SharedTest {

    protected static ContainerRepository containerRepository;
    protected static ArticleTypeRepository articleTypeRepository;
    protected static ArticleService articleService;


    @BeforeClass
    public static void beforeClass() {
        containerRepository = new ContainerRepositoryImpl();
        articleTypeRepository = new ArticleRepositoryImpl();
        articleService = new ArticleServiceImpl(articleTypeRepository);
    }

    public static ContainerType container(Integer w, Integer h, Integer l) {
        ContainerType container = new ContainerTypeImpl(w.doubleValue(), h.doubleValue(), l.doubleValue());
        containerRepository.save(container);
        return containerRepository.container();

    }

    public static List<ArticleType> articles(Integer w, Integer h, Integer l, Integer number) {
        articleTypeRepository.saveType(new ArticleTypeImpl(w.doubleValue(), h.doubleValue(), l.doubleValue()), number.longValue());
        return articleTypeRepository.unpackedTypes();
    }

    public static Gap gap(Integer w, Integer h, Integer l) {
        return new GapImpl(w.doubleValue(), h.doubleValue(), l.doubleValue());
    }

    public static Gap gap(Double w, Double h, Double l) {
        return new GapImpl(w, h, l);
    }

    @Before
    public void clearRepos() {
        articleTypeRepository.clear();
        containerRepository.clear();
    }
}
