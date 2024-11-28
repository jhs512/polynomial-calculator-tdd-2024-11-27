package org.example;

import java.util.Stack;

public class ExpressionCalculator {
    public static int calculate(String expression) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        // 중위 표기법 -> 후위 표기법 변환
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                numbers.push(Character.getNumericValue(c));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.pop(); // '(' 제거
            } else {
                while (!operators.isEmpty() && getPrecedence(c) <= getPrecedence(operators.peek())) {
                    numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.push(c);
            }
        }

        // 남은 연산 처리
        while (!operators.isEmpty()) {
            numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()));
        }

        return numbers.pop();
    }

    private static int getPrecedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    private static int calculate(int b, int a, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;

            default: return 0;
        }
    }

    public static void main(String[] args) {
        String expression = "((5+3)*-4)";
        int result = calculate(expression);
        System.out.println(result); // -32 출력
    }
}
