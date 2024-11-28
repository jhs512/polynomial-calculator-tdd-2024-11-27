package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public static int calculate(String expression) {
        //"(5 * (3 + 5) * 5 + -10) * 10 / 5"

        return Integer.parseInt(dfs(expression, new int[]{0}));
    }

    //괄호처리를 위한 재귀함수
    public static String dfs(String expression, int[] i) {
        StringBuilder sb = new StringBuilder();

        while (i[0] < expression.length() && expression.charAt(i[0]) != ')') {

            if (expression.charAt(i[0]) == '(') {
                ++i[0];
                sb.append(dfs(expression, i));
            } else {
                sb.append(expression.charAt(i[0]));
            }

            ++i[0];
        }

        return String.valueOf(calculateWithoutParen(sb.toString()));
    }

    //괄호없는식 계산
    private static int calculateWithoutParen(String expression) {
        String[] arr = expression.split(" ");

        List<String> list = new ArrayList<>();
        list.add(arr[0]);

        // 곱셈 나눗셈 먼저 처리하기
        for (int i=1; i<arr.length; i+=2) {
            if ("*/".contains(arr[i])) {
                int op1 = Integer.parseInt(list.removeLast());
                int op2 = Integer.parseInt(arr[i+1]);

                if (arr[i].equals("*")) {
                    list.add(String.valueOf(op1*op2));
                } else if (arr[i].equals("/")) {
                    list.add(String.valueOf(op1/op2));
                }
            } else {
                list.add(arr[i]);
                list.add(arr[i+1]);
            }
        }

        if (list.isEmpty()) return 0;
        if (list.size() == 1) return Integer.parseInt(list.getFirst());

        //덧셈 뺄셈 처리
        int res = Integer.parseInt(list.getFirst());
        for (int i = 1; i < list.size();  i+=2) {
            String operator = list.get(i);
            int operand = Integer.parseInt(list.get(i+1));

            if (operator.equals("+")) {
                res += operand;
            } else if (operator.equals("-")) {
                res -= operand;
            }
        }

        return res;
    }



}
