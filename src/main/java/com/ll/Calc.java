package com.ll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {
    static double run(String str){
        List<String> arr = new ArrayList<>(Arrays.stream(str.split(" ")).toList());
        /*
        List<Integer> arrLater = new ArrayList<>();
        List<Integer> arrFirst = new ArrayList<>();

        double result = 0;
        for(int i = 0; i<arr.size(); i++){
            Pattern pattern = Pattern.compile(Pattern.quote(arr.get(i)));

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
        */
        double result = 0;
        try {
            result = Double.parseDouble(loop(arr).getFirst());
        }
        catch (IndexOutOfBoundsException e){System.out.println(e.toString());}
        return  result;
    }

    static List<String> loop(List<String> arr) throws IndexOutOfBoundsException{
        arr.forEach(System.out::print);
        System.out.println();
        if(arr.contains("*")) {
           int index = arr.indexOf("*");
            cal(arr,index);
            loop(arr);
        }
        if(arr.contains("/")) {
            int index = arr.indexOf("/");
            cal(arr,index);
            loop(arr);
        }
        if(arr.contains("-")) {
            int index = arr.indexOf("-");
            cal(arr,index);
            loop(arr);
        }
        if(arr.contains("+")) {
            int index = arr.indexOf("+");
            cal(arr,index);
            loop(arr);
        }

        System.out.println(arr);
        return arr;
    }


    public static double cal(List<String> arr, int i){
        double result = 0;
        if(arr.get(i).contains("+")) {
            result = Double.parseDouble(arr.get(i - 1)) + Double.parseDouble(arr.get(i + 1));
            arr.set(i+1,result+"");
            arr.remove(i);
            arr.remove(i-1);
            return result;
        }
        if(arr.get(i).contains("-")) {
            result = Double.parseDouble(arr.get(i - 1)) - Double.parseDouble(arr.get(i + 1));
            arr.set(i+1,result+"");
            arr.remove(i);
            arr.remove(i-1);
            return result;
        }
        if(arr.get(i).contains("*")) {
            result = Double.parseDouble(arr.get(i - 1)) * Double.parseDouble(arr.get(i + 1));
            arr.set(i+1,result+"");
            arr.set(i-1,"");
            arr.remove(i);
            arr.remove(i-1);
            return result;
        }
        if(arr.get(i).contains("/")) {
            result = Double.parseDouble(arr.get(i - 1)) / Double.parseDouble(arr.get(i + 1));
            arr.set(i+1,result+"");
            arr.remove(i);
            arr.remove(i-1);
            return result;
        }
        return result;
    }
}
//1 나눈다.
//2 나눈거 기준으로 계산
//2-1 일단 * / 먼저
//2-2 그다음에 + 계산해보기