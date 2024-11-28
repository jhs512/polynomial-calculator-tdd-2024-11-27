package com.ll;

import java.util.Arrays;
import java.util.List;

public class Calc {
    static double run(String str){
        if(str.contains("+")){
            String[] a = str.split("\\+");
            return Arrays.stream(a).map(String::trim).mapToDouble(Double::parseDouble).reduce(CalculatorService::plus).orElse(0);
        }
        if(str.contains("-")){
            String[] a = str.split("\\-");
            return Arrays.stream(a).map(String::trim).mapToDouble(Double::parseDouble).reduce(CalculatorService::sub).orElse(0);
        }
        if(str.contains("*")){
            String[] a = str.split("\\*");
            return Arrays.stream(a).map(String::trim).mapToDouble(Double::parseDouble).reduce(CalculatorService::mul).orElse(0);
        }
        if(str.contains("/")){
            String[] a = str.split("\\/");
            return Arrays.stream(a).map(String::trim).mapToDouble(Double::parseDouble).reduce(CalculatorService::divide).orElse(0);
        }
        List<String> arr = Arrays.stream(str.split(" ")).toList();
        return 0;
    }
}
