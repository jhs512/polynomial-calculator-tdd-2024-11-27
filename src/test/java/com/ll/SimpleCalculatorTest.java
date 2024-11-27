package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SimpleCalculatorTest {
    @Test
    @DisplayName("1 + 2 =3")
    public void testPlus(){
        SimpleCaculator simple = new SimpleCaculator();
        int rs = simple.plus(1,2);
        assertEquals(3,rs);
    }
    @Test
    @DisplayName("6 + 8 =14")
    public void testPlus2(){
        SimpleCaculator simple = new SimpleCaculator();
        int rs = simple.plus(6,8);
        assertEquals(14,rs);
    }
}
