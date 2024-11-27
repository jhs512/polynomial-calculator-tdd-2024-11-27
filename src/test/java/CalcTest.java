import org.example.Calc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;;

public class CalcTest {
    @Test
    @DisplayName("더하기1: 3 + 5 = 8")
    public void threePlusFive_equalsEight() {
        assertEquals(8, Calc.run("3 + 5"));
    }

    @Test
    @DisplayName("더하기2: 10 + 5 = 15")
    public void tenPlusFive_equalsFifteen() {
        assertEquals(15, Calc.run("10 + 5"));
    }

    @Test
    @DisplayName("더하기3: 20 + 5 = 25")
    public void twentyPlusFive_equalsTwentyFive() {
        assertEquals(25, Calc.run("20 + 5"));
    }

    @Test
    @DisplayName("곱하기: 3 * 5 = 15")
    public void threeMultiplyFive_equalsFifteen() {
        assertEquals(15, Calc.run("3 * 5"));
    }

    @Test
    @DisplayName("빼기: 10 - 5 = 5")
    public void tenMinusFive_equalsFive() {
        assertEquals(5, Calc.run("10 - 5"));
    }

    @Test
    @DisplayName("나누기: 10 / 5 = 2")
    public void tenDivideFive_equalsTwo() {
        assertEquals(2, Calc.run("10 / 5"));
    }

    @Test
    @DisplayName("피연산자가 여러 개인 경우")
    public void plusManyOperands() {
        assertEquals(15, Calc.run("3 + 5 + 7"));
    }

    @Test
    @DisplayName("곱하기나 나누기 먼저 계산")
    public void plusAndMultiply_multiplyFirst() {
        assertEquals(28, Calc.run("3 + 5 * 5"));
    }

    @Test
    @DisplayName("괄호가 있는 경우 먼저 계산")
    public void calcWithBracket_bracketFirst() {
        assertEquals(40 , Calc.run("(3 + 5) * 5"));
    }

    @Test
    @DisplayName("목표 다항식 계산!")
    public void calcComplicatedPolynomial()  {
        assertEquals(60 , Calc.run("((3 + 5) * 5 + -10) * 10 / 5"));
    }
}
