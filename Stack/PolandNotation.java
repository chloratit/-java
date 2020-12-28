package com.atguigu.stack;

import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //中缀表达式转后缀表达式 1+((2+3)*4)-5 转成1 2 3 + 4 * + 5 -

        String expression="1+((2+3)*4)-5";
        //直接对str进行操作 先将1+((2+3)*4)-5中缀表达式对应ArrayList

        //ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] =>ArrayList[1, 2, 3, +, 4,  *, +, 5,-]
        List<String> infixExpressionList=toInfixExpressionList(expression);
        System.out.println(infixExpressionList);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(parseSuffixExpressionList);
        System.out.println(calculate(parseSuffixExpressionList));
/*
        String suffixExpression="3 4 + 5 * 6 -";
        List<String> list=getListString(suffixExpression);
        System.out.println("list="+list);
        int res=calculate(list);
        System.out.println("计算结果为"+res);
*/

    }
    /**
     * 方法：将中缀表达式对应的list转换为后缀表达式对应的list
     *
     */
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈s1 s2  s1是运算符栈 s2是储存中间结果的栈 有数字和运算符
        //s2没有pop操作 而且最后需要逆序数出 可以用arrayList代替 不用栈
        Stack<String> s1=new Stack<>();
        List<String> s2=new ArrayList<>();


        for(String item:ls){
            //遍历ls
            if(item.matches("\\d")){
                //如果是数 就加入s2
                s2.add(item);
            }else if(item.equals("(")){
                //如果是左括号 入栈s1
                s1.push(item);
            }else if(item.equals(")")){
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                while(s1.size()!=0&&Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }

        }
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }





    /**
     * 方法：将中缀表达式转成对应的list
     * ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] =>ArrayList[1, 2, 3, +, 4,  *, +, 5,-]
     */
    public static List<String> toInfixExpressionList(String s){
        List<String> ls=new ArrayList<>();
        int i=0;//用于遍历的指针
        String str;//对多位数拼接
        char c;//每遍历一个字符都放入c中
        do{
            if((c=s.charAt(i))<48||(c=s.charAt(i))>57){//不是数字
                ls.add(""+c);
                i++;
            }else{//如果是一个数 需要考虑多位数
                str="";//先将str置空
                while(i<s.length()&&(c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                    str+=c;//拼接起来
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }

    //将后缀表达式放到ArrayList中
    //传递一个方法给ArrayList，遍历其 配合栈 完成计算
    public static List<String> getListString(String suffixExpression){
        String[] split=suffixExpression.split(" ");
        List<String> list=new ArrayList<>();
        for(String ele:split){
            list.add(ele);
        }
        return list;
    }
    public static int calculate(List<String> ls){
        Stack<String> stack=new Stack<>();
        for(String item:ls){
            if(item.matches("\\d+")){//匹配的是多位数
                stack.push(item);
            }else{
                int num2= Integer.parseInt(stack.pop());
                int num1= Integer.parseInt(stack.pop());
                int res=0;
                if(item.equals("+")) {
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res=num1-num2;
                }else if(item.equals("*")){
                    res=num1*num2;
                }else if(item.equals("/")){
                    res=num1/num2;
                }else {
                    throw new RuntimeException("运算符有错误");
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

}
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //写一个方法 返回对应的优先级数字

    public static int getValue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");;
                break;

        }
        return result;
    }

}