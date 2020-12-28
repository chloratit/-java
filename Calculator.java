package com.atguigu.stack;
/**
 * 1 通过一个index值 当作索引 遍历表达式
 * 2 如果发现是一个数字 直接入数栈
 * 3 如果扫描到一个符号 分以下情况：
 *      符号栈为空的话 直接入栈
 *      如果栈内有操作符，进行比较
 *          如果当前操作符优先级小于或者等于栈中的操作符：
 *              从数栈中pop出两个数字 从符号栈pop出一个符号，进行运算，结果入数栈 当前操作符入符号栈
 *          如果当前操作符优先级大于栈中的操作符：直接入符号栈
 *4 当表达式扫描完毕 顺序从数栈和符号栈pop出响应数和符号并运行
 *5 数栈最后只有一个数字 就是运算结果
 *
 */

public class Calculator {
    public static void main(String[] args) {
        String expression="32+6/2+3*4";
        //创建两个栈 一个数字栈 一个符号栈
        ArrayStack2 numStack=new ArrayStack2(10);
        ArrayStack2 operStack=new ArrayStack2(10);
        int index=0;//用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;//保存结果
        char ch=' ';//将每次扫描的char保存到ch中
        String keepNum="";
        while(true){
            //依次得到expression的每一个字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么相应处理
            if(operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    //比较当前运算符和栈中运算符的优先级大小
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //把运算结果入数栈
                        numStack.push(res);
                        //把当前符号入符号栈
                        operStack.push(ch);
                    }
                    else{
                        operStack.push(ch);
                    }
                }else{
                    operStack.push(ch);
                }
            }
            else{
                //数字入栈要进行asc码转换操作
                //numStack.push(ch-48);
                //处理多位数 不能发现一个数就直接入栈
                //在处理数 需要向表达式后多看一位数 判断是否仍然是数字
                //定义一个字符串变量用于拼接
                //如果ch已经是表达式的最后一位 直接入栈

                keepNum+=ch;
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        //重要 要清空！！！！！
                        keepNum="";
                    }
                }
            }
                index++;
                if(index>=expression.length()){
                    break;
                }
        }
        //扫描完毕 顺序地从两个栈中pop出数字和符号进行计算
        while(true){
            if(operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            /**
             * 下面一句话是需要的 不然在有些结果中会显示栈为空
             * ？？为啥呢 res值不是已经算出来了吗 为什么不可以直接显示
             */
            numStack.push(res);
        }
        System.out.println(expression+"="+res);

        }
    }
class ArrayStack2{
    private int maxsize;
    private int[] stack;
    private int top=-1;
    //构造器

    public ArrayStack2(int maxsize) {
        this.maxsize = maxsize;
        stack=new int[this.maxsize];
    }
    //栈满
    public  boolean isFull(){
        return top==maxsize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈 数据入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈已经满了 无法入栈");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈 数据出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈是空的");
        }
        int value=stack[top];
        top--;
        return value;
    }
    //遍历栈中的数据 这里遍历是从栈顶往下遍历
    public void show(){
        if(isEmpty()){
            System.out.println("栈是空的 无法遍历");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
    //增加一个方法 返回当前栈顶的值 但是不是pop
    public int peek(){
        return stack[top];
    }
    //返回运算符的优先级 优先级是程序员自己确定 优先级使用数字表示
    //数字越大 优先级越搞
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else{
            return -1;
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }

    public int cal(int num1,int num2,int oper){
        int res=0;
        switch (oper){
            case '+':
                res=num2+num1;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num2*num1;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}