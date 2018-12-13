package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(2);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        System.out.print(number+"=");
        String s="";
        while (number>=3) {
            if (number % 3 == 2) {
                s += "-";
                number = number / 3 + 1;
            } else {
                if(number%3==1) s+="+"; else s+="0";
                number/=3;
            }
        }
        if(number==2)s+="-+"; else if(number==1) s+="+"; else s+="0";
        int n=1;
        while (s.length()!=0){
            switch (s.substring(0,1)){
                case "0":n*=3; s=s.substring(1); break;
                case  "+":System.out.print("+"+n);s=s.substring(1);n*=3;break;
                case "-":System.out.print("-"+n);n*=3;s=s.substring(1);break;
            }
        }

    }
}