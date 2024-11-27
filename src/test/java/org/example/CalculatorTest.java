package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("괄호가 정확하게 입력되지 않았을 경우 에러 발생 ex 2+(2 ")
    void isvValidateFormatTest() {
        Calculator calculator = new Calculator();

        Assertions.assertThatThrownBy(() -> calculator.isValidateFormat("2+(2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("괄호를 제대로 입력해주세요.");
    }

    @Test
    @DisplayName("숫자와 */+-() 외의 문자가 들어오면 에러 발생")
    void isvValidateFormatTest_type_permit() {
        Calculator calculator = new Calculator();

        Assertions.assertThatThrownBy(() -> calculator.isValidateFormat("add"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자와 */+-() 만 허용합니다.");
    }
}