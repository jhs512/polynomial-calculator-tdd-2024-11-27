package org.example;

import java.util.*;


public class App {

    private static List<Integer> strToInts(String expression, String operator) {
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

    public static int add(String expression) {
        List<Integer> intItems = strToInts(expression, "\\+");
        return intItems.stream().reduce(0, Integer::sum);
    }

    public static int minus(String expression) {
        List<Integer> intItems = strToInts(expression, "-");
        return intItems.subList(1, intItems.size()).stream().reduce(intItems.getFirst(), (result, a) -> result - a);
    }

    public static int multiply(String expression) {
        List<Integer> intItems = strToInts(expression, "\\*");
        return intItems.subList(1, intItems.size()).stream().reduce(intItems.getFirst(), (result, a) -> result * a);
    }

    public static int divide(String expression) {
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
        this.expression = expression.replaceAll("\\s+", "");
        this.parsed = this.expression.toCharArray();
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
                        Map<String, String> t = new HashMap<>();
                        t.put("value", get());
                        t.put("attribute", "close");
                        t.put("depth", String.valueOf(depth));
                        depth -= 1;
                        tokens.add(t);
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
                            Map<String, String> t = new HashMap<>();
                            t.put("value", get());
                            t.put("attribute", "close");
                            t.put("depth", String.valueOf(depth));
                            depth -= 1;
                            tokens.add(t);
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
                if (!hasNext()) {
                    tokens.add(token);
                    break;
                }
                advance();
            }
            tokens.add(token);
        }
        return tokens;
    }
}

class Executor {
    static int finalResult = 0;

    static public void checkIsValid(List<Map<String, String>> tokens) {
        //괄호 개수 체크
        List<Map<String, String>> opens = tokens.stream().filter(
            (item)->(Objects.equals(item.get("attribute"), "open"))
        ).toList();

        List<Map<String, String>> closes = tokens.stream().filter(
            (item)->(Objects.equals(item.get("attribute"), "close"))
        ).toList();

        if (opens.size() != closes.size()) throw new RuntimeException("괄호의 개수가 맞지 않습니다");

        //연산자 개수 체크
        List<Map<String, String>> operators = tokens.stream().filter(
            (item)->(Objects.equals(item.get("attribute"), "operator"))
        ).toList();

        List<Map<String, String>> values = tokens.stream().filter(
            (item)->(Objects.equals(item.get("attribute"), "value"))
        ).toList();

        if (operators.size() != values.size()-1) throw new RuntimeException("연산자와 값의 개수가 맞지 않습니다");
    }

    static public List<Integer> look(List<Map<String, String>> tokens) {
        int startIndex = 0;
        int endIndex = tokens.size() - 1;
        int currentDepth = 0;

        //시작 인덱스와 종료 인덱스 찾기 (괄호 기준)
        for (int i = 0; i < tokens.size(); i++) {
            Map<String, String> item = tokens.get(i);
            if (Objects.equals(item.get("attribute"), "open")) {
                int depth = Integer.parseInt(item.get("depth"));
                if (depth > currentDepth) {
                    startIndex = i;
                    currentDepth = depth;
                }
            }

            if (Objects.equals(item.get("attribute"), "close")) {
                int depth = Integer.parseInt(item.get("depth"));
                if (depth == currentDepth) {
                    endIndex = i;
                }
            }
        }
        List<Integer> indexes = new ArrayList<>();
        indexes.add(startIndex);
        indexes.add(endIndex);
        return indexes;

    }

    static public String calculate(int startIndex, int endIndex, List<Map<String, String>> tokens) {

        Stack<String> operatorStack = new Stack<>();
        Stack<String> valueStack = new Stack<>();


        //우선순위 낮은연산자 탐색 -> 스택에 집어넣음 (나중에 계산)
        //후위 표기법으로 계산
        //LIFO 구조이기 때문에 인덱스의 역순으로 계산
        for (int i = endIndex; i >= startIndex; i--) {
            Map<String, String> item = tokens.get(i);
            if (Objects.equals(item.get("attribute"), "operator")) {
                String operator = item.get("value");
                String value1 = tokens.get(i-1).get("value");
                String value2 = tokens.get(i+1).get("value");

                int priority = Integer.parseInt(item.get("priority"));

                if (priority == 1) {
                    if (valueStack.isEmpty()) {
                        valueStack.push(value2);
                    }
                    valueStack.push(value1);
                    operatorStack.push(operator);
                }
            }
        }

        //우선순위 높은 연산자 탐색
        for (int i = endIndex; i >= startIndex; i--) {
            Map<String, String> item = tokens.get(i);
            if (Objects.equals(item.get("attribute"), "operator")) {
                String operator = item.get("value");
                String value1 = tokens.get(i-1).get("value");
                String value2 = tokens.get(i+1).get("value");

                int priority = Integer.parseInt(item.get("priority"));

                if (priority == 0) {
                    if (valueStack.isEmpty()) {
                        valueStack.push(value2);
                    }
                    valueStack.push(value1);
                    operatorStack.push(operator);
                }
            }
        }

        int result = 0;

        //후위 표기법 연산
        while (!operatorStack.isEmpty()) {
            StringBuilder expression = new StringBuilder();
            String value1 = valueStack.pop();
            String value2 = valueStack.pop();

            if (value1.contains("-")) value1 = value1.replace("-", "m");
            if (value2.contains("-")) value2 = value2.replace("-", "m");

            String operator = (operatorStack.pop());
            expression.append(value1);
            expression.append(operator);
            expression.append(value2);

            if (operator.contains("*")) {
                result = App.multiply(expression.toString());
                valueStack.push(String.valueOf(result));
            }
            if (operator.contains("/")) {
                result = App.divide(expression.toString());
                valueStack.push(String.valueOf(result));
            }
            if (operator.contains("+")) {
                result = App.add(expression.toString());
                valueStack.push(String.valueOf(result));
            }
            if (operator.contains("-")) {
                result = App.minus(expression.toString());
                valueStack.push(String.valueOf(result));
            }
        }

        return valueStack.pop();
    }

    static public List<Map<String, String>> save(int startIndex, int endIndex, String value, List<Map<String, String>> tokens) {

        // 새로운 리스트 생성
        List<Map<String, String>> newList = new ArrayList<>();

        // 합칠 부분 이전의 요소 복사
        for (int i = 0; i < startIndex; i++) {
            newList.add(tokens.get(i));
        }

        // 합칠 부분의 값을 합쳐서 새로운 리스트에 추가
        Map<String, String> combinedValue = new HashMap<>();
        combinedValue.put("value", value);
        combinedValue.put("attribute", "value");
        newList.add(combinedValue);

        // 합칠 부분 이후의 요소 복사
        for (int i = endIndex + 1; i < tokens.size(); i++) {
            newList.add(tokens.get(i));
        }

        return newList;
    }

    static public String execute(List<Map<String,String>> tokens) {
        checkIsValid(tokens);
        List<Integer> indexes = look(tokens);
        int startIndex = indexes.getFirst();
        int endIndex = indexes.getLast();

        String result = calculate(startIndex, endIndex, tokens);
        List<Map<String, String>> newTokens = save(startIndex, endIndex, result, tokens);

        if (newTokens.size() != 1) {
            return execute(newTokens);
        }
        return newTokens.getFirst().get("value");
    }
}
