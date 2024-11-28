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
    private static String REGEX_NUMBER = "^-?[0-9]+$";
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
        checkBracketFirst(sArray);
        checkBracketLast(sArray);

        List<String> sList = this.makePosteriorCalculation(sArray);

        result = calculatorService.run(sList);

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
                    // 만약 괄호 앞이 - 일 경우 -1과 곱하기를 넣는다
                    // 예시) -(7 + 3)
                    if(numStr.equals("-")) {
                        String tmp = sList.removeLast();
                        tmp += "1";
                        sList.add(tmp);
                        sList.add("*");
                    }
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
                    // 만약 저장된 문자열이 없을 경우
                    // 음수의 경우이므로 숫자 문자열에 추가
                    if(sList.isEmpty()) {
                        numStr += str;
                        continue;
                    }
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

    /**
     *
     * 후위 계산식 생성 함수
     *
     * @param sArray 문자열 배열
     * @return 후위 계산식 리스트
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private List<String> makePosteriorCalculation(String[] sArray){
        // 후위 계산식 표기법 리스트 변수 생성
        List<String> strList = new ArrayList<>();
        // 후위 계산식의 연산자를 잠시 넣을 스택 리스트 생성
        List<String> stackList = new ArrayList<>();

        // 입력받은 문자열을 순환하면서 후위 표기법 생성
        for(String s : sArray){
            // 만약 빈 문자열이나 공백 문자열일 경우 패스
            if(s.isBlank()) continue;
            // 만약 입력받은 문자가 숫자일 경우 후위 표기법에 바로 저장
            if(checkPriority(s) == 0) strList.add(s);
            // 만약 입력받은 문자가 곱셈이거나 나눗셈일 경우 바로 저장
            // 만약 여는 괄호일 경우 바로 저장
            // - 여는 괄호는 무조건 저장
            // - 곱셈과 나눗셈은 우선순위에서 곱하기와 빼기보다 우열이므로 무조건 저장
            else if(checkPriority(s) == 1 || checkPriority(s) == 3){
                stackList.add(s);
            }
            // 만약 입력받은 문자가 더하기나 빼기 일경우
            else if(checkPriority(s) == 2){
                // 후위 계산 표기법에 맞는 우선순위에 따라 스택 문자열에서 꺼냄
                pop(strList, stackList);
                // 현재 문자열을 스택 문자열에 저장
                stackList.add(s);
            }

            // 닫는 괄호일 경우 여는 괄호까지 스택 문자열에서 꺼내서 후위 표기법에 저장
            else if(checkPriority(s) == -1){
                // 꺼내는 재귀함수 호출
                pop(strList, stackList);
            }
        }

        // 끝난 후에 스택 문자열에 있던 문자들을 후위 표기법에 저장
        pop(strList, stackList);

        return strList;
    }

    /**
     *
     * 후위 표기법에 맞는 우선 순위에 따른 스택 문자열에서 꺼내는 함수
     *
     * @param strList 후위 표기법 리스트
     * @param stackList 연산자 스택 리스트
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private void pop(List<String> strList, List<String> stackList){
        // 만약 스택 리스트가 존재하지 않으면 함수 종료
        if(stackList.isEmpty()) return;
        // 스택 리스트에서 마지막 연산자 꺼내기
        String operator = stackList.removeLast();
        // 마지막 연산자가 여는 괄호일 경우 종료
        if(checkPriority(operator) == 3) return;
        // 그 외에 존재할 경우 후위 표기법 리스트에 추가
        strList.add(operator);
        // 끝나지 않았으므로 다시 스스로 호출
        pop(strList, stackList);
    }

    /**
     *
     * 여는 괄호의 숫자가 제대로 존재하는지 확인하고 수정하는 함수
     * - 여는 괄호가 더 많을 경우
     *
     * @param sArray 입력받은 문자열 배열
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private void checkBracketFirst(String[] sArray){
        List<Integer> checkFirstList = new ArrayList<>();
        for(int i = 0; i < sArray.length; i++){
            if(sArray[i].matches(REGEX_BARCKET_FIRST)){
                checkFirstList.add(i);
            } else if(sArray[i].matches(REGEX_BARCKET_LAST)){
                checkFirstList.removeLast();
            }
        }
        if(!checkFirstList.isEmpty()){
            for (Integer integer : checkFirstList) {
                sArray[integer] = "";
            }
        }

    }

    /**
     *
     * 마지막 괄호의 숫자가 제대로 존재하는 지 확인하고 수정하는 함수
     * - 더 많을 경우를 계산
     *
     * @param sArray 입력받은 문자열 배열
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private void checkBracketLast(String[] sArray){
        List<Integer> checkLastList = new ArrayList<>();
        for(int i = sArray.length - 1; i >= 0; i--) {
            if (sArray[i].matches(REGEX_BARCKET_LAST)) {
                checkLastList.add(i);
            } else if (sArray[i].matches(REGEX_BARCKET_FIRST)) {
                checkLastList.removeLast();
            }
        }
        if(!checkLastList.isEmpty()){
            for(Integer integer : checkLastList){
                sArray[integer] = "";
            }
        }

    }

    /**
     *
     * 우선 순위 계산식
     *
     * @param s 들어오는 문자열
     * @return 우선 순위 번호
     *
     * @author shjung
     * @since 2024. 11. 28.
     */
    private int checkPriority(String s){
        return switch (s) {
            case ")" -> -1;
            case "*", "/" -> 1;
            case "+", "-" -> 2;
            case "(" -> 3;
            default -> 0;
        };
    }
}
