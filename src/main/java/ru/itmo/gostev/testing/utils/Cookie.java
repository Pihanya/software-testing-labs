package ru.itmo.gostev.testing.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Cookie {
  private final String name;

  private final String value;

  private final String domain;

  private final String path;

  private final Date expiresOn;

  private final boolean httpOnly;

  private final boolean secure;

  public Cookie(
      @JsonProperty("name") final String name,
      @JsonProperty("value") final String value,
      @JsonProperty("domain") final String domain,
      @JsonProperty("path") final String path,
      @JsonProperty("expiresOn") final Date expiresOn,
      @JsonProperty("httpOnly") final boolean httpOnly,
      @JsonProperty("secure") final boolean secure
  ) {
    this.name = name;
    this.value = value;
    this.domain = domain;
    this.path = path;
    this.expiresOn = expiresOn;
    this.httpOnly = httpOnly;
    this.secure = secure;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  public String getDomain() {
    return domain;
  }

  public String getPath() {
    return path;
  }

  public Date getExpiresOn() {
    return expiresOn;
  }

  public boolean isHttpOnly() {
    return httpOnly;
  }

  public boolean isSecure() {
    return secure;
  }

  public org.openqa.selenium.Cookie toSeleniumCookie() {
    return new org.openqa.selenium.Cookie.Builder(name, value)
        .domain(domain)
        .path(path)
        .expiresOn(expiresOn)
        .isHttpOnly(httpOnly)
        .isSecure(secure)
        .build();
  }
}
