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

    @Test
    @DisplayName("2 + 1 == 3")
    void t2() {
        //given
        String expression = "2 + 1";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 == 4")
    void t3() {
        //given
        String expression = "2 + 2";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void t4() {
        //given
        String expression = "1000 + 280";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(1280);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void t5() {
        //given
        String expression = "2 - 1";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("3 - 1 == 2")
    void t6() {
        //given
        String expression = "3 - 1";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("100 - 20 == 80")
    void t7() {
        //given
        String expression = "100 - 20";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(80);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t8() {
        //given
        String expression = "10 + 20 + 30";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t9() {
        //given
        String expression = "10 - 20 + 30";

        //when
        int result = calculatorRecursion.calculate(expression);

        //then
        assertThat(result).isEqualTo(20);
    }

}