import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTest {
    @Test
    @DisplayName("계산기 생성")
    void t1(){
        Calculator calculator = new Calculator();
        int result = calculator.run("1 + 2");
        assertTrue(result == 3);
    }

    @Test
    @DisplayName("계산 순서대로")
    void t2(){
        Calculator calculator = new Calculator();
        int result = calculator.run("1 + 2 - -3");
        assertTrue(result == 6);
    }

    @Test
    @DisplayName("우선순위가 있는 계산")
    void t3(){
        Calculator calculator = new Calculator();
        int result = calculator.run("1 + 2 * -3");
        assertTrue(result == -5);
    }

    @Test
    @DisplayName("우선순위가 있는 계산2")
    void t4(){
        Calculator calculator = new Calculator();
        int result = calculator.run("1 + 2 * -3 + 7");
        assertTrue(result == 2);
    }

    @Test
    @DisplayName("괄호처리")
    void t5(){
        Calculator calculator = new Calculator();
        int result = calculator.run("(1 + 3) * 4");
        assertTrue(result == 16);
    }

    @Test
    @DisplayName("복잡한 계산식 처리")
    void t6(){
        Calculator calculator = new Calculator();
        int result = calculator.run("((3 + 5) * 5 + -10) * 10 / 5");
        assertTrue(result == 60);
    }

    @Test
    @DisplayName("공백이 2연타로 들어오는 경우")
    void t7(){
        Calculator calculator = new Calculator();
        int result = calculator.run("5 / 2 *   4");
        assertTrue(result == 8);
    }

}
