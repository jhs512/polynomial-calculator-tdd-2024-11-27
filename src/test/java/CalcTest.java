import org.example.Calc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;;

public class CalcTest {
    @Test
    public void threePlusFive_equalsEight() {
        assertEquals(8, Calc.run("3 + 5"));
    }
}
