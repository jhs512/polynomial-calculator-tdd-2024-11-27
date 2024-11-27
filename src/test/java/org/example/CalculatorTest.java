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
    public void t1() {

        int rs = Calculator.run("5+4+3");

        assertThat(rs).isEqualTo(12);
    }

    @Test
    @DisplayName("곱셈 테스트")
    public void t2() {
        int rs = Calculator.run("5*4*3");

        assertThat(rs).isEqualTo(60);
    }

}