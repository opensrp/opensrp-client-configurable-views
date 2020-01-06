package org.smartregister.configurableviews;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.smartregister.Context;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by ndegwamartin on 05/02/2018.
 */

public class ConfigurableViewsLibraryTest extends BaseUnitTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private Context context = Context.getInstance();

    private ConfigurableViewsLibrary library;

    @After
    public void tearDown() {
        if (library != null)
            library.destroy();
    }

    @Test
    public void callingGetInstanceOfConfigurableViewsLibraryDoesNotReturnNull() {
        ConfigurableViewsLibrary.init(context);
        library = ConfigurableViewsLibrary.getInstance();
        assertNotNull(library);

    }

    @Test(expected = IllegalStateException.class)
    public void callingGetInstanceOfConfigurableViewsLibraryWithoutInitFirstThrowsIllegalStateException() {
        library = ConfigurableViewsLibrary.getInstance();
    }

    @Test
    public void testDestroy() {
        ConfigurableViewsLibrary.init(context);
        library = ConfigurableViewsLibrary.getInstance();
        assertNotNull(library);
        library.destroy();
        assertNull(ConfigurableViewsLibrary.instance);
    }
}
