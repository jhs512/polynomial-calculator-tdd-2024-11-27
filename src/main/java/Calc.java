import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calc {



    public int run(String input) {
       String postfix=infixToPostfix(input);
       int result=calPostfix(postfix);
       return result;
    }

    String infixToPostfix(String expression) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<expression.length();i++) {
            char ch=expression.charAt(i);

            if(Character.isDigit(ch)) {
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    postfix.append(expression.charAt(i)); // 숫자를 결과에 추가
                    i++;
                }
                postfix.append(' '); // 숫자 구분을 위해 공백 추가
                i--; //

            }
            else if(ch=='(') {
                stack.push(ch);
            }
            else if(ch==')') {
                while(!stack.isEmpty() && stack.peek() !='(') {
                    postfix.append(stack.pop());

                }
                stack.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while(!stack.isEmpty() && precedence(stack.peek()) >=precedence(ch)) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }



        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '(':
                return 0;
            default:
                return -1;
        }
    }

    int calPostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);

            // 숫자일 경우 스택에 푸시
            if (Character.isDigit(ch)) {
                StringBuilder sb=new StringBuilder();
                while (i < postfix.length() && Character.isDigit(postfix.charAt(i))) {
                    sb.append(postfix.charAt(i)); // 숫자를 결과에 추가
                    i++;
                }
                stack.push(Integer.parseInt(sb.toString()));

            } else {
                // 두 피연산자를 스택에서 팝
                int b = stack.pop();
                int a = stack.pop();

                // 연산 수행 후 결과를 스택에 푸시
                switch (ch) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.pop(); // 최종 결과
    }

}


