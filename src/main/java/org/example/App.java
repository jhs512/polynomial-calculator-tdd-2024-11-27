package org.example;

import javax.management.RuntimeOperationsException;
import java.io.EOFException;
import java.util.*;


public class App {

    private List<Integer> strToInts(String expression, String operator) {
        String[] items = expression.replaceAll("\\s+", "").split(operator);
        if (items.length <= 1) throw new IllegalArgumentException("");

        //배열의 각 문자열을 int형으로 변환
        return Arrays.stream(items).map(
            (item) -> {
                if (item.contains("m")) {
                    return Integer.parseInt(item.replace("m", "-"));
                }
                return Integer.parseInt(item);
            }
        ).toList();
    }

    public int add(String expression) {
        List<Integer> intItems = strToInts(expression, "\\+");
        return intItems.stream().reduce(0, Integer::sum);
    }

    public int minus(String expression) {
        List<Integer> intItems = strToInts(expression, "-");
        return intItems.subList(1, intItems.size()).stream().reduce(intItems.getFirst(), (result, a) -> result - a);
    }

    public int multiply(String expression) {
        List<Integer> intItems = strToInts(expression, "\\*");
        return intItems.subList(1, intItems.size()).stream().reduce(intItems.getFirst(), (result, a) -> result * a);
    }

    public int divide(String expression) {
        List<Integer> intItems = strToInts(expression, "\\/");
        return intItems.subList(1, intItems.size()).stream().reduce(intItems.getFirst(), (result, a) -> result / a);
    }

}

class Tokenizer {

    private final String expression;
    private final char[] parsed;
    private List<Map<String, String>> tokens = new ArrayList<Map<String, String>>();
    private int index = 0;
    private int depth = 0;

    Tokenizer(String expression) {
        this.expression = expression;
        this.parsed = expression.toCharArray();
    }

    private boolean advance() {
        if (index == parsed.length-1) throw new RuntimeException("EOF");
        index+=1;
        return true;
    }

    private boolean hasNext() {
        if (index == parsed.length-1) return false;
        return true;
    }

    private void retreat() {
        if (index == 0) return;
        index-=1;
    }

    private String get() {
        return String.valueOf(parsed[index]);
    }

    private String getNext() {
        return String.valueOf(parsed[index+1]);
    }


    public List<Map<String, String>> tokenize() {

        outerloop:
        while (true) {
            String arg = get();
            Map<String, String> token = new HashMap<>();

            //숫자 돌입
            if ("0123456789".contains(arg)) {
                StringBuilder valueBuilder = new StringBuilder();
                while ("0123456789".contains(get())) {
                    valueBuilder.append(get());
                    if (!hasNext()) break;
                    advance();
                }
                token.put("value", valueBuilder.toString());
                token.put("attribute", "value");


                if (!hasNext()) {
                    tokens.add(token);
                    if (")".contains(get())) {
                        token.put("value", get());
                        token.put("attribute", "close");
                        token.put("depth", String.valueOf(depth));
                        depth -= 1;
                        tokens.add(token);
                    }
                    break;
                }
            }

            //우선순위 높은 연산자 & 부호가 될 수 없는 연산자
            if ("\\*\\/".contains(arg)) {
                retreat();
                if ("\\*\\/\\-\\+".contains(get())) throw new RuntimeException("잘못된 연산자 사용 index : " + index);
                advance();

                token.put("value", arg);
                token.put("attribute", "operator");
                token.put("priority", "0");
                advance();
            }

            //우선순위 낮은 연산자 & 부호가 될 수 있는 연산자
            if ("\\+\\-".contains(arg)) {
                retreat();
                if ("\\*\\/\\-\\+".contains(get())) {
                    advance();

                    StringBuilder valueBuilder = new StringBuilder();
                    if (("-".contains(get()))) {
                        valueBuilder.append("-");
                        advance();
                    } else advance();

                    while ("0123456789".contains(get())) {
                        valueBuilder.append(get());
                        if (!hasNext()) break;
                        advance();
                    }
                    token.put("value", valueBuilder.toString());
                    token.put("attribute", "value");

                    if (!hasNext()) {
                        tokens.add(token);
                        if (")".contains(get())) {
                            token.put("value", get());
                            token.put("attribute", "close");
                            token.put("depth", String.valueOf(depth));
                            depth -= 1;
                            tokens.add(token);
                        }
                        break;
                    }
                }

                advance();
                token.put("value", arg);
                token.put("attribute", "operator");
                token.put("priority", "1");
                advance();
            }

            if ("(".contains(arg)) {
                depth += 1;
                token.put("value", arg);
                token.put("attribute", "open");
                token.put("depth", String.valueOf(depth));
                advance();
            }

            if (")".contains(arg)) {
                token.put("value", arg);
                token.put("attribute", "close");
                token.put("depth", String.valueOf(depth));
                depth -= 1;
                advance();
            }
            tokens.add(token);
        }
        return tokens;
    }
}

