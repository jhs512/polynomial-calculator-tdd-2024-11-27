import com.ll.SimpleCalulator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleCalulatorTest {

    @Test
    @DisplayName("1 + 2 = 3")
    public void t1(){
        SimpleCalulator simpleCalulator = new SimpleCalulator();

        // 1 + 1
        int rs = simpleCalulator.plus(1, 2);

        // 결과 확인
        assertEquals(3, rs); // 기대값은 3
    }


    @Test
    @DisplayName("4 - 2 = 2")
    public void t2(){
        SimpleCalulator simpleCalulator = new SimpleCalulator();

        // 4 - 2
        int rs = simpleCalulator.minus(4, 2);

        // 결과 확인
        assertEquals(2, rs); // 기대값은 2
    }

    @Test
    @DisplayName("2 * 2 = 4")
    public void t3(){
        SimpleCalulator simpleCalulator = new SimpleCalulator();

        // 2 * 2
        int rs = simpleCalulator.multiply(2, 2);

        // 결과 확인
        assertEquals(4, rs); // 기대값은 4
    }


    @Test
    @DisplayName("4 / 2 = 2")
    public void t4(){
        SimpleCalulator simpleCalulator = new SimpleCalulator();

        // 4 / 2
        int rs = simpleCalulator.divide(4, 2);

        // 결과 확인
        assertEquals(2, rs); // 기대값은 2
    }


    @Test
    @DisplayName("((3 + 5) * 5 + -10) * 10 / 5 = 60")
    public void t5(){
        SimpleCalulator simpleCalulator = new SimpleCalulator();
        // 먼저 식을 분해 한다

        // (3 + 5)
        int step1 = simpleCalulator.plus(3, 5);

        // (3 + 5) * 5
        int step2 = simpleCalulator.multiply(step1, 5);

        // (3 + 5) * 5 + -10
        int step3 = simpleCalulator.plus(step2, -10);

        // ((3 + 5) * 5 + -10) * 10
        int step4 = simpleCalulator.multiply(step3, 10);

        // ((3 + 5) * 5 + -10) * 10 / 5
        int result = simpleCalulator.divide(step4, 5);

        // 결과 확인
        assertEquals(60, result); // 기대값은 60
    }

}
