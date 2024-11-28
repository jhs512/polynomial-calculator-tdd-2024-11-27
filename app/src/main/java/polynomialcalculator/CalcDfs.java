package polynomialcalculator;

import java.util.ArrayList;
import java.util.List;

import polynomialcalculator.Util.CalcUtil;

public class CalcDfs {
    public static int dfs(int ans,List<Integer>intVal,List<String>operVal,int idx,int operIdx) {
        if(idx == intVal.size()) {
            return ans;
        } else {
            if(ans == 0) {
                // 초기 설정
                ans = intVal.get(idx);
                dfs(ans, intVal, operVal, ++idx, operIdx);
            } else {
                // 연산자가 + 일 때 
                if ( operVal.get(operIdx).equals("+") ) {
                    ans += intVal.get(idx);
                    dfs(ans, intVal, operVal, ++idx, ++operIdx);
                } 
                // 연산자가 - 일 떄 
                else if ( operVal.get(operIdx).equals("-")) {
                    ans -= intVal.get(idx);
                    dfs(ans, intVal, operVal, ++idx, ++operIdx);
                } 
                // 연산자가 * 일 때 
                else if  ( operVal.get(operIdx).equals("*")) {
                    ans *= intVal.get(idx);
                    dfs(ans, intVal, operVal, ++idx, ++operIdx);
                } 
                // 연산자가 / 일 때 
                else if  ( operVal.get(operIdx).equals("/")) {
                    ans /= intVal.get(idx);
                    dfs(ans, intVal, operVal, ++idx, ++operIdx);
                } 
            }
            // 식에서 가지고 있는 연산자를 전부 이용했을 때.  
            return dfs(ans, intVal, operVal, idx, operIdx); 
        }
    }

    public static int run(String cal) {
        int ans = 0; // 리턴값 변수

        // 식에서 소괄호 전부 재거 
        //    >>  입력 식 : ((3 + 5) * 5 + -10) * 10 / 5
        //    >>  기대 식 : 3 + 5 * 5 + -10 * 10 / 5
        cal = CalcUtil.RemoveParen(cal);

        //  " " 을기준으로 잘라서 임시 문자 배열 생성 
        //  입력 문자열 : 3 + 5 * 5 + -10 * 10 / 5
        //  기대 문자열 : {3, +, 5, *, 5, +, -10, *, 10, /, 5}
        String[] sArr = cal.split(" ");

        //  숫자가 임시로 저장 될 리스트 
        List<Integer> intVal = new ArrayList<>();

        //  연산자가 임시로 저장 될 리스트
        List<String> operVal = new ArrayList<>();

        for(String s : sArr) {
            //  연산자인 경우 operVal 리스트에 값 입력 
            //  리스트 기대 결과 : [+, *, +, *, /] 
            if(s.equals("+") || s.equals("*") || s.equals("-") || s.equals("/")) {
                operVal.add(s);
            } 
            //  숫자인 경우 intVal 리스트에 값 입력 
            //  리스트 기대 결과 : [3, 5, 5, -10, 10, 5]
            else {
                intVal.add(Integer.parseInt(s));
            }
        }

        //  재귀함수 호출(intVal의 리스트 길이만큼 반복 될 예정 )
        ans = dfs(ans, intVal, operVal, 0, 0);
       
        return ans;
    }
}
