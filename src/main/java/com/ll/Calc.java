package com.ll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {
    static double run(String str){
        List<String> arr = new ArrayList<>(Arrays.stream(str.split(" ")).toList());
        List<Integer> arrInt = new ArrayList<>();
        double result = 0;
        for(int i = 0; i<arr.size(); i++){
            Pattern pattern = Pattern.compile(Pattern.quote(arr.get(i)));
            Matcher matcher = pattern.matcher("[+\\-/*]");
            if(matcher.find()){
                arrInt.add(i);
            }
        }


        for(int i : arrInt){
            arr.forEach(System.out::print);
            System.out.println();
            System.out.println(arr.get(i));
            if(arr.get(i).contains("+")) {
                result = Double.parseDouble(arr.get(i - 1)) + Double.parseDouble(arr.get(i + 1));
                arr.set(i+1,result+"");
            }
            if(arr.get(i).contains("-")) {
                result = Double.parseDouble(arr.get(i - 1)) - Double.parseDouble(arr.get(i + 1));
                arr.set(i+1,result+"");
            }
            if(arr.get(i).contains("*")) {
                result = Double.parseDouble(arr.get(i - 1)) * Double.parseDouble(arr.get(i + 1));
                arr.set(i+1,result+"");
            }
            if(arr.get(i).contains("/")) {
                result = Double.parseDouble(arr.get(i - 1)) / Double.parseDouble(arr.get(i + 1));
                arr.set(i+1,result+"");
            }

        }
        return result;
    }
}
