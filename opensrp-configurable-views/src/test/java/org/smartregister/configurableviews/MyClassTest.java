package org.smartregister.configurableviews;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MyClassTest extends BaseUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        MyClass testClass = new MyClass();

        assertEquals(4, testClass.testAddition(1, 3));
    }
}