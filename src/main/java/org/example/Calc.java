package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calc {

    int run(String expression) {
        expression = expression.replace(" ", "");
        expression = expression.replace("+-", "-");

        char[] charArr = expression.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (char c : charArr) {
            if (c == ')') {
                while(!stack.isEmpty()) {
                    char ch = stack.pop();
                    if (ch == '(') {
                        String subExpression = sb.toString();
                        sb.setLength(0); // StringBuilder 초기화
                        int subResult = simpleCalculate(subExpression);
                        for(char sc : (subResult+"").toCharArray()) {
                            stack.push(sc);
                        }
                        break;
                    }
                    sb.insert(0, ch);
                }
            }
            else
                stack.push(c);
        }
        while(!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return simpleCalculate(sb.toString());
    }


    int simpleCalculate(String expression) {
        int answer = 0;
        int mul = 1;
        int div = 1;
        String regex = "[+\\-*/]\\s*\\d+";

        expression = "+" + expression;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        Deque<String> opStack = new ArrayDeque<>();
        Deque<String> numStack = new ArrayDeque<>();

        matcher.results()
                .map(MatchResult::group)
                .forEach(
                        str-> {
                            opStack.push(str.substring(0,1));
                            numStack.push(str.substring(1).trim());
                        }
                );
        while(!numStack.isEmpty()) {
            int num = Integer.parseInt(numStack.pop());
            String op = opStack.pop();

            if(op.equals("+")) {
                answer += num*mul/div;
                mul=1;
                div=1;
            }
            else if(op.equals("-")) {
                answer -= num*mul/div;
                mul=1;
                div=1;
            }
            else if(op.equals("*")) {
                mul *= num;
            }
            else if(op.equals("/")) {
                div *= num;
            }
        }

        return answer;
    }
}
