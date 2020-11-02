package ru.itmo.gostev.testing.page;

import static ru.itmo.gostev.testing.page.Smi2PageUrls.MAIN_PAGE_URL;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.itmo.gostev.testing.driver.DriverType;
import ru.itmo.gostev.testing.utils.Smi2AuthorizationFlow;

public class MainPage extends AuthorizedPage {

  public MainPage(final DriverType driverType) {
    super(driverType, MAIN_PAGE_URL);
  }

  @Override
  void performAuthorization() {
    new Smi2AuthorizationFlow(this).authorize();
  }

  public NewsTopic getActiveTopic() {
    try {
      final WebElement activeTopic = driver.findElement(By.cssSelector(".Topic_topicActive"));
      return new NewsTopic(true, activeTopic.getText(), activeTopic);
    } catch (final NoSuchElementException e) {
      return null;
    }
  }

  public List<NewsTopic> getTopics() {
    final var topics = driver.findElements(By.cssSelector(".Topic_topic")).stream()
        .map(topicLink -> {
          final boolean active = topicLink.getAttribute("class").contains(".Topic_topicActive");
          final String topicTitle = topicLink.getText();

          return new NewsTopic(active, topicTitle, topicLink);
        })
        .collect(Collectors.toList());

    return topics.subList(0, topics.size() - 1);
  }

  public String getCurrentTopicName() {
    return driver.findElement(By.cssSelector(".Line-topicTitle--zaKWH")).getText();
  }

  public List<News> getNews() {
    return driver.findElements(By.cssSelector(".ListItem-listItem--3ZKI_ .Line-titleWrapper--Rq8t6")).stream()
        .map(newsLink -> {
          final String title = newsLink.findElement(By.tagName("span")).getText();
          final String href = newsLink.getAttribute("href");

          return new News(title, href);
        })
        .collect(Collectors.toList());
  }

  public Tag getActiveTag() {
    return getTags().stream().filter(Tag::isActive).findFirst().orElse(null);
  }

  public List<Tag> getTags() {
    return driver.findElements(By.cssSelector(".Tags-tag--18RTn")).stream()
        .map(topicLink -> {
          final boolean active = topicLink.getAttribute("class").contains("Active");
          final String title = topicLink.getText();

          return new Tag(active, title, topicLink);
        })
        .collect(Collectors.toList());
  }

  public static class NewsTopic {

    private final boolean active;

    private final String topicName;

    private final WebElement link;

    public NewsTopic(final boolean active, final String topicName, final WebElement link) {
      this.active = active;
      this.topicName = topicName;
      this.link = link;
    }

    public boolean isActive() {
      return active;
    }

    public String getTopicName() {
      return topicName;
    }

    public WebElement getLink() {
      return link;
    }
  }

  public static class Tag {

    private final boolean active;

    private final String name;

    private final WebElement link;

    public Tag(boolean active, String name, WebElement link) {
      this.active = active;
      this.name = name;
      this.link = link;
    }

    public boolean isActive() {
      return active;
    }

    public String getName() {
      return name;
    }

    public WebElement getLink() {
      return link;
    }
  }

  public static class News {
    private final String title;

    private final String href;

    public News(String title, String href) {
      this.title = title;
      this.href = href;
    }

    public String getTitle() {
      return title;
    }

    public String getHref() {
      return href;
    }
  }
}
