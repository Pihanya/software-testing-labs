package ru.itmo.anokhin.testing.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UserCredentials {

  private final String authorizationUrl;

  private final String username;

  private final String password;

  @JsonCreator
  public UserCredentials(
      @JsonProperty("authorizationUrl") final String authorizationUrl,
      @JsonProperty("username") final String username,
      @JsonProperty("password") final String password
  ) {
    this.authorizationUrl = Objects.requireNonNull(authorizationUrl);
    this.username = Objects.requireNonNull(username);
    this.password = Objects.requireNonNull(password);
  }

  public String getAuthorizationUrl() {
    return authorizationUrl;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
