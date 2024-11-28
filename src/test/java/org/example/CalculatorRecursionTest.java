package org.example;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorRecursionTest {

    CalculatorRecursion calculatorRecursion = CalculatorRecursion.getInstance();

    @Test
    @DisplayName("1 + 2 == 3")
    void t1() {
        //given
        String expression = "1 + 2";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(3);
    }

}