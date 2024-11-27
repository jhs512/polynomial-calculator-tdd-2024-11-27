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


}
