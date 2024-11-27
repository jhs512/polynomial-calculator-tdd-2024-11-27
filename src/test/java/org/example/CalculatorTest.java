package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Test
    @DisplayName("덧셈 테스트")
    public void t1_1() {

        int rs = Calculator.run("5+4+3");

        assertThat(rs).isEqualTo(12);
    }

    @Test
    @DisplayName("음수 덧셈 테스트")
    public void t1_2() {

        int rs = Calculator.run("5+4+-3");

        assertThat(rs).isEqualTo(6);
    }


    @Test
    @DisplayName("곱셈 테스트")
    public void t2() {
        int rs = Calculator.run("5*4*3");

        assertThat(rs).isEqualTo(60);
    }

    @Test
    @DisplayName("음수 곱셈 테스트")
    public void t2_2() {
        int rs = Calculator.run("5*4*-3");
        int rs2 = Calculator2.run("5*4*-3");

        assertThat(rs).isEqualTo(-60);
        assertThat(rs2).isEqualTo(-60);
    }

    @Test
    @DisplayName("덧셈 + 곱셈 테스트")
    public void t3() {
        int rs = Calculator.run("53+3*4+3");

        assertThat(rs).isEqualTo(68);
    }

    @Test
    @DisplayName("복잡한 괄호 테스트")
    public void t1() {

        int rs = Calculator.run("((8 - 3) * (5 + 2)) - (10 / 2)");
        int rs2 = Calculator2.run("((8 - 3) * (5 + 2)) - (10 / 2)");
        assertThat(rs).isEqualTo(30);
        assertThat(rs2).isEqualTo(30);
    }

}