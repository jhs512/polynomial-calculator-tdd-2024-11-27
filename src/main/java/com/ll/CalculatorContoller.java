package com.ll;

import java.util.ArrayList;
import java.util.List;

public class CalculatorContoller {

    public int run(String cmd){

        List<Token> Tokens = new ArrayList<>();
        int depth=0;

        for(int i=0;i<cmd.length();i++){
            String str = cmd.substring(i,i+1);
            if(str.equals(" ")) continue; //공백 제외

            //단항 연산자 - 처리
            if(str.equals("-") && (i==0 || cmd.substring(i+1,i+2).equals("(") || cmd.substring(i+1,i+2).matches("-?\\d+"))){
                Tokens.add(new Token(-1,"num",0));
                Tokens.add(new Token("*","oper",(depth*2)+getPriority("*")));
            }
            // 숫자인 경우
            else if(str.matches("-?\\d+")){
                String num = "";
                while(i<cmd.length() &&cmd.substring(i,i+1).matches("-?\\d+")){
                    num+=cmd.substring(i,i+1);
                    i++;
                }
                i--;
                Tokens.add(new Token(Integer.parseInt(num),"num",0));
            }else if(str.equals("(")){
                depth++;
            }else if(str.equals(")")){
                depth--;
            }else{
                Tokens.add(new Token(str,"oper",(depth*2)+getPriority(str)));
            }
        }
        int result= Cal(Tokens);

        return result;
    }

    public int Cal(List<Token> Tokens){

        if(Tokens.size()==1){
            return Tokens.get(0).num;
        }
        int max_index=0;
        int max_priority=0;

        for(int i=0;i<Tokens.size();i++){
            Token token = Tokens.get(i);
            if(token.priority>max_priority){
                max_priority=token.priority;
                max_index=i;
            }
        }
        int num1= Tokens.get(max_index-1).num;
        int num2 = Tokens.get(max_index+1).num;
        String oper = Tokens.get(max_index).value;
        int result = solve(oper,num1,num2);

        Tokens.set(max_index,new Token(result,"num",0));
        Tokens.remove(max_index+1);
        Tokens.remove(max_index-1);



        return Cal(Tokens);
    }

    public int solve(String str,int num1,int num2){
        if(str.equals("+"))return num1+num2;
        else if(str.equals("-")) return num1-num2;
        else if(str.equals("*")) return num1*num2;
        else if(str.equals("/")) return num1/num2;
        return 0;
    }

    public int getPriority(String str){
        if(str.equals("*") || str.equals("/")){
            return 2;
        }else if(str.equals("+") || str.equals("-")){
            return 1;
        }else return 0;
    }
}
