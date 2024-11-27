import static org.assertj.core.api.Assertions.*;

import org.example.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CombinedOperationsTest {

    Calculator calculator = Calculator.getInstance();

//    @Test
//    @DisplayName("다항식 연산 테스트")
//    void combinedOperationTest() {
//        //given
//        String expression1 = "2 * (5 + 2)";
//
//        //when
//        int result = calculator.combinedOperations(expression1);
//
//        //then
//        assertThat(result).isEqualTo(14);
//    }
//
//    @Test
//    @DisplayName("다항식 연산 테스트2")
//    void combinedOperationTest2() {
//        //given
//        String expression1 = "(5 + 3) * 3";
//
//        //when
//        int result = calculator.combinedOperations(expression1);
//
//        //then
//        assertThat(result).isEqualTo(24);
//    }
//
//    @Test
//    @DisplayName("다항식 연산 테스트3")
//    void combinedOperationTest3() {
//        //given
//        String expression1 = "5 + 3 * 3";
//
//        //when
//        int result = calculator.combinedOperations(expression1);
//
//        //then
//        assertThat(result).isEqualTo(14);
//    }
//
//    @Test
//    @DisplayName("다항식 연산 테스트4")
//    void combinedOperationTest4() {
//        //given
//        String expression1 = "5 * 3 + 3";
//
//        //when
//        int result = calculator.combinedOperations(expression1);
//
//        //then
//        assertThat(result).isEqualTo(18);
//    }
//
//    @Test
//    @DisplayName("다항식 연산 테스트5")
//    void combinedOperationTest5() {
//        //given
//        String expression1 = "((3 + 5) * 5 + 10) * 10 / 5";
//
//        //when
//        int result = calculator.combinedOperations(expression1);
//
//        //then
//        assertThat(result).isEqualTo(100);
//    }
//
//    @Test
//    @DisplayName("다항식 연산 테스트6")
//    void combinedOperationTest6() {
//        //given
//        String expression1 = "((3 + 5) * 5 + -10) * 10 / 5";
//
//        //when
//        int result = calculator.combinedOperations(expression1);
//
//        //then
//        assertThat(result).isEqualTo(60);
//    }
//
//    @Test
//    @DisplayName("다항식 연산 테스트7")
//    void combinedOperationTest7() {
//        //given
//        String expression1 = "((5 + -2) * 5 + -10) * 10 / 5 - 5";
//
//        //when
//        int result = calculator.combinedOperations(expression1);
//
//        //then
//        assertThat(result).isEqualTo(5);
//    }

    @Test
    @DisplayName("기본 연산")
    void combinedOperationTest1() {
        //given
        String expression = "3 + 2 * 2";

        //when
        int result = calculator.combinedOperations(expression);

        //then
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("괄호 포함")
    void combinedOperationTest2() {
        //given
        String expression = "(1+(4+5+2)-3)+(6+8)";

        //when
        int result = calculator.combinedOperations(expression);

        //then
        assertThat(result).isEqualTo(23);
    }

    @Test
    @DisplayName("음수 처리")
    void combinedOperationTest3() {
        //given
        String expression = "2-1+2";

        //when
        int result = calculator.combinedOperations(expression);

        //then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("연속된 연산자")
    void combinedOperationTest4() {
        //given
        String expression = "3 + 5 / 2";

        //when
        int result = calculator.combinedOperations(expression);

        //then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("연산자 우선순위 확인")
    void combinedOperationTest5() {
        //given
        String expression = "3 + 2 * 2";

        //when
        int result = calculator.combinedOperations(expression);

        //then
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("여러 개의 연산자와 괄호")
    void combinedOperationTest6() {
        //given
        String expression = "5+(8-3*2)+(1+4*5)";

        //when
        int result = calculator.combinedOperations(expression);

        //then
        assertThat(result).isEqualTo(23);
    }

    @Test
    @DisplayName("음수 처리(부호 앞에 연산자)")
    void combinedOperationTest7() {
        //given
        String expression = "3+-2";

        //when
        int result = calculator.combinedOperations(expression);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("빈 문자열 처리")
    void combinedOperationTest8() {
        //given
        String expression = "";

        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.combinedOperations(expression);
        });

    }

    @Test
    @DisplayName("잘못된 입력(괄호 불균형)")
    void combinedOperationTest9() {
        //given
        String expression = "";

        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.combinedOperations(expression);
        });

    }



}
