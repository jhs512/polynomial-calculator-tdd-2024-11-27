import static org.assertj.core.api.Assertions.*;

import org.example.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    @DisplayName("덧셈 테스트")
    void addTest() {
        //given
        int a = 20;
        int b = 10;

        //when
        int result = calculator.add(a, b);

        //then
        assertThat(result).isEqualTo(a + b);
    }

    @Test
    @DisplayName("뺄셈 테스트")
    void subTest() {
        //given
        int a = 20;
        int b = 10;

        //when
        int result = calculator.subtract(a, b);

        //then
        assertThat(result).isEqualTo(a - b);
    }

    @Test
    @DisplayName("곱셈 테스트")
    void mulTest() {
        //given
        int a = 20;
        int b = 10;

        //when
        int result = calculator.multiply(a, b);

        //then
        assertThat(result).isEqualTo(a * b);
    }

    @Test
    @DisplayName("나눗셈 테스트")
    void divTest() {
        //given
        int a = 20;
        int b = 10;

        //when
        int result = calculator.divide(a, b);

        //then
        assertThat(result).isEqualTo(a / b);
    }

    @Test
    @DisplayName("사칙연산 테스트1 - 덧셈(파라미터 String으로 받을 경우)")
    void calcTest1() {
        //given
        String cal = "10 + 20";

        //when
        int reslut = calculator.calculate(cal);

        //then
        assertThat(reslut).isEqualTo(30);
    }

    @Test
    @DisplayName("사칙연산 테스트2 - 뺄셈(파라미터 String으로 받을 경우)")
    void calcTest2() {
        //given
        String cal = "10 - 20";

        //when
        int reslut = calculator.calculate(cal);

        //then
        assertThat(reslut).isEqualTo(-10);
    }

    @Test
    @DisplayName("사칙연산 테스트3 - 곱셈(파라미터 String으로 받을 경우)")
    void calcTest3() {
        //given
        String cal = "10 * 20";

        //when
        int reslut = calculator.calculate(cal);

        //then
        assertThat(reslut).isEqualTo(200);
    }

    @Test
    @DisplayName("사칙연산 테스트4 - 나눗셈(파라미터 String으로 받을 경우)")
    void calcTest4() {
        //given
        String cal = "20 / 10";

        //when
        int reslut = calculator.calculate(cal);

        //then
        assertThat(reslut).isEqualTo(2);
    }


}
