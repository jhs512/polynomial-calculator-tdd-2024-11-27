import org.assertj.core.api.Assertions;
import org.example.Calc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// int rs = Calc.run("((3 + 5) * 5 + -10) * 10 / 5");
public class PolynomalTest {

    @Test
    @DisplayName("1 + 1 == 2")
    void 일더하기일() {
        Assertions.assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 == 3")
    void 이더하기일() {
        Assertions.assertThat(Calc.run("2 + 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 == 4")
    void 이더하기이() {
        Assertions.assertThat(Calc.run("2 + 2")).isEqualTo(4);
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void 천더하기이백팔십() {
        Assertions.assertThat(Calc.run("1000 + 280")).isEqualTo(1280);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void 이빼기일() {
        Assertions.assertThat(Calc.run("2 - 1")).isEqualTo(1);
    }

    @Test
    @DisplayName("3 - 1 == 2")
    void 삼빼기일() {
        Assertions.assertThat(Calc.run("3 - 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("100 - 20 == 80")
    void 백빼기이십() {
        Assertions.assertThat(Calc.run("100 - 20")).isEqualTo(80);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void 숫자가세개지요() { // 10 + 20 + 30
        Assertions.assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }
}
