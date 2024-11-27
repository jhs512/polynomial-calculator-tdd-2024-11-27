package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 버전1 : 후위표기식 사용
public class Calculator {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int rs = Calculator.run(input);
        System.out.println(rs);

        int rs2 = Calculator2.run(input);
        System.out.println(rs2);
    }

    public static int run(String expr) {
        // 공백문자 제거
        expr = expr.replaceAll("\\s", "");

        // 중위표기식 -> 후위표기식 변환
        Stack<String> postfixStack = convertToPostfix(expr);

        // 후위표기식에 대하여 연산 진행
        int rs = calculatePostfix(postfixStack);

        return rs;
    }

    public static Stack<String> convertToPostfix(String expr) {
        // 최종 후위 표기식 저장
        Stack<String> postfixStack = new Stack<>();
        // 임시 저장 스택
        Stack<Character> opStack = new Stack<>();

        // expr 를 후위표기식으로 변환
        for(int i=0; i<expr.length(); i++) {
            char c = expr.charAt(i);

            switch(c) {
                case '-' :
                    // 연산자가 아닌 음수부호인 경우
                    if(i==0 || expr.charAt(i-1) == '+' || expr.charAt(i-1) == '-' || expr.charAt(i-1) == '*' || expr.charAt(i-1) == '/') {
                        StringBuilder sb = new StringBuilder("-");
                        i++;
                        i = addNumberToStack(postfixStack, expr, i);
                        sb.append(postfixStack.pop());
                        postfixStack.push(sb.toString());
                    }
                    else {
                        if(!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(c)) {
                            postfixStack.push(String.valueOf(opStack.pop()));
                        }
                        opStack.push(c);
                    }
                    break;
                case '+' :
                case '*' :
                case '/' :
                    if(!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(c)) {
                        postfixStack.push(String.valueOf(opStack.pop()));
                    }
                    opStack.push(c);
                    break;
                case '(' :
                    opStack.push(c);
                    break;
                case ')' :
                    while(!opStack.isEmpty() && opStack.peek() != '(') {
                        postfixStack.push(String.valueOf(opStack.pop()));
                    }
                    opStack.pop();
                    break;
                default :
                    i = addNumberToStack(postfixStack, expr, i);
            }
        }

        while(!opStack.isEmpty()) {
            postfixStack.push(String.valueOf(opStack.pop()));
        }

        return postfixStack;
    }

    public static int getPriority(char c) {
        if (c == '(' || c ==')')
            return 0;

        if (c == '+' || c =='-')
            return 1;

        if (c == '*' || c =='/')
            return 2;

        throw new IllegalArgumentException("Invalid operator : " + c);
    }

    public static boolean isNumber(char c){
        return 48 <= c && c <= 57;
    }

    public static int addNumberToStack(Stack<String> stack, String expr, int start) {

        StringBuilder sb = new StringBuilder();

        int i = start;

        while(i < expr.length() && isNumber(expr.charAt(i))) {
            sb.append(expr.charAt(i));
            i++;
        }

        i--;
        stack.push(sb.toString());
        return i;
    }

    public static int calculatePostfix(Stack<String> postfixStack) {
        Stack<String> tempStack = new Stack<>();

        int reps = 0;
        while(true) {
            if(tempStack.isEmpty() &&postfixStack.size() == 1)
                break;

            String cur = postfixStack.pop();
            if(isOperator(cur)) {
                tempStack.add(cur);
                reps = 0;
            } else if (isNumber(cur)) {
                tempStack.add(cur);
                reps++;
                if(reps == 2) {
                    int operand1 = Integer.parseInt(tempStack.pop());
                    int operand2 = Integer.parseInt(tempStack.pop());
                    String operator = tempStack.pop();
                    int rs = calculate(operand1, operand2, operator);
                    postfixStack.push(String.valueOf(rs));
                    if(!tempStack.isEmpty() && isNumber(tempStack.peek())) {
                        reps = 1;
                    } else {
                        reps = 0;
                    }
                }
            }
        }

        return Integer.parseInt(postfixStack.pop());
    }

    public static int calculate(int operand1, int operand2, String operator) {
        switch(operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if(operand2 == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                return operand1 / operand2;
        }
        throw new IllegalArgumentException("잘못된 연산자입니다.");
    }

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false; // null 또는 빈 문자열은 숫자가 아님
        }

        if (str.equals("-"))
            return false;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)!='-' && !Character.isDigit(str.charAt(i))) {
                return false; // 숫자가 아닌 문자가 포함되어 있으면 false
            }
        }

        // 음수, 양수 모두 return 가능
        return true;
    }

    public static boolean isOperator(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return true;
        }

        return false;
    }
}
