package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calc {
    public static int run(String expr) {
        String calcBracketResult = calcPolynomialInBracket(expr);

        return Integer.parseInt(calcPolynomial(calcBracketResult));
    }

    public static String calcPolynomialInBracket(String expr) {
        String[] exprs = expr.replace("(", "( ")
                .replace(")", " )")
                .split("\\s");
        List<String> temp = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < exprs.length; i++) {
            if (exprs[i].equals(")")) {
                while (!stack.peek().equals("(")) {
                    temp.add(stack.pop());
                }
                stack.pop();

                String result = calcPolynomial(String.join(" ", temp.reversed()));
                stack.add(result);
                temp.clear();
            } else {
                stack.add(exprs[i]);
            }
        }

        return String.join(" ", stack);
    }

    private static String calcPolynomial(String expr) {
        List<String> exprs = new ArrayList<>(Arrays.asList(expr.split("\\s")));

        for (int i = 1; i < exprs.size(); i++) {
            if (exprs.get(i).equals("*") || exprs.get(i).equals("/")) {
                int num1 = Integer.parseInt(exprs.remove(i - 1));
                String operator = exprs.remove(i - 1);
                int num2 = Integer.parseInt(exprs.remove(i - 1));

                exprs.add(i - 1, String.valueOf(calc(num1, num2, operator)));
            }
        }

        int answer = Integer.parseInt(exprs.getFirst());

        for (int i = 1; i < exprs.size() - 1; i += 2) {
            String operator = exprs.get(i);
            int operand = Integer.parseInt(exprs.get(i + 1));

            answer = calc(answer, operand, operator);
        }

        return String.valueOf(answer);
    }

    private static int calc(int num1, int num2, String operator) {
        if (operator.equals("+")) {
            return num1 + num2;
        } else if (operator.equals("-")) {
            return num1 - num2;
        } else if (operator.equals("*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }
}
