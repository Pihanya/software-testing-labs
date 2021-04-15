package ru.itmo.gostev.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import ru.itmo.gostev.testing.page.MainPage;
import ru.itmo.gostev.testing.page.MainPage.NewsTopic;
import ru.itmo.gostev.testing.utils.PropertiesHelper;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class MainPageTest {

  private MainPage page;

  @BeforeEach
  public void setUp() {
    final var testProperties = PropertiesHelper.getTestProperties();

    page = new MainPage(testProperties.getDriverType());
  }

  @AfterEach
  public void breakDown() {
    page.close();
  }

  @Test
  @Order(1)
  public void successfullyAuthorize() {
    // Add cookies to bypass strong captcha
    PropertiesHelper.getAuthorizationCookies().forEach(cookie -> page.getDriver().manage().addCookie(cookie.toSeleniumCookie()));

    page.setCredentials(PropertiesHelper.getUserCredentials());
    page.authorize();
  }

  @Test
  @Order(2)
  public void mainTopicChosenOnPageInitialize() {
    final NewsTopic activeTopic = page.getActiveTopic();

    assertTrue(activeTopic.isActive());
    assertEquals("Главное", activeTopic.getTopicName());
  }

  @Test
  public void selectTopics() {
    final var topicsList = page.getTopics();
    for (int i = 1; i < topicsList.size(); ++i) { // skip main topic starting from i == 1
      final var topic = topicsList.get(i);
      topic.getLink().click();

      final var activeTopic = page.getActiveTopic();

      assertTrue(activeTopic.isActive());
      assertEquals(topic.getTopicName(), activeTopic.getTopicName());

      assertEquals(topic.getTopicName().toLowerCase(), page.getCurrentTopicName().toLowerCase());
    }
  }

  @Test
  public void selectTags() {
    final var tagsList = page.getTags();
    for (int i = 0; i < tagsList.size(); ++i) {
      final var tag = tagsList.get(i);
      tag.getLink().click();

      final var activeTag = page.getActiveTag();
      assertEquals(tag.getName(), activeTag.getName());

      assertEquals(tag.getName().toLowerCase(), page.getCurrentTopicName().toLowerCase());
    }
  }
}
