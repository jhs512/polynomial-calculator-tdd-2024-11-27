package org.example;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

public class AppTest {
    App app = new App();

    @DisplayName("덧셈")
    @ParameterizedTest
    @CsvSource({
        "5+3, 8",
        "5+4+7, 16",
        "5 + 3, 8",
        "m5+3, -2"
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
        "5 - 3, 2",
        "m5-3, -8"
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
        "5 * 3, 15",
        "m3*5, -15"
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

    @DisplayName("토큰화 테스트")
    @ParameterizedTest
    @CsvSource(value = {
//        "5+3: [{attribute=value, value=5}, {attribute=operator, priority=1, value=+}, {attribute=value, value=3}]",
//        "5+3-2:[{attribute=value, value=5}, {attribute=operator, priority=1, value=+}, {attribute=value, value=3}, {attribute=operator, priority=1, value=-}, {attribute=value, value=2}]",
//        "5+3+-2:[{attribute=value, value=5}, {attribute=operator, priority=1, value=+}, {attribute=value, value=3}, {attribute=operator, priority=1, value=+}, {attribute=value, value=-2}]",
        "5*(3+1):dasd"
    }, delimiter=':')
    public void parseTest(String s, String i) {
        Tokenizer tokenizer = new Tokenizer(s);
        List<Map<String, String>> tokens = tokenizer.tokenize();
        assertThat(tokens.toString()).isEqualTo(i);
    }

    @DisplayName("기능 테스트")
    @ParameterizedTest
    @CsvSource({
        "((5+3)*-4), 4"
    })
    public void appTest(String s, int i) {
        int result = ExpressionCalculator.calculate(s);
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