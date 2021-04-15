package ru.itmo.gostev.testing.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.List;

public class PropertiesHelper {

  public static List<Cookie> authorizationCookies;

  private static TestProperties testProperties = null;

  private static UserCredentials userCredentials = null;

  public static List<Cookie> getAuthorizationCookies() {
    if (authorizationCookies == null) {
      final URL fileUrl = PropertiesHelper.class.getClassLoader().getResource("authorization_cookies.json");
      try {
        authorizationCookies =  new ObjectMapper().readValue(fileUrl, new TypeReference<>() {});
      } catch (final Exception e) {
        throw new RuntimeException(e);
      }
    }

    return authorizationCookies;
  }

  public static TestProperties getTestProperties() {
    if (testProperties == null) {
      final URL fileUrl = PropertiesHelper.class.getClassLoader().getResource("test_properties.json");
      try {
        testProperties = new ObjectMapper().readValue(fileUrl, TestProperties.class);
      } catch (final Exception e) {
        throw new RuntimeException(e);
      }
    }

    return testProperties;
  }

  public static UserCredentials getUserCredentials() {
    if (userCredentials == null) {
      final URL fileUrl = PropertiesHelper.class.getClassLoader().getResource("user_credentials.json");
      try {
        userCredentials = new ObjectMapper().readValue(fileUrl, UserCredentials.class);
      } catch (final Exception e) {
        throw new RuntimeException(e);
      }
    }

    return userCredentials;
  }
}
