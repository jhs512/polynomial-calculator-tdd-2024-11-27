package org.example;

public class New_Calc {
    public static int run(String expr) {
        expr = expr.replaceAll("\\s", ""); // 공백 제거
        return evaluate(expr, new int[]{0});
    }

    private static int evaluate(String expr, int[] index) {
        int num = 0;
        int result = 0;
        char sign = '+';
        boolean flag = false;

        while (index[0] < expr.length()) { // 글자 하나당 index값 증가
            char c = expr.charAt(index[0]);

            if (c == '-') { // 음수 처리
                if (index[0] == 0 || (!Character.isDigit(expr.charAt(index[0] - 1)) && expr.charAt(index[0] - 1) != ')')) {
                    flag = true;
                    index[0]++;
                    continue;
                }
            }

            if (Character.isDigit(c)) { // 숫자 처리
                num = num * 10 + (c - '0');
            }

            if (c == '(') { // 괄호 처리
                index[0]++;
                num = evaluate(expr, index); // 재귀 호출로 괄호 안의 값을 계산
            }

            if (!Character.isDigit(c) && c != '(' || index[0] == expr.length() - 1) {
                if (flag) {
                    num = -num;
                    flag = false;
                }
                switch (sign) {
                    case '+' -> result += num;
                    case '-' -> result -= num;
                    case '*' -> result *= num;
                    case '/' -> result /= num;
                }
                sign = c;
                num = 0;
            }

            if (c == ')') { // 괄호 종료
                index[0]++;
                break;
            }

            index[0]++;
        }

        return result;
    }
}
