import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class CalTest {

    Calc calc=new Calc();
    @Test
    void CalTest1() {
        int rs= calc.run("3+5");

        assertThat(rs).isEqualTo(8);
    }

    void CalTest2() {
        int rs= calc.run("33+55");

        assertThat(rs).isEqualTo(88);
    }

    void CalTest3() {
        int rs= calc.run("3-5");

        assertThat(rs).isEqualTo(8);
    }

    void CalTest4() {
        int rs= calc.run("3*5");

        assertThat(rs).isEqualTo(15);
    }

    void CalTest5() {
        int rs= calc.run("30/5");

        assertThat(rs).isEqualTo(6);
    }

    void CalTest6() {
        int rs= calc.run("((3 + 5) * 5 + -10) * 10 / 5");

        assertThat(rs).isEqualTo(60);
    }




}
