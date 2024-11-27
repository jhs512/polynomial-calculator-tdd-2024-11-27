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

                if (operator.equals("+")) {
                    exprs.add(i - 1, String.valueOf(num1 + num2));
                } else if (operator.equals("*")) {
                    exprs.add(i - 1, String.valueOf(num1 * num2));
                } else if (operator.equals("-")) {
                    exprs.add(i - 1, String.valueOf(num1 - num2));
                } else if (operator.equals("/")) {
                    exprs.add(i - 1, String.valueOf(num1 / num2));
                }
            }
        }

        int answer = Integer.parseInt(exprs.getFirst());

        for (int i = 1; i < exprs.size() - 1; i += 2) {
            String operator = exprs.get(i);
            int operand = Integer.parseInt(exprs.get(i + 1));

            if (operator.equals("+")) {
                answer += operand;
            } else if (operator.equals("*")) {
                answer *= operand;
            } else if (operator.equals("-")) {
                answer -= operand;
            } else if (operator.equals("/")) {
                answer /= operand;
            }
        }

        return answer;
    }
}
