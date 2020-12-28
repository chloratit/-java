package com.atguigu.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
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

    }
}

