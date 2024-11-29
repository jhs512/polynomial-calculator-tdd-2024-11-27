package com.ll;

import java.util.*;


public class Calc {


    static double run(String str) {
        str = str.replaceAll("[(]", "( ");
        str = str.replaceAll("[)]", " )");
        List<String> arr = new ArrayList<>(Arrays.stream(str.split(" ")).toList());
        double result = 0;
        result = Double.parseDouble(loop(arr).getFirst());
        return result;
    }

    static public int containsIndex(List<String> arr, String ward, int startIndex) {
        int start = startIndex;
        for (; start < arr.size(); start++) {
            if (arr.get(start).contains(ward))
                return start;
        }
        return -1;
    }

    static public HashMap<String, Integer> ParenIndex(List<String> arr) {

        HashMap<String, Integer> parenIndex = new HashMap<>();
        int start = containsIndex(arr, "(", 0);
        if (start == -1) return new HashMap<>();
        parenIndex.put("start", start);
        int i = parenIndex.get("start");
        int count = 0;
        while (true) {
            if (arr.get(i).contains("(")) count++;
            else if (arr.get(i).equals(")")) {
                count--;
                if (count == 0) break;
            }
            i++;
        }
        parenIndex.put("end", i);
        return parenIndex;
    }

    // ((1+1)+1)+(1+1)
    static List<String> loop(List<String> arr) {

        HashMap<String, Integer> parenIndex = ParenIndex(arr);
        for (; parenIndex.containsKey("start"); parenIndex = ParenIndex(arr)) {

            int start = parenIndex.get("start");
            int end = parenIndex.get("end");
            List<String> newArr = loop(new ArrayList<>(arr.subList(start + 1, end)));
            if (arr.get(start).equals("-("))
                newArr.set(0, "-" + newArr.getFirst());
            if (end >= start) arr.subList(start, end + 1).clear();
            arr.addAll(start, newArr);
        }
        if(calculate(arr))
            loop(arr);


        return arr;
    }

    public static boolean calculate(List<String> arr) {
        int index = -1;
        double result = 0;
        if (arr.contains("*")) {
            index = arr.indexOf("*");
            result = Calculator.mul(arr.get(index - 1), arr.get(index + 1));
        } else if (arr.contains("/")) {
            index = arr.indexOf("/");
            result = Calculator.divide(arr.get(index - 1), arr.get(index + 1));
        } else if (arr.contains("-")) {
            index = arr.indexOf("-");
            result = Calculator.sub(arr.get(index - 1), arr.get(index + 1));
        } else if (arr.contains("+")) {
            index = arr.indexOf("+");
            result = Calculator.plus(arr.get(index - 1), arr.get(index + 1));
        } else return false;
        arr.set(index + 1, result + "");
        arr.remove(index);
        arr.remove(index - 1);
        return true;
    }


}