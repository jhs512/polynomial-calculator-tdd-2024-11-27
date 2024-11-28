package com.ll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class SimpleCaculatorTest2 {
    @Mock
    CalculatorScanner mockedCal= mock(CalculatorScanner.class);;

    @Test
    @DisplayName("1 + 1 + 1 + 1 = 4")
    public void test1() {
        when(mockedCal.run("1 + 1 + 1 + 1")).thenReturn(4.0);
        final double result = mockedCal.run("1 + 1 + 1 + 1");
        assertThat(result).isEqualTo(4.0);
    }
    @Test
    @DisplayName("1 - 1 - 1 - 1 = -4")
    public void test2() {
        when(mockedCal.run("1 - 1 - 1 - 1")).thenReturn(-4.0);
        final double result = mockedCal.run("1 - 1 - 1 - 1");
        assertThat(result).isEqualTo(-4);
    }
    @Test
    @DisplayName("3 * 2 * 3 * 2 = 36")
    public void test3() {
        when(mockedCal.run("3 * 2 * 3 * 2 ")).thenReturn(36.0);
        final double result = mockedCal.run("3 * 2 * 3 * 2 ");
        assertThat(result).isEqualTo(36.0);
    }
    @Test
    @DisplayName("10 / 2 / 5 = 5")
    public void test4() {
        when(mockedCal.run("10 / 2 / 5")).thenReturn(1.0);
        final double result = mockedCal.run("10 / 2 / 5");
        assertThat(result).isEqualTo(1.0);
    }
    @Test
    @DisplayName("( 1 + 1 ) * 3 = 6")
    public void test5() {
        when(mockedCal.run("( 1 + 1 ) * 3")).thenReturn(6.0);
        final double result = mockedCal.run("( 1 + 1 ) * 3");
        assertThat(result).isEqualTo(6.0);
    }
    @Test
    @DisplayName("2 + ( ( 1 + 1 ) * 3 ) + 2 = 10")
    public void test6() {
        when(mockedCal.run("2 + ( ( 1 + 1 ) * 3 ) + 2")).thenReturn(10.0);
        final double result = mockedCal.run("2 + ( ( 1 + 1 ) * 3 ) + 2");
        assertThat(result).isEqualTo(10.0);
    }
}
