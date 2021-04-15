package ru.itmo.anokhin.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import ru.itmo.anokhin.testing.page.MainPage;
import ru.itmo.anokhin.testing.page.MainPage.SubscriptionTopic;
import ru.itmo.anokhin.testing.utils.PropertiesHelper;

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
    page.setCredentials(PropertiesHelper.getUserCredentials());
    page.authorize();
  }

  @Test
  @Order(2)
  public void noTopicChosenOnInitialize() {
    page.getTopics().stream()
        .filter(SubscriptionTopic::isActive)
        .findFirst()
        .ifPresent(topic -> fail("Topic is active: " + topic));

    page.getSubTopics().stream()
        .filter(SubscriptionTopic::isActive)
        .findFirst()
        .ifPresent(topic -> fail("Topic is active: " + topic));
  }

  @Test
  @Order(3)
  public void topicBecomesActiveOnChoosing() {
    final List<String> topicNames = page.getTopics().stream()
        .filter(t -> !t.isAdvertisementLink())
        .map(SubscriptionTopic::getTopicName)
        .collect(Collectors.toList());

    assertFalse(topicNames.isEmpty());

    for (int topicIndex = 0; topicIndex < topicNames.size(); ++topicIndex) {
      final String topicName = topicNames.get(topicIndex);

      page.getTopics().stream()
          .filter(t -> t.getTopicName().equals(topicName))
          .findFirst().orElseThrow()
          .getLink().click(); // Choosing topic

      int finalTopicIndex = topicIndex;
      page.getTopics().stream()
          .filter(t -> t.getTopicName().equals(topicName)).findFirst()
          .ifPresentOrElse(
              activeTopic -> {
                if (finalTopicIndex != 0) { // Topic name is not displayed for overall topics
                  assertEquals(topicName, page.getCurrentTopicName());
                }

                assertTrue(activeTopic.isActive());
              },
              () -> fail("No active topics were found for name " + topicName)
          );
    }
  }

  @Test
  @Order(4)
  public void subtopicBecomesActiveOnChoosing() {
    final List<String> topicNames = page.getTopics().stream()
        .map(SubscriptionTopic::getTopicName)
        .collect(Collectors.toList());

    assertFalse(topicNames.isEmpty());
  }
}
