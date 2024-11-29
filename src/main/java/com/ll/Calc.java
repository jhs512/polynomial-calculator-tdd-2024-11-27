package com.ll;

import java.util.*;


public class Calc {


    static double run(String str){
        str = str.replaceAll("[(]","( ");
        str = str.replaceAll("[)]"," )");

        List<String> arr = new ArrayList<>(Arrays.stream(str.split(" ")).toList());
        double result = 0;
        result = Double.parseDouble(loop(arr).getFirst());

        return  result;
    }
    static public HashMap<String, Integer> ParenIndex(List<String> arr){
        HashMap<String, Integer> parenIndex = new HashMap<>();
        int start = arr.indexOf("(");
        if(start == -1) return new HashMap<>();
        parenIndex.put("start",start);
        int i = parenIndex.get("start");
        int count = 0;
        boolean isEnd = false;
        while(true){
            if(isEnd){
                if(count == 0) {break; }
                else if(arr.get(i).equals(")")) count--;
            }
            else{
                if(arr.get(i).equals("(")) count++;
                else if(arr.get(i).equals(")")){
                    count--;
                    isEnd = true;

                }

            }
            i++;
        }
        parenIndex.put("end",i-1);
        return parenIndex;
    }

// ((1+1)+1)+(1+1)
    static List<String> loop(List<String> arr){

        HashMap<String, Integer> parenIndex = ParenIndex(arr);
        if(parenIndex.containsKey("start")){
            int start = parenIndex.get("start");
            int end = parenIndex.get("end");
            System.out.println(start + ", " + end);
            List<String> newArr = loop(new ArrayList<>(arr.subList(start+1,end)));
            for(int i = start; i<=end; i++)
                arr.remove(start);
            arr.addAll(start,newArr);
        }
        choice(arr);



        return arr;
    }
    public static void choice(List<String> arr){
        if(arr.contains("*")) {
            int index = arr.indexOf("*");
            mul(arr,index);
            loop(arr);
        }
        if(arr.contains("/")) {
            int index = arr.indexOf("/");
            divide(arr,index);
            loop(arr);
        }
        if(arr.contains("-")) {
            int index = arr.indexOf("-");
            sub(arr,index);
            loop(arr);
        }
        if(arr.contains("+")) {
            int index = arr.indexOf("+");
            plus(arr,index);
            loop(arr);
        }
    }

    public static double plus(List<String> arr, int i){
        double result = Double.parseDouble(arr.get(i - 1)) + Double.parseDouble(arr.get(i + 1));
        arr.set(i+1,result+"");
        arr.remove(i);
        arr.remove(i-1);
        return result;
    }
    public static double sub(List<String> arr, int i){
        double result = Double.parseDouble(arr.get(i - 1)) - Double.parseDouble(arr.get(i + 1));
        arr.set(i+1,result+"");
        arr.remove(i);
        arr.remove(i-1);
        return result;
    }
    public static double mul(List<String> arr, int i){
        double result = Double.parseDouble(arr.get(i - 1)) * Double.parseDouble(arr.get(i + 1));
        arr.set(i+1,result+"");
        arr.remove(i);
        arr.remove(i-1);
        return result;
    }
    public static double divide(List<String> arr, int i){
        double result = Double.parseDouble(arr.get(i - 1)) / Double.parseDouble(arr.get(i + 1));
        arr.set(i+1,result+"");
        arr.remove(i);
        arr.remove(i-1);
        return result;
    }

}