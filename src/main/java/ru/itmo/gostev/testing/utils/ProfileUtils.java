package ru.itmo.gostev.testing.utils;

import java.io.File;

public class ProfileUtils {

  public static File getProfilesDirectory() {
    return new File(ProfileUtils.class.getClassLoader().getResource("profiles").getFile());
  }

  public static File getChromeProfileDirectory() {
    return new File(getProfilesDirectory(), "chrome");
  }

  public static File getFirefoxProfileDirectory() {
    return new File(getProfilesDirectory(), "firefox");
  }
}
