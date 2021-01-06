package com.atguigu.recursion;

public class Queen8 {

    //定义一个max表示多少个皇后
    int max=8;
    //定义数组array 保存皇后放置位置的结果
    int[] array=new int[max];
    static int count=0;
    static int judgeCount=0;
    public static void main(String[] args) {
        Queen8 queen=new Queen8();
        queen.check(0);
        System.out.println(count);

    }
    private void check(int n){
        if(n==max){
            print();
            return;
        }
        for(int i=0;i<max;i++){
            array[n]=i;
            if(judge(n)){
                check(n+1);
            }

        }

    }
    private boolean judge(int n){
        judgeCount++;
        for(int i=0;i<n;i++){
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }
    //写一个方法 可以将皇后摆放的位置输出
    private void print(){
        count++;
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();

        
    }
}
