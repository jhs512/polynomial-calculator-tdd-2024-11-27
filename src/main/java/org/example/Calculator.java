package org.example;

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
        String[] split = expression.split("[+\\-*/]");

        if (expression.contains("+")) {
            return add(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim()));
        } else if(expression.contains("-")) {
            return subtract(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim()));
        } else if(expression.contains("*")) {
            return multiply(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim()));
        } else if (expression.contains("/")) {
            return divide(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim()));
        }
        return 0;
    }

}
