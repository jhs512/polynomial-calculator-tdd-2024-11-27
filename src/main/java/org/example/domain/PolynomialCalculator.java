package org.example.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PolynomialCalculator {
    public int run(String mathExpression){
        checkValidity(mathExpression);
        mathExpression = mathExpression.replaceAll("\\s", "");

        return simpleCalc(mathExpression);
    }

    private int simpleCalc(String mathExpression){
        String firstInnerExpression = findFirstInnerExpression(mathExpression);
        if(firstInnerExpression.equals(mathExpression)){
            return calcWithOutParentheses(mathExpression);
        }
        else {
            String c = mathExpression.replace(firstInnerExpression, String.valueOf(calcWithOutParentheses(firstInnerExpression)));
            return simpleCalc(c);
        }
    }

    private void checkValidity(String mathExpression){
        long open = mathExpression.chars()
                .filter(c -> c == '(')
                .count();

        long close = mathExpression.chars()
                .filter(c -> c == ')')
                .count();
        if (open != close){
            throw new IllegalStateException();
        }
    }


    /*
        맨 처음 나오는 최초 괄호 식 추출.
     */
    private String findFirstInnerExpression(String mathExpression){
        char[] cArray = mathExpression.toCharArray();
        int open = -1;
        for(int i = 0; i < cArray.length; i++){
            if(cArray[i] == '('){
                open = i;
            }else if(cArray[i] == ')'){
                if(open == -1){
                    throw new IllegalStateException();
                }else {
                    return mathExpression.substring(open, i + 1);
                }
            }
        }
        if(open != -1){
            throw new IllegalStateException();
        }
        return mathExpression;
    }

    /*
     괄호 없는 수식 계산하기.
     */
    private int calcWithOutParentheses(String mathExpression){
        mathExpression = mathExpression.replaceAll("\\(", "").replaceAll("\\)", "");

        List<MathOperator> mathOperatorList = Arrays.stream(mathExpression.split("\\d"))
                .filter(s -> !s.isBlank())
                .map(MathOperator::fromIcon)
                .collect(Collectors.toCollection(ArrayList::new));

        List<Integer> numbers = Arrays.stream(mathExpression.split("\\D"))
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));

        int result = numbers.getFirst();

        while (!mathOperatorList.isEmpty()){
            int index = mathOperatorList.stream()
                    .filter(mathOperator -> mathOperator.equals(MathOperator.DIVIDE) || mathOperator.equals(MathOperator.MULTIPLY))
                    .findFirst()
                    .map(mathOperatorList::indexOf).orElse(0);

            int left = numbers.get(index);
            int right = numbers.get(index + 1);
            result = calc(left, right, mathOperatorList.get(index));
            numbers.set(index, result);

            numbers.remove(index + 1);
            mathOperatorList.remove(index);
        }
        return result;
    }

    private int calc(int left, int right, MathOperator mathOperator){
        return switch (mathOperator) {
            case PLUS -> left + right;
            case MINUS -> left - right;
            case MULTIPLY -> left * right;
            case DIVIDE -> left / right;
        };
    }
}
