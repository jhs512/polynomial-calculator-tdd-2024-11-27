package org.example;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class AppTest {
    App app = new App();

    @DisplayName("덧셈")
    @ParameterizedTest
    @CsvSource({
        "5+3, 8",
        "5+4+7, 16",
        "5 + 3, 8",
    })
    public void addTest(String s, int i) {
       int result = app.add(s);
       assertThat(result).isEqualTo(i);
    }

    @DisplayName("뺄셈")
    @ParameterizedTest
    @CsvSource({
        "5-3, 2",
        "5-4-7, -6",
        "0-0, 0",
        "1-1-2, -2",
        "5 - 3, 2"
    })
    public void minusTest(String s, int i) {
        int result = app.minus(s);
        assertThat(result).isEqualTo(i);
    }

    @DisplayName("곱셈")
    @ParameterizedTest
    @CsvSource({
        "5*3, 15",
        "2*2*3, 12",
        "4*4*4, 64",
        "5 * 3, 15"
    })
    public void multiplyTest(String s, int i) {
        int result = app.multiply(s);
        assertThat(result).isEqualTo(i);
    }

    @DisplayName("나눗셈")
    @ParameterizedTest
    @CsvSource({
        "5/3, 1",
        "5/4/7, 0",
        "20/5, 4",
        "20 / 5, 4"
    })
    public void divideTest(String s, int i) {
        int result = app.divide(s);
        assertThat(result).isEqualTo(i);
    }
}

class AppExceptionTest {
    App app = new App();

    @DisplayName("덧셈 예외 테스트")
    @ParameterizedTest
    @CsvSource({
        "안녕+s, 8",
        "\", 16",
        "5, 5",
        "5-3, 0"
    })

    public void addTest(String s, int i) {
        App app = new App();
        assertThatThrownBy(()->{
            int result = app.add(s);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("뺄셈 예외 테스트")
    @ParameterizedTest
    @CsvSource({
        "안녕+s, 8",
        "\", 16",
        "5, 5",
        "5--3, 0"
    })

    public void minusTest(String s, int i) {
        App app = new App();
        assertThatThrownBy(()->{
            int result = app.add(s);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("곱셈 예외 테스트")
    @ParameterizedTest
    @CsvSource({
        "안녕+s, 8",
        "\", 16",
        "5, 5",
        "5*&3, 0"
    })

    public void multiplyTest(String s, int i) {
        App app = new App();
        assertThatThrownBy(()->{
            int result = app.add(s);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나눗셈 예외 테스트")
    @ParameterizedTest
    @CsvSource({
        "안녕+s, 8",
        "\", 16",
        "5, 5",
        "5-3, 0"
    })

    public void divideTest(String s, int i) {
        App app = new App();
        assertThatThrownBy(()->{
            int result = app.add(s);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}