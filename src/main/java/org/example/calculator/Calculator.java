package org.example.calculator;

public class Calculator {

    private static final Calculator calculator = new Calculator();

    public static Calculator getInstance() {
        return calculator;
    }

    private Calculator() {
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public int calculate(char op, int num1, int num2) {

        // 연산 결과 리턴
        return switch (op) {
            case '+' -> add(num2, num1);
            case '-' -> subtract(num2, num1);
            case '*' -> multiply(num2, num1);
            case '/' -> divide(num2, num1);
            default -> 0;
        };
    }

    /* 사용 X
    public int combinedOperations(String expression) {
        if(expression.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");     // 입력값이 없을 때 예외 던짐
        }
        expression = expression.replaceAll("\\s+", "");

        Stack<Integer> number = new Stack<>();
        Stack<Character> operator = new Stack<>();

        boolean isNegative = false;     // 음수 판별을 위한 플래그
        boolean isNegative2 = false;    // 괄호 바로 앞에 음수 판별
        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // 숫자 판별
            if(Character.isDigit(c)) {
                StringBuilder temp = new StringBuilder();
                if(isNegative) {
                    temp.append("-");
                    isNegative = false;
                }
                // 숫자가 두자리 이상일 경우를 위해 반복문으로 연속된 숫자 처리
                while(i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    temp.append(expression.charAt(i));
                    i++;
                }
                i--;
                number.push(Integer.parseInt(temp.toString()));
            } else if(c == '(') {
                operator.push(c);
            } else if (c == ')') {
                // 괄호 안에 연산을 모두 처리하기 위해 '('를 만날 때 까지 반복문 돌림 ex) (1 + 5 * 10)
                while(operator.peek() != '(') {
                    number.push(calculate(operator.pop(), number.pop(), number.pop()));
                }
                operator.pop();

                // 괄호 바로 앞에 - 연산자가 붙을 경우 괄호 안에 연산이 끝나고 음수로 바꿔서 다시 Stack에 push
                if(isNegative2) {
                    number.push(number.pop() * -1);
                    isNegative2 = false;
                }
            } else if(isOperator(c)){
                // 괄호가 안 붙은 음수 판별 조건
                if(c == '-' && expression.charAt (i + 1) != '(' && (i == 0 || isOperator(expression.charAt(i - 1)) || expression.charAt(i - 1) == '(')) {
                    isNegative = true;
                }
                // 괄호가 붙은 음수 판별
                else if(c == '-' && expression.charAt(i + 1) == '(' && (i == 0 || isOperator(expression.charAt(i - 1)))) {
                    isNegative2 = true;
                }
                // 해당 안될 겅우 연산자 우선순위에 따라 연산 진행
                else {
                    while(!operator.isEmpty() && priority(operator.peek()) >= priority(c)) {
                        number.push(calculate(operator.pop(), number.pop(), number.pop()));
                    }
                    operator.push(c);
                }

            }
        }

        // 남아 있는 연산 처리
        while(!operator.isEmpty()) {
            number.push(calculate(operator.pop(), number.pop(), number.pop()));
        }

        return number.pop();
    }

    private int priority(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

     */
}
