import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PolynomialCalculatorTest {

    @Test
    @DisplayName("계산")
    public void t1(){
        //given
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();
        Calc calc = new Calc(TestUtil.genScanner("""
                ((3 + 5) * 5 + -10) * 10 / 5
                """));

        //when
        int rs = calc.run();
        String output = outputStream.toString();

        //then
        assertThat(output)
                .contains("1번째 계산 : 3 + 5")
                .contains("2번째 계산 : 8 * 5")
                .contains("3번째 계산 : 40 - 10")
                .contains("4번째 계산 : 30 * 10")
                .contains("5번째 계산 : 300 / 5")
                .contains("결과 : 60");

        TestUtil.clearSetOutToByteArray(outputStream);
    }
}
