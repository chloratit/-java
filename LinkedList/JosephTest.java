package com.atguigu.linkedlist;

public class JosephTest {
    public static void main(String[] args) {
        CircleLinkedList boys=new CircleLinkedList();
        boys.add(6);
        boys.show();
        boys.count(1,2,5);


    }
}
class CircleLinkedList{
    private Boy1 first=null;
    public void add(int num){
        if( num<1){
            System.out.println("数字不正确");
            return;
        }
        Boy1 cur=null;
        for(int i=1;i<=num;i++){
            Boy1 boy=new Boy1(i);
            if(i==1){
                first=boy;
                first.setNext(first);
                cur=first;
            }else{
                cur.setNext(boy);
                boy.setNext(first);
                cur=boy;
            }
        }
    }
    //遍历
    public void show(){
        if(first==null){
            System.out.println("链表为空");
        }
        Boy1 cur=first;
        while(true){
            System.out.println("小孩编号为"+cur.getNo());
            if(cur.getNext()==first){
                break;
            }
            cur=cur.getNext();
        }
    }
    //
    public void count(int startNo,int countNum,int num){
        if(first==null || startNo<1 || startNo>num){
            System.out.println("输入数据有误");
            return;
        }
        Boy1 helper=first;
        while(true){
            if(helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }
        for(int j=0;j<startNo-1;j++){
            first=first.getNext();
            helper=helper.getNext();
        }
        while(true){
            if(helper==first){
                break;
            }
            for(int ii=0;ii<countNum-1;ii++){
                first=first.getNext();
                helper=helper.getNext();
            }
            System.out.println("出圈的小孩编号为"+first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.println("留在圈中的小孩编号为"+helper.getNo());
    }
}
class Boy1{
    private int no;
    private Boy1 next;

    public Boy1(int no) {
        this.no = no;

    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Boy1 getNext() {
        return next;
    }
    public void setNext(Boy1 next) {
        this.next = next;
    }
}
