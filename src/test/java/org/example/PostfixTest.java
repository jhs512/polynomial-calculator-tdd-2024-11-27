package org.example;

import static org.assertj.core.api.Assertions.*;

import org.example.postfix.Postfix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostfixTest {

    Postfix postfix = Postfix.getInstance();

    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {
        //given
        String expression = "1 + 1";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 == 3")
    void t2() {
        //given
        String expression = "2 + 1";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 == 4")
    void t3() {
        //given
        String expression = "2 + 2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void t4() {
        //given
        String expression = "1000 + 280";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(1280);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void t5() {
        //given
        String expression = "2 - 1";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("3 - 1 == 2")
    void t6() {
        //given
        String expression = "3 - 1";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("100 - 20 == 80")
    void t7() {
        //given
        String expression = "100 - 20";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(80);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t8() {
        //given
        String expression = "10 + 20 + 30";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t9() {
        //given
        String expression = "10 - 20 + 30";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 == -20")
    void t10() {
        //given
        String expression = "10 - 10 - 10 - 10";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(-20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void t11() {
        //given
        String expression = "10 - 10 - 10 - 10 + 10 + 10 - 10";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(-10);
    }

    @Test
    @DisplayName("10 * 10 == 100")
    void t12() {
        //given
        String expression = "10 * 10";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(100);
    }

    @Test
    @DisplayName("10 * -10 == -100")
    void t13() {
        //given
        String expression = "10 * -10";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(-100);
    }

    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void t14() {
        //given
        String expression = "10 * 10 * 10";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void t15() {
        //given
        String expression = "10 + 5 * 2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(20);
    }

    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void t16() {
        //given
        String expression = "20 + 10 + 5 * 2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(40);
    }

    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void t17() {
        //given
        String expression = "10 * 20 + 10 + 5 * 2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(220);
    }

    @Test
    @DisplayName("(10 + 20) == 30")
    void t18() {
        //given
        String expression = "(10 + 20)";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(30);
    }

    @Test
    @DisplayName("((10 + 20)) == 30")
    void t19() {
        //given
        String expression = "((10 + 20))";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(30);
    }

    @Test
    @DisplayName("(((10 + 20))) == 30")
    void t20() {
        //given
        String expression = "(((10 + 20)))";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(30);
    }

    @Test
    @DisplayName("(20 + 20) + 20 == 60")
    void t21() {
        //given
        String expression = "(20 + 20) + 20";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("((20 + 20)) + 20 == 60")
    void t22() {
        //given
        String expression = "((20 + 20)) + 20";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    void t23() {
        //given
        String expression = "(10 + 20) * 3";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(90);
    }

    @Test
    @DisplayName("10 + (10 + 5) == 25")
    void t24() {
        //given
        String expression = "10 + (10 + 5)";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(25);
    }

    @Test
    @DisplayName("-(10 + 5) == -15")
    void t25() {
        //given
        String expression = "-(10 + 5)";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(-15);
    }

    @Test
    @DisplayName("-(8 + 2) * -(7 + 3) + 5 == 105")
    void t26() {
        //given
        String expression = "-(8 + 2) * -(7 + 3) + 5";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(105);
    }

    @Test
    @DisplayName("5 - (1 + 5) == -1")
    void t27() {
        //given
        String expression = "5 - (1 + 5)";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("3 * 1 + (1 - (4 * 1 - (1 - 1))) == 0")
    void t28() {
        //given
        String expression = "3 * 1 + (1 - (4 * 1 - (1 - 1)))";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("3 - (1 + 2 * 5) == -8")
    void t29() {
        //given
        String expression = "3 - (1 + 2 * 5)";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(-8);
    }


    @Test
    @DisplayName("다항식 연산 테스트")
    void combinedOperationTest() {
        //given
        String expression = "2 * (5 + 2)";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("다항식 연산 테스트2")
    void combinedOperationTest2() {
        //given
        String expression = "(5 + 3) * 3";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(24);
    }

    @Test
    @DisplayName("다항식 연산 테스트3")
    void combinedOperationTest3() {
        //given
        String expression = "5 + 3 * 3";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("다항식 연산 테스트4")
    void combinedOperationTest4() {
        //given
        String expression = "5 * 3 + 3";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(18);
    }

    @Test
    @DisplayName("다항식 연산 테스트5")
    void combinedOperationTest5() {
        //given
        String expression = "((3 + 5) * 5 + 10) * 10 / 5";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(100);
    }

    @Test
    @DisplayName("다항식 연산 테스트6")
    void combinedOperationTest6() {
        //given
        String expression = "((3 + 5) * 5 + -10) * 10 / 5";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("다항식 연산 테스트7")
    void combinedOperationTest7() {
        //given
        String expression = "((5 + -2) * 5 + -10) * 10 / 5 - 5";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("기본 연산")
    void combinedOperationTest8() {
        //given
        String expression = "3 + 2 * 2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("괄호 포함")
    void combinedOperationTest9() {
        //given
        String expression = "(1+(4+5+2)-3)+(6+8)";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(23);
    }

    @Test
    @DisplayName("음수 처리")
    void combinedOperationTest10() {
        //given
        String expression = "2-1+2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("연속된 연산자")
    void combinedOperationTest11() {
        //given
        String expression = "3 + 5 / 2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("연산자 우선순위 확인")
    void combinedOperationTest12() {
        //given
        String expression = "3 + 2 * 2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("여러 개의 연산자와 괄호")
    void combinedOperationTest13() {
        //given
        String expression = "5+(8-3*2)+(1+4*5)";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(28);
    }

    @Test
    @DisplayName("음수 처리(부호 앞에 연산자)")
    void combinedOperationTest14() {
        //given
        String expression = "3+-2";

        //when
        int result = postfix.calculatePostfix(postfix.infixToPostfix(expression));

        //then
        assertThat(result).isEqualTo(1);
    }

}
