package com.ll;

import com.ll.controller.CalculatorContoller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalcTest {
    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("1 + 1");

        assertTrue(result == 2);

    }

    @Test
    @DisplayName("2 + 1 == 3")
    void t2() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("2 + 1");

        assertTrue(result == 3);
    }

    @Test
    @DisplayName("2 + 2 == 4")
    void t3() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("2 + 2");

        assertTrue(result == 4);
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void t4() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("1000 + 280");

        assertTrue(result == 1280);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void t5() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("2 - 1");

        assertTrue(result == 1);
    }

    @Test
    @DisplayName("3 - 1 == 2")
    void t6() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("3 - 1");

        assertTrue(result == 2);
    }

    @Test
    @DisplayName("100 - 20 == 80")
    void t7() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("100 - 20");

        assertTrue(result == 80);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t8() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 + 20 + 30");

        assertTrue(result == 60);
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t9() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 - 20 + 30");

        assertTrue(result == 20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 == -20")
    void t10() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 - 10 - 10 - 10");

        assertTrue(result == -20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void t11() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 - 10 - 10 - 10 + 10 + 10 - 10");

        assertTrue(result == -10);
    }

    @Test
    @DisplayName("10 * 10 == 100")
    void t12() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 * 10");

        assertTrue(result == 100);
    }

    @Test
    @DisplayName("10 * -10 == -100")
    void t13() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 * -10");

        assertTrue(result == -100);
    }

    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void t14() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 * 10 * 10");

        assertTrue(result == 1000);
    }

    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void t15() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 + 5 * 2");

        assertTrue(result == 20);
    }

    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void t16() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("20 + 10 + 5 * 2");

        assertTrue(result == 40);
    }

    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void t17() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 * 20 + 10 + 5 * 2");

        assertTrue(result == 220);
    }

    @Test
    @DisplayName("(10 + 20) == 30")
    void t18() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("(10 + 20)");

        assertTrue(result == 30);
    }

    @Test
    @DisplayName("((10 + 20)) == 30")
    void t19() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("((10 + 20))");

        assertTrue(result == 30);
    }

    @Test
    @DisplayName("(((10 + 20))) == 30")
    void t20() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("(((10 + 20)))");

        assertTrue(result == 30);
    }

    @Test
    @DisplayName("(20 + 20) + 20 == 60")
    void t21() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("(20 + 20) + 20");

        assertTrue(result == 60);
    }

    @Test
    @DisplayName("((20 + 20)) + 20 == 60")
    void t22() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("((20 + 20)) + 20");

        assertTrue(result == 60);
    }

    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    void t23() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("(10 + 20) * 3");

        assertTrue(result == 90);
    }

    @Test
    @DisplayName("10 + (10 + 5) == 25")
    void t24() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("10 + (10 + 5)");

        assertTrue(result == 25);
    }

    @Test
    @DisplayName("-(10 + 5) == -15")
    void t25() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("-(10 + 5)");

        assertTrue(result == -15);
    }

    @Test
    @DisplayName("-(8 + 2) * -(7 + 3) + 5 == 105")
    void t26() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("-(8 + 2) * -(7 + 3) + 5");

        assertTrue(result == 105);
    }

    @Test
    @DisplayName("5 - (1 + 5) == -1")
    void t27() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("5 - (1 + 5)");

        assertTrue(result == -1);
    }

    @Test
    @DisplayName("3 * 1 + (1 - (4 * 1 - (1 - 1))) == 0")
    void t28() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("3 * 1 + (1 - (4 * 1 - (1 - 1)))");

        assertTrue(result == 0);
    }

    @Test
    @DisplayName("-(8 + 2) / -(7 + 3) + 5 == 6")
    void t29() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("-(8 + 2) / -(7 + 3) + 5");

        assertTrue(result == 6);
    }

    @Test
    @DisplayName("-(8 + 2) (/ -(7 + 3) + 5 == 6")
    void t30() {
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("-(8 + 2) (/ -(7 + 3) + 5");

        assertTrue(result == 6);
    }
}