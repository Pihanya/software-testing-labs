package ru.itmo.anokhin.testing.lab2.trigonometric;

import static ru.itmo.anokhin.testing.lab2.TestUtils.asResourceFile;
import static ru.itmo.anokhin.testing.lab2.TestUtils.testUnaryFunction;

import java.io.File;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import ru.itmo.anokhin.testing.lab2.utils.Constants;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

public class TrigonometricFunctionsTest {

  private static final String TABLE_PREFIX = "trigonometric" + File.separator;

  private static final String SIN_TABLE = TABLE_PREFIX + "sin_table.csv";

  private static final String COS_TABLE = TABLE_PREFIX + "cos_table.csv";

  private static final String TAN_TABLE = TABLE_PREFIX + "tan_table.csv";

  private static final String COSEC_TABLE = TABLE_PREFIX + "cosec_table.csv";

  private static final String SEC_TABLE = TABLE_PREFIX + "sec_table.csv";

  private static final BigDecimal PRECISION = Constants.DEFAULT_PRECISION;

  private static final MathFacade FACADE = new MathFacade(PRECISION);

  @Test
  public void testSin() {
    testUnaryFunction(FACADE.sin(), asResourceFile(SIN_TABLE), PRECISION);
  }

  @Test
  public void testCos() {
    testUnaryFunction(FACADE.cos(), asResourceFile(COS_TABLE), PRECISION);
  }

  @Test
  public void testTan() {
    testUnaryFunction(FACADE.tan(), asResourceFile(TAN_TABLE), PRECISION);
  }

  @Test
  public void testCosec() {
    testUnaryFunction(FACADE.cosec(), asResourceFile(COSEC_TABLE), PRECISION);
  }

  @Test
  public void testSec() {
    testUnaryFunction(FACADE.sec(), asResourceFile(SEC_TABLE), PRECISION);
  }
}
