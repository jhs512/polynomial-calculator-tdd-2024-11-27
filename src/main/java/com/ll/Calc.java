package com.ll;

import java.util.Arrays;
import java.util.List;

public class Calc {
    static double run(String str){
        if(str.contains("+")){
            String[] a = str.split("\\+");
            return Arrays.stream(a).map(String::trim).mapToInt(Integer::parseInt).sum();
        }
        if(str.contains("-")){
            String[] a = str.split("\\-");
            return Arrays.stream(a).map(String::trim).mapToInt(Integer::parseInt).reduce((n1,n2) -> (n1-n2)).orElse(0);
        }
        if(str.contains("*")){
            String[] a = str.split("\\*");
            return Arrays.stream(a).map(String::trim).mapToInt(Integer::parseInt).reduce((n1,n2) -> (n1*n2)).orElse(0);
        }
        if(str.contains("/")){
            String[] a = str.split("\\/");
            return Arrays.stream(a).map(String::trim).mapToInt(Integer::parseInt).reduce((n1,n2) -> (n1/n2)).orElse(0);
        }
        List<String> arr = Arrays.stream(str.split(" ")).toList();
        return 0;
    }
}
