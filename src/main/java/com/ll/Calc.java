package com.ll;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {


    public static int run(String expression) {
        if(parenthesesContain(expression)){
            return parentheses(0, expression);
        }else {
            return calculateByFormula(0, expression);
        }
    }

    public static boolean parenthesesContain(String expression){
        return expression.contains("(");
    }

    public static int parentheses(int result, String expression){

        String regex1 =  "\\(([^()]*?)\\)";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(expression);

        if(matcher1.find()) {
            result = calculateByFormula(result, matcher1.group().substring(1, matcher1.group().length() - 1));
            expression = expression.substring(0, matcher1.start()) + result + expression.substring(matcher1.end(), expression.length());
            result = parentheses(result, expression);
        }else if(expression.length()<=3){
            result = Integer.parseInt(expression);
        }else{
            result = calculateByFormula(result, expression);
        }

        return result;

    }



    public static int calculateByFormula(int result, String expression) {

        String regex1 = "(\\*\\s)|(/\\s)";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(expression);

        String regex2 = "(\\+\\s)|(-\\s)";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(expression);


        //괄호연산 구현해야함
        if (matcher1.find()) {
            //연산자 인덱스 구하기
            int op_idx = findFirstOperationIndex(expression, '*', '/');

            // 기호 앞의 숫자 추출
            int leftStartIndex = findLeftNumberStartIndex(expression, op_idx);
            int leftNumber = Integer.parseInt(expression.substring(leftStartIndex, op_idx).trim());

            // 기호 뒤의 숫자 추출
            int rightEndIndex = findRightNumberEndIndex(expression, op_idx);
            int rightNumber = Integer.parseInt(expression.substring(op_idx + 1, rightEndIndex).trim());

            //계산 및 계산식 재작성
            result = calculate(expression.charAt(op_idx), leftNumber, rightNumber);
            expression = expression.substring(0, leftStartIndex) + result + expression.substring(rightEndIndex);
            result = calculateByFormula(result, expression);

        } else if (matcher2.find()) {
            //연산자 인덱스 구하기
            int op_idx = findFirstOperationIndex(expression, '+', '-');

            // 기호 앞의 숫자 추출
            int leftStartIndex = findLeftNumberStartIndex(expression, op_idx);
            int leftNumber = Integer.parseInt(expression.substring(leftStartIndex, op_idx).trim());

            // 기호 뒤의 숫자 추출
            int rightEndIndex = findRightNumberEndIndex(expression, op_idx);
            int rightNumber = Integer.parseInt(expression.substring(op_idx + 1, rightEndIndex).trim());

            //계산 및 계산식 재작성
            result = calculate(expression.charAt(op_idx), leftNumber, rightNumber);
            expression = expression.substring(0, leftStartIndex) + result + expression.substring(rightEndIndex);
            result = calculateByFormula(result, expression);
        }

        return result;
    }

    public static int findFirstOperationIndex(String str, char a, char b) {

        int indexA = str.indexOf(a);
        int indexB = str.indexOf(b);
        //3 + -3
        if(str.charAt(indexB+1) != ' '){
            indexB = str.indexOf(b,indexB+1);
        }


        if (indexA != -1 && indexB != -1) {
            return Math.min(indexA, indexB);
        } else if (indexA != -1) {
            return indexA;
        } else {
            return indexB;
        }
    }

    public static int findLeftNumberStartIndex(String expr, int op_idx) {
        int i = op_idx - 2;
        while (i >= 0 && Character.isDigit(expr.charAt(i)) || i >= 0 && expr.charAt(i) == '-') {
            i--;
        }
        return i + 1; // 숫자 시작 인덱스
    }

    // 기호 오른쪽 숫자의 끝 인덱스를 찾는 메서드
    public static int findRightNumberEndIndex(String expr, int op_idx) {
        int i = op_idx + 2;

        while (i >= 0 && Character.isDigit(expr.charAt(i)) || i >= 0 && expr.charAt(i) == '-')  {
            i++;
            if(expr.length() == i){
                return i;
            }
        }
        return i; // 숫자 끝 인덱스
    }

    public static int splitExpression(String expression){

        for(int i =0; i<expression.length(); i++){
            if(expression.charAt(i)=='('){

            }else {

            }
        }
        return 1;
    }

    public static int calculate(char op, int num1, int num2) {

        return switch (op) {
            case '+' -> plus(num1, num2);
            case '-' -> minus(num1, num2);
            case '*' -> multiply(num1, num2);
            case '/' -> divide(num1, num2);
            default -> 0;
        };
    }

    public static int plus(int num1, int num2){
        return  num1+num2;
    }

    public static int minus(int num1, int num2) {
        return num1-num2;
    }

    public static int multiply(int num1, int num2) {
        return  num1*num2;
    }

    public static int divide(int num1, int num2) {
        return num1/num2;
    }
}
