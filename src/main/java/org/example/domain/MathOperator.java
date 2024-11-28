package org.example.domain;

public enum MathOperator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    MathOperator(String icon){
        this.icon = icon;
    }

    private final String icon;

    public static MathOperator fromIcon(String icon){
        for (MathOperator mathOperator : MathOperator.values()){
            if (mathOperator.icon.equals(icon)){
                return mathOperator;
            }
        }
        throw new IllegalArgumentException();
    }

    public static boolean isNull(MathOperator mathOperator){
        return mathOperator == null;
    }
}
