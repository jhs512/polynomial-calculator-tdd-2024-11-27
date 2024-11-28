package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 조건
 * - 문자열에 포함된 숫자와 연산 기호, 괄호를 기반으로 계산
 * - 정수 기반 연산을 위해 나눗셈(/)에서 발생하는 나머지는 버림
 *
 * 접근
 * 1. 연산 우선순위에 따라 괄호 내 연산을 위해 문자열에서 괄호 부분 문자열 추출
 * 2. 괄호 내 문자열의 추출은 괄호의 우선순위를 고려 -> 우선 연산 대상 = '(' + 수식 문자열 + ')' 형태
 * 3. 추출된 문자열은 반드시 한 쌍의 괄호에 둘러 쌓인 수식 형태로 존재해야 함, 괄호 중첩 또는 수식이 아닌 형태 시 예외 발생
 * 4. 추출된 문자열 연산 후 추출된 부분에 연산 결과 대입
 * 4. 문자열에서 모든 괄호 부분이 변경될 때까지 반복 호출
 * 5. 마지막에 괄호가 존재하지 않는 문자열이 되면 최종 연산 후 결과 리턴
 *
 * ex. String expr = (3 + 5 * (-1 - -7 * (3 * 15 - -1) / 2) + 10) - 1;
 *      [1-1] 연산 대상 추출 -> (3 * 15 - -1) = 46
 *      [1-2] String expr = (3 + 5 * (-1 - -7 * 46 / 2) + 10) - 1;
 *      [2-1] 연산 대상 추출 -> (-1 - -7 * 46 / 2) = 160
 *      [2-2] String expr = (3 + 5 * 160 + 10) - 1;
 *      [3-1] 연산 대상 추출 -> (3 + 5 * 160 + 10) = 813
 *      [3-2] String expr = 813 - 1;
 *      [4-1] 추출 대상 없음, 최종 연산
 *      [4-2] int result = 812;
 */
public class Calc {

    private static final String POLYNOMIAL = "^[\\d+\\-*/()\\s]+$"; //계산식에 허용된 문자열 확인
    private static final String CACULABLE = "^[+-]?(\\d+)([+\\-*/][+-]?(\\d+))*$";  //계산 가능 문자열 확인: "정수" 또는 "정수 + 연산기호(+, -, *, /) + 정수"
    private static final String BRACKET = "\\([^()]*\\)";   //괄호 중첩 비허용

    /**
     * 계산기 실행
     *
     * @param expr - 연산 대상 문자열
     * @return 연산 결과
     */
    public static int run(final String expr) {
        String input = expr.replaceAll("\\s", "");  //문자열 내 모든 공백 제거

        if (!input.matches(POLYNOMIAL)) {   //문자열 내 연산 불가능한 문자 포함 시 예외
            throw new IllegalArgumentException("계산할 수 없는 문자가 포함되어있습니다.: " + expr);
        }

        Pattern pattern = Pattern.compile(BRACKET);

        String tmp = null;
        while (true) {
            input = extractBracket(input, pattern); //괄호 내 문자열 연산 후 대입 반복
            if (input.equals(tmp)) {    //이전 문자열과 비교 -> 변경 사항 없음 = 추출할 괄호가 없음 -> 반복 종료
                break;
            } else {
                tmp = input;    //이전 문자열 기록
            }
        }

        return calculateString(input);  //최종 연산
    }

    /**
     * 문자열 내 pattern과 일치하는 괄호 추출 -> 연산 후 추출된 위치에 대입하여 리턴
     *
     * @param input   - 연산 대상 문자열
     * @param pattern - 추출 대상 괄호 패턴
     * @return 변경된 문자열
     */
    private static String extractBracket(final String input, final Pattern pattern) {
        Matcher matcher = pattern.matcher(input);

        String result = input;

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {    //패턴과 일치하는 경우를 찾음 = 괄호 부분 추출
            matches.add(matcher.group());   //matches에 추출된 문자열 저장
        }

        for (String match : matches) {  //추출된 문자열을 연산 후 추출 위치에 대입
            result = result.replaceFirst(Pattern.quote(match),
                    String.valueOf(calculateString(match.substring(1, match.length() - 1))));
        }

        return result;
    }

    /**
     * 정수, 연산 기호(+, -, *, /)만 포함된 문자열을 계산한 다음 리턴
     *
     * @param input - 연산 대상 문자열
     * @return 연산 결과
     */
    private static int calculateString(final String input) {
        if (!input.matches(CACULABLE)) {    //연산 불가능한 문자열 판별
            throw new IllegalArgumentException("연산 기호와 정수가 아닌 문자, 공백, 괄호 등이 포함된 식은 연산할 수 없습니다.: " + input);
        }

        int result = 0;

        Stack<Integer> nums = new Stack<>();

        int currentNum = 0;
        char lastOp = '+';  //시작 부호
        boolean isNegative = false; //숫자 앞 부호 확인

        for (int i = 0; i <= input.length(); i++) {
            char ch = i < input.length() ? input.charAt(i) : '\0';  //문자열에서 index로 문자 1개씩 추출

            if (Character.isDigit(ch)) {   //추출된 문자가 숫자인 경우
                int start = i;
                while (i < input.length() && Character.isDigit(input.charAt(i))) {
                    i++;
                }
                currentNum = Integer.parseInt(input.substring(start, i));
                i--;

                if (isNegative) {
                    currentNum = -currentNum;
                    isNegative = false;
                }
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '\0') { //추출된 문자가 연산 기호 또는 문자열 끝 값인 경우
                if ((ch == '+' || ch == '-') && (i == 0 || !Character.isDigit(
                        input.charAt(i - 1)))) {  //숫자 앞 + 또는 - 부호 확인
                    isNegative = ch == '-';
                } else {    //숫자 앞 부호가 아닌 연산자일 경우
                    switch (lastOp) {
                        case '+' -> nums.push(currentNum);
                        case '-' -> nums.push(-currentNum);
                        case '*' -> nums.push(nums.pop() * currentNum);
                        case '/' -> {
                            if (currentNum == 0) {
                                throw new ArithmeticException("0으로 나눌 수 없습니다.");
                            }
                            nums.push(nums.pop() / currentNum);
                        }
                        default -> throw new IllegalArgumentException(
                                "알 수 없는 연산자입니다.: " + lastOp); //생략 가능(CACULABLE 검사 이후)
                    }
                    lastOp = ch;    //현재 연산자 갱신
                    currentNum = 0;
                }
            }
        }

        while (!nums.isEmpty()) {
            result += nums.pop();
        }

        return result;
    }

}
