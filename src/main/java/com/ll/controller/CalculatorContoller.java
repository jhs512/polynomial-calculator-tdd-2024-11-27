package com.ll.controller;

import com.ll.service.CalculatorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorContoller {

    private CalculatorService calculatorService = new CalculatorService();

    // 사칙 연산 정규식
    private static String REGEX_NOT_NUMBER = "[+*/-]";
    // 숫자 (음수 포함) 정규식
    private static String REGEX_NUMBER = "^-?[0-9]*$";
    // 괄호 앞 부분
    private static String REGEX_BARCKET_FIRST = "[(]";
    // 괄호 뒷 부분
    private static String REGEX_BARCKET_LAST = "[)]";

    /**
     *
     * main 에서 호출되는 함수
     *
     * @param s 메인에서 입력받은 다항식
     *
     * @return 다항식 결과값
     *
     * @author shjung
     * @since 2024. 11. 27.
     */
    public int run(String s) {
        // 괄호 및 연산자 리스트
        List<String> strList = new ArrayList<>();
        // 숫자 리스트
        List<Integer> numList = new ArrayList<>();

        // 결과값
        int result = 0;

        // 입력 받은 문자열을 빈 문자열 기준으로 나누기
        String[] sArray = this.makeStringArray(s);


        // 나눈 문자열을 숫자 리스트와 연산자 리스트로 넣기
        for(String str : sArray){
            // 만약 나온 문자열이 빈 문자열일 경우 넘기기
            if(str.isBlank()) continue;
            // 괄호 닫는 부분일 경우
            // 넣었던 걸 빼야한다.
            // - 먼저 계산해야 되기 때문에
            if(str.matches(REGEX_BARCKET_LAST)) {
                // 만약 닫는 괄호와 숫자가 붙어있을 경우 넣기
                if(!str.replaceAll(REGEX_BARCKET_LAST, "").isBlank()) numList.add(Integer.parseInt(str.replaceAll(REGEX_BARCKET_LAST, "")));
                // 닫는 괄호에 따른 계산 시작
                calculatorService.calculation(strList, numList);
            }
            // 괄호 여는 부분일 경우
            else if(str.matches(REGEX_BARCKET_FIRST)){
                strList.add("(");
                // 만약 숫자가 붙어있을 경우 숫자 추가
                if(!str.replaceAll(REGEX_BARCKET_FIRST, "").isBlank()) numList.add(Integer.parseInt(str.replaceAll("\\(", "")));
            }
            // 연산자 추가
            else if(str.matches(REGEX_NOT_NUMBER)){
                strList.add(str);
            }
            // 숫자일 경우 숫자 리스트에 추가
            else if(str.matches(REGEX_NUMBER)){
                numList.add(Integer.parseInt(str));
            }
        }

        // 마지막으로 괄호가 없는 부분 계산
        result = calculatorService.calculation(strList, numList);

        return result;
    }

    /**
     *
     * 입력 받은 문자열을 연산자 및 숫자로 나누어서 문자열 배열에 저장하는 함수
     *
     * @param s 입력받은 문자열
     * @return 문자열 배열
     *
     * @authos shjung
     * @since 2024. 11. 27.
     */
    private String[] makeStringArray(String s){
        // 문자열을 하나하나 다 나누는 작업
        String[] sArr = s.split("");

        // 문자열을 저장하기 위한 리스트
        List<String> sList = new ArrayList<>();

        // 숫자를 문자열로 저장하기 위한 문자열 변수
        String numStr = "";

        // 문자 하나하나 순회하는 반복문
        for(String str : sArr){
            // 만약 빈 문자열일 경우 반복문 통과
            if(str.isBlank()) continue;
            // 만약 괄호가 문자열일 경우 바로 넣기
            if(str.matches(REGEX_BARCKET_FIRST) || str.matches(REGEX_BARCKET_LAST)){
                // 만약 괄호가 닫히기 전이나 열리기 전에 숫자가 있으면 넣고 닫거나 열기
                if(!numStr.isBlank()) {
                    sList.add(numStr);
                    numStr = "";
                }
                sList.add(str);
            }
            // 만약 사칙연산이 문자열로 들어올 경우
            else if(str.matches(REGEX_NOT_NUMBER)){
                // 숫자 문자열이 있을 경우 먼저 넣기
                // 그리고 숫자 문자열 추가
                if(!numStr.isBlank()) {
                    sList.add(numStr);
                    numStr = "";
                }
                // 만약 문자열이 빼기(-) 가 아닐 경우 바로 문자열 배열에 추가
                if(!str.equals("-")){
                    sList.add(str);
                }
                // 만약 문자열이 빼기(-)일 경우
                else {
                    // 현재까지 입력받은 문자열 중 마지막 추출 - 비교를 위함
                    String tmp = sList.removeLast();
                    // 만약 사칙 연산 중 하나일 경우
                    if(tmp.matches(REGEX_NOT_NUMBER)){
                        // 추출한 문자열을 다시 넣기
                        sList.add(tmp);
                        // 사칙 연산 후에 들어오는 빼기(-)는 음수를 표현하기 위함이기 때문에 숫자 문자열에 추가
                        numStr += str;
                    }
                    // 만약 숫자나 괄호일 경우 바로 추출한 문자열과 숫자 바로 넣기
                    else if(tmp.matches(REGEX_NUMBER) || tmp.matches(REGEX_BARCKET_LAST)
                            || tmp.matches(REGEX_BARCKET_FIRST)){
                        sList.add(tmp);
                        sList.add(str);
                    }
                }
            }
            // 만약 문자열이 숫자일 경우 숫자 문자열에 추가
            else if(str.matches(REGEX_NUMBER)){
                numStr += str;
            }
        }

        // 마지막이 숫자 문자열이 비어있지 않을 경우
        // - 마지막이 괄호가 아닐 경우에 숫자 문자열을 문자열 배열에 추가
        if(!numStr.isBlank()){
            sList.add(numStr);
        }

        // 리스트를 문자열 배열로 변환
        String[] strArr = sList.toArray(new String[sList.size()]);

        // 문자열 배열 리턴
        return strArr;
    }
}
