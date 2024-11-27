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

}
