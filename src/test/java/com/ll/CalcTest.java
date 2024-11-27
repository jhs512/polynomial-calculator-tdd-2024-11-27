package com.ll;

import com.ll.util.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

class CalcTest {

    ByteArrayOutputStream outputStream;

    @BeforeEach
    void before(){
        outputStream = TestUtil.setOutToByteArray();
    }

    @AfterEach
    void after(){
        TestUtil.clearSetOutToByteArray(outputStream);
    }

    @Test
    void 수식_입력(){
        int result = Calc.run("((3 + 5) * 5 + -10) + 10 / 5");
        Assertions.assertThat(result).isEqualTo(32);
        Assertions.assertThat(Calc.run("100 * (2 + 3) / 5 - 20")).isEqualTo(80);
        Assertions.assertThat(Calc.run("(-1)")).isEqualTo(-1);
        Assertions.assertThat(Calc.run("((15 / (7-(1+1)))*-3) - (2 + (1+1))")).isEqualTo(-13);

        String output = outputStream.toString();
        Assertions.assertThat(output).contains("입력된 수식 : " + "((3 + 5) * 5 + -10) + 10 / 5");
    }

    @Test
    void 수식_체크_올바른_경우(){
        int result = Calc.run("((3 + 5) * 5 + -10) + 10 / 5");
        String output = outputStream.toString();
        Assertions.assertThat(output).doesNotContain("잘못된 수식입니다.");
        Assertions.assertThat(output).contains("괄호 계산 : (3 + 5) * 5 + -10");
        Assertions.assertThat(output).contains("괄호 계산 : 3 + 5");
    }

    @Test
    void 수식_체크_잘못된_경우(){
        int result = Calc.run("((3 + 5))) * 5 + -10) * 10 / 5");
        String output = outputStream.toString();
        Assertions.assertThat(output).contains("잘못된 수식입니다.");
    }
}