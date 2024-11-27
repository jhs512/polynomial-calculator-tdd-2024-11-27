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
}
