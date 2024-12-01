package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {
    
    // "(" 부터 ")" 까지 추출 , 추출한 식 내에서 사칙연산 우선순위 로직 통해 계산
    // 나온 값을 다시 스트링으로 변환해서 붙이며 괄호가 나오지 않을때까지 반복
    
    public static int run(String input) {
        isValidateFormat(input);
        
        // 소괄호가 있으면 제거 해서 계산해주는 작업
        while (input.contains("(") || input.contains(")")) {
            String preSplit = input.split("\\)")[0];
            int idx = preSplit.lastIndexOf("(");
            String numsInParentheses = preSplit.substring(idx + 1);

            // 여기서는 괄호도 없애주고 괄호안에 계산 값이 나와야함
            calculate(toPostfix(numsInParentheses));
            input = input.replace("("+numsInParentheses+")", String.valueOf(calculate(toPostfix(numsInParentheses))));
        }
        return calculate(toPostfix(input));
    }

    // 소괄호가 제거된 상태에서 숫자만 옴
    private static String toPostfix(String removedParentheses) {
        // 공백 제거
        removedParentheses = removedParentheses.replace(" ", "");

        // 사칙연산 우선순위가 적용되어야함 "* , /" 우선 계산 , "+ - " 나중 계산
        // 숫자일때와 연산자일때 구분이 필요
        // 1. 숫자일때는 십자리수 이상도 처리가 필요 String 으로 들어오기 때문에 ,
        // 2. 연산자일 경우 우선순위 정리가 필요,

        int currentNum = 0;
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        boolean minusFlag = false;

        for (int i=0; i<removedParentheses.length(); i++) {
            char tmp = removedParentheses.charAt(i);

            if(minusFlag) {
                currentNum = -(tmp - '0');
                minusFlag = false;
                continue;
            } else if (Character.isDigit(tmp)){
                // 두자리수 이상일때 처리, 바로전이 숫자일 경우 *10 씩 곱해주기
                // 전에 담긴 currentNum 이 음수면 + 가 아니라 - 를 해줘야 한다 ex) -15 = (-1 * 10) -5
                if (currentNum < 0) {
                    currentNum = currentNum * 10 - (tmp - '0');
                } else {
                    currentNum = currentNum * 10 + (tmp - '0');
                }
            }
            if(!Character.isDigit(tmp)) {
                // - 일때 음수처리
                if (tmp == '-' && (i == 0 || !Character.isDigit(removedParentheses.charAt(i-1)))) {
                    minusFlag = true;
                    continue;
                } else {

                    // 연산자 스택이 비어있을 때 그냥 넣기
                    if (stack.isEmpty()) {
                        stack.push(tmp);
                        // 연산자 스택에 연산자 있으면 꺼내서 우선순위 비교
                    } else {
                        // 현재 연산자가 우선순위가 더 크면 그냥 넣기
                        // 작거나 같으면 pop() 하여 결과값에 넣고 꺼내서 계속 비교
                        // - 일 경우 부호로 처리하지 말고 그냥 숫자로 취급해버리자
                        if (getPriority(stack.peek()) < getPriority(tmp)) {
                            stack.push(tmp);
                        } else {
                            while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(tmp)) {
                                // 스택에 남아있는게 없거나, 현재 연산자 우선순위가 더 크면 스택에 푸쉬
                                result.append(currentNum).append(" ");
                                result.append(stack.pop()).append(" ");
                            }
                            stack.push(tmp);
                            currentNum = 0;
                            continue;
                        }
                    }
                }
                if (currentNum != 0) {
                result.append(currentNum).append(" ");
                }
                currentNum = 0;
            }
        }
        // 마지막에 남아있는 연산자와 숫자 털어넣기
        result.append(currentNum).append(" ");
        while (!stack.isEmpty()) {

            result.append(stack.pop()).append(" ");
        }
        //  "30 2 2 * + 1 - 1 -" 이런식;
        return result.toString();
    }

    private static int calculate(String postFix) {
        
        String[] elements = postFix.split(" ");
        Stack<Integer> stack = new Stack<>();

        int result = 0;

        // "30 2 2 * + 1 - 1 -"
        for (String element : elements) {
            // 숫자일경우 스택에 담기
            if (!isOp(element)) {
                stack.push(Integer.parseInt(element));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                
                if (element.equals("*")) {
                    result = b * a;
                } else if (element.equals("/")) {
                    result = b / a;
                } else if (element.equals("+")) {
                    result = b + a;
                } else if (element.equals("-")) {
                    result = b - a;
                }

                stack.push(result);
            }
        }
        return stack.pop();
    }

    private static boolean isOp(String element) {
        return element.equals("*") || element.equals("/") || element.equals("+") || element.equals("-");
    }

    // 연산자의 우선순위를 꺼낸다
    private static int getPriority(char op) {

        if (op == '*' || op == '/') {
            return 2;
        } else if (op == '+' || op == '-') {
            return 1;
        }
        return 1;
    }

    private static boolean isValidateFormat(String input) {

        // 숫자랑 */+-() 만 허용
        if (input.matches(".*[^0-9*/+\\-()\\s].*")) {
            throw new IllegalArgumentException("숫자와 */+-(), 공백만 허용합니다.");
        }
        int count = 0;
        for (char c : input.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
        }
        if (count == 0) {
            return true;
        }
        throw new IllegalArgumentException("괄호를 제대로 입력해주세요.");
    }
}
