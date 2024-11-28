package org.example;

import java.util.Optional;

public class Calc {

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

    public static Integer run2(String str) {
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
}
