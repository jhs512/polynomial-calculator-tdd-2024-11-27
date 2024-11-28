package org.example.validator;

public class ExpressionValidateUtil {
    public ExpressionValidateUtil(){
        throw new IllegalStateException("It's Util Class");
    }

    public static void checkValidity(String mathExpression){
        long open = mathExpression.chars()
                .filter(c -> c == '(')
                .count();

        long close = mathExpression.chars()
                .filter(c -> c == ')')
                .count();
        if (open != close){
            throw new IllegalStateException("Expression is wrong");
        }
    }
}
