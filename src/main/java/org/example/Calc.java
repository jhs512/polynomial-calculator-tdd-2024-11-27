package org.example;

public class Calc {
    public static int run(String expr) {
        String[] temp = expr.split(" \\+ ");
        int num1 = Integer.parseInt(temp[0]);
        int num2 = Integer.parseInt(temp[1]);

        return num1 + num2;
    }
}
