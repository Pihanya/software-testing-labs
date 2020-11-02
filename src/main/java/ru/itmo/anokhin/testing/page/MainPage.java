package ru.itmo.anokhin.testing.page;

import static ru.itmo.anokhin.testing.page.SubscribeRuPageUrls.MAIN_PAGE_URL;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.itmo.anokhin.testing.driver.DriverType;
import ru.itmo.anokhin.testing.page.MainPage.MenuItem.ItemType;
import ru.itmo.anokhin.testing.utils.SubscribeRuAuthorizationFlow;

public class MainPage extends AuthorizedPage {

  public MainPage(final DriverType driverType) {
    super(driverType, MAIN_PAGE_URL);
  }

  @Override
  void performAuthorization() {
    new SubscribeRuAuthorizationFlow(this).authorize();
  }

  public List<MenuItem> getMenuItems() {
    final String menuItemsSelector = ".mod_menu1 a.mod_menu1Link";

    return driver.findElements(new ByCssSelector(menuItemsSelector)).stream()
        .map(link -> {
          final ItemType itemType = ItemType.findByHref(link.getAttribute("href"));
          final boolean active = link.getAttribute("class").contains("active");

          return new MenuItem(itemType, active, link);
        })
        .collect(Collectors.toList());
  }

  public String getCurrentTopicName() {
    final String topicNameElementSelector = ".rightmenu > h2";

    try {
      final WebElement currentTopicElement = driver.findElement(new ByCssSelector(topicNameElementSelector));
      return currentTopicElement.getText();
    } catch (final NoSuchElementException e) {
      return null;
    }
  }

  public List<SubscriptionTopic> getTopics() {
    final String topicsSelector = ".leftmenu_item > .leftmenu_link";
    return driver.findElements(new ByCssSelector(topicsSelector)).stream()
        .map(topicLink -> {
          final boolean active = topicLink.getAttribute("class").contains("active");
          final String topicTitle = topicLink.getAttribute("title");
          final boolean advertisementLink = !topicLink.getAttribute("href").contains(SubscribeRuPageUrls.SHORT_ADDRESS);

          return new SubscriptionTopic(active, advertisementLink, topicTitle, topicLink);
        })
        .collect(Collectors.toList());
  }

  public List<SubscriptionTopic> getSubTopics() {
    final String subTopicsSelector = ".rightmenu > .rightmenu_link";

    return driver.findElements(new ByCssSelector(subTopicsSelector)).stream()
        .map(subtopicLink -> {
          final boolean active = subtopicLink.getAttribute("class").contains("active");
          final String title = subtopicLink.getText();
          return new SubscriptionTopic(active, title, subtopicLink);
        })
        .collect(Collectors.toList());
  }

  public static class MenuItem {
    private final ItemType type;

    private final boolean active;

    private final WebElement link;

    public MenuItem(ItemType type, boolean active, WebElement link) {
      this.type = type;
      this.active = active;
      this.link = link;
    }

    public ItemType getType() {
      return type;
    }

    public boolean isActive() {
      return active;
    }

    public WebElement getLink() {
      return link;
    }

    public enum ItemType {
      DIGEST(".*/digest/"),
      ALL_SUBSCRIPTIONS("/issue/(.*)?"),
      DISCUSSIONS(".*/digest(/.*)?/discussions");

      private final Pattern hrefPattern;

      ItemType(String hrefRegex) {
        this.hrefPattern = Pattern.compile(hrefRegex);
      }

      private Pattern getHrefPattern() {
        return hrefPattern;
      }

      public static ItemType findByHref(final String href) {
        return Stream.of(values())
            .filter(type -> type.getHrefPattern().matcher(href).matches())
            .findFirst().orElseThrow();
      }
    }
  }

  public static class SubscriptionTopic {

    private final boolean active;

    private final boolean advertisementLink;

    private final String topicName;

    private final WebElement link;

    public SubscriptionTopic(final boolean active, final boolean advertisementLink, final String topicName, final WebElement link) {
      this.active = active;
      this.advertisementLink = advertisementLink;
      this.topicName = topicName;
      this.link = link;
    }

    public SubscriptionTopic(final boolean active, final String topicName, final WebElement link) {
      this(active, false, topicName, link);
    }

    public boolean isActive() {
      return active;
    }

    public boolean isAdvertisementLink() {
      return advertisementLink;
    }

    public String getTopicName() {
      return topicName;
    }

    public WebElement getLink() {
      return link;
    }
  }
}
