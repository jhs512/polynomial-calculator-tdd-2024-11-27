package com.ll;


import java.util.ArrayDeque;
import java.util.Deque;

public class Calc {
    private static final Deque<Integer> valueStack = new ArrayDeque<>();
    private static final Deque<Character> opStack = new ArrayDeque<>();

    public static int run(String exp) {
        /*
        * 1. 숫자는 변환해서 값 스택에 추가
        * 2. 연산자는 연산자 스택에 추가 (우선순위 비교)
        * 3. ( 괄호 무조건 연산자 스택에 추가
        * 4. ) 괄호 나오면 여는 괄호까지 연산 수행
        * 5. 끝난 후 남아있는 연산 모두 수행
        * */
        
        for(int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == ' ') continue;
            
            // 숫자 처리
            if(Character.isDigit(ch)){
                int num = 0;

                while(i < exp.length() && Character.isDigit(exp.charAt(i))){
                    num = num * 10 + (exp.charAt(i) - '0');
                    i++;
                }
                // 인덱스 조정
                i--;
                valueStack.addLast(num);
            }
            
            // 여는 괄호
            else if(ch == '('){
                opStack.addLast(ch);
            }
            
            // 닫는 괄호
            else if(ch == ')'){
                while(!opStack.isEmpty() && opStack.getLast() != '('){
                    int b = valueStack.removeLast();
                    int a = valueStack.removeLast();
                    char op = opStack.removeLast();
                    valueStack.addLast(operate(a, b, op));
                }
                // 여는 괄호 제거
                opStack.removeLast();
            }
            // 연산자
            else{
                // 이미 스택에 있는 연산이 우선순위 높을때 먼저 실행
                while(!opStack.isEmpty() &&
                        getPrecedence(opStack.getLast()) >= getPrecedence(ch)) {
                    int b = valueStack.removeLast();
                    int a = valueStack.removeLast();
                    char op = opStack.removeLast();
                    valueStack.addLast(operate(a, b, op));
                }
                opStack.addLast(ch);
            }
            
        }

        // 남은 연산 처리
        while(!opStack.isEmpty()){
            int b = valueStack.removeLast();
            int a = valueStack.removeLast();
            char op = opStack.removeLast();
            valueStack.addLast(operate(a, b, op));
        }

        return valueStack.removeLast();
    }

    private static int operate(int a, int b, char op){
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

    private static int getPrecedence(char op){
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    public static void clear() {
        valueStack.clear();
        opStack.clear();
    }
}
