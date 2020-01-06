package org.smartregister.configurableviews;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by ndegwamartin on 15/02/2018.
 */

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
public abstract class BaseUnitTest {
    protected static final String DUMMY_TEST_STRING = "DUMMY_TEST_STRING";
}
