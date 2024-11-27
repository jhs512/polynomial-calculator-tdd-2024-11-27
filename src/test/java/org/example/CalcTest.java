package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {
    Calc calc = new Calc();
    @Test
    @DisplayName("SimpleCalculate : 괄호 없는 연산")
    void simpleCalculate() {
        String expression1 = "1 + 2";
        String expression2 = "1 - 2";
        String expression3 = "1 - 2 * 4 / 2";
        int result1 = calc.simpleCalculate(expression1);
        int result2 = calc.simpleCalculate(expression2);
        int result3 = calc.simpleCalculate(expression3);

        Assertions.assertThat(result1).isEqualTo(3);
        Assertions.assertThat(result2).isEqualTo(-1);
        Assertions.assertThat(result3).isEqualTo(-3);
    }

    @Test
    @DisplayName("run test 실행")
    void run() {
        // given
        String expression = "((3 + 5) * 5 + -10) * 10 / 5";

        // when
        int result = calc.run(expression);

        // then
        Assertions.assertThat(result).isEqualTo(60);

    }
}