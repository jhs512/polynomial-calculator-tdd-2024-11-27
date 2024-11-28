import com.ll.controller.CalculatorContoller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorContollerTest {

    @Test
    @DisplayName("1 + 1 = 2")
    void t1(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("1 + 1");

        assertTrue(result == 2);
    }

    @Test
    @DisplayName("1 + 2 = 3")
    void t2(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("1 + 2");

        assertTrue(result == 3);
    }

    @Test
    @DisplayName("((1 + 2) * -8) = -24")
    void t3(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("((1 + 2) * -8)");

        assertTrue(result == -24);
    }

    @Test
    @DisplayName("((5 + 1) * 8) - 40 = 8")
    void t4(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("((5 + 1) * 8) - 40");

        assertTrue(result == 8);
    }

    @Test
    @DisplayName("((8 + 1) * (2 + 10)) = 108")
    void t5(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("((8 + 1) * (2 + 10))");

        assertTrue(result == 108);
    }

    @Test
    @DisplayName("1 - 2 = -1")
    void t6(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("1 - 2");

        assertTrue(result == -1);
    }

    @Test
    @DisplayName("1 + (1 - 2) = 0")
    void t7(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("1 + (1 - 2)");

        assertTrue(result == 0);
    }

    @Test
    @DisplayName("1 + ( 1 - 2 ) = 0")
    void t8(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("1 + ( 1 - 2 )");

        assertTrue(result == 0);
    }

    @Test
    @DisplayName("1+(1-2) = 0")
    void t9(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("1+(1-2)");

        assertTrue(result == 0);
    }

    @Test
    @DisplayName("((5 + 1) * 8) - 40 = 8")
    void t10(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("((5+1)*8)-40");

        assertTrue(result == 8);
    }

    @Test
    @DisplayName("((8+1)*(2+10)) = 108")
    void t11(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("((8+1)*(2+10))");

        assertTrue(result == 108);
    }

    @Test
    @DisplayName("((8+1)*(2+10) = 108")
    void t12(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("((8+1)*(2+10))");

        assertTrue(result == 108);
    }

    @Test
    @DisplayName("-(10 + 5) = -15")
    void t13(){
        CalculatorContoller calculatorContoller = new CalculatorContoller();
        int result = calculatorContoller.run("-(10 + 5)");

        assertTrue(result == -15);
    }
}