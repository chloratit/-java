package com.atguigu.linkedlist;

public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        //小孩出圈
        circleSingleLinkedList.countBoy(1,2,5);

    }
}
//创建环形的单向链表
class CircleSingleLinkedList{
    //创建first节点
    private  Boy first=null;
    //添加小孩节点 构建环形链表
    public void addBoy(int nums){
        if(nums<1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy=null;
        //使用for循环来创建环形链表
        for(int i=1;i<=nums;i++){
            Boy boy=new Boy(i);
            if(i==1){//第一个小孩
                first=boy;
                first.setNext(first);//构成环 自己指向自己
                curBoy=first;//让curBoy指向第一个小孩
            }else{//第二个小孩开始
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }
    //遍历当前的环形链表
    public void showBoy(){
        if(first==null){
            System.out.println("链表为空");
        }
        //
        Boy cur=first;
        while(true){
            System.out.println("小孩的编号为"+cur.getNo());
            if(cur.getNext()==first){
                break;
            }
            cur=cur.getNext();
        }
    }
    //根据用户输入 计算出圈顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下出一次圈
     * @param nums 表示最初有几个小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        if(first==null||startNo<1||startNo>nums){
            System.out.println("参数输入有误");
            return;
        }
        //让helper指向first的前一个指针为止
        Boy helper=first;
        while(true){
            if(helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }
        //
        for(int j=0;j<startNo-1;j++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数时 让first和helper同时移动m-1次
        while(true){
            if(helper==first){
                break;//说明只有一个节点
            }
            for(int j=0;j<countNum-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点就是要出圈的小孩节点
            System.out.println("小孩"+first.getNo()+"出圈");
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.println("留在圈中的小孩编号"+helper.getNo());
    }
}
//创建节点 boy类
class Boy{
    private int no;
    private Boy next;
    public Boy(int no) {
        this.no = no;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Boy getNext() {
        return next;
    }
    public void setNext(Boy next) {
        this.next = next;
    }
}