package com.ll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleCalculatorTest {
    @Test
    @DisplayName("10 + 2 = 12")
    public void testPlus1() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int rs = simpleCalculator.plus(10,2);

        assertThat(12).isEqualTo(rs);
    }


    @Test
    @DisplayName("10 + 20 = 30")
    public void testPlus2() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int rs = simpleCalculator.plus(10,20);

        assertEquals(30, rs);
    }
    @Test
    @DisplayName("1 + 2 = 3")
    public void testPlus3() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int rs = simpleCalculator.plus(1,2);

        assertEquals(3, rs);
    }
    @Test
    @DisplayName("1 - 2 = -1")
    public void testMinus1() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int rs = simpleCalculator.minus(1,2);

        assertEquals(-1, rs);
    }
    @Test
    @DisplayName("10 - 20 = -10")
    public void testMinus2() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int rs = simpleCalculator.minus(10,20);

        assertEquals(-10, rs);
    }
    @Test
    @DisplayName("10 - 2 = 8")
    public void testMinus3() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int rs = simpleCalculator.minus(10,2);

        assertEquals(8, rs);
    }
    @Test
    @DisplayName("1 / 2 = 0.0")
    public void testDiv1() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        double rs = simpleCalculator.div(1,2);

        assertEquals(0.0, rs);
    }
    @Test
    @DisplayName("10 / 20 = 0.0")
    public void testDivs2() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        double rs = simpleCalculator.div(10,20);

        assertEquals(0.0, rs);
    }
    @Test
    @DisplayName("10 / 2 = 5.0")
    public void testDiv3() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        double rs = simpleCalculator.div(10,2);

        assertEquals(5.0, rs);
    }
}