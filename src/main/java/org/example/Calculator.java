package org.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {

    public static int run(String expr) {
        // 공백문자 제거
        expr = expr.replaceAll("\\s", "");

        /* 덧셈 테스트
        int rs = 0;
        String[] numbers = expr.split("\\+");
        for(int i=0; i<numbers.length; i++){
            rs += Integer.parseInt(numbers[i]);
        }
         */

        /* 곱셈 테스트
        int rs = 1;
        String[] numbers = expr.split("\\*");
        for(int i=0; i<numbers.length; i++){
            rs *= Integer.parseInt(numbers[i]);
        }
         */

        // 덧셈 + 곱셈 테스트
        Deque<String> deque = new ArrayDeque<>();

        for(int i=0; i<expr.length(); i++) {
            char c = expr.charAt(i);
            if(isNumber(c)) {
                i = addNumberToDeque(deque, expr, i);
            } else if (c == '*') {

                int operand1 = Integer.parseInt(deque.remove());
                i++;
                i = addNumberToDeque(deque, expr, i);
                int operand2 = Integer.parseInt(deque.remove());
                deque.addFirst(String.valueOf(operand1*operand2));

            } else if (c == '+') {
                deque.addFirst(String.valueOf(c));
            }
        }

        int rs = 0;
        while(!deque.isEmpty()) {
            int operand2 = Integer.parseInt(deque.remove());
            String operator = deque.remove();
            int operand1 = Integer.parseInt(deque.remove());
            deque.addFirst(String.valueOf(operand1 + operand2));
            if (deque.size() == 1) {
                break;
            }
        }
        rs = Integer.parseInt(deque.remove());

        return rs;
    }


    public static boolean isNumber(char c){
        return 48 <= c && c <= 57;
    }

    public static int addNumberToDeque(Deque<String> deque, String expr, int start) {

        StringBuilder sb = new StringBuilder();

        int i = start;

        while(i < expr.length() && isNumber(expr.charAt(i))) {
            sb.append(expr.charAt(i));
            i++;
        }

        i--;
        deque.addFirst(sb.toString());
        System.out.println(sb);
        return i;
    }

}
