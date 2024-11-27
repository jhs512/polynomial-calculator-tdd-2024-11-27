package baekgwa.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalcTest {
    @DisplayName("11+12 = ?")
    @Test
    void 기본_더하기_계산() {
        // given
        String polynomial = "11 + 12";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(23);
    }

    @DisplayName("1-1 = ?")
    @Test
    void 기본_뺄셈_계산() {
        // given
        String polynomial = "1 - 1";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(0);
    }

    @DisplayName("2*2 = ?")
    @Test
    void 기본_곱셈_계산() {
        // given
        String polynomial = "2 * 2";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(4);
    }

    @DisplayName("4 / 2 = ?")
    @Test
    void 기본_나눗셈_계산() {
        // given
        String polynomial = "4 / 2";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(2);
    }

    @DisplayName("(1 + 2) * 4 / 2 = ?")
    @Test
    void 복합한_다형식_계산1() {
        // given
        String polynomial = "(1 + 2) * 4 / 2";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("((1 + 2) * 4) / 2 = ?")
    @Test
    void 복합한_다형식_계산2() {
        // given
        String polynomial = "((1 + 2) * 4) / 2";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("3 + 5 * 2 - (6 / 3) = ?")
    @Test
    void 복합한_다형식_계산3() {
        // given
        String polynomial = "3 + 5 * 2 - (6 / 3)";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(11);
    }

    @DisplayName("(3 + 2) * (5 + 1) / 2 = ?")
    @Test
    void 복합한_다형식_계산4() {
        // given
        String polynomial = "(3 + 2) * (5 + 1) / 2";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(15);
    }

    @DisplayName("8 + (4 * 3) - (6 / 2) = ?")
    @Test
    void 복합한_다형식_계산5() {
        // given
        String polynomial = "8 + (4 * 3) - (6 / 2)";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(17);
    }

    @DisplayName("((7 + 3) * 4) / (2 + 1) = ?")
    @Test
    void 복합한_다형식_계산6() {
        // given
        String polynomial = "((7 + 3) * 4) / (2 + 1)";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(13);
    }

    @DisplayName("(9 * (4 - 2)) + (5 / (1 + 4)) = ?")
    @Test
    void 복합한_다형식_계산7() {
        // given
        String polynomial = "(9 * (4 - 2)) + (5 / (1 + 4))";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(19);
    }

    @DisplayName("2 - 1 * 2 = ?")
    @Test
    void 복합한_다형식_계산8() {
        // given
        String polynomial = "2 - 1 * 2";

        // when
        int result = Calc.run(polynomial);

        // then
        assertThat(result).isEqualTo(0);
    }
}