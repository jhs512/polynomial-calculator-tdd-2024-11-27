import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {
    private List<String> list;
    private int endIdx;
    Calculator(){
        list = new ArrayList<>();
    }

    public int run(String str){
        list = preProcessing(str);
        endIdx = list.size()-1;
        parenthesesCalculate();
        calculate(0,endIdx);
        return Integer.parseInt(list.get(0));
    }

    private List<String> preProcessing(String str) {
        str = str.replaceAll("\\(", "\\( ");
        str = str.replaceAll("\\)", " \\)");
        for(String element : str.split(" ")){
            list.add(element);
        }
        return list;
    }

    private void parenthesesCalculate(){
        // 괄호가 있는지 확인하고 없으면 종료
        if(!inParentheses()){
            return;
        }
        // 괄호 속 계산
        Map<String, Integer> map = findParentheses();
        calculate(map.get("start")+1,map.get("end")-1);

        // 그리고 괄호를 제거함
        list.remove(map.get("start").intValue());
        list.remove(map.get("start").intValue()+1);
        endIdx -= 2;
        // 재귀호출
        parenthesesCalculate();
    }

    private Map<String, Integer> findParentheses() {
        Map<String, Integer> map = new HashMap<>();
        int start=0;
        int end=0;
        for(int i=0; i<=endIdx; i++){
            if(list.get(i).equals("(")){
                start = i;
            }
            else if(list.get(i).equals(")")){
                end = i;
                map.put("start", start);
                map.put("end",end);
                break;
            }
        }
        return map;
    }

    private void calculate(int start, int end) {
        // 계산을 다했으면 엔드포인트 = start 포인트임.
        if(start==end){
            return;
        }
        // 시작부터 끝 사이에 곱하기가 있다면
        else if(inMuitiplyAndDivide(start,end)){
            calculateMultiplyAndDivide(start, end);
            calculate(start, end-2);
        }
        else{ // 앞에서부터 계산
            calculatePlusAndMinus(start);
            calculate(start, end-2);
        }
    }

    private boolean inMuitiplyAndDivide(int start, int end){
        for(int i=start+1;i<end;i++){
            if("*".equals(list.get(i)) || "/".equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    private int whereMuitiplyAndDivide(int start, int end){
        for(int i=start+1;i<end;i++){
            if("*".equals(list.get(i)) || "/".equals(list.get(i))){
                return i;
            }
        }
        return 1;
    }

    private void calculateMultiplyAndDivide(int start, int end){
        int idx = whereMuitiplyAndDivide(start,end);
        int num1 = Integer.parseInt(list.get(idx-1));
        int num2 = Integer.parseInt(list.get(idx+1));
        String cmd = list.get(idx);
        list.remove(idx-1);
        list.remove(idx-1);
        list.remove(idx-1);
        endIdx -= 2;
        switch(cmd){
            case "*":
                list.add(idx-1,Integer.toString(num1*num2));
                break;
            case "/":
                list.add(idx-1,Integer.toString(num1/num2));
                break;
        }
    }

    private void calculatePlusAndMinus(int start){
        int num1 = Integer.parseInt(list.get(start));
        int num2 = Integer.parseInt(list.get(start+2));
        String cmd = list.get(start+1);
        list.remove(start);
        list.remove(start);
        list.remove(start);
        endIdx -= 2;
        switch(cmd){
            case "+":
                list.add(start,Integer.toString(num1+num2));
                break;
            case "-":
                list.add(start,Integer.toString(num1-num2));
                break;
        }
    }

    private boolean inParentheses(){
        for(int i = 0; i<=endIdx; i++){
            if("(".equals(list.get(i)) || ")".equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

}
