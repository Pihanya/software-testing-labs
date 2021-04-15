package ru.itmo.anokhin.testing.lab2.logarithmic;

import static ru.itmo.anokhin.testing.lab2.TestUtils.asResourceFile;
import static ru.itmo.anokhin.testing.lab2.TestUtils.testUnaryFunction;

import java.io.File;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

public class LogarithmicFunctionsTest {

  private static final String TABLE_PREFIX = "logarithmic" + File.separator;

  private static final String LN_TABLE = TABLE_PREFIX + "ln_table.csv";

  private static final String LOG2_TABLE = TABLE_PREFIX + "log2_table.csv";

  private static final String LOG3_TABLE = TABLE_PREFIX + "log3_table.csv";

  private static final String LOG5_TABLE = TABLE_PREFIX + "log5_table.csv";

  private static final BigDecimal PRECISION = BigDecimal.valueOf(0.0001D);

  private static final MathFacade FACADE = new MathFacade(PRECISION);

  @Test
  public void testLn() {
    testUnaryFunction(FACADE.ln(), asResourceFile(LN_TABLE), PRECISION);
  }

  @Test
  public void testLog2() {
    testUnaryFunction(FACADE.log2(), asResourceFile(LOG2_TABLE), PRECISION);
  }

  @Test
  public void testLog3() {
    testUnaryFunction(FACADE.log3(), asResourceFile(LOG3_TABLE), PRECISION);
  }

  @Test
  public void testLog5() {
    testUnaryFunction(FACADE.log5(), asResourceFile(LOG5_TABLE), PRECISION);
  }
}
