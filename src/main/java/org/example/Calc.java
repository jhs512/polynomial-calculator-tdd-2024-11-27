package org.example;

import java.util.Stack;

public class Calc {
    public static int run(String expr){
        expr = expr.replaceAll("\\s", ""); // 공백 제거
        Stack<Integer> numbers = new Stack<>();
        int num = 0;
        char sign = '+';

        for(int i=0; i<expr.length(); i++){
            char c = expr.charAt(i);

            if(Character.isDigit(c)){ // 여러 자리 숫자 처리
                num = num * 10 + (c-'0');
            }

            if(c=='('){
                int end = findIndex(expr, i); // 괄호 끝나는 index
                num = run(expr.substring(i+1, end)); // 괄호 안 수식 계산
                i = end;
            }

            /**
             * 12 + 3 같은 경우 +3도 계산을 해줘야 하기 때문에
             * i가 expr의 마지막 index일 때도 연산을 해줘야함
             */

            if (!Character.isDigit(c) || i == expr.length() - 1) {
                switch (sign) {
                    case '+' -> numbers.push(num);
                    case '-' -> numbers.push(-num);
                    case '*' -> numbers.push(numbers.pop() * num);
                    case '/' -> numbers.push(numbers.pop() / num);
                }
                num = 0;
                sign = c;
            }
        }

        int result = 0;
        while (!numbers.isEmpty()) {
            result += numbers.pop();
        }

        return result;
    }

    private static int findIndex(String expr, int index) {
        int count = 0;

        for(int i=index; i<expr.length(); i++) {
            if (expr.charAt(i) == '(') count++;
            else if (expr.charAt(i) == ')') count--;
            if (count == 0) return i; // 괄호 끝나는 index 반환
        }
        throw new IllegalArgumentException("괄호 개수가 맞지 않습니다.");
    }
}
