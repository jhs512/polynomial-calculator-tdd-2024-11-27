package com.ll;

import java.util.List;

public class Calculator {
    public static double plus(String n1, String n2){
        return Double.parseDouble(n1) + Double.parseDouble(n2);

    }
    public static double sub(String n1, String n2){
        return Double.parseDouble(n1) - Double.parseDouble(n2);

    }
    public static double mul(String n1, String n2){
        return Double.parseDouble(n1) * Double.parseDouble(n2);

    }
    public static double divide(String n1, String n2){
        return Double.parseDouble(n1) / Double.parseDouble(n2);

    }
}
