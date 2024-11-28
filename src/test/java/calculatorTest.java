import calculator.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class calculatorTest {


    @Test
    public void simpleAdd() {
        int addition = Calculator.calculate("1 + 3");
        assertEquals(4, addition);
    }

    @Test
    public void longAdd() {
        int res = Calculator.calculate("1 + 1 + 1");
        assertEquals(3, res);
    }

    @Test
    public void subAndAdd() {
        int res = Calculator.calculate("1 + 1 - 1 + 5");
        assertEquals(6, res);
    }

    @Test
    @DisplayName("1 + 1 * 5 * 2 + 1")
    public void shortPoly() {
        int res = Calculator.calculate("1 + 1 * 5 * 2 + 1");
        assertEquals(12, res);
    }

    @Test
    @DisplayName("1 + 3 * 3 - 2 + 5 - 6 / 2")
    public void longPolyAdd() {
        int res = Calculator.calculate("1 + 3 * 3 - 2 + 5 - 6 / 2");
        assertEquals(10, res);
    }
}
