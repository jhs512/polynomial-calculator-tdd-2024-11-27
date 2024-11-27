package com.ll.service;

import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    /**
     *
     * 계산기
     *
     * @param strList 연산자 리스트
     * @param numList 숫자 리스트
     * @return 계산 결과값
     *
     * @author shjung
     * @since 2024. 11. 27.
     */
    public int calculation(List<String> strList, List<Integer> numList){
        // 스택 연산자 리스트 - 곱하기와 나눗셈
        List<String> polynomialList = new ArrayList<>();
        // 스택 연산자 리스트 - 더하기와 뺄셈
        List<String> stackList = new ArrayList<>();
        // 스택 숫자 리스트
        List<Integer> stackNumList = new ArrayList<>();


        this.makeStack(strList, polynomialList, numList, stackNumList);

        // 결과값 초기화 - 기존에 결과값이 0일 경우 숫자 리스트에서 추가
        int result = stackNumList.removeLast();


        result = this.calculatorMultiDivi(polynomialList, stackList, stackNumList, result);
        result = this.calculatePlusMinus(stackList, stackNumList, result);

        // 결과값 숫자 리스트에 추가
        numList.add(result);

        //결과값 리턴
        return result;
    }

    /**
     *
     * 스택을 구현하기 위한 함수
     *
     * @param strList 연산자 리스트
     * @param polynomialList 스택 연산자 리스트
     * @param numList 숫자 리스트
     * @param stackNumList 스택 숫자 리스트
     *
     * @author shjung
     * @since 2024. 11. 27.
     */
    private void makeStack(List<String> strList, List<String> polynomialList, List<Integer> numList, List<Integer> stackNumList){
        // ------------ 스택 구현 위치 시작 -------------------------
        // 스택 숫자 연산자에 숫자 리스트의 마지막 숫자 하나 미리 입력
        // - 연산자는 숫자보다 하나 적기 때문에 미리 넣는다.
        stackNumList.add(numList.removeLast());
        // 입력받은 연산자 리스트에서 스택 연산자 리스트에 추가
        while(!strList.isEmpty()){
            String s = strList.removeLast();
            // 여는 괄호 나올 때 스택 리스트에 스택 추가 종료
            if(s.equals("(")) break;
            // 스택 연산자에 마지막 연산자 추가
            polynomialList.add(s);
            // 스택 숫자 연산자에 마지막 숫자 추가
            stackNumList.add(numList.removeLast());
        }
        // ------------ 스택 구현 위치 종료 -------------------------
    }

    /**
     *
     * 곱하기 나누기 함수
     *
     * @param polynomialList 곱하기와 나누기를 계산하기 위한 스택 연산자 리스트
     * @param stackList 곱하기와 나누기를 제외한 더하기와 빼기를 계산하기 위한 스택 연산자 리스트
     * @param stackNumList 스택 숫자 리스트
     * @param result 계산되기 전 결과값
     * @return 계산된 후 결과값
     *
     * @author shjung
     * @since 2024. 11. 27.
     */
    private int calculatorMultiDivi(List<String> polynomialList, List<String> stackList, List<Integer> stackNumList, int result){
        // ------------ 곱하기 나누기 계산 시작 ----------------------
        // 곱하기와 나눗셈부터 계산
        while(!polynomialList.isEmpty()){
            // 스택 연산자에서 첫번째 것 제거
            String s = polynomialList.removeFirst();
            // 곱하기와 나눗셈일 경우 계산
            switch (s){
                case "*":{
                    result *= stackNumList.removeLast();
                    break;
                }
                case "/":{
                    result /= stackNumList.removeLast();
                    break;
                }
                default:{
                    stackList.add(s);
                    break;
                }
            }
        }
        // ------------ 곱하기 나누기 계산 종료 ----------------------
        return result;
    }

    /**
     *
     * 더하기 빼기 함수
     * - 우선 순위가 동일하기 때문에 같이 계산한다.
     *
     * @param stackList 연산자 스택 리스트
     * @param stackNumList 숫자 스택 리스트
     * @param result 계산되기 전 결과값
     * @return 계산된 결과값
     *
     * @author shjung
     * @since 2024. 11. 27.
     */
    private int calculatePlusMinus(List<String> stackList, List<Integer> stackNumList, int result){
        // ------------ 더하기 빼기 계산 시작 ----------------------
        // 더하기와 뺄셈 계산
        while(!stackList.isEmpty()){
            // 스택 리스트에서 숫자 계산 +, - 이라 어느 것부터 해도 상관이 없다.
            String s = stackList.removeLast();
            switch (s){
                case "+":{
                    result += stackNumList.removeLast();
                    break;
                }
                case "-":{
                    result -= stackNumList.removeLast();
                    break;
                }
            }
        }
        // ------------ 더하기 빼기 계산 종료 ----------------------

        return result;
    }

}
