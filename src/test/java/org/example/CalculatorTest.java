package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

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

    @Test
    @DisplayName("덧셈 + 곱셈 테스트")
    public void t3() {
        int rs = Calculator.run("53+3*4+3");

        assertThat(rs).isEqualTo(68);
    }

    @Test
    @DisplayName("Expr에서 숫자만 읽어서 Deque에 넣기")
    public void t4() {
        Deque<String> deque = new ArrayDeque<>();

        Calculator.addNumberToDeque(deque, "1234abcd", 0);

        assertThat(deque.remove()).isEqualTo("1234");
    }

}