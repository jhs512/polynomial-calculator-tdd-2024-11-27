package org.example;

import java.util.Stack;

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

    public int calculate(char op, int num1, int num2) {

        // 연산 결과 리턴
        return switch (op) {
            case '+' -> add(num2, num1);
            case '-' -> subtract(num2, num1);
            case '*' -> multiply(num2, num1);
            case '/' -> divide(num2, num1);
            default -> 0;
        };
    }

    public int combinedOperations(String expression) {
        if(expression.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");     // 입력값이 없을 때 예외 던짐
        }
        expression = expression.replaceAll("\\s+", "");

        Stack<Integer> number = new Stack<>();
        Stack<Character> operator = new Stack<>();

        boolean isNegative = false;
        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if(Character.isDigit(c)) {
                StringBuilder temp = new StringBuilder();
                if(isNegative) {
                    temp.append("-");
                    isNegative = false;
                }

                while(i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    temp.append(expression.charAt(i));
                    i++;
                }
                i--;
                number.push(Integer.parseInt(temp.toString()));
            } else if(c == '(') {
                operator.push(c);
            } else if (c == ')') {
                int result = calculate(operator.pop(), number.pop(), number.pop());
                number.push(result);
                operator.pop();
            } else {
                if(!operator.isEmpty() && (c == '+' || c == '-') && (operator.peek() == '*' || operator.peek() == '/')) {
                    int result = calculate(operator.pop(), number.pop(), number.pop());
                    number.push(result);
                }
                if(!operator.isEmpty() && (c == '*' || c == '/') && (operator.peek() == '*' || operator.peek() == '/')) {
                    int result = calculate(operator.pop(), number.pop(), number.pop());
                    number.push(result);
                }

                if(c == '-' && isOperator(expression.charAt(i - 1))) {
                    isNegative = true;
                } else {
                    operator.push(c);
                }

            }
        }

        while(!operator.isEmpty()) {
            int result = calculate(operator.pop(), number.pop(), number.pop());
            number.push(result);
        }

        return number.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

}
