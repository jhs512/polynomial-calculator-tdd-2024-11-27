package org.example;

public class Calc {
    public static int run(String expr) {
        String[] exprs = expr.split("\\s");
        int answer = Integer.parseInt(exprs[0]);

        for (int i = 1; i < exprs.length - 1; i += 2) {
            String operator = exprs[i];
            int operand = Integer.parseInt(exprs[i + 1]);

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
