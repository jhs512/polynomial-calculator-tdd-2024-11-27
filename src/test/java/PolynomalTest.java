import org.example.Calc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// int rs = Calc.run("((3 + 5) * 5 + -10) * 10 / 5");
public class PolynomalTest {

    @Test
    @DisplayName("1 + 1 == 2")
    void 일더하기일() {
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 == 3")
    void 이더하기일() {
        assertThat(Calc.run("2 + 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 == 4")
    void 이더하기이() {
        assertThat(Calc.run("2 + 2")).isEqualTo(4);
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void 천더하기이백팔십() {
        assertThat(Calc.run("1000 + 280")).isEqualTo(1280);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void 이빼기일() {
        assertThat(Calc.run("2 - 1")).isEqualTo(1);
    }

    @Test
    @DisplayName("3 - 1 == 2")
    void 삼빼기일() {
        assertThat(Calc.run("3 - 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("100 - 20 == 80")
    void 백빼기이십() {
        assertThat(Calc.run("100 - 20")).isEqualTo(80);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void 숫자가세개지요() { // 10 + 20 + 30
        assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void 숫자가세개지요2() {
        assertThat(Calc.run("10 - 20 + 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 == -20")
    void 숫자가네개지요() {
        assertThat(Calc.run("10 - 10 - 10 - 10")).isEqualTo(-20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void 숫자가일곱개지요() {
        assertThat(Calc.run("10 - 10 - 10 - 10 + 10 + 10 - 10")).isEqualTo(-10);
    }

    @Test
    @DisplayName("10 * 10 == 100")
    void 십곱하기십() {
        assertThat(Calc.run("10 * 10")).isEqualTo(100);
    }

    @Test
    @DisplayName("10 * -10 == -100")
    void t13() {
        assertThat(Calc.run("10 * -10")).isEqualTo(-100);
    }

    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void t14() {
        assertThat(Calc.run("10 * 10 * 10")).isEqualTo(1000);
    }

    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void 곱하기먼저() {
        assertThat(Calc.run("10 + 5 * 2")).isEqualTo(20);
    }

    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void 곱하기먼저2() {
        assertThat(Calc.run("20 + 10 + 5 * 2")).isEqualTo(40);
    }

    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void t17() {
        assertThat(Calc.run("10 * 20 + 10 + 5 * 2")).isEqualTo(220);
    }

    @Test
    @DisplayName("(10 + 20) == 30")
    void t18() {
        assertThat(Calc.run("(10 + 20)")).isEqualTo(30);
    }

    @Test
    @DisplayName("((10 + 20)) == 30")
    void t19() {
        assertThat(Calc.run("((10 + 20))")).isEqualTo(30);
    }

    @Test
    @DisplayName("(((10 + 20))) == 30")
    void t20() {
        assertThat(Calc.run("(((10 + 20)))")).isEqualTo(30);
    }

    @Test
    @DisplayName("(20 + 20) + 20 == 60")
    void t21() {
        assertThat(Calc.run("(20 + 20) + 20")).isEqualTo(60);
    }

    @Test
    @DisplayName("((20 + 20)) + 20 == 60")
    void t22() {
        assertThat(Calc.run("((20 + 20)) + 20")).isEqualTo(60);
    }

    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    void t23() {
        assertThat(Calc.run("(10 + 20) * 3")).isEqualTo(90);
    }

    @Test
    @DisplayName("10 + (10 + 5) == 25")
    void t24() {
        assertThat(Calc.run("10 + (10 + 5)")).isEqualTo(25);
    }

    @Test
    @DisplayName("-(10 + 5) == -15")
    void t25() {
        assertThat(Calc.run("-(10 + 5)")).isEqualTo(-15);
    }

    @Test
    @DisplayName("-(8 + 2) * -(7 + 3) + 5 == 105")
    void t26() {
        assertThat(Calc.run("-(8 + 2) * -(7 + 3) + 5")).isEqualTo(105);
    }

    @Test
    @DisplayName("5 - (1 + 5) == -1")
    void t27() {
        assertThat(Calc.run("5 - (1 + 5)")).isEqualTo(-1);
    }

    @Test
    @DisplayName("3 * 1 + (1 - (4 * 1 - (1 - 1))) == 0")
    void t28() {
        assertThat(Calc.run("3 * 1 + (1 - (4 * 1 - (1 - 1)))")).isEqualTo(0);
    }
}