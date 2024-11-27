package org.example;

import java.util.Arrays;

public class Calculator {

    private static final Calculator calculator = new Calculator();

    public static Calculator getInstance() {
        return calculator;
    }

    private Calculator() {
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public int calculate(String expression) {

        if(expression.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");     // 입력값이 없을 때 예외 던짐
        }

        expression = expression.replaceAll("\\s+", "");     // 공백 제거
        String[] split = expression.split("(?<=[-+*/])|(?=[-+*/])");    // 연산자 앞뒤로 자름

        int num1 = Integer.parseInt(split[0]);
        int num2 = Integer.parseInt(split[2]);
        String operator = split[1];

        // 연산 결과 리턴
        switch (operator) {
            case "+":
                return add(num1, num2);
            case "-":
                return subtract(num1, num2);
            case "*":
                return multiply(num1, num2);
            case "/":
                return divide(num1, num2);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }

    }

}
