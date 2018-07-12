package org.smartregister.configurableviews.model;

import junit.framework.Assert;

import org.junit.Test;
import org.smartregister.configurableviews.BaseUnitTest;

/**
 * Created by ndegwamartin on 12/07/2018.
 */
public class MainConfigTest extends BaseUnitTest {

    @Test
    public void testMainConfigInstantiatesCorrectly() {
        MainConfig mainConfig = new MainConfig();
        Assert.assertNotNull(mainConfig);

        mainConfig.setApplicationName(DUMMY_TEST_STRING);
        Assert.assertEquals(DUMMY_TEST_STRING, mainConfig.getApplicationName());

        mainConfig.setEnableJsonViews(true);
        Assert.assertTrue(mainConfig.isEnableJsonViews());

        mainConfig.setEnableJsonViews(false);
        Assert.assertFalse(mainConfig.isEnableJsonViews());

        mainConfig.setLanguage(DUMMY_TEST_STRING);
        Assert.assertEquals(DUMMY_TEST_STRING, mainConfig.getLanguage());


    }
}
