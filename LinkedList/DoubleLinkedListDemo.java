package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
    //先创建节点
        //进行测试
        //先创建节点
        HeroNode2 hero1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero4=new HeroNode2(4,"林冲","豹子头");
        HeroNode2 hero3=new HeroNode2(3,"吴用","智多星");
        //创建链表
        DoubleLinkedList heros=new DoubleLinkedList();
        //加入
        heros.addByOrder(hero1);
        heros.addByOrder(hero4);
        heros.addByOrder(hero3);
        heros.addByOrder(hero2);
        //显示
        heros.list();
    }
}
//创建链表
class DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"","");
    //添加方法1：在队尾添加新的节点
    public void add(HeroNode2 hero){
        HeroNode2 temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=hero;
        hero.pre=temp;
    }
    //添加方法2：在指定位置加入新的节点
    public void addByOrder(HeroNode2 heroNode2){
        HeroNode2 temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>heroNode2.no){

                break;
            }
            if(temp.next.no==heroNode2.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            System.out.println("无法加入");

        }else{
            heroNode2.next=temp.next;
            temp.next=heroNode2;
            temp.next.pre=heroNode2;
            heroNode2.pre=temp;
        }
    }

    //列出节点
    public void list(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        while(temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }
    //修改
    public void update(HeroNode2 newHero){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        boolean flag=false;
        while(true){
            if(temp==null){
                break;
            }
            if(temp.no==newHero.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name=newHero.name;
            temp.nickname=newHero.nickname;
        }else{
            System.out.println("没有找到要修改的节点"+newHero);
        }
    }
    //删除
    public void del(int no){
        HeroNode2 temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.pre.next=temp.next;
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        }else{
            System.out.println("没有找到要删除的节点"+no);
        }
    }

}

//定义HeroNode2节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;
    public HeroNode2(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }
    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
