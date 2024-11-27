import com.ll.Calc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalcTest {
    // int rs = Calc.run("((3 + 5) * 5 + -10) * 10 / 5");

    @Test
    @DisplayName("3 + 5")
    public void plus_success() {
        int rs = Calc.run("3 + 5");
        assertThat(rs).isEqualTo(8);
    }

    @Test
    @DisplayName("10 / 5")
    public void divide_success() {
        int rs = Calc.run("10 / 5");
        assertThat(rs).isEqualTo(2);
    }

    @Test
    @DisplayName("10 / 0")
    public void divide_failure() {
        assertThatThrownBy(() -> Calc.run("10 / 0"))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("0으로 나눌수 없음.");
    }

    @Test
    @DisplayName("20 * 5")
    public void multiplu_success() {
        int rs = Calc.run("20 * 5");
        assertThat(rs).isEqualTo(100);
    }

    @Test
    @DisplayName("20 - 5")
    public void minus_success() {
        int rs = Calc.run("20 - 5");
        assertThat(rs).isEqualTo(15);
    }

}
