package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation{


     public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(String str : tokens){
            if(str.equals("*")){
                int res =  stack.pop() * stack.pop()   ;
                stack.push(res) ;
            }else if(str.equals("+")){
                int res =  stack.pop() + stack.pop()    ;
                stack.push(res) ;
            }else if(str.equals("/")){
                int num1 =  stack.pop()  ;
                int num2 =  stack.pop()  ;
                int res =  num2/num1 ;
                stack.push(res) ;
            }else if(str.equals("-")){
                int num1 =  stack.pop() ;
                int num2 =  stack.pop()  ;
                int res =  num2-num1 ;
                stack.push(res) ;
            }else{
                stack.push(Integer.parseInt(str)) ;
            }
        }
        return stack.pop() ;
    }
}