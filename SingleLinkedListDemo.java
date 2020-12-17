package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");
        //创建链表
        SingleLinkedList heros=new SingleLinkedList();
        //加入
        heros.addByOrder(hero1);
        heros.addByOrder(hero4);
        heros.addByOrder(hero3);
        heros.addByOrder(hero2);
        //修改
        HeroNode newHeroNode=new HeroNode(2,"lu","玉麒麟～～");
        heros.update(newHeroNode);
        //删除
        heros.del(3);
        //显示
        heros.list();
        System.out.println("有效的节点个数="+getLength(heros.getHead()));

        //测试看看是否得到倒数第k个节点
        //HeroNode res=findLastIndexNode(heros.getHead(),3);
        //System.out.println(res);

        //反转单链表
        System.out.println("反转单链表");
        reverseList(heros.getHead());
        heros.list();

        //逆序打印单链表
        System.out.println("逆序打印");
        reversePrint(heros.getHead());
    }
    /**
     *利用栈实现逆序打印
     */
    public static void reversePrint(HeroNode head){
        if(head.next==null){
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur=head.next;
        while(cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }



    /**
     * 将单链表反转
     */
    public static void reverseList(HeroNode head){
        //如果当前链表为空 或者只有一个节点 无需反转直接返回
        if(head.next==null||head.next.next==null){
            return;
        }
        //定义辅助变量 帮助遍历原来的链表
        HeroNode cur=head.next;
        HeroNode nn=null;//指向当前节点（cur）的下一个节点
        HeroNode reverseHead=new HeroNode(0,"","");
        //遍历原来的链表 每遍历一个节点 就将其取出
        //并放在新链表reverseHead的最前端
        while(cur!=null){
            nn=cur.next;//先暂时保存当前节点的下一个节点 因为后面需要使用
            cur.next=reverseHead.next;
            reverseHead.next=cur;
            cur=nn;
        }
        head.next=reverseHead.next;
    }
    /**
     * 查找单链表的倒数第k个节点【新浪】
     * 1.编写一个方法 接收head节点，同时接受一个index
     * 2.index表示是倒数第index个节点
     * 3.先把链表从头到尾遍历 得到链表总长度
     * 4.得到size后，从链表第一个开始遍历（size-index）个 就可以得到
     * 5.找到了就返回该节点 否则返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //判断链表空 返回null
        if(head.next==null){
            return null;
        }
        //第一次得到链表长度
        int size=getLength(head);
        //第二次遍历size-index位置 就是倒数第K个节点
        //先做一个index的校验
        if(index<=0||index>size){
            return null;
        }
        //for循环定位
        HeroNode cur=head.next;
        for(int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;
    }


    /**
     * 获取到单链表的节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next==null){
            return 0;
        }
        int length=0;
        HeroNode cur=head.next;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }

}
//定义SingleLinkedList 管理英雄
class SingleLinkedList{
    //先初始化一个头节点 不动  不存放具体数据
    private HeroNode head=new HeroNode(0,"","");
    //添加节点到单向链表
    //因为head节点不能动 因此我们需要一个辅助变量temp

    //返回头节点
    public HeroNode getHead() {
        return head;
    }
    public void add(HeroNode heroNode){
    //不考虑编号顺序的时候
    //思路：1.找到当前链表最后的节点
        HeroNode temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
    //2.将最后这个节点的next指向新的节点
    //当退出while循环时候 temp就指向了链表最后
        temp.next=heroNode;
    }

    /**
     * 第二种方法 在添加英雄时，根据排名将英雄插入到指定位置
     *
     */
    public void addByOrder(HeroNode heroNode){
        //头节点不能动 因此仍然通过辅助指针找到添加位置
        //因为单链表 因此找到的temp是位于添加元素的前一个节点,否则插入不了
        HeroNode temp=head;
        boolean flag=false;//标志添加
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>heroNode.no){
                break;
            }else if(temp.next.no==heroNode.no){
                //要插入标号已经存在
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            System.out.println("准备插入的英雄编号"+heroNode.no+"已经存在，不能添加");
        }else{
            //插入链表中
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    /**
     * 修改节点信息 根据no编号来修改 no编号不能改变
     * 根据newHeroNode的no来修改
     */
    public void update(HeroNode newHeroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点 根据no编号
        HeroNode temp=head.next;
        boolean flag=false;
        while(true){
            if(temp==null){
                break;
            }
            if(temp.no==newHeroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else{
            System.out.println("没有该节点");
        }
    }

    /**
     * 删除节点
     */
    //head不能动 因此需要temp找到代删除节点的前一个节点
    //比较时候 是用temp.next.no和需要删除的节点的no比较
    public void del(int no){
        HeroNode temp=head;
        boolean flag=false;//标志是否找到代删除节点
        while(true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }else{
            System.out.println("没有需要删除的节点"+no);
        }
    }



    //显示链表【遍历】
    public void list(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        while(temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }
}
//定义HeroNode 每个HeroNode对象就是一个节点
class HeroNode{
//序号 名字 外号   下一个节点
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    //构造器
    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }
    //为了显示方便 重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}