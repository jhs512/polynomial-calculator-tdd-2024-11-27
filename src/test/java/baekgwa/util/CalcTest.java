package baekgwa.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalcTest {
    @DisplayName("1+1 = ?")
    @Test
    void test() {
        // given
        String polynomial = "1 + 1";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(2);
    }
}