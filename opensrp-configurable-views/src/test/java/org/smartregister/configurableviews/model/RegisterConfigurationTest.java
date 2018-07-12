package org.smartregister.configurableviews.model;

import junit.framework.Assert;

import org.junit.Test;
import org.smartregister.configurableviews.BaseUnitTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ndegwamartin on 12/07/2018.
 */
public class RegisterConfigurationTest extends BaseUnitTest {

    @Test
    public void testRegisterConfigurationInstantiatesCorrectly() {

        RegisterConfiguration configuration = new RegisterConfiguration();
        Assert.assertNotNull(configuration);

    }

    @Test
    public void testRegisterConfigurationMethodsWorkCorrectly() {

        RegisterConfiguration configuration = new RegisterConfiguration();
        Assert.assertNotNull(configuration);

        configuration.setEnableAdvancedSearch(true);
        Assert.assertTrue(configuration.isEnableAdvancedSearch());

        configuration.setEnableAdvancedSearch(false);
        Assert.assertFalse(configuration.isEnableAdvancedSearch());


        configuration.setEnableFilterList(true);
        Assert.assertTrue(configuration.isEnableFilterList());

        configuration.setEnableFilterList(false);
        Assert.assertFalse(configuration.isEnableFilterList());


        configuration.setEnableSortList(true);
        Assert.assertTrue(configuration.isEnableSortList());

        configuration.setEnableFilterList(false);
        Assert.assertFalse(configuration.isEnableFilterList());

        configuration.setEnableJsonViews(true);
        Assert.assertTrue(configuration.isEnableJsonViews());

        configuration.setEnableJsonViews(false);
        Assert.assertFalse(configuration.isEnableJsonViews());

        Field field = new Field();
        List<Field> fieldList = Arrays.asList(field);
        configuration.setFilterFields(fieldList);
        Assert.assertEquals(fieldList, configuration.getFilterFields());

        configuration.setSortFields(fieldList);
        Assert.assertEquals(fieldList, configuration.getSortFields());

        List<String> stringList = Arrays.asList(DUMMY_TEST_STRING);
        configuration.setSearchableFields(stringList);
        Assert.assertEquals(stringList, configuration.getSearchableFields());

        configuration.setSearchBarText(DUMMY_TEST_STRING);
        Assert.assertEquals(DUMMY_TEST_STRING, configuration.getSearchBarText());

    }
}
