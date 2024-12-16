package com.ll;

public class Token {
        int num;

        String value;
        String type;
        int priority;
        public Token(String value,String type,int priority){
            this.value = value;
            this.type= type;
            this.priority = priority;
            this.num=0;
        }

        public Token(int num,String type,int priority){
            this.num = num;
            this.type=type;
            this.priority = priority;
            this.value="";
        }
}
