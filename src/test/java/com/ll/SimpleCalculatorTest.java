package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
public class SimpleCalculatorTest {
    @Test
    @DisplayName("1 + 2 =3")
    public void testPlus(){
        SimpleCaculator simple = new SimpleCaculator();
        double rs = simple.plus(1,2);
        assertThat(rs).isEqualTo(3);
    }
    @Test
    @DisplayName("6 + 8 =14")
    public void testPlus2(){
        SimpleCaculator simple = new SimpleCaculator();
        double rs = simple.plus(6,8);
        assertThat(rs).isEqualTo(14);
    }
    @Test
    @DisplayName("6 - 8 =-2")
    public void testPlus3(){
        SimpleCaculator simple = new SimpleCaculator();
        double rs = simple.sub(6,8);
        assertThat(rs).isEqualTo(-2);
    }
    @Test
    @DisplayName("13 - 5 =8")
    public void testPlus4(){
        SimpleCaculator simple = new SimpleCaculator();
        double rs = simple.sub(13,5);
        assertThat(rs).isEqualTo(8);
    }
    @Test
    @DisplayName("3 * 5 =15")
    public void testPlus5(){
        SimpleCaculator simple = new SimpleCaculator();
        double rs = simple.mul(3,5);
        assertThat(rs).isEqualTo(15);
    }
    @Test
    @DisplayName("2 * 3 =6")
    public void testPlus6(){
        SimpleCaculator simple = new SimpleCaculator();
        double rs = simple.mul(2,3);
        assertThat(rs).isEqualTo(6);
    }
    @Test
    @DisplayName("3 / 5 =0.6")
    public void testPlus7(){
        SimpleCaculator simple = new SimpleCaculator();
        double rs = simple.divide(3,5);
        assertThat(rs).isEqualTo(0.6);
    }
    @Test
    @DisplayName("10 / 2 =5")
    public void testPlus8(){
        SimpleCaculator simple = new SimpleCaculator();
        double rs = simple.divide(10,2);
        assertThat(rs).isEqualTo(5);
    }
}
