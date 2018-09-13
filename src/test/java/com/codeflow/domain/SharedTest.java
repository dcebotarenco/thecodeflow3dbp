package com.codeflow.domain;

import com.codeflow.application.configuration.DefaultConfiguration;
import com.codeflow.domain.article.Article;
import com.codeflow.domain.container.Container;
import com.codeflow.domain.gap.Gap;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.List;
import java.util.Random;

public class SharedTest {

    protected static DefaultConfiguration config;

    @BeforeClass
    public static void beforeClass() {
        config = new DefaultConfiguration();
    }

    public static Container container(Integer id, Integer w, Integer h, Integer l) {
        Container container = config.getContainerFactory().create(id.longValue(), w.doubleValue(), h.doubleValue(), l.doubleValue());
        config.getContainerRepository().save(container);
        return config.getContainerRepository().container();
    }

    public static Container container(Integer w, Integer h, Integer l) {
        Random r = new Random();
        int random = r.nextInt(100 + 1);
        Long id = (long) random;
        Container container = config.getContainerFactory().create(id, w.doubleValue(), h.doubleValue(), l.doubleValue());
        config.getContainerRepository().save(container);
        return config.getContainerRepository().container();

    }

    public static List<Article> articles(Integer w, Integer h, Integer l, Integer number) {
        for (Integer i = 0; i < number; i++) {
            Article article = config.getArticleFactory().create(i.longValue(), w.doubleValue(), h.doubleValue(), l.doubleValue());
            config.getArticleRepository().saveReceived(article);
        }
        return config.getArticleRepository().receivedArticles();
    }

    public static Gap gap(Integer w, Integer h, Integer l) {
        Random r = new Random();
        int random = r.nextInt(100 + 1);
        Long id = (long) random;
        return config.getGapFactory().create(id, w.doubleValue(), h.doubleValue(), l.doubleValue());
    }

    public static Gap gap(Double w, Double h, Double l) {
        Random r = new Random();
        int random = r.nextInt(100 + 1);
        Long id = (long) random;
        return config.getGapFactory().create(id, w, h, l);
    }

    @Before
    public void clearRepos() {
        config.getArticleRepository().clear();
        config.getContainerRepository().clear();
    }
}
