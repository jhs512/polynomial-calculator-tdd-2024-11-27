package baekgwa.util;

import java.util.Stack;

public class Calc {

    private static final char MINUS = '-';
    private static final char PLUS = '+';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';

    /*
    잘못된 수식 케이스
    1. 괄호가 짝을 이루지 않는 경우.
    2. 괄호를 닫는게 먼저 나오는 경우.
     */

    /*
    아이디어 정리
    1. 계산식의 우선 순위는?
      - 1순위 괄호, 2순위 곱셈,나눗셈, 3순위 덧셈뺄셈
    2. 만약 괄호 `(`를 만난다면? `)`를 만날떄까지 찾아서 문자열을 잘라서, 따로 계산을 맡기고, 결과를 replace 시켜주면?
    3. 즉, `( )`를 따로 맡기면, 괄호에 대한 연산을 신경쓸 필요가 없음.
    4. 메서드로 역할을 분리 시키자.
    5. 반복문을 통해, 괄호가 있는 경우에는, `calculateWithoutParentheses`을 호출해서 괄호와 내용을 결과값으로 바꿔치기 하자.
    6. 모든 괄호가 없어진다면? 마지막으로 `calculateWithoutParentheses`을 호출해서 결과값을 확인.
     */
    public static int run(String polynomial) {
        //공백이 들어오는 경우, 계산이 귀찮아짐
        polynomial = polynomial.replaceAll("\\s+", "");

        //step1 ~ step5
        while (polynomial.contains("(")) {
            int startIndex = -1;
            int endIndex = -1;

            //괄호 찾기
            for (int i=0; i<polynomial.length(); i++) {
                if (polynomial.charAt(i) == '(') {
                    startIndex = i;
                } else if(polynomial.charAt(i) == ')') {
                    endIndex = i;
                    break;
                }
            }

            //step N/A : 잘못된 수식 케이스1, 2 처리.
            if(startIndex == -1 || endIndex == -1 || endIndex <= startIndex) {
                throw new RuntimeException("잘못된 다항식 입니다.");
            }

            String innerExpression = polynomial.substring(startIndex + 1, endIndex); //이상 미만
            int innerResult = calculateWithoutParentheses(innerExpression);
            polynomial = polynomial.substring(0, startIndex) + innerResult + polynomial.substring(endIndex + 1);
        }

        return calculateWithoutParentheses(polynomial);
    }

    /*
    아이디어 정리
    1. 여기에는 괄호가 없는 수식이 들어옴.
    2. 즉, 곱하기 나누기, 더하기 뺴기의 우선순위만 신경써서 return 해주면 됨.
    3. stack 사용해서, +나 -가 들어올경우, 스택에 넣어두고 나중에 처리.
    4. * / 가 들어오면, 바로 처리.

    아이디어2)
     1 + 2 + 3을
     (+0) (+1) (+2) (+3) 등으로 묶어서 생각할 것.
     */
    private static int calculateWithoutParentheses(String polynomial) {
        int result = 0;
        int currentValue = 0;
        char lastOperator = '+';

        // 연산자 우선순위: '*'와 '/'를 먼저 처리
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < polynomial.length(); i++) {
            char now = polynomial.charAt(i);

            //숫자 추출
            if (Character.isDigit(now)) {
                currentValue = currentValue * 10 + convertFrom(now);
            }

            //연산자일경우
            if (i == polynomial.length() - 1 || isOperator(now)) {
                if (lastOperator == PLUS) {
                    stack.push(currentValue);
                } else if (lastOperator == MINUS) {
                    stack.push(-currentValue);
                } else if (lastOperator == MULTIPLY) {
                    stack.push(stack.pop() * currentValue);
                } else if (lastOperator == DIVIDE) {
                    stack.push(stack.pop() / currentValue);
                }

                lastOperator = now;
                currentValue = 0;
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    private static int convertFrom(char c) {
        return c - '0';
    }

    private static boolean isOperator(char c) {
        return c == DIVIDE || c == MINUS || c == MULTIPLY || c == PLUS;
    }
}
