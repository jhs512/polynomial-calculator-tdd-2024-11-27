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
        if (opIndexPM(str) == -1) {
            return Integer.parseInt(str);
        }

        int operatorIndex = opIndexPM(str);
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
        if (opIndexPM(str) == -1 && opIndexMD(str) == -1) return Integer.parseInt(str);
        int result = 0;

//        int operatorIndex = operatorIndex(str);
        int opIndexMD = opIndexMD(str);
        char mdOperator = 0;
        if (opIndexMD != -1) mdOperator = str.charAt(opIndexMD); // * /  만 고르는거

        int opIndexPM = opIndexPM(str);
        char pmOperator = 0;
        if (opIndexPM != -1) pmOperator = str.charAt(opIndexPM); // + - 만 고르는거

        int index = 0;
        if (opIndexPM != -1) {
            index = opIndexPM;
        } else {
            index = opIndexMD;
        }

        String left = str.substring(0, index).trim();
        String right = str.substring(index + 1).trim();


        int leftNum = run3(left);
        int rightNum = run3(right);

        if (pmOperator != 0) { //   +나 -가 있다면 양쪽으로 나누기 ==   * 나 /만 있는 연산으로 남기기 위해
            result += calcMain(leftNum, rightNum, pmOperator);
        } else {
            result += calcMain(leftNum, rightNum, mdOperator);
        }

        return Optional.of(result).get();
    }

    public static int run4(String str) { // str = (10 + 20)
        if (opIndexPM(str) == -1 && opIndexMD(str) == -1) return Integer.parseInt(str);
        int result = 0;

//        int operatorIndex = operatorIndex(str);
        int opIndexMD = opIndexMD(str);
        char mdOperator = 0;
        if (opIndexMD != -1) mdOperator = str.charAt(opIndexMD); // * /  만 고르는거

        int opIndexPM = opIndexPM(str);
        char pmOperator = 0;
        if (opIndexPM != -1) pmOperator = str.charAt(opIndexPM); // + - 만 고르는거

        int index = 0;
        if (opIndexPM != -1) {
            index = opIndexPM;
        } else {
            index = opIndexMD;
        }

        String left = str.substring(0, index).trim();
        String right = str.substring(index + 1).trim();
        int leftNum = run3(left);
        int rightNum = run3(right);

        if (pmOperator != 0) { //   +나 -가 있다면 양쪽으로 나누기 ==   * 나 /만 있는 연산으로 남기기 위해
            result += calcMain(leftNum, rightNum, pmOperator);
        } else {
            result += calcMain(leftNum, rightNum, mdOperator);
        }

        return Optional.of(result).get();
    }

    private static int calcMain(int leftNum, int rightNum, char operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = leftNum + rightNum;
                break;
            case '-':
                result = leftNum - rightNum;
                break;
            case '*':
                result = leftNum * rightNum;
                break;
            case '/':
                result = leftNum / rightNum;
                break;
        }
        return result;
    }

    private static boolean isParenthesisExist(String str) { // parenthesis 괄호라는 뜻
        boolean result = false;
        for (char c : str.toCharArray()) {
            if (c == '(' || c == ')') {
                result = true;
                break;
            }
        }
        return result;
    }

    private static int opIndexMD(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ("*/".indexOf(str.charAt(i)) >= 0) {
                return i;
            }
        }
        return -1;
    }

    private static int opIndexPM(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ("+-".indexOf(str.charAt(i)) >= 0) {
                return i;
            }
        }
        return -1;
    }

}
