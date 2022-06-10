package com.mamay.inspection.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class PageContentManagerTest {
    private String expectedMessage;
    private String propertyName;
    private PageContentManager manager;

    public PageContentManagerTest(String expectedMessage, String propertyName) {
        this.expectedMessage = expectedMessage;
        this.propertyName = propertyName;
    }

    @Parameters
    public static Collection<String[]> propertyValues() {
        return Arrays
                .asList(new String[][]{
                        {"Login", "link.login"},
                        {"Magazine", "title.magazine.item"},
                        {"Age", "label.user.age"},
                        {"Register", "button.register"}});
    }

    @Before
    public void before() {
        manager = new PageContentManager();
        manager.changeResource(Locale.ENGLISH);
    }

    @After
    public void after() {
        manager = null;
    }

    @Test
    public void getPropertyTest() {
        String actualMessage = manager.getProperty(this.propertyName);
        assertEquals("Wrong message", expectedMessage, actualMessage);
    }

    @Test
    public void changeLocaleTest() {
        String enValue = manager.getProperty(this.propertyName);
        manager.changeResource(new Locale("ru"));
        String rusValue = manager.getProperty(this.propertyName);
        assertNotEquals("Same labels in different languages", enValue, rusValue);
    }
}
