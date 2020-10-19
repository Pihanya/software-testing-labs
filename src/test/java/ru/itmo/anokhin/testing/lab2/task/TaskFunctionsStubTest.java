package ru.itmo.anokhin.testing.lab2.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static ru.itmo.anokhin.testing.lab2.TestUtils.stubWith;
import static ru.itmo.anokhin.testing.lab2.TestUtils.valuesFitAllowedDifference;
import static ru.itmo.anokhin.testing.lab2.utils.Constants.DEFAULT_PRECISION;
import static ru.itmo.anokhin.testing.lab2.utils.Constants.DEFAULT_PRECISION_DOUBLE;
import static ru.itmo.anokhin.testing.lab2.utils.Constants.PI_DOUBLE;

import java.util.Map;
import org.junit.jupiter.api.Test;
import ru.itmo.anokhin.testing.lab2.JavaMathFacade;
import ru.itmo.anokhin.testing.lab2.logarithmic.Log2;
import ru.itmo.anokhin.testing.lab2.logarithmic.Log3;
import ru.itmo.anokhin.testing.lab2.logarithmic.Log5;
import ru.itmo.anokhin.testing.lab2.logarithmic.LogE;
import ru.itmo.anokhin.testing.lab2.trigonomertic.Cos;
import ru.itmo.anokhin.testing.lab2.trigonomertic.Cosec;
import ru.itmo.anokhin.testing.lab2.trigonomertic.Sec;
import ru.itmo.anokhin.testing.lab2.trigonomertic.Sin;
import ru.itmo.anokhin.testing.lab2.trigonomertic.Tan;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

public class TaskFunctionsStubTest {

  private static final JavaMathFacade M = new JavaMathFacade();

  @Test
  public void testCosWithStubs() {
    final double x = -PI_DOUBLE / 4;
    final MathFacade stubbedFacade;
    {
      final Cos cosFunction = new Cos(new Sin(DEFAULT_PRECISION));

      final Sin sinStub = stubWith(mock(Sin.class), Map.of(x, M.sin(x)));
      final Tan tanStub = stubWith(mock(Tan.class), Map.of(x, M.tan(x)));
      final Cosec cosecStub = stubWith(mock(Cosec.class), Map.of(x, M.cosec(x)));
      final Sec secStub = stubWith(mock(Sec.class), Map.of(x, M.sec(x)));

      stubbedFacade = new MathFacade(
          mock(LogE.class), mock(Log2.class), mock(Log3.class), mock(Log5.class),
          sinStub, cosFunction, tanStub, cosecStub, secStub
      );
    }
    stubTestComparingToJavaFacade(x, DEFAULT_PRECISION_DOUBLE, stubbedFacade);
  }

  public void stubTestComparingToJavaFacade(final double x, final double precision, final MathFacade stubbedFacade) {
    final double expectedY = new TaskEquationSystem(M).calculate(x);
    final double actualY = new TaskEquationSystem(stubbedFacade).calculate(x);

    assertTrue(valuesFitAllowedDifference(expectedY, actualY, precision));
  }
}
