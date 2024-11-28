package com.ll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static com.ll.TestUtil.genScanner;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SimpleCalculatorTest {

    @Test
    @DisplayName("1 + 2 =3")
    public void test(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.plus(1,2);
        assertThat(rs).isEqualTo(3);
    }
    @Test
    @DisplayName("6 + 8 =14")
    public void test2(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.plus(6,8);
        assertThat(rs).isEqualTo(14);
    }
    @Test
    @DisplayName("6 - 8 =-2")
    public void test3(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.sub(6,8);
        assertThat(rs).isEqualTo(-2);
    }
    @Test
    @DisplayName("13 - 5 =8")
    public void test4(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.sub(13,5);
        assertThat(rs).isEqualTo(8);
    }
    @Test
    @DisplayName("3 * 5 =15")
    public void test5(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.mul(3,5);
        assertThat(rs).isEqualTo(15);
    }
    @Test
    @DisplayName("2 * 3 =6")
    public void test6(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.mul(2,3);
        assertThat(rs).isEqualTo(6);
    }
    @Test
    @DisplayName("3 / 5 =0.6")
    public void test7(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.divide(3,5);
        assertThat(rs).isEqualTo(0.6);
    }
    @Test
    @DisplayName("10 / 2 =5")
    public void test8(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.divide(10,2);
        assertThat(rs).isEqualTo(5);
    }
    @Test
    @DisplayName("1 + 1 + 1 + 1 =5")
    public void test9(){
        CalculatorService simple = new CalculatorService();
        double rs = simple.divide(10,2);
        assertThat(rs).isEqualTo(5);
    }
}
