package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcImplTest {
    private CalcImpl calc = new CalcImpl();

    @Test
    void split() {
        calc.run("(2 + 5 * (3 + 5) * 5 + -10) * 10 / 5");
        ArrayList<String> sp = calc.splitPolynomial();

        assertEquals("2 + 5 * ", sp.get(0));
        assertEquals("3 + 5", sp.get(1));
        assertEquals(" * 5 + -10", sp.get(2));
        assertEquals(" * 10 / 5", sp.get(3));
    }

    @Test
    void getPriorityArr() {
        calc.run("(2 + 5 * (3 + 5) * 5 + -10) * 10 / 5");
        int[] testArr = {2, 3, 2, 1};
        ArrayList<Integer> priorityArr = calc.getPriorityArr(calc.splitPolynomial());

        for(int i = 0; i< testArr.length; i++) {
            assertEquals(testArr[i], priorityArr.get(i));
        }
    }

    @Test
    @DisplayName("(2 + 5 * (3 + 5) * 5 + -10) * 10 / 5")
    void t0() {
        assertEquals(384, calc.run("(2 + 5 * (3 + 5) * 5 + -10) * 10 / 5"));
    }

    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {
        assertEquals(2, calc.run("1 + 1"));
    }

    @Test
    @DisplayName("2 + 1 == 3")
    void t2() {
        assertEquals(3, calc.run("2 + 1"));
    }

    @Test
    @DisplayName("2 + 2 == 4")
    void t3() {
        assertEquals(4, calc.run("2 + 2"));
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void t4() {
        assertEquals(1280, calc.run("1000 + 280"));
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void t5() {
        assertEquals(1, calc.run("2 - 1"));
    }

    @Test
    @DisplayName("3 - 1 == 2")
    void t6() {
        assertEquals(2, calc.run("3 - 1"));
    }

    @Test
    @DisplayName("100 - 20 == 80")
    void t7() {
        assertEquals(80, calc.run("100 - 20"));
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t8() {
        assertEquals(60, calc.run("10 + 20 + 30"));
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t9() {
        assertEquals(20, calc.run("10 - 20 + 30"));
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 == -20")
    void t10() {
        assertEquals(-20, calc.run("10 - 10 - 10 - 10"));
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void t11() {
        assertEquals(-10, calc.run("10 - 10 - 10 - 10 + 10 + 10 - 10"));
    }

    @Test
    @DisplayName("10 * 10 == 100")
    void t12() {
        assertEquals(100, calc.run("10 * 10"));
    }

    @Test
    @DisplayName("10 * -10 == -100")
    void t13() {
        assertEquals(-100, calc.run("10 * -10"));
    }

    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void t14() {
        assertEquals(1000, calc.run("10 * 10 * 10"));
    }

    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void t15() {
        assertEquals(20, calc.run("10 + 5 * 2"));
    }

    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void t16() {
        assertEquals(40, calc.run("20 + 10 + 5 * 2"));
    }

    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void t17() {
        assertEquals(220, calc.run("10 * 20 + 10 + 5 * 2"));
    }

    @Test
    @DisplayName("(10 + 20) == 30")
    void t18() {
        assertEquals(30, calc.run("(10 + 20)"));
    }

    @Test
    @DisplayName("((10 + 20)) == 30")
    void t19() {
        assertEquals(30, calc.run("((10 + 20))"));
    }

    @Test
    @DisplayName("(((10 + 20))) == 30")
    void t20() {
        assertEquals(30, calc.run("(((10 + 20)))"));
    }

    @Test
    @DisplayName("(20 + 20) + 20 == 60")
    void t21() {
        assertEquals(60, calc.run("(20 + 20) + 20"));
    }

    @Test
    @DisplayName("((20 + 20)) + 20 == 60")
    void t22() {
        assertEquals(60, calc.run("((20 + 20)) + 20"));
    }

    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    void t23() {
        assertEquals(90, calc.run("(10 + 20) * 3"));
    }

    @Test
    @DisplayName("10 + (10 + 5) == 25")
    void t24() {
        assertEquals(25, calc.run("10 + (10 + 5)"));
    }

    @Test
    @DisplayName("-(10 + 5) == -15")
    void t25() {
        assertEquals(-15, calc.run("-(10 + 5)"));
    }

    @Test
    @DisplayName("-(8 + 2) * -(7 + 3) + 5 == 105")
    void t26() {
        assertEquals(105, calc.run("-(8 + 2) * -(7 + 3) + 5"));
    }

    @Test
    @DisplayName("5 - (1 + 5) == -1")
    void t27() {
        assertEquals(-1, calc.run("5 - (1 + 5)"));
    }

    @Test
    @DisplayName("3 * 1 + (1 - (4 * 1 - (1 - 1))) == 0")
    void t28() {
        assertEquals(0, calc.run("3 * 1 + (1 - (4 * 1 - (1 - 1)))"));
    }
}