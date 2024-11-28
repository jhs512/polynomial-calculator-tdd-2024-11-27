package org.example.parser;

public class MathExpressionParser {
    public String findFirstInnerExpression(String mathExpression){
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
}
