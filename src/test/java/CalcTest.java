import org.example.Calc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;;

public class CalcTest {
    @Test
    public void threePlusFive_equalsEight() {
        assertEquals(8, Calc.run("3 + 5"));
    }

    @Test
    public void tenPlusFive_equalsFifteen() {
        assertEquals(15, Calc.run("10 + 5"));
    }

    @Test
    public void twentyPlusFive_equalsTwentyFive() {
        assertEquals(25, Calc.run("20 + 5"));
    }

    @Test
    public void threeMultiplyFive_equalsFifteen() {
        assertEquals(15, Calc.run("3 * 5"));
    }

    @Test
    public void tenMinusFive_equalsFive() {
        assertEquals(5, Calc.run("10 - 5"));
    }

    @Test
    public void tenDivideFive_equalsTwo() {
        assertEquals(2, Calc.run("10 / 5"));
    }

    @Test
    public void plusWithPolynomial() {
        assertEquals(15, Calc.run("3 + 5 + 7"));
    }

    @Test
    public void plusAndMultiply_multiplyFirst() {
        assertEquals(28, Calc.run("3 + 5 * 5"));
    }

    @Test
    public void calcWithBracket_bracketFirst() {
        assertEquals(40 , Calc.run("(3 + 5) * 5"));
    }

    @Test
    public void calcComplicatedPolynomial()  {
        assertEquals(60 , Calc.run("((3 + 5) * 5 + -10) * 10 / 5"));
    }
}
