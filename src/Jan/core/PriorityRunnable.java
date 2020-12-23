package Jan.core;

import Jan.util.CounterTime;
import org.w3c.dom.css.Counter;

public class PriorityRunnable implements Runnable{
    //按优先级调度的Runnable类
    private static final Object obj;
    private int priority;
    private DeadPaper deadPaper;
    private String name;
    static{
        obj = new Object();
    }
    public PriorityRunnable(int priority,DeadPaper deadPaper,String name){
        this.priority = priority;
        this.deadPaper = deadPaper;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    //给Core的调度方法提供优先级参数
    public int getPriority(){
        return priority;
    }
    public int getDeadId(){
        return deadPaper.getId();
    }
    public DeadPaper getDeadPaper(){
        return deadPaper;
    }
    @Override
    public void run() {

        synchronized (obj){
            PriorityRunnable pr = Core.getSubmitJoblist().get(0);
            while(pr.getDeadId() != this.getDeadPaper().getId() || Core.getRunningStatus()){
                //如果该进程不是就绪队列里面优先级最高的，就不会进行执行
                try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                //更新最大优先级
                pr = Core.getSubmitJoblist().get(0);
            }

            Core.setRunningProcess(this);
            //把isRunning设置为true，表示有进程正在CPU里面运行
            Core.setRunningStatus(true);
            do {

            } while (!deadPaper.getKillCommit());
            //把change改为null
            Core.setRunningProcess(null);
            //把isRunning改为false
            Core.setRunningStatus(false);
            //该进程结束运行，通知下一个进程进来运行
            obj.notify();
        }
    }

    @Override
    public String toString() {
        CounterTime counterTime = new CounterTime(deadPaper.getLifeTime());
        return "name"+": "+name+"  "+"UID: "+ deadPaper.getId() +"  "+"run time: "+counterTime;
    }
}
