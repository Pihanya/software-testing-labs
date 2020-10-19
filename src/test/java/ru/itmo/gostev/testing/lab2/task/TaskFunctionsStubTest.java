package ru.itmo.gostev.testing.lab2.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static ru.itmo.gostev.testing.lab2.TestUtils.stubWith;

import java.util.Map;
import org.junit.jupiter.api.Test;
import ru.itmo.gostev.testing.lab2.JavaMathFacade;
import ru.itmo.gostev.testing.lab2.TestUtils;
import ru.itmo.gostev.testing.lab2.logarithmic.Log10;
import ru.itmo.gostev.testing.lab2.logarithmic.Log2;
import ru.itmo.gostev.testing.lab2.logarithmic.Log3;
import ru.itmo.gostev.testing.lab2.logarithmic.Log5;
import ru.itmo.gostev.testing.lab2.logarithmic.LogE;
import ru.itmo.gostev.testing.lab2.trigonomertic.Cos;
import ru.itmo.gostev.testing.lab2.trigonomertic.Cosec;
import ru.itmo.gostev.testing.lab2.trigonomertic.Cot;
import ru.itmo.gostev.testing.lab2.trigonomertic.Sec;
import ru.itmo.gostev.testing.lab2.trigonomertic.Sin;
import ru.itmo.gostev.testing.lab2.utils.Constants;
import ru.itmo.gostev.testing.lab2.utils.MathFacade;

public class TaskFunctionsStubTest {

  private static final JavaMathFacade M = new JavaMathFacade();

  @Test
  public void testCosWithStubs() {
    final double x = -Constants.PI_DOUBLE / 4;
    final MathFacade stubbedFacade;
    {
      final Cos cosFunction = new Cos(new Sin(Constants.DEFAULT_PRECISION));

      final Sin sinStub = stubWith(mock(Sin.class), Map.of(x, M.sin(x)));
      final Cot cotStub = stubWith(mock(Cot.class), Map.of(x, M.cot(x)));
      final Cosec cosecStub = TestUtils.stubWith(mock(Cosec.class), Map.of(x, M.cosec(x)));
      final Sec secStub = TestUtils.stubWith(mock(Sec.class), Map.of(x, M.sec(x)));

      stubbedFacade = new MathFacade(
          mock(LogE.class), mock(Log2.class), mock(Log3.class), mock(Log5.class), mock(Log10.class),
          sinStub, cosFunction, cotStub, cosecStub, secStub
      );
    }
    stubTestComparingToJavaFacade(x, Constants.DEFAULT_PRECISION_DOUBLE, stubbedFacade);
  }

  public void stubTestComparingToJavaFacade(final double x, final double precision, final MathFacade stubbedFacade) {
    final double expectedY = new TaskEquationSystem(M).calculate(x);
    final double actualY = new TaskEquationSystem(stubbedFacade).calculate(x);

    assertTrue(TestUtils.valuesFitAllowedDifference(expectedY, actualY, precision));
  }
}
