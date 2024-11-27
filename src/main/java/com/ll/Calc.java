package com.ll;


import java.util.ArrayDeque;
import java.util.Deque;

public class Calc {
    private static final Deque<Integer> valueStack = new ArrayDeque<>();
    private static final Deque<Character> opStack = new ArrayDeque<>();

    public static int run(String exp) {
        /*
         * 0. 문자열 전체에서 공백 제거(음수 판별 시 오류 제거)
         * 1. 숫자는 변환해서 값 스택에 추가
         * 2. 연산자는 연산자 스택에 추가 (우선순위 비교)
         * 3. ( 괄호 무조건 연산자 스택에 추가
         * 4. ) 괄호 나오면 여는 괄호까지 연산 수행
         * 5. 끝난 후 남아있는 연산 모두 수행
         * */

        // 문자열 전체에서 공백 제거
        exp = exp.replaceAll("\\s+", "");

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch) || ch == '-' && isMinusNumber(exp, i))
                i = pushNumber(exp, ch, i);// 숫자 처리 (음수는 별도 처리 확인)
            else if (ch == '(') opStack.addLast(ch);// 여는 괄호
            else if (ch == ')') flushOps(); // 닫는 괄호
            else calculatePrecedence(ch);// 연산자
        }

        // 남은 연산 처리
        while (!opStack.isEmpty()) {
            valueStack.addLast(binaryOperate());
        }

        return valueStack.removeLast();
    }

    private static void flushOps() {
        while (!opStack.isEmpty() && opStack.getLast() != '(') {
            valueStack.addLast(binaryOperate());
        }
        // 여는 괄호 제거
        opStack.removeLast();
    }

    private static void calculatePrecedence(char ch) {
        // 이미 스택에 있는 연산이 우선순위 높을 때, 먼저 실행
        while (!opStack.isEmpty() &&
                getPrecedence(opStack.getLast()) >= getPrecedence(ch)) {
            valueStack.addLast(binaryOperate());
        }
        opStack.addLast(ch);
    }

    private static int pushNumber(String exp, char ch, int i) {
        int num = 0;
        boolean isNegative = false;

        // 음수 처리
        if (ch == '-') {
            isNegative = true;
            i++;
        }

        while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
            num = num * 10 + (exp.charAt(i) - '0');
            i++;
        }
        // 인덱스 조정
        i--;

        if (isNegative) valueStack.addLast(-num);
        else valueStack.addLast(num);
        return i;
    }

    private static int binaryOperate() {
        int b = valueStack.removeLast();
        int a = valueStack.removeLast();
        char op = opStack.removeLast();

        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("0으로 나눌 수 없음.");
                yield a / b;
            }
            default -> 0;
        };
    }

    private static int getPrecedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    private static boolean isMinusNumber(String exp, int i) {
        return (i == 0 || exp.charAt(i - 1) == '(' || "+-*/".indexOf(exp.charAt(i - 1)) != -1);
    }

    public static void clear() {
        valueStack.clear();
        opStack.clear();
    }
}
