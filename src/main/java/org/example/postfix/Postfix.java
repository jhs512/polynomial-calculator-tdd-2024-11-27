package org.example.postfix;

import java.util.Stack;
import org.example.calculator.Calculator;

public class Postfix {

    private static final Postfix postfix = new Postfix();

    private Postfix(){}

    public static Postfix getInstance(){
        return postfix;
    }
    Calculator calculator = Calculator.getInstance();

    /**
     * 중위 표기법 -> 후위 표기법
     * 1. 연산식을 앞에서 부터 읽으면서 피연산자면 StringBuilder 에 추가한다.
     * 2. '('를 만나면 stack 에 push 한다.
     * 3. 연산자를 만났을 때 stack 의 top 에 있는 연산자 보다 우선순위가 높으면 스택에 push 한다.
     * 4. top 에 있는 연산자의 우선순위와 같거나 작다면 우선순위가 더 낮은 연산자가 나올 때 까지 pop 하고
     *      push 한다.
     * 5. ')'를 만나면 '('가 나올때 까지 pop 한다. 괄호는 StringBuilder에 붙이지 않음.
     * 6. 연산식을 끝까지 돌았다면, 스택에 남은 연산자를 pop 한다.
     */
    public String infixToPostfix(String expression) {
        expression = expression.replaceAll("\\s", "");  //공백 제거
        String[] split = expression.split("(?<=[-+*/()])|(?=[-+*/()])");    //-+*/() 앞뒤로 자름
        StringBuilder sb = new StringBuilder();
        Stack<String> op = new Stack<>();

        boolean preOperator = false;
        for (String s : split) {
            if(isNumber(s)){
                sb.append(Integer.parseInt(s)).append(",");
                preOperator = false;
            } else if(isOperator(s)) {
                if (preOperator && s.equals("-")) {
                    sb.append("0,");
                    op.push(s);
                } else {
                    while (!op.isEmpty() && priority(s) <= priority(op.peek())) {
                        sb.append(op.pop()).append(",");
                    }
                    op.push(s);
                }
                preOperator = true;
            } else if (s.equals("(")) {
                op.push(s);
                preOperator = false;
            } else if (s.equals(")")) {
                while(!op.peek().equals("(")) {
                    sb.append(op.pop()).append(",");
                }
                op.pop();
                preOperator = false;
            }
        }

        while(!op.isEmpty()) {
            sb.append(op.pop()).append(",");
        }

        return sb.toString();
    }

    /**
     * postfix 계산 - Stack 사용
     * 1. 피연산자를 만나면 stack에 push 한다.
     * 2. 연산자를 만나면 2개의 피연산자를 pop 하여 연산을 수행하고 결과 값을 다시 push 한다.
     * 3. 수식이 끝나면 마지막으로 pop 하여 최종 결과 값을 리턴한다.
     * 문제점: 두자리 이상의 숫자들을 구분할 수가 없음..
     * -> 해결 방법 : char로 한글자씩 받지 않고 String으로 통째로 받고 ,를 구분자로 사용해서 처리
     */
    public int calculatePostfix(String expression) {
        String[] split = expression.split(",");
        Stack<Integer> stack = new Stack<>();

        for (String s : split) {
            if (isNumber(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                if (stack.size() == 1 && s.equals("-")) {
                    stack.push(stack.pop() * -1);
                } else {
                    int result = calculator.calculate(s.charAt(0), stack.pop(), stack.pop());
                    stack.push(result);
                }

            }
        }

        return stack.pop();
    }


    private int idx;
    public int calculatePostfixRecursive(String expression) {
        String[] split = expression.split(",");
        idx = split.length - 1;
        return recurPostfix(split);
    }

    /**
     * postfix 계산 - 재귀 방식
     * 1. 후위 표기법으로 변환한 연산식을 앞에서 부터 처리한다.
     * 2. 매개 변수로 후위 표기식 배열을 받는다.
     * 3. 연산식은 뒤에서 부터 처리한다.
     * 4. 숫자가 나오면 리턴
     * 5. 연산자가 나오면 재귀함수로 숫자를 받아서 계산
     */

    private int recurPostfix(String[] expression) {
        // 마지막 '-' 연산 처리를 위해 0 리턴
        if(idx < 0) {
            return 0;
        }

        String target = expression[idx--];

        // 숫자인 경우 그대로 리턴
        if (isNumber(target)) {
            return Integer.parseInt(target);
        }

        // 연산자인 경우 재귀로 리턴 받은 숫자로 계산함
        int num2 = recurPostfix(expression);
        int num1 = recurPostfix(expression);

        // 연산결과 리턴
        return calculator.calculate(target.charAt(0), num2, num1);
    }

    private int priority(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "(" -> 0;
            default -> -1;
        };
    }

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private boolean isOperator(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }

}
