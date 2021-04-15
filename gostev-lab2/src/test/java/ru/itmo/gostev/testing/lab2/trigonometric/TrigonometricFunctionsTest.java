package ru.itmo.gostev.testing.lab2.trigonometric;

import static ru.itmo.gostev.testing.lab2.TestUtils.testUnaryFunction;
import static ru.itmo.gostev.testing.lab2.utils.Constants.DEFAULT_PRECISION;

import java.io.File;
import org.junit.jupiter.api.Test;
import ru.itmo.gostev.testing.lab2.TestUtils;
import ru.itmo.gostev.testing.lab2.utils.MathFacade;

public class TrigonometricFunctionsTest {

  private static final String TABLE_PREFIX = "trigonometric" + File.separator;

  private static final String SIN_TABLE = TABLE_PREFIX + "sin_table.csv";

  private static final String COS_TABLE = TABLE_PREFIX + "cos_table.csv";

  private static final String COT_TABLE = TABLE_PREFIX + "cot_table.csv";

  private static final String COSEC_TABLE = TABLE_PREFIX + "cosec_table.csv";

  private static final String SEC_TABLE = TABLE_PREFIX + "sec_table.csv";

  private static final MathFacade FACADE = new MathFacade(DEFAULT_PRECISION);

  @Test
  public void testSin() {
    testUnaryFunction(FACADE.sin(), TestUtils.asResourceFile(SIN_TABLE), DEFAULT_PRECISION);
  }

  @Test
  public void testCos() {
    testUnaryFunction(FACADE.cos(), TestUtils.asResourceFile(COS_TABLE), DEFAULT_PRECISION);
  }

  @Test
  public void testCot() {
    testUnaryFunction(FACADE.cot(), TestUtils.asResourceFile(COT_TABLE), DEFAULT_PRECISION);
  }

  @Test
  public void testCosec() {
    testUnaryFunction(FACADE.cosec(), TestUtils.asResourceFile(COSEC_TABLE), DEFAULT_PRECISION);
  }

  @Test
  public void testSec() {
    testUnaryFunction(FACADE.sec(), TestUtils.asResourceFile(SEC_TABLE), DEFAULT_PRECISION);
  }
}
