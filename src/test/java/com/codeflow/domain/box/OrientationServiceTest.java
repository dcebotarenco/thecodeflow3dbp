package com.codeflow.domain.box;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.ArticleTypeImpl;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.articletype.orientation.ArticleOrientationImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OrientationServiceTest extends SharedTest {

    @Test
    public void calculateOrientations3Unique() {
        ArticleType box3D = new ArticleTypeImpl(2., 3., 4.);
        List<ArticleOrientation> orientations = box3D.getOrientations();
        Assert.assertEquals(6, orientations.size());
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(2., 3., 4., box3D)));
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(2., 4., 3., box3D)));
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(4., 3., 2., box3D)));
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(3., 2., 4., box3D)));
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(3., 4., 2., box3D)));
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(4., 2., 3., box3D)));
    }

    @Test
    public void calculateOrientations2Unique() {
        ArticleType articleType = new ArticleTypeImpl(2., 2., 4.);
        List<ArticleOrientation> orientations = articleType.getOrientations();
        Assert.assertEquals(3, orientations.size());
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(2., 2., 4., articleType)));
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(2., 4., 2., articleType)));
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(4., 2., 2., articleType)));
    }

    @Test
    public void calculateOrientations1Unique() {
        ArticleType articleType = new ArticleTypeImpl(2., 2., 2.);
        List<ArticleOrientation> orientations = articleType.getOrientations();
        Assert.assertEquals(1, orientations.size());
        Assert.assertTrue(orientations.contains(new ArticleOrientationImpl(2., 2., 2., articleType)));
    }
}