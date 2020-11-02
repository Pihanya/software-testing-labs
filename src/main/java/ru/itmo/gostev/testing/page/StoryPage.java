package ru.itmo.gostev.testing.page;

import org.openqa.selenium.By;
import ru.itmo.gostev.testing.driver.DriverType;

public class StoryPage extends AbstractPage {

  private static final String PUBLICATION_TIME_SELECTOR = ".sc-fzqzlV";

  private static final String TITLE_SELECTOR = ".sc-fzoMdx";

  private static final String SHORT_TEXT_SELECTOR = ".sc-fznzqM";

  public StoryPage(final DriverType driverType, final String startPageUrl) {
    super(driverType, startPageUrl);
  }

  public String getPublicationTime() {
    return driver.findElement(By.cssSelector(PUBLICATION_TIME_SELECTOR)).getText();
  }

  public String getTitle() {
    return driver.findElement(By.cssSelector(TITLE_SELECTOR)).getText();
  }

  public String getShortText() {
    return driver.findElement(By.cssSelector(SHORT_TEXT_SELECTOR)).getText();
  }
}
