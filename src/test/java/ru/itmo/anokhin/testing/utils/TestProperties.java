package ru.itmo.anokhin.testing.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.itmo.anokhin.testing.driver.DriverType;

public class TestProperties {

  private final DriverType driverType;

  @JsonCreator
  public TestProperties(@JsonProperty("driverType") final DriverType driverType) {
    this.driverType = driverType;
  }

  public DriverType getDriverType() {
    return driverType;
  }
}
