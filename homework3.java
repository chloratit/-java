public class homework3 {
    public static void main(String[] args) {
        MyTime t1 = new MyTime(3,4,5);
        t1.display();
        t1.addSecond(59);
        t1.display();

    }

}
class MyTime{
    private int hour;
    private int minute;
    private int second;

    public MyTime() {
    }

    public MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public  void display(){
        System.out.println(this.getHour()+"时"+this.getMinute()+"分"+this.second+"秒");
    }
    public void addSecond(int sec){
        //在原来秒的基础上再加秒
        int old=this.getSecond();
        int news=old+sec;
        if(news<60){
            this.setSecond(news);
        }else if(news==60){
            this.addMinute(1);
            this.setSecond(0);

        }else{
            int newM=news/60;
            this.addMinute(newM);
            this.addSecond(news%60);
        }

    }
    public void addMinute(int min){
        int oldm=this.getMinute();
        int newm=oldm+min;
        this.setMinute(newm);

    }
    public void Hour(int hour){

    }
}
