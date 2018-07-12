package org.smartregister.configurableviews.model;

import junit.framework.Assert;

import org.junit.Test;
import org.smartregister.configurableviews.BaseUnitTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ndegwamartin on 12/07/2018.
 */
public class HomeConfigTest extends BaseUnitTest {

    @Test
    public void testHomeConfigInstantiatesCorrectly() {
        HomeConfig homeConfig = new HomeConfig();
        Assert.assertNotNull(homeConfig);

        View view = new View();
        View view2 = new View();

        List<View> views = new ArrayList<>();
        views.add(view);
        views.add(view2);

        homeConfig.setViews(views);

        Assert.assertNotNull(homeConfig.getViews());
        Assert.assertTrue(homeConfig.getViews().size() > 0);
        Assert.assertEquals(views, homeConfig.getViews());

    }
}
