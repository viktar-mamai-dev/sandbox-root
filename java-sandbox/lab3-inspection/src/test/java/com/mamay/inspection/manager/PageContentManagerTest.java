package com.mamay.inspection.manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class PageContentManagerTest {

  @InjectMocks private PageContentManager manager;

  @BeforeEach
  public void before() {
    manager = new PageContentManager();
    manager.changeResource(Locale.ENGLISH);
  }

  @AfterEach
  public void after() {
    manager = null;
  }

  @ParameterizedTest
  @MethodSource("propertyValues")
  public void changeLocaleTest(String expectedMessage, String propertyName) {
    String enValue = manager.getProperty(propertyName);
    manager.changeResource(new Locale("ru"));
    String rusValue = manager.getProperty(propertyName);
    assertNotEquals("Same labels in different languages", enValue, rusValue);
  }

  public static Stream<Arguments> propertyValues() {
    return Stream.of(
        Arguments.of("Login", "link.login"),
        Arguments.of("Magazine", "title.magazine.item"),
        Arguments.of("Age", "label.user.age"),
        Arguments.of("Register", "button.register"));
  }
}
