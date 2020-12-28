package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("测试数组环形队列");
            //测试
            //创建一个队列
            CircleArray arrayQueue=new CircleArray(4);
            //设置长度为4 其队列的有效长度最大是3
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
class CircleArray{
    private int maxSize;
    //队列头front
    private int front;
    //队列尾rear指向队列最后一个元素的后一个位置
    private int rear;
    //该数组用于存放队列arr
    private int[] arr;
    public CircleArray(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=0;// front指向队列头的前一个位置，并不是直接指向队列头
        rear=0;//
    }
    // 判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
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
        //直接将数据加入
        arr[rear]=n;
        //将rear后移 此时必须考虑取模
        rear=(rear+1)%maxSize;
    }

    //获取队列的数据 数据出队列getQueue
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列是空的 无法获取数据");
        }
        //这里需要分析front指向队列的第一个位置
        //1 先把front对应的值保留到一个临时变量
        //2 将front后移
        //3 将临时保存的变量返回
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示队列的所有数据showQueue
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列是空的 无法获取数据");
            return;
        }//遍历
        //从front开始遍历 遍历多少个元素
        for(int i=front;i<front+size();i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //求出当前队列有效数据的个数
    public int size(){
        return(rear+maxSize-front)%maxSize;
    }
    //显示队列的头数据 注意不是取出数据headQueue
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的 无法获取数据");
        }
        return arr[front];
    }
}