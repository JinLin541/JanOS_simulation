package Jan.core;

public class PriorityRunnable implements Runnable{
    //按优先级调度的Runnable类
    private int priority;
    private DeadPaper deadPaper;
    private String name;
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
        //让这个类一直运行，直到遇到kill方法
        do {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!deadPaper.getKillCommit());
    }

    @Override
    public String toString() {
        return "name"+": "+name+"  "+"UID: "+ deadPaper.getId();
    }
}