package com.atguigu.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
//        int arr[]={101,34,119,1};
        int arr[]=new int[80000];
        for(int i=0;i<80000;i++){
            arr[i]=(int)(Math.random()*800000);
        }
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:MM:ss");
        String date1str = simpleDateFormat.format(date1);
        System.out.println(date1str);

        Select(arr);

        Date date2=new Date();
        String date2str = simpleDateFormat.format(date2);
        System.out.println(date2str);

    }
    //选择排序
    public static void Select(int[] arr){
        for(int i=0;i<arr.length-1;i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];//重置最小值和最小索引 将后面较小的数字和索引作为最小
                    minIndex = j;
                }
            }
            //循环结束 最小值和最小索引值找到 交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第"+(i+1)+"次排序后");
//            System.out.println(Arrays.toString(arr));
        }



/**
 * 自己写的：
 */
//        for(int i=1;i<arr.length;i++){
//            int temp=0;
//            if(arr[0]>arr[i]){
//                temp=arr[i];
//                arr[i]=arr[0];
//                arr[0]=temp;
//            }
//        }
//        System.out.println(Arrays.toString(arr));
    }

}
