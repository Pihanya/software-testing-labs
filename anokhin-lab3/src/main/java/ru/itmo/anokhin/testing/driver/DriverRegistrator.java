package ru.itmo.anokhin.testing.driver;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class DriverRegistrator {

  private static final Map<DriverType, Boolean> DRIVER_REGISTRATIONS;

  static {
    DRIVER_REGISTRATIONS = new HashMap<>(DriverType.values().length);
    Stream.of(DriverType.values()).forEach(type -> DRIVER_REGISTRATIONS.put(type, Boolean.FALSE));
  }

  public static boolean isDriverRegistered(final DriverType type) {
    return Boolean.TRUE.equals(DRIVER_REGISTRATIONS.get(type));
  }

  public static void registerAll() {
    Stream.of(DriverType.values()).forEach(DriverRegistrator::registerDriver);
  }

  public static void registerDriver(final DriverType type) {
    if (isDriverRegistered(type)) {
      return;
    }

    switch (type) {
      case CHROME: {
        final Path driverPath = getChromeDriverPath();
        System.setProperty("webdriver.chrome.driver", driverPath.toString());
        break;
      }

      case FIREFOX: {
        final Path driverPath = getFirefoxDriverPath();
        System.setProperty("webdriver.gecko.driver", driverPath.toString());
        break;
      }

      default:
        throw new IllegalStateException(String.format("Driver type %s is not supported", type.toString()));
    }

    DRIVER_REGISTRATIONS.put(type, Boolean.TRUE);
  }

  private static Path getChromeDriverPath() {
    String driverPath = "drivers" + File.separator + "chrome" + File.separator;
    switch (Platform.getCurrent()) {
      case WINDOWS:
        driverPath += "chromedriver_win32.exe";
        break;
      case LINUX:
        driverPath += "chromedriver_linux64";
        break;
      case MACOS:
        driverPath += "chromedriver_mac64";
        break;
    }

    try {
      return Path.of(DriverRegistrator.class.getClassLoader().getResource(driverPath).toURI());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static Path getFirefoxDriverPath() {
    String driverPath = "drivers" + File.separator + "firefox" + File.separator;
    switch (Platform.getCurrent()) {
      case WINDOWS:
        driverPath += "geckodriver_win64.exe";
        break;
      case LINUX:
        driverPath += "geckodriver_linux64";
        break;
      case MACOS:
        driverPath += "geckodriver_macos";
        break;
    }

    try {
      return Path.of(DriverRegistrator.class.getClassLoader().getResource(driverPath).toURI());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private enum Platform {
    WINDOWS,
    LINUX,
    MACOS;

    public static Platform getCurrent() {
      final var platformName = System.getProperty("os.name").toLowerCase();
      if (platformName.contains("windows")) {
        return WINDOWS;
      } else if (platformName.contains("linux")) {
        return LINUX;
      } else if (platformName.contains("mac")) {
        return MACOS;
      } else {
        throw new IllegalStateException(String.format("Platforms %s is not supported", platformName));
      }
    }
  }
}
