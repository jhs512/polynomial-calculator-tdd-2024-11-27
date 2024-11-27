package com.ll.controller;

import com.ll.service.CalculatorService;

import java.util.ArrayList;
import java.util.List;

public class CalculatorContoller {

    private CalculatorService calculatorService = new CalculatorService();

    // 사칙 연산 정규식
    private static String REGEX_NOT_NUMBER = "[+*/-]";
    // 숫자 (음수 포함) 정규식
    private static String REGEX_NUMBER = "^-?[0-9]*$";
    // 빈 문자열 정규식
    private static String REGEX_EMPTY_SPACE = "\\s+";
    // 괄호 앞 부분
    private static String REGEX_BRACKET_FIRST = "(";
    // 괄호 뒷 부분
    private static String REGEX_BARCKET_LAST = ")";

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
        String[] sArray = s.split(REGEX_EMPTY_SPACE);

        // 나눈 문자열을 숫자 리스트와 연산자 리스트로 넣기
        for(String str : sArray){
            // 만약 나온 문자열이 빈 문자열일 경우 넘기기
            if(str.isBlank()) continue;
            // 괄호 닫는 부분일 경우
            // 넣었던 걸 빼야한다.
            // - 먼저 계산해야 되기 때문에
            if(str.endsWith(REGEX_BARCKET_LAST)) {
                // 닫는 괄호 체크 변수
                int check = 0;
                // 닫는 괄호 갯수 계산
                for(int i = 0; i < str.length(); i++){
                    if(str.charAt(i) == ')') check++;
                }
                // 만약 닫는 괄호와 숫자가 붙어있을 경우 넣기
                numList.add(Integer.parseInt(str.replaceAll("\\)", "")));
                // 닫는 괄호에 따른 계산 시작
                for(int i = 0; i < check; i++){
                    result = calculatorService.calculation(strList, numList);
                }
            }
            // 괄호 여는 부분일 경우
            else if(str.startsWith(REGEX_BRACKET_FIRST)){
                // 괄호 여는 부분 갯수 확인 변수
                int check = 0;
                // 괄호 여는 부분 갯수 확인
                for(int i = 0; i < str.length(); i++){
                    if(str.charAt(i) == '(') check++;
                }
                // 여는 부분 만큼 연산자 리스트에 추가
                for(int i = 0; i < check; i++){
                    strList.add("(");
                }
                // 만약 숫자가 붙어있을 경우 숫자 추가
                numList.add(Integer.parseInt(str.replaceAll("\\(", "")));
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





}
