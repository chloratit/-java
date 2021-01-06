package com.atguigu.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        //int arr[]={3,2,9,10,11};
        //测试80000个数据的排序时间
        int arr[]=new int[80000];
        for(int i=0;i<80000;i++){
            arr[i]=(int)(Math.random()*800000);
        }
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:MM:ss");
        String date1str = simpleDateFormat.format(date1);
        System.out.println(date1str);

        bubbleSort(arr);


        Date date2=new Date();
        String date2str = simpleDateFormat.format(date2);
        System.out.println(date2str);

//        System.out.println("排序后的数组：");
//        System.out.println(Arrays.toString(arr));

    }
    //将冒泡排序封装成一个方法
    public static void bubbleSort(int[] arr){
        int temp=0;
        boolean flag=false;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
//            System.out.println("第"+(i+1)+"次排序结果为：");
//            System.out.println(Arrays.toString(arr));
            if(!flag){
                break;
            }else{
                flag=false;
            }
        }

    }

}
