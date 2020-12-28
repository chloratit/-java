package com.atguigu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue arrayQueue=new ArrayQueue(3);
        char key=' ';//接受用户输入
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key=scanner.next().charAt(0);//接受一个字符
            switch(key){
                case's'://显示队列
                    arrayQueue.showQueue();
                    break;
                case'a'://添加数据到队列
                    System.out.println("请输入要添加的数据：");
                    int value=scanner.nextInt();
                    arrayQueue.addQueue(value);
                    System.out.println();
                    break;
                case'g'://从队列取出数据
                    try {
                        int res=arrayQueue.getQueue();
                        System.out.println("取出的数据是"+res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case'h'://查看队列头的数据
                    try {
                        int res=arrayQueue.headQueue();
                        System.out.println("队列头的数据是"+res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());;
                    }
                    break;
                case'e'://退出程序
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序结束");
    }
}
//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue{
    //表示数组的最大容量maxSize
    private int maxSize;
    //队列头front
    private int front;
    //队列尾rear
    private int rear;
    //该数组用于存放队列arr
    private int[] arr;
    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=-1;// front指向队列头的前一个位置，并不是直接指向队列头
        rear=-1;// rear指向队列尾部（队列最后一个数据
    }

    // 判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }

    // 判断队列是否空
    public boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列addQueue
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满 无法再添加数据");
            return;
        }
        rear++;
        arr[rear]=n;
    }

    //获取队列的数据 数据出队列getQueue
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列是空的 无法获取数据");
        }
        front++;
        return arr[front];
    }
    //显示队列的所有数据showQueue
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列是空的 无法获取数据");
            return;
        }//遍历
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列的头数据 注意不是取出数据headQueue
    public int headQueue(){
        if(isEmpty()){
            System.out.println("队列是空的 无法获取数据");
            throw new RuntimeException("队列是空的 无法获取数据");
        }
        return arr[front+1];
    }

}


