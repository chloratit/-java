package com.atguigu.recursion;

public class MiGong{
    public static void main(String[] args) {
        //先创建二维数组 模拟迷宫
        int[][] map=new int[8][7];
        //使用1表示墙
        //先把上下界置为1
        System.out.println("地图情况：");
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //先把左右界置为1
        for(int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
        map[1][2]=1;
        map[2][2]=1;
        //输出地图
        for(int i=0;i<8;i++ ){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }
        //使用递归回溯找路
        setWay(map,1,1);

        //输出新的地图 小球走过并标识过的地图
        System.out.println("小球走过并标识过的地图：");
        for(int i=0;i<8;i++ ){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }
    }
    /**
     * 使用递归回溯来给小球找路
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路返回 true 否则返回false
     *
     * 约定：1.i j表示地图的哪个位置 [1,1]是出发位置  [6,5]是终点位置 代表通路找到
     *      2.当map[i][j]  为0时表示该点没有走过，为1时表示墙，为2表示通路可以走，为3表示该点位置已经走过但是走不通
     *      3.在走迷宫时，需要确定一个策略/方法：下-右-上-左，如果该点走不通就回溯
     **/
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else{
            if(map[i][j]==0){//如果当前点还没走过 按照策略走
                map[i][j]=2;//假定可以走通
                if(setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else{
                    //说明该点走不通 是死路
                    map[i][j]=3;
                    return false;
                }
            }else{
                //如果map[i][j]！=0 可能是1，2，3
                return false;
            }
        }
    }
}
