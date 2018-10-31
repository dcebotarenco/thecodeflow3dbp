package com.codeflow.domain;

import com.codeflow.domain.articletype.ArticleRepositoryImpl;
import com.codeflow.domain.articletype.ArticleTypeImpl;
import com.codeflow.domain.articletype.ArticleTypeRepository;
import com.codeflow.domain.containertype.ContainerRepository;
import com.codeflow.domain.containertype.ContainerRepositoryImpl;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.ContainerTypeImpl;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapImpl;
import org.junit.Before;
import org.junit.BeforeClass;

public class SharedTest {

    protected static ContainerRepository containerRepository;
    protected static ArticleTypeRepository articleTypeRepository;


    @BeforeClass
    public static void beforeClass() {
        containerRepository = new ContainerRepositoryImpl();
        articleTypeRepository = new ArticleRepositoryImpl();
    }

    public static ContainerType container(Integer w, Integer h, Integer l) {
        ContainerType container = new ContainerTypeImpl(w.doubleValue(), h.doubleValue(), l.doubleValue());
        containerRepository.save(container);
        return containerRepository.container();

    }

    public static void articles(Integer w, Integer h, Integer l, Integer number) {
        articleTypeRepository.saveType(new ArticleTypeImpl(w.doubleValue(), h.doubleValue(), l.doubleValue()), number.longValue());
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
