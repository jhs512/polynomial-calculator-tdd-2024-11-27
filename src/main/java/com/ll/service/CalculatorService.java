package com.ll.service;

import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    // 숫자 (음수 포함) 정규식
    private static String REGEX_NUMBER = "^-?[0-9]+$";

    /**
     *
     * 계산 로직 시작 함수
     *
     * @param strList
     * @return
     */
    public int run(List<String> strList){
        List<Integer> numList = new ArrayList<>();

        int result = 0;
        result = calc(strList, numList);

        return result;
    }


    /**
     *
     * 후위 표기법 계산 함수
     *
     * @param strlist 후위 표기법 리스트
     * @param numList 스택 숫자 리스트
     * @return 후위 표기법 계산 결과값
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    public int calc(List<String> strlist, List<Integer> numList){
        // 후위 표기법이 끝났을 때 결과값 리턴
        if(strlist.isEmpty()){
            return numList.getFirst();
        }
        // 후위 표기법 앞부터 추출
        String s = strlist.removeFirst();
        // 만약 숫자일 경우 숫자 스택에 추가
        if(s.matches(REGEX_NUMBER)){
            numList.add(Integer.parseInt(s));
        }
        // 연산자일 경우 계산 시작
        else {
            // 숫자 스택 맨 마지막으로 들어온 숫자
            int a = numList.removeLast();
            // 숫자 스택 맨 마지막보다 앞서서 들어간 숫자
            // - 계산할 때의 앞 숫자
            int b = numList.removeLast();
            switch (s) {
                case "+" -> numList.add(plus(a, b));
                case "-" -> numList.add(minus(b, a));
                case "*" -> numList.add(multiple(a, b));
                case "/" -> numList.add(division(b, a));
            }
        }

        // 후위 표기법 리스트가 계속 존재할 경우 자기 자신 호출
        return calc(strlist, numList);
    }

    /**
     *
     * 더하기 함수
     *
     * @param a 계산할 앞 숫자
     * @param b 계산할 뒷 숫자
     * @return 계산된 결과값
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private int plus(int a, int b){
        return a + b;
    }

    /**
     *
     * 빼기 함수
     *
     * @param a 계산할 앞 숫자
     * @param b 계산할 뒷 숫자
     * @return 계산된 결과값
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private int minus(int a, int b){
        return a - b;
    }

    /**
     *
     * 곱하기 함수
     *
     * @param a 계산할 앞 숫자
     * @param b 계산할 뒷 숫자
     * @return 계산된 결과값
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private int multiple(int a, int b){
        return a * b;
    }

    /**
     *
     * 나누기 함수
     *
     * @param a 계산할 앞 숫자
     * @param b 계산할 뒷 숫자
     * @return 계산된 결과값
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private int division(int a, int b){
        return a / b;
    }
}
