package ru.itmo.anokhin.testing.page;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;
import ru.itmo.anokhin.testing.driver.DriverType;

public class ArticlePage extends AbstractPage {

  public ArticlePage(final DriverType driverType, final String pageUrl) {
    super(driverType, pageUrl);
  }

  public List<BreadCrumbElement> getBreadCrumb() {
    final String breadCrumbItemsSelector = ".bread .bread_list .bread_item";
    final String breadCrumbLinkSelector = ".bread_link";

    final List<WebElement> breadCrumbItems = driver.findElements(new ByCssSelector(breadCrumbItemsSelector));
    final List<BreadCrumbElement> breadCrumbElements = new ArrayList<>(breadCrumbItems.size());

    for (int i = 0; i < breadCrumbItems.size(); i += 2) {
      final WebElement breadCrumbElement = breadCrumbItems.get(i);
      if (i == breadCrumbItems.size() - 1) {
        breadCrumbElements.add(new BreadCrumbElement(breadCrumbElement.getText(), null));
      } else {
        final WebElement realElement = breadCrumbElement.findElement(By.cssSelector(breadCrumbLinkSelector));
        breadCrumbElements.add(new BreadCrumbElement(realElement.getText(), realElement.getAttribute("href")));
      }
    }

    return breadCrumbElements;
  }

  public String getTopic() {
    return driver.findElement(By.cssSelector(".article-page h3.article_title")).getText();
  }

  public String getPreviousArticleUrl() {
    return driver.findElement(By.cssSelector(".article_pages > .article_prev")).getAttribute("href");
  }

  public String getNextArticleUrl() {
    return driver.findElement(By.cssSelector(".article_pages > .article_next")).getAttribute("href");
  }

  public String getName() {
    return driver.findElement(By.cssSelector(".article_content > h2")).getText();
  }

  public static class BreadCrumbElement {

    private final String title;

    private final String href;

    @JsonCreator
    public BreadCrumbElement(
        @JsonProperty("title") final String title,
        @JsonProperty("href") final String href
    ) {
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
