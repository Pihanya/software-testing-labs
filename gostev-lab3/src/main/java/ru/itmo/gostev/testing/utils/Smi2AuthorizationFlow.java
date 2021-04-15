package ru.itmo.gostev.testing.utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;
import ru.itmo.gostev.testing.page.AuthorizedPage;

@SuppressWarnings("FieldCanBeLocal")
public class Smi2AuthorizationFlow {

  private final String LOGIN_FORM_CSS_SELECTOR = "form[name=\"loginForm\"]";

  private final String LOGIN_INPUT_SELECTOR = LOGIN_FORM_CSS_SELECTOR + " input[name=\"email\"]";

  private final String PASSWORD_INPUT_SELECTOR = LOGIN_FORM_CSS_SELECTOR + " input[name=\"password\"]";

  private final String SUBMIT_BUTTON_SELECTOR = LOGIN_FORM_CSS_SELECTOR + " button";

  private final String CAPTCHA_SUBMIT_CHECKBOX_SELECTOR = LOGIN_FORM_CSS_SELECTOR + " iframe";

  private final AuthorizedPage page;

  public Smi2AuthorizationFlow(final AuthorizedPage page) {
    this.page = page;
  }

  public void authorize() {
    final var driver = this.page.getDriver();
    final var credentials = this.page.getCredentials();
    final var initialUrl = driver.getCurrentUrl();

    { // Fill username/password inputs
      final var loginInput = driver.findElement(new ByCssSelector(LOGIN_INPUT_SELECTOR));
      loginInput.click();
      loginInput.sendKeys(credentials.getUsername());

      final var passwordInput = driver.findElement(new ByCssSelector(PASSWORD_INPUT_SELECTOR));
      passwordInput.click();
      passwordInput.sendKeys(credentials.getPassword());
    }

    { // Submit credentials
      final var submitButton = driver.findElement(new ByCssSelector(SUBMIT_BUTTON_SELECTOR));
      submitButton.click();
    }

    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (final InterruptedException e) {
      throw new RuntimeException(e);
    }

    // Captcha submission was asked
    if (driver.getCurrentUrl().equals(initialUrl)) {
      final WebElement captchaCheckbox = driver.findElement(By.cssSelector(CAPTCHA_SUBMIT_CHECKBOX_SELECTOR));
      captchaCheckbox.click();

      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (final InterruptedException e) {
        throw new RuntimeException(e);
      }

      /*final var submitButton = driver.findElement(new ByCssSelector(SUBMIT_BUTTON_SELECTOR));
      submitButton.click();*/
    }
  }
}
