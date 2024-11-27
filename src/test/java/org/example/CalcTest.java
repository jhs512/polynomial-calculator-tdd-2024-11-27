package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CalcTest {

    @Test
    @DisplayName("3 + 5")
    public void t1() {
        int rs = Calc.run("3 + 5");

        assertThat(rs).isEqualTo(8);
    }

    @Test
    @DisplayName("5 - 3")
    public void t2() {
        int rs = Calc.run("5 - 3");

        assertThat(rs).isEqualTo(2);
    }

    @Test
    @DisplayName("5 * 3")
    public void t3() {
        int rs = Calc.run("5 * 3");

        assertThat(rs).isEqualTo(15);
    }

    @Test
    @DisplayName("6 / 3")
    public void t4() {
        int rs = Calc.run("6 / 3");

        assertThat(rs).isEqualTo(2);
    }

    @Test
    @DisplayName("(3+5) * 8")
    public void t5() {
        int rs = Calc.run("(3+5) * 8");

        assertThat(rs).isEqualTo(64);
    }


    @Test
    @DisplayName("((3 + 5) * 5 + -10) * 10 / 5")
    public void t6() {
        int rs = Calc.run("((3 + 5) * 5 + -10) * 10 / 5");

        assertThat(rs).isEqualTo(60);
    }

}