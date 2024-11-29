package org.example;

import java.util.Optional;

public class Calc {

    // 1 ~ 14번
    public static Integer run(String str) {
        String[] tokens = str.split(" ");
        int[] num = new int[tokens.length];
        String[] operators = new String[tokens.length];

        int numIndex = 2;
        int operatorIndex = 1;

        for (int i = 0; i < tokens.length; i++) {
            if (i % 2 == 0) num[i] = Integer.parseInt(tokens[i]);
            else operators[i] = tokens[i];
        }

        int result = num[0];
        while (true) {
            if (operatorIndex >= tokens.length || numIndex >= tokens.length) break;
            switch (operators[operatorIndex]) {
                case "+":
                    result += num[numIndex];
                    break;
                case "-":
                    result -= num[numIndex];
                    break;
                case "*":
                    result *= num[numIndex];
                    break;
                case "/":
                    result /= num[numIndex];
                    break;
            }
            numIndex += 2;
            operatorIndex += 2;
        }
        return Optional.of(result).get();
    }

    //("10 + 5 * 2 == 20")
    // 15 ~ 16
    public static Integer run2(String str) { // str = 10 + 5 * 2
        if (operatorIndex(str) == -1) {
            return Integer.parseInt(str);
        }

        int operatorIndex = operatorIndex(str);
        char operator = str.charAt(operatorIndex);
        String leftOperand = str.substring(0, operatorIndex).trim();
        String rightOperand = str.substring(operatorIndex + 1).trim();
        int result = 0;

        int leftCalc = run2(leftOperand);
        int rightCalc = run2(rightOperand);

        switch (operator) {
            case '+':
                result = leftCalc + rightCalc;
                break;
            case '-':
                result = leftCalc - rightCalc;
                break;
            case '*':
                result = leftCalc * rightCalc;
                break;
            case '/':
                result = leftCalc / rightCalc;
                break;
        }
        return Optional.of(result).get();
    }

    public static Integer run3(String str) { // str = 10 * 20 + 10 + 5 * 2 == 220
        if (operatorIndex(str) == -1) {
            return Integer.parseInt(str);
        }

        int operatorIndex = operatorIndex(str);

        char operator = str.charAt(operatorIndex);
        String leftOperand = str.substring(0, operatorIndex).trim();
        String rightOperand = str.substring(operatorIndex + 1).trim();
        int result = 0;

        int leftCalc = run3(leftOperand);
        int rightCalc = run3(rightOperand);

        switch (operator) {
            case '+':
                result = leftCalc + rightCalc;
                break;
            case '-':
                result = leftCalc - rightCalc;
                break;
            case '*':
                result = leftCalc * rightCalc;
                break;
            case '/':
                result = leftCalc / rightCalc;
                break;
        }
        return Optional.of(result).get();
    }

    private static int operatorIndex(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ("+-*/".indexOf(str.charAt(i)) >= 0) {
                return i;
            }
        }
        return -1;
    }

}
