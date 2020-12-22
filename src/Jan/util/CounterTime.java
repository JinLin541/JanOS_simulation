package Jan.util;

public class CounterTime {
    long second;
    long minute;
    long trueSecond;
    public CounterTime(long millSecond){
        second = millSecond/1000;
        minute = second/60;
        if(millSecond != 0){
            trueSecond = second - minute*60;
        }
    }

    @Override
    public String toString() {
        if(minute != 0){
            return String.valueOf(minute)+" 分"+" "+trueSecond+" 秒";
        }else{
            return String.valueOf(second)+" 秒";
        }
    }

    public static void main(String[] args) {
        CounterTime time = new CounterTime(89323);
        System.out.println(time);
    }
}
