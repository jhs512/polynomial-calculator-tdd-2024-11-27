package org.example;

import java.util.Optional;

public class Calc {

    // except 28 22
    // 3 * 1 + (1 - (4 * 1 - (1 - 1)))
    public static int run(String str) {
        int leftIndex = 0; // ( 인덱스
        int rightIndex = 0; // ) 인덱스
        String leftString = "";
        String rightString = "";
        str = str.replaceAll(" - ", " + -");

        if ((str.contains("(") && str.contains(")")) && !(opIndexPM(str) == -1 && opIndexMD(str) == -1)) { // 괄호 ( ) + 연산식 있으면
            String firstCalc = ""; // 괄호 계산 식  (결과 아님)
            String secondCalc = "";

            leftIndex = str.indexOf("(");
            rightIndex = str.indexOf(")");

            firstCalc = str.substring(leftIndex, rightIndex+1); // 괄호 식 , + 1은 ( 괄호가 안붙어짐.
            secondCalc = firstCalc.replaceAll("[()]", "");
            String afterCalc = String.valueOf(run(secondCalc)); // 괄호 결과를 문자로 .
            str = str.replace(firstCalc, afterCalc);
            return run(str);
        }
        // 연산식이 없고, 괄호 ( 또는 )가 있을때 ex. str = 40)
        else if((opIndexPM(str) == -1 && opIndexMD(str) == -1) && (str.contains("(") || str.contains(")"))) return Integer.parseInt(str.replaceAll("[()]", ""));
        // 40) + 20 처럼 괄호가 둘 중 하나고 연산식이 있을때
        else if(!(opIndexPM(str) == -1 || opIndexMD(str) == -1) && (str.contains("(") || str.contains(")"))) return run(str.replaceAll("[()]", ""));

        else if (opIndexPM(str) == -1 && opIndexMD(str) == -1) return Integer.parseInt(str); // 연산이 아무것도 없으면 끝.
        // 그냥 정수만 있으면 끝내기
        else if (isNumberic(str)) return Integer.parseInt(str); // 음의 정수만 있어도 끝내기

        int opIndexMD = opIndexMD(str);
        int opIndexPM = opIndexPM(str);
        char mdOperator = 0;
        char pmOperator = 0;

        if (opIndexMD != -1) mdOperator = str.charAt(opIndexMD); // * /  만 고르는거
        if (opIndexPM != -1) pmOperator = str.charAt(opIndexPM); // + - 만 고르는거
        if (opIndexPM != -1) {
            leftString = str.substring(0, opIndexPM).trim();
            rightString = str.substring(opIndexPM + 1).trim();
        } else {
            leftString = str.substring(0, opIndexMD).trim();
            rightString = str.substring(opIndexMD + 1).trim();
        }
        int leftNum = run(leftString);
        int rightNum = run(rightString);

        int result = 0;
        if (pmOperator != 0) { //   +나 -가 있다면 양쪽으로 나누기 ==   * 나 /만 있는 연산으로 남기기 위해
            result += calcMain(leftNum, rightNum, pmOperator);
        } else {
            result += calcMain(leftNum, rightNum, mdOperator);
        }
        return Optional.of(result).get();

    }

    public static boolean isNumberic(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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
            if ("+-".indexOf(str.charAt(i)) >= 0 && !isNumberic(String.valueOf(str.charAt(i + 1)))) {
                return i;
            }
        }
        return -1;
    }

}
