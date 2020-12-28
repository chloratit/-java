package com.atguigu.sparsearray;
import java.io.*;

/**
 * 在SparseArray的基础上，将稀疏数组保存到磁盘上，比如map.data
 * 恢复原来的数组时，读取map.data进行恢复
 */
public class SparseArrayIO {
    public static void main(String[] args) throws IOException {
        //创建原始的二维数组 11*11
        //0表示没有棋子 1表示黑子 2表示蓝子
        //chessArr row  data
        int chessArr[][]=new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][3]=2;

        //输出原始的二维数组
        for(int[] row:chessArr){
            for(int data:row){
                System.out.print(data+" ");
            }
            System.out.println();
        }

        //将二维数组转为细数数组的思路：
        //1.先遍历二维数组 得到非0数据的个数
        int sum=0;
        for(int i=0;i<chessArr.length;i++ ){
            for(int j=0;j<chessArr[i].length;j++){
                if(chessArr[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println(sum);

        //2.创建对应的稀疏数组
        int sparseArr[][]=new int[sum+1][3];
        //给稀疏矩阵赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        int count=0;
        //遍历二维数组，将非0的值存放到sparseArr中
        for(int i=0;i<chessArr.length;i++ ){
            for(int j=0;j<chessArr[i].length;j++){
                if(chessArr[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println("=============输出的稀疏数组为=============");
        for(int i=0;i<sparseArr.length;i++ ) {
            for(int j=0; j<3;j++){
                System.out.print(sparseArr[i][j]+" ");
            }
            System.out.println();
        }
        //将稀疏数组恢复成原始的二维数组
        System.out.println("==========将稀疏数组恢复成二维数组=============");
        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][]=new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.再读取稀疏数组后几行的数据，并赋给原始的二维数组即可
        for(int i=1;i<sparseArr.length;i++ ) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        for(int i=0;i<chessArr2.length;i++ ){
            for(int j=0;j<chessArr2[i].length;j++){
                System.out.print(chessArr2[i][j]+" ");
            }
            System.out.println();
        }

        /**Java存入数组到txt文件和从txt文件中读取数组*/

         //写入文件
        FileWriter out=null;
        try {
            out=new FileWriter("map.data");
            for(int i=0;i<sparseArr.length;i++ ){
                for(int j=0;j<sparseArr[i].length;j++){
                    String content=String.valueOf(sparseArr[i][j]);
                    out.write(content+" ");
                }
                out.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //读取文件 从文件中读取数组
        int[][] data=new int[3][3];
        //BufferedReader带有缓冲区 不需要定义char数组和byte数组 自带缓冲
        FileReader reader=new FileReader("map.data");
        BufferedReader bufferedReader=new BufferedReader(reader);
        //在这里 FileReader是节点流 BufferedReader是包装流

        String s=null;
        int i=0;
        while((s=bufferedReader.readLine())!=null) {
            System.out.println(s);
//               String[] strings = s.split("\\t");
//                for (int k = 0; k < strings.length; k++) {
//                    data[i][k] = Integer.valueOf(strings[k]);
//                }
//               i++;
            }
            bufferedReader.close();
            //关闭流只需要关闭外层流 节点流会自动关

    }
}
