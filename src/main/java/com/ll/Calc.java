package com.ll;

import com.ll.exception.LogicException;

import java.util.Stack;

public class Calc {
    private Calc(){}

    public static int run(String formula){

        System.out.println("입력된 수식 : " + formula);

        if(!formulaCheck(formula)){
            System.out.println("잘못된 수식입니다.");
            return Integer.MAX_VALUE;
        }

        return calculate(formula);  // 꼼수로 처리
    }

    /**
     * 수식이 올바른지 체크할 리스트들을 모아서 처리한다.
     * @param formula 수식
     * @return 판별 여부
     */
    private static boolean formulaCheck(String formula){
        return (parenthesesCheck(formula));
        // TODO : 더 체크할 예외를 여기에 추가한다.
    }

    /**
     * 수식의 괄호가 올바르게 존재하는지 판별한다.
     * @param formula 수식
     * @return 판별 여부
     */
    private static boolean parenthesesCheck(String formula){
        int count = 0;
        for(int i = 0; i < formula.length(); i++){
            switch (formula.charAt(i)){
                case '(' -> count++;
                case ')' -> {
                    if(count-- == 0) return false;
                }
            }
        }
        return count == 0;
    }

    // ex. "((3 + 5) * 5 + -10) + 10 / 5"
    /**
     * 수식을 계산한다.
     * @param formula 수식
     * @return 결과 값
     */
    private static int calculate(String formula){
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for(int i = 0; i < formula.length(); i++){

            char ch = formula.charAt(i);
            if(ch == ' ') continue;

            // 괄호를 만나면 짝을 찾아서 해당 짝을 찾아서 재귀 -> 괄호 이후로 인덱스를 옮긴다.
            if(ch == '('){
                int matchIndex = findMatch(formula, i);
                String subFormula = formula.substring(i + 1, matchIndex);
                int num = calculate(subFormula);
                i = matchIndex;

                processHighPrecedenceOperations(numbers, operators, num);
                continue;
            }

            // 숫자 or 연산자 체크 및 동작
            if(Character.isDigit(ch) || isNegative(formula, ch, i)){
                int[] num = {0};
                i = findNumber(formula, i, num);
                processHighPrecedenceOperations(numbers, operators, num[0]);
            }else if(ch == '-' && formula.charAt(i + 1) == '('){
                int matchIndex = findMatch(formula, i + 1);
                String subFormula = formula.substring(i + 2, matchIndex);
                int num = calculate(subFormula);
                i = matchIndex;

                processHighPrecedenceOperations(numbers, operators, -num);
            } else{
                operators.push(ch);
            }
        }

        if(numbers.isEmpty()){
            throw new LogicException("Number 스택이 비어있어 올바른 동작이 진행되지 않습니다.");
        }else if(numbers.size() <= operators.size()){
            throw new LogicException("Number 스택이 비어있어 올바른 동작이 진행되지 않습니다.");
        }

        return processRemainingAdditionSubtraction(numbers, operators);
    }

    /**
     * 음수인지 판별하는 함수
     * @param formula 수식
     * @param ch 시작 문자
     * @param i 수식 인덱스
     * @return
     */
    private static boolean isNegative(String formula, char ch, int i){
        return (ch == '-' && (i != formula.length() - 1 && formula.charAt(i + 1) != ' ' && formula.charAt(i + 1) != '('));
    }

    /**
     * 높은 우선순위에 있는 곱셈, 나눗셈을 진행한다. 곱셈이나 나눗셈이 아니라면 우선순위에 의해 생략한다.
     * @param numbers 수식 값
     * @param operators 수식 연산자
     * @param num 연산을 위한 대상 값.
     */
    private static void processHighPrecedenceOperations(Stack<Integer> numbers, Stack<Character> operators, int num){
        if(!numbers.isEmpty() && !operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
            char op = operators.pop();
            int prev = numbers.pop();
            num = switch (op) {
                case '*' -> prev * num;
                case '/' -> prev / num;
                default -> num;
            };
        }
        numbers.push(num);
    }

    /**
     * 곱셈, 나눗셈을 마친 수식의 나머지 덧셈, 뺄셈을 진행한다.
     * @param numbers 수식 값
     * @param operators 수식 연산자
     * @return 결과 값
     */
    private static int processRemainingAdditionSubtraction(Stack<Integer> numbers, Stack<Character> operators){
        int result = numbers.removeFirst();
        while(!operators.isEmpty()){
            char op = operators.removeFirst();
            int nextNum = numbers.removeFirst();
            result = switch (op) {
                case '+' -> result + nextNum;
                case '-' -> result - nextNum;
                default -> result;
            };
        }
        return result;
    }

    /**
     * 숫자를 찾아서 반환한다. 참조자 이용을 위해 int[] 값으로 num 을 받는다.
     * @param formula : 수식
     * @param curIndex : 현재 인덱스
     * @param num : 찾은 숫자 값
     * @return 숫자의 마지막 인덱스
     */
    private static int findNumber(String formula, int curIndex, int[] num){
        int idx = curIndex;
        int sign = 1;
        if(formula.charAt(curIndex) == '-'){
            idx++;
            sign = -1;
        }
        while (idx < formula.length() && Character.isDigit(formula.charAt(idx))) {
            num[0] = num[0] * 10 + (formula.charAt(idx) - '0');
            idx++;
        }
        num[0] *= sign;
        return idx - 1;
    }

    /**
     * 현재 인덱스부터 시작해서 괄호의 짝을 찾는 메서드
     * @param formula : 수식
     * @param curIndex : 현재 인덱스
     * @return 짝이 위치한 인덱스 (찾지 못하는 경우, FIND_FAIL 반환)
     */
    private static final int FIND_FAIL = java.lang.Integer.MAX_VALUE;
    private static int findMatch(String formula, int curIndex){
        int count = 0;
        for(int i = curIndex; i < formula.length(); i++){
            switch (formula.charAt(i)){
                case '(' -> count++;
                case ')' -> count--;
            }
            if(count == 0) return i;
        }
        return FIND_FAIL;
    }
}
