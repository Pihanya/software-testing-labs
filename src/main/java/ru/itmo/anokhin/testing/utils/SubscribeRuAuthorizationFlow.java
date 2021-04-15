package ru.itmo.anokhin.testing.utils;

import org.openqa.selenium.By.ByCssSelector;
import ru.itmo.anokhin.testing.page.AuthorizedPage;

@SuppressWarnings("FieldCanBeLocal")
public class SubscribeRuAuthorizationFlow {

  private final String AUTHORIZATION_BUTTON_SELECTOR = "a.usermenu_link[href^=\"/member/join\"]";

  private final String LOGIN_FORM_CSS_SELECTOR = "#loginForm";

  private final String LOGIN_INPUT_SELECTOR = LOGIN_FORM_CSS_SELECTOR + " #credential_0";

  private final String PASSWORD_INPUT_SELECTOR = LOGIN_FORM_CSS_SELECTOR + " #credential_1";

  private final String SUBMIT_BUTTON_SELECTOR = LOGIN_FORM_CSS_SELECTOR + " input[type=\"submit\"]";

  private final AuthorizedPage page;

  public SubscribeRuAuthorizationFlow(final AuthorizedPage page) {
    this.page = page;
  }

  public void authorize() {
    final var driver = this.page.getDriver();
    final var credentials = this.page.getCredentials();

    { // Open login lookup
      final var authorizationMenuOpenButton = driver.findElement(new ByCssSelector(AUTHORIZATION_BUTTON_SELECTOR));
      authorizationMenuOpenButton.click();
    }

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
  }
}
