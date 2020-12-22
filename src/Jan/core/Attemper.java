package Jan.core;

import java.util.ArrayList;
import java.util.List;

public class Attemper {
    //作业队列
    private static List<PriorityRunnable> jobList;
    private static int threadId;
    static{
        jobList = new ArrayList<>();
        threadId = 1;
    }
    //shell层指定Priority
    //把作业都put到作业列表里面
    public static void putoIntoJobList(int priority,String name){
        int id = threadId;
        DeadPaper deadPaper = new DeadPaper(false,id);
        PriorityRunnable pr = new PriorityRunnable(priority,deadPaper,name);
        jobList.add(pr);
        threadId++;
    }

    public static List<PriorityRunnable> getJobList(){
        return jobList;
    }
    public static void clearJobList(){
        jobList.clear();
    }
    public static void displayJobs(){
        for(PriorityRunnable x : jobList){
            System.out.println(x);
        }
    }
}
