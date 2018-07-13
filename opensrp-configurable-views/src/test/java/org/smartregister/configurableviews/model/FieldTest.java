package org.smartregister.configurableviews.model;

import org.junit.Assert;
import org.junit.Test;
import org.smartregister.configurableviews.BaseUnitTest;

/**
 * Created by ndegwamartin on 12/07/2018.
 */
public class FieldTest extends BaseUnitTest {
    private static final String DUMMY_DISPLAY_NAME = "DUMMY DISPLAY NAME";
    private static final String DUMMY_DB_ALIAS = "DUMMY DB ALIAS";

    @Test
    public void testFieldConstructorInitializesCorrectly() {
        Field field = new Field();
        Assert.assertNotNull(field);
        Assert.assertNull(field.getDisplayName());
        Assert.assertNull(field.getDbAlias());

        field.setDbAlias(DUMMY_DB_ALIAS);
        field.setDisplayName(DUMMY_DISPLAY_NAME);
        Assert.assertEquals(DUMMY_DISPLAY_NAME, field.getDisplayName());
        Assert.assertEquals(DUMMY_DB_ALIAS, field.getDbAlias());

        field = new Field(DUMMY_DISPLAY_NAME, DUMMY_DB_ALIAS);

        Assert.assertNotNull(field);
        Assert.assertNotNull(field.getDisplayName());
        Assert.assertEquals(DUMMY_DISPLAY_NAME, field.getDisplayName());
        Assert.assertNotNull(field.getDbAlias());
        Assert.assertEquals(DUMMY_DB_ALIAS, field.getDbAlias());

    }

    @Test
    public void testEqualsShouldReturnFalseIfParameterIsNull() {
        Field field = new Field();
        Object randomObject = null;
        Assert.assertFalse(field.equals(randomObject));
    }

    @Test
    public void testEqualsShouldReturnFalseIfParameterIsNotInstanceOfField() {
        Field field = new Field();
        Assert.assertFalse(field.equals(new Object()));
    }

    @Test
    public void testEqualsShouldReturnTrueIfParameterIsSameInstance() {
        Field field = new Field();
        Assert.assertTrue(field.equals(field));
    }

    @Test
    public void testEqualsShouldReturnTrueIfParameterInstanceHasSimilarProperties() {
        Field field = new Field();
        field.setDbAlias(DUMMY_DB_ALIAS);
        field.setDisplayName(DUMMY_DISPLAY_NAME);

        Field field2 = new Field(DUMMY_DISPLAY_NAME, DUMMY_DB_ALIAS);

        Assert.assertNotNull(field);
        Assert.assertNotNull(field2);

        Assert.assertTrue(field.equals(field2));
    }


}
