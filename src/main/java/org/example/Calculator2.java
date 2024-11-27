package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 버전 2 : 재귀 사용
public class Calculator2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rs = run(br.readLine());
        System.out.println(rs);
    }

    public static int run(String expr) {
        expr = expr.replaceAll("\\s", "");
        int rs = calculate(expr);
        return rs;
    }

    public static int calculate(String expr) {
        Stack<Integer> numbers = new Stack<>();

        expr = "+" + expr;

        for(int i=0; i<expr.length(); i++) {
            char c = expr.charAt(i);

            if(isOperator(c)) {
                int num = 0;
                int start = i+1;
                boolean minus_flag = false;

                // 음의 정수 처리
                if(expr.charAt(i+1) == '-') {
                    minus_flag = true;
                    start++;
                }

                // 괄호 처리 (재귀)
                if(expr.charAt(i+1) == '(') {
                    int end = findCloseIndex(i+1, expr);
                    num = calculate(expr.substring(i+2, end));
                    i = end;
                }

                for(int j=start; j<expr.length(); j++, i++) {
                    char tmp = expr.charAt(j);
                    if(Character.isDigit(tmp)) {
                        num = num * 10 + tmp - '0';
                    } else {
                        break;
                    }
                }

                if(minus_flag) {
                    num *= -1;
                }

                switch(c){
                    case '+':
                        numbers.push(num);
                        break;
                    case '-':
                        numbers.push(-num);
                        break;
                    case '*':
                        numbers.push(numbers.pop() * num);
                        break;
                    case '/':
                        numbers.push(numbers.pop() / num);

                }
            }
        }

        int answer = 0;
        while(!numbers.isEmpty()) {
            answer += numbers.pop();
        }

        return answer;
    }

    public static boolean isOperator(char c) {
        if (c == '+' | c == '-' | c == '*' | c == '/') {
            return true;
        }

        return false;
    }

    public static int findCloseIndex(int start, String expr) {
        int cnt = 0;
        for (int i = start; i < expr.length(); i++) {
            if(expr.charAt(i) == '(')
                cnt++;
            if(expr.charAt(i) == ')')
                cnt--;

            if(cnt == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("괄호의 개수가 맞지 않는 다항식입니다.");
    }
}
