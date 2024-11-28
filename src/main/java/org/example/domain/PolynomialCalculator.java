package org.example.domain;

import org.example.parser.MathExpressionParser;
import org.example.validator.ExpressionValidateUtil;

import java.util.ArrayList;
import java.util.List;

public class PolynomialCalculator {
    private final MathExpressionParser parser = new MathExpressionParser();

    public int run(String mathExpression){
        ExpressionValidateUtil.checkValidity(mathExpression);
        mathExpression = mathExpression.replaceAll("\\s", "");

        return calc(mathExpression);
    }

    private int calc(String mathExpression){
        String firstInnerExpression = parser.findFirstInnerExpression(mathExpression);
        if(firstInnerExpression.equals(mathExpression)){
            return calcWithOutParentheses(mathExpression);
        }
        else {
            String firstCalculatedExpression = mathExpression.replace(firstInnerExpression, String.valueOf(calcWithOutParentheses(firstInnerExpression)));
            return calc(firstCalculatedExpression);
        }
    }

    /*
     괄호 없는 수식 계산하기.
     */
    private int calcWithOutParentheses(String mathExpression){
        mathExpression = mathExpression.replaceAll("\\(", "").replaceAll("\\)", "");
        StringBuilder stringBuilder = new StringBuilder();

        List<MathOperator> mathOperatorList = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        char[] chars = mathExpression.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(Character.isDigit(chars[i])){
                stringBuilder.append(Character.getNumericValue(chars[i]));
            }else if(chars[i] == '-' && (i == 0 || !Character.isDigit(chars[i -1]))){
                stringBuilder.append(chars[i]);
            }else {
                numbers.add(Integer.parseInt(stringBuilder.toString()));
                stringBuilder.setLength(0);
                mathOperatorList.add(MathOperator.fromIcon(String.valueOf(chars[i])));
            }
        }

        numbers.add(Integer.parseInt(stringBuilder.toString()));

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
