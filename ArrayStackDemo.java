package com.atguigu.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack(5);
        String key="";
        boolean loop=true;
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示退出");
            System.out.println("push:表示添加数据到栈中");
            System.out.println("pop:表示数据出栈");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入要入栈的数据");
                    int value=scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println("出栈的数据为");
                        int res=stack.pop();
                        System.out.println(res);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序退出");
    }
}
class ArrayStack{
    private int maxsize;
    private int[] stack;
    private int top=-1;
    //构造器

    public ArrayStack(int maxsize) {
        this.maxsize = maxsize;
        stack=new int[maxsize];
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
}
