package org.example;

public class Calc {
    public static int run(String expr) {
        String[] temp = expr.split("\\s");
        int num1 = Integer.parseInt(temp[0]);
        String operator = temp[1];
        int num2 = Integer.parseInt(temp[2]);

        if (operator.equals("+")) {
            return num1 + num2;
        } else if (operator.equals("*")) {
            return num1 * num2;
        }
        return 0;
    }
}
