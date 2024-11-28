package polynomialcalculator;

import java.util.ArrayList;
import java.util.List;

public class CalcDfs {

    public static int run(String cal) {
        while (cal.contains("(")) {
            int open = cal.lastIndexOf("(");
            int close = cal.indexOf(")", open);
            String inner = cal.substring(open + 1, close);
            int result = run(inner);
            cal = cal.substring(0, open) + result + cal.substring(close + 1);         
        }

        //괄호 X ==> 우선순위에 따라 계산
        return cal(cal);
    }

    //  입력 식 : ((3 + 5) * 5 + -10) * 10 / 5
    public static int cal(String cal) {
        //  숫자가 임시로 저장 될 리스트 
        List<Integer> intVal = new ArrayList<>();

        //  연산자가 임시로 저장 될 리스트
        List<String> operVal = new ArrayList<>();

        //  " " 을기준으로 잘라서 임시 문자 배열 생성 
        String[] sArr = cal.split(" ");

        for(String s : sArr) {
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                operVal.add(s);
            } else {
                intVal.add(Integer.parseInt(s));
            }
        }

        //  곱셈, 나눗셈 선처리
        int i = 0;
        while (i < operVal.size()) {            
            if(operVal.get(i).equals("*") || operVal.get(i).equals("/")) {
                int result = operVal.get(i).equals("*") ? 
                    intVal.get(i) * intVal.get(i + 1) : 
                    intVal.get(i) / intVal.get(i + 1);

                intVal.set(i, result);
                intVal.remove(i+1);
                
                //  처리 연산자 제거
                operVal.remove(i);
            } else {
                i++;
            }
        }
        
        int ans = intVal.get(0); // 리턴값 변수
        
        //  덧셈 뺼셈 처리 
        for(i = 0; i < operVal.size() ; i++) {
            ans = operVal.get(i).equals("+") ? 
                ans + intVal.get(i + 1) : 
                ans - intVal.get(i + 1); 
        }

        System.out.println("최종 결과 : " + ans);
       
        return ans;
    }
}
