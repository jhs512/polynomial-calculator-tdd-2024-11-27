package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class App {

    private List<Integer> strToInts (String expression, String operator) {
        String[] items = expression.replaceAll("\\s+", "").split(operator);
        if (items.length <= 1) throw new IllegalArgumentException("");

        //배열의 각 문자열을 int형으로 변환
        List<Integer> intItems = Arrays.stream(items).map(
            Integer::parseInt
        ).toList();
        return intItems;
    }

    public int add(String expression) {
        List<Integer> intItems = strToInts(expression, "\\+");
        return intItems.stream().reduce(0, Integer::sum);
    }

    public int minus(String expression) {
        List<Integer> intItems = strToInts(expression, "-");
        return intItems.subList(1,intItems.size()).stream().reduce(intItems.getFirst(), (result, a)->result-a);
    }

    public int multiply(String expression) {
        List<Integer> intItems = strToInts(expression, "\\*");
        return intItems.subList(1,intItems.size()).stream().reduce(intItems.getFirst(), (result, a)->result*a);
    }

    public int divide(String expression) {
        List<Integer> intItems = strToInts(expression, "\\/");
        return intItems.subList(1,intItems.size()).stream().reduce(intItems.getFirst(), (result, a)->result/a);
    }
}