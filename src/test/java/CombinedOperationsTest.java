import static org.assertj.core.api.Assertions.*;

import org.example.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CombinedOperationsTest {

    Calculator calculator = Calculator.getInstance();

    @Test
    @DisplayName("다항식 연산 테스트")
    void combinedOperationTest() {
        //given
        String expression1 = "2 * (5 + 2)";

        //when
        int result = calculator.combinedOperations(expression1);

        //then
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("다항식 연산 테스트2")
    void combinedOperationTest2() {
        //given
        String expression1 = "(5 + 3) * 3";

        //when
        int result = calculator.combinedOperations(expression1);

        //then
        assertThat(result).isEqualTo(24);
    }

    @Test
    @DisplayName("다항식 연산 테스트3")
    void combinedOperationTest3() {
        //given
        String expression1 = "5 + 3 * 3";

        //when
        int result = calculator.combinedOperations(expression1);

        //then
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("다항식 연산 테스트4")
    void combinedOperationTest4() {
        //given
        String expression1 = "5 * 3 + 3";

        //when
        int result = calculator.combinedOperations(expression1);

        //then
        assertThat(result).isEqualTo(18);
    }

    @Test
    @DisplayName("다항식 연산 테스트5")
    void combinedOperationTest5() {
        //given
        String expression1 = "((3 + 5) * 5 + 10) * 10 / 5";

        //when
        int result = calculator.combinedOperations(expression1);

        //then
        assertThat(result).isEqualTo(100);
    }

    @Test
    @DisplayName("다항식 연산 테스트6")
    void combinedOperationTest6() {
        //given
        String expression1 = "((3 + 5) * 5 + -10) * 10 / 5";

        //when
        int result = calculator.combinedOperations(expression1);

        //then
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("다항식 연산 테스트7")
    void combinedOperationTest7() {
        //given
        String expression1 = "((5 + -2) * 5 + -10) * 10 / 5 - 5";

        //when
        int result = calculator.combinedOperations(expression1);

        //then
        assertThat(result).isEqualTo(5);
    }

}
