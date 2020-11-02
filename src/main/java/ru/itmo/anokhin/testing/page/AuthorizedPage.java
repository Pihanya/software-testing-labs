package ru.itmo.anokhin.testing.page;

import ru.itmo.anokhin.testing.driver.DriverType;
import ru.itmo.anokhin.testing.utils.UserCredentials;

public abstract class AuthorizedPage extends AbstractPage {

  private UserCredentials credentials;

  private boolean authorized;

  protected AuthorizedPage(final DriverType driverType, final UserCredentials credentials, final String startPageUrl) {
    super(driverType, startPageUrl);

    this.credentials = credentials;
  }

  protected AuthorizedPage(final DriverType driverType, final String startPageUrl) {
    this(driverType, null, startPageUrl);
  }

  public UserCredentials getCredentials() {
    return credentials;
  }

  public void setCredentials(UserCredentials credentials) {
    this.credentials = credentials;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  abstract void performAuthorization();

  public void authorize() {
    final UserCredentials credentials = this.credentials;
    if (credentials == null) {
      this.authorized = false;
    }

    final String initialUrl = driver.getCurrentUrl();

    driver.get(credentials.getAuthorizationUrl());
    performAuthorization();

    driver.get(initialUrl);
  }
}
