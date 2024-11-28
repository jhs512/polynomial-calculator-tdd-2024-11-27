package org.example;

import java.util.*;

/**
 * @deprecated
 * App 클래스의 문자 계산 메서드가 아닌 Executor가 내부적으로 계산을 처리합니다
 */

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

/**
 * @author jung-yongjun
 * @apiNote
문자열 식을 입력받은 후 구분 분석하여 토큰으로 변환합니다
 Map<String, String> token
 token에는 다음과 같은 키들이 있습니다

 value:String 토큰의 실제 값입니다
 attribute:value, operator, open, close 토큰의 속성입니다. 각 피연산자, 연산자 괄호 열기, 괄호 닫기 입니다
 depth:Int : 괄호의 깊이입니다 0부터 시작하여 괄호가 추가될 때마다 1씩 늘어납니다
 priority:Int : 연산자의 우선순위입니다 *, / 는 0, +, - 는 1 입니다
 */
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

    //인덱스 전진
    private boolean advance() {
        if (index == parsed.length-1) throw new RuntimeException("EOF");
        index+=1;
        return true;
    }

    private boolean hasNext() {
        if (index == parsed.length-1) return false;
        return true;
    }

    //인덱스 후진
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

                //끝단이면 현재 토큰, 괄호로 감싸져있다면 괄호 반환
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

                //부호일 때와 연산자일 때 구분
                if ("\\*\\/\\-\\+".contains(get())) {
                    //부호일 때
                    advance();
                    StringBuilder valueBuilder = new StringBuilder();

                    //괄호 앞에 -올 때 -> -1 * 로 치환 (앞에 아무런 것도 없을 때)
                    if ("(".contains(get())) {
                        valueBuilder.append("-1");
                        token.put("value", valueBuilder.toString());
                        token.put("attribute", "value");
                        tokens.add(token);

                        //새 토큰으로 * 연산자 삽입
                        Map<String, String> t = new HashMap<>();
                        t.put("value", "*");
                        t.put("attribute", "operator");
                        t.put("priority", "0");
                        tokens.add(t);
                        continue;
                    }

                    if ("-".contains(get())) {
                        valueBuilder.append("-");
                        advance();
                    } else advance();

                    //괄호 앞에 -올 때 -> -1 * 로 치환 (앞에 요소가 있을 때)
                    if ("(".contains(get())) {
                        valueBuilder.append("1");
                        token.put("value", valueBuilder.toString());
                        token.put("attribute", "value");
                        tokens.add(token);

                        //새 토큰으로 * 연산자 삽입
                        Map<String, String> t = new HashMap<>();
                        t.put("value", "*");
                        t.put("attribute", "operator");
                        t.put("priority", "0");
                        tokens.add(t);
                        continue;
                    }

                    //숫자가 있을 때 계속 인덱스 전진
                    while ("0123456789".contains(get())) {
                        valueBuilder.append(get());
                        if (!hasNext()) break;
                        advance();
                    }
                    token.put("value", valueBuilder.toString());
                    token.put("attribute", "value");

                    //끝일 때 토큰을 추가하고, 괄호로 감싸져있다면 괄호 토큰 추가
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

                } else {
                    // 부호가 아닌 연산자일떄
                    advance();
                    token.put("value", arg);
                    token.put("attribute", "operator");
                    token.put("priority", "1");
                    advance();
                }
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

/**
 * @author jung-yongjun
 * @apiNote
실행자입니다. 모든 메서드 및 변수는 static이며 모든 입력 값은 토큰화된 구문을 입력으로 받습니다
 (문자열 식x)
 */
class Executor {
    static int finalResult = 0;

    /**
     * @param tokens
     * @apiNote
     * 토큰의 유효성을 검사합니다
     */
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

    /**
     * @param tokens
     * @apiNote
     * 시작과 끝 인덱스를 탐색합니다. 괄호가 있다면 괄호가 시작, 끝점이 되며 없다면 0부터 토큰의 길이까지입니다
     */

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
                    break;
                }
            }
        }
        List<Integer> indexes = new ArrayList<>();
        indexes.add(startIndex);
        indexes.add(endIndex);
        return indexes;

    }

    /**
     * @param tokens, startIndex, endIndex
     * @apiNote
     * 시작과 끝 인덱스를 기준으로 계산합니다. 후위 표현삭으로 변환한 후 계산합니다
     */
    static public String calculate(int startIndex, int endIndex, List<Map<String, String>> tokens) {

        Stack<Map<String, String>> operatorStack = new Stack<>();
        Stack<Integer> valueStack = new Stack<>();
        List<Map<String, String>> postfix = new ArrayList<>();

        //후위 표기법으로 변환
        //LIFO 구조이기 때문에 인덱스의 역순으로 계산
        for (int i = startIndex; i <= endIndex; i++) {
            Map<String, String> item = tokens.get(i);
            if (Objects.equals(item.get("attribute"), "operator")) {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(item);
                    continue;
                };
                int postPriority = Integer.parseInt(operatorStack.peek().get("priority"));
                int currentPriority = Integer.parseInt(item.get("priority"));
                if (currentPriority < postPriority) operatorStack.push(item);
                if (currentPriority >= postPriority) {
                    postfix.add(operatorStack.pop());
                    operatorStack.push(item);
                }
            }

            if (Objects.equals(item.get("attribute"), "value")) {
                postfix.add(item);
            }
        }

        //남은 연산자 모두 리스트에 집어넣기
        while (!operatorStack.isEmpty()) {
            postfix.add(operatorStack.pop());
        }

        //후위 표기법 연산
        postfix.forEach(
            (item)->{
                if (Objects.equals(item.get("attribute"), "value")) {
                    valueStack.push(Integer.parseInt(item.get("value")));
                }

                if (Objects.equals(item.get("attribute"), "operator")) {
                    int value1 = valueStack.pop();
                    int value2 = valueStack.pop();

                    if (Objects.equals(item.get("value"), "+")) valueStack.push(value2+value1);
                    if (Objects.equals(item.get("value"), "-")) valueStack.push(value2-value1);
                    if (Objects.equals(item.get("value"), "*")) valueStack.push(value2*value1);
                    if (Objects.equals(item.get("value"), "/")) valueStack.push(value2/value1);
                }
            }
        );

        return String.valueOf(valueStack.pop());
    }

    /**
     * @apiNote
     * 괄호의 계산을 끝낸 후 리스트를 재생성합니다
     * 괄호의 시작과 끝을 제거하고 괄호의 연산 결과로 대체합니다
     */
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

    /**
     * @apiNote
     * 재귀적으로 괄호가 사라질 때까지 실행합니다. 토큰의 길이가 1일 때 (결과값만 남을 때) 재귀를 빠져나옵니다
     */
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
