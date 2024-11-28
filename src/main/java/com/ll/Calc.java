package com.ll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {
    static double run(String str){
        List<String> arr = new ArrayList<>(Arrays.stream(str.split(" ")).toList());
        List<Integer> arrLater = new ArrayList<>();
        List<Integer> arrFirst = new ArrayList<>();

        double result = 0;
        for(int i = 0; i<arr.size(); i++){
            Pattern pattern = Pattern.compile(Pattern.quote(arr.get(i)));
            Matcher matcher = pattern.matcher("[*/]");
            if(matcher.find()){
                arrFirst.add(i);
            }
        }
        for(int i : arrFirst){
            arr.forEach(System.out::print);
            System.out.println();
            System.out.println(arr.get(i));
            result =cal(arr,i);
            System.out.println(result);
        }
        List<String>  arr2 = new ArrayList<>(arr.stream().filter(e -> !e.isEmpty()).toList());
        for(int i = 0; i<arr2.size(); i++){
            Pattern pattern = Pattern.compile(Pattern.quote(arr2.get(i)));
            Matcher matcher = pattern.matcher("[+\\-]");
            if(matcher.find()){
                arrLater.add(i);
            }
        }

        for(int i : arrLater){
            arr2.forEach(System.out::print);
            System.out.println();
            System.out.println(arr2.get(i));
            result =cal(arr2,i);
        }
        return result;
    }

    public static double cal(List<String> arr, int i){
        double result = 0;
        if(arr.get(i).contains("+")) {
            result = Double.parseDouble(arr.get(i - 1)) + Double.parseDouble(arr.get(i + 1));
            arr.set(i+1,result+"");
            arr.set(i-1,"");
            arr.set(i,"");
            return result;
        }
        if(arr.get(i).contains("-")) {
            result = Double.parseDouble(arr.get(i - 1)) - Double.parseDouble(arr.get(i + 1));
            arr.set(i+1,result+"");
            arr.set(i-1,"");
            arr.set(i,"");
            return result;
        }
        if(arr.get(i).contains("*")) {
            result = Double.parseDouble(arr.get(i - 1)) * Double.parseDouble(arr.get(i + 1));
            arr.set(i+1,result+"");
            arr.set(i-1,"");
            arr.set(i,"");
            return result;
        }
        if(arr.get(i).contains("/")) {
            result = Double.parseDouble(arr.get(i - 1)) / Double.parseDouble(arr.get(i + 1));
            arr.set(i+1,result+"");
            arr.set(i-1,"");
            arr.set(i,"");
            return result;
        }
        return result;
    }
}
