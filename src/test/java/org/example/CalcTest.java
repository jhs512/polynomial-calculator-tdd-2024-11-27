package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalcTest {

    @Test
    @DisplayName("3 + 5")
    void t1() {
        //Given
        String input = "3 + 5";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(8);
    }

    @Test
    @DisplayName("8 - 2")
    void t2() {
        //Given
        String input = "8 - 2";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("10 * 5")
    void t3() {
        //Given
        String input = "10 * 5";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(50);
    }

    @Test
    @DisplayName("100 / 25")
    void t4() {
        //Given
        String input = "100 / 25";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("(5 - 10) * 2")
    void t5() {
        //Given
        String input = "(5 - 10) * 2";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(-10);
    }

    @Test
    @DisplayName("1 + (3 * (5 + 2 * 3) - +7) - -3")
    void t6() {
        //Given
        String input = "1 + (3 * (5 + 2 * 3) - +7) - -3";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(30);
    }

    @Test
    @DisplayName("6 - ((10 / -2) * (10 + -7) / -3) + +3")
    void t7() {
        //Given
        String input = "6 - ((10 / -2) * (10 + -7) / -3) + +3";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("3x + -8 / (10 - 2), 계산할 수 없는 문자 포함")
    void t8() {
        //Given
        String input = "3x + -8 / (10 - 2)";

        //When

        //Then
        assertThatThrownBy(() -> Calc.run(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("계산할 수 없는 문자가 포함되어있습니다.: 3x + -8 / (10 - 2)");
    }

    @Test
    @DisplayName("5 - (1 * -3 + (7 + -1) / 2, 괄호 문제로 계산 불가능한 수식")
    void t9() {
        //Given
        String input = "5 - (1 * -3 + (7 + -1) / 2";

        //When

        //Then
        assertThatThrownBy(() -> Calc.run(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("연산 기호와 정수가 아닌 문자, 공백, 괄호 등이 포함된 식은 연산할 수 없습니다.: 5-(1*-3+6/2");
    }

    @Test
    @DisplayName("(3 + 5 * (-1 - -7 * (3 * 15 - -1) / 2) + 10) -1")
    void t10() {
        //Given
        String input = "(3 + 5 * (-1 - -7 * (3 * 15 - -1) / 2) + 10) -1";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(812);
    }

    @Test
    @DisplayName("11 * (22 - 10 + -1) - 3 * -7 + (36 / +6 * -5)")
    void t11() {
        //Given
        String input = "11 * (22 - 10 + -1) - 3 * -7 + (36 / +6 * -5)";

        //When
        int result = Calc.run(input);

        //Then
        assertThat(result).isEqualTo(112);
    }

    @Test
    @DisplayName("10 + 5 / 3 * (-8 + +4 - (12 - 6 / (7 + -1 * 7))), 0으로 나누는 수식")
    void t12() {
        //Given
        String input = "10 + 5 / 3 * (-8 + +4 - (12 - 6 / (7 + -1 * 7)))";

        //When

        //Then
        assertThatThrownBy(() -> Calc.run(input))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("0으로 나눌 수 없습니다.");
    }

}
