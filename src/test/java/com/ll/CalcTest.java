package com.ll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// 목표 Calc.run("((3 + 5) * 5 + -10) * 10 / 5");
public class CalcTest {
    @Test
    @DisplayName("3 + 5 = 8")
    public void t1() {
        int rs = Calc.run1("3 + 5");

        assertThat(8).isEqualTo(rs);
    }
    @Test
    @DisplayName("-10 + 5 = 15")
    public void t2() {
        int rs = Calc.run1("-10 + 5");

        assertThat(-5).isEqualTo(rs);
    }
    @Test
    @DisplayName("(3 + 5) * 5 = 40")
    public void t3() {
        int rs = CalcDFS.run("(3 + 5) * 5");

        assertThat(40).isEqualTo(rs);
    }
    @Test
    @DisplayName("(10 + 5) * 5 = 75")
    public void t4() {
        int rs = CalcDFS.run("(10 + 5) * 5");

        assertThat(75).isEqualTo(rs);
    }
    @Test
    @DisplayName("5 * (3 + 5) = 40")
    public void t5() {
        int rs = CalcDFS.run("5 * (3 + 5)");

        assertThat(40).isEqualTo(rs);
    }
    @Test
    @DisplayName("(3 + 5) * 5 + -10 = 30")
    public void t6() {
        int rs = CalcDFS.run("(3 + 5) * 5 + -10");

        assertThat(30).isEqualTo(rs);
    }
    @Test
    @DisplayName("-10 + 5 * (3 + 5) = 30")
    public void t7() {
        int rs = CalcDFS.run("-10 + 5 * (3 + 5)");

        assertThat(30).isEqualTo(rs);
    }
    @Test
    @DisplayName("((3 + 5) * 5 + -10) * 10 = 300")
    public void t8() {
        int rs = CalcDFS.run("((3 + 5) * 5 + -10) * 10");

        assertThat(300).isEqualTo(rs);
    }
    @Test
    @DisplayName("10 * (-10 + 5 * (3 + 5)) = 300")
    public void t9() {
        int rs = CalcDFS.run("10 * (-10 + 5 * (3 + 5))");

        assertThat(300).isEqualTo(rs);
    }
    @Test
    @DisplayName("5 - 3 = 2")
    public void t10() {
        int rs = CalcDFS.run("5 - 3");

        assertThat(2).isEqualTo(rs);
    }
    @Test
    @DisplayName("-3 + 5 = 2")
    public void t11() {
        int rs = CalcDFS.run("-3 + 5");

        assertThat(2).isEqualTo(rs);
    }
    @Test
    @DisplayName("10 * (-10 + 5 * (8 - 5)) = 50")
    public void t12() {
        int rs = CalcDFS.run("10 * (-10 + 5 * (8 - 5))");

        assertThat(50).isEqualTo(rs);
    }
    @Test
    @DisplayName("10 * (-10 + 5 * (-5 + 8)) = 50")
    public void t13() {
        int rs = CalcDFS.run("10 * (-10 + 5 * (-5 + 8))");

        assertThat(50).isEqualTo(rs);
    }
    @Test
    @DisplayName("10 / 5 = 2")
    public void t14() {
        int rs = CalcDFS.run("10 / 5");

        assertThat(2).isEqualTo(rs);
    }
    @Test
    @DisplayName("5 * 10 / 5 = 10")
    public void t15() {
        int rs = CalcDFS.run("5 * 10 / 5");

        assertThat(10).isEqualTo(rs);
    }
    @Test
    @DisplayName("((3 + 5) * 5 + -10) * 10 / 5 = 60")
    public void t16() {
        int rs = CalcDFS.run("((3 + 5) * 5 + -10) * 10 / 5");

        assertThat(60).isEqualTo(rs);
    }
    @Test
    @DisplayName("((3 + 5) / (2 + 2)) * (10 / 5) + 5 = 9")
    public void t17() {
        int rs = CalcDFS.run("((3 + 5) / (2 + 2)) * (10 / 5) + 5");

        assertThat(9).isEqualTo(rs);
    }
    @Test
    @DisplayName("((3 + 5) / (2 + 2)) * (10 / 5) + -5 = 9")
    public void t18() {
        int rs = CalcDFS.run("((3 + 5) / (2 + 2)) * (10 / 5) + -5");

        assertThat(-1).isEqualTo(rs);
    }
}
