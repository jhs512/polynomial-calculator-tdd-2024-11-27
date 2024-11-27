import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class CalTest {

    Calc calc=new Calc();
    @Test
    void CalTest1() {
        int rs= calc.calDigit("3+5");

        assertThat(rs).isEqualTo(8);
    }

    @Test
    void CalTest2() {
        int rs= calc.calDigit("3+5+2");

        assertThat(rs).isEqualTo(10);
    }

    @Test
    void CalTest3() {
        int rs= calc.calDigit("3-5");

        assertThat(rs).isEqualTo(-2);
    }

    @Test
    void CalTest4() {
        int rs= calc.calDigit("-3+5");

        assertThat(rs).isEqualTo(2);
    }

    @Test
    void CalTest5() {
        int rs= calc.calDigit("3*5");

        assertThat(rs).isEqualTo(15);
    }

    @Test
    void CalTest6() {
        int rs= calc.calDigit("10/5");

        assertThat(rs).isEqualTo(2);
    }

    @Test
    void CalTest7() {
        int rs= calc.calDigit("10/-5");

        assertThat(rs).isEqualTo(-2);
    }
}
