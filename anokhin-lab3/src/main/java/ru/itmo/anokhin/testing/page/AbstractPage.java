package ru.itmo.anokhin.testing.page;

import java.io.Closeable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.itmo.anokhin.testing.driver.DriverRegistrator;
import ru.itmo.anokhin.testing.driver.DriverType;

public abstract class AbstractPage implements Closeable {

  protected final RemoteWebDriver driver;

  protected AbstractPage(final DriverType driverType, final String startPageUrl) {
    this.driver = initializeDriver(Objects.requireNonNull(driverType));
    initializeStartPage(startPageUrl);
  }

  public RemoteWebDriver getDriver() {
    return driver;
  }

  protected void initializeStartPage(final String startPageUrl) {
    driver.get(startPageUrl);
  }

  @Override
  public void close() {
    driver.quit();
  }

  private RemoteWebDriver initializeDriver(final DriverType driverType) {
    final RemoteWebDriver driver;

    DriverRegistrator.registerDriver(driverType);
    switch (driverType) {
      case CHROME:
        driver = new ChromeDriver();
        break;
      case FIREFOX:
        driver = new FirefoxDriver();
        break;
      default:
        throw new IllegalStateException(String.format("Driver type %s is not supported", driverType.toString()));
    }

    {
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      driver.manage().window().maximize();
    }

    return driver;
  }
}
