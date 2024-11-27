package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calc {
    public static int run(String expr) {
        ArrayList<String> exprs = new ArrayList<>(Arrays.asList(expr.split("\\s")));

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

        return answer;
    }

    private static int calc(int num1, int num2, String operator) {
        if (operator.equals("+")) {
            return num1 + num2;
        } else if (operator.equals("-")) {
            return num1 - num2;
        } else if (operator.equals("*")) {
            return num1 * num2;
        } else if (operator.equals("/")) {
            return num1 / num2;
        }

        return 0;
    }
}
