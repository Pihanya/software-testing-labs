package ru.itmo.anokhin.testing.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;

public class PropertiesHelper {

  private static TestProperties testProperties = null;

  private static UserCredentials userCredentials = null;

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
