package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalcDFS {
    public static int run(String expr) {
        if (expr.contains("(")) {
            int start = expr.lastIndexOf("(");
            int end = expr.indexOf(")", start);

            String inner = expr.substring(start + 1, end);
            int result = run(inner);

            expr = expr.substring(0, start) + result + expr.substring(end + 1);
            return run(expr);
        }

        return calculator(expr);
    }

    public static int calculator(String expr) {
        List<Integer> numbers = new ArrayList<>();
        List<Character> operator = new ArrayList<>();
        StringBuilder num = new StringBuilder();

        boolean minus = false;

        for (int i = 0; i < expr.length(); i++) {
            char e = expr.charAt(i);

            if (e == ' ') {
                minus = false;
                continue;
            }
            if (Character.isDigit(e)) {
                if (minus) {
                    num.append("-");
                    operator.removeLast();
                    minus = false;
                }
                num.append(e);
            }
            else {
                if (e == '-') minus = true;
                operator.add(e);
                if (!num.isEmpty()) {
                    numbers.add(Integer.parseInt(num.toString()));
                    num = new StringBuilder();
                }
            }

            if (i == expr.length() - 1) numbers.add(Integer.parseInt(num.toString()));
        }

        for (int i = 0; i < operator.size(); i++) {
            if (operator.get(i) == '*' || operator.get(i) == '/') {
                int result = fourBasic(numbers.remove(i), numbers.remove(i), operator.remove(i));
                numbers.add(i, result);
                i--;
            }
        }

        for (int i = 0; i < operator.size(); i++) {
            int result = fourBasic(numbers.remove(i), numbers.remove(i), operator.remove(i));
            numbers.add(i, result);
            i--;
        }

        return numbers.getFirst();
    }

    private static int fourBasic(int num1, int num2, char op) {
        return switch (op) {
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            default -> 0;
        };
    }
}
