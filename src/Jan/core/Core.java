package Jan.core;

import Jan.shell.Shell;
import com.sun.xml.internal.bind.v2.runtime.output.Pcdata;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 内核用来创建进程，给shell层抛出接口
 */
public class Core {
    private static ExecutorService executorService;
    private static int PCB_Number;
    private static List<PriorityRunnable> joblist;
    private static LinkedList<PriorityRunnable> waitJob;
    private static List<PriorityRunnable> submitJoblist;
    static{
        submitJoblist = new ArrayList<>();
        waitJob = new LinkedList<>();
    }
    public static List<PriorityRunnable> getSubmitJoblist(){
        return submitJoblist;
    }
    public static void setPCB_Number(int PCB_Number){
        Core.PCB_Number = PCB_Number;
        executorService = Executors.newFixedThreadPool(PCB_Number);
    }

    public static void priorityAttemper(){
        //获取作业队列
        joblist = Attemper.getJobList();
        joblist.sort(new Comparator<PriorityRunnable>() {
            @Override
            public int compare(PriorityRunnable o1, PriorityRunnable o2) {
                return Integer.compare(o1.getPriority(), o2.getPriority());
            }
        });

        if(submitJoblist.size() == PCB_Number){
            //全部放到等待列表里面去
            waitJob.addAll(joblist);
        }else if(joblist.size() > PCB_Number){
            for(int i = 0;i <PCB_Number;i++){
                PriorityRunnable pr = joblist.get(i);
                Thread thread = new Thread(pr,pr.getName());
                thread.setPriority(1);
                executorService.submit(thread);
                submitJoblist.add(pr);
            }
            //超过pcb的个数之后的Runnable先加到等待列表里面
            for(int i = PCB_Number;i < joblist.size();i++){
                waitJob.add(joblist.get(i));
            }
        }else{
            //没有超过pcb，就全部加进去
            for(PriorityRunnable x : joblist){
                Thread thread = new Thread(x,x.getName());
                thread.setPriority(1);
                //提交给线程池
                executorService.submit(thread);
                submitJoblist.add(x);
            }
        }
        Attemper.clearJobList();
    }
    public static void displayProcess(){
        for(PriorityRunnable x : submitJoblist){
            System.out.println(x);
        }
    }
    public static void displayWait(){
        for(PriorityRunnable x : waitJob){
            System.out.println(x);
        }
    }

    public static boolean killThread(int id){
        boolean flag = false;
        try {
            for(PriorityRunnable x : submitJoblist){
                if(id == x.getDeadId()){
                    DeadPaper deadPaper = x.getDeadPaper();
                    deadPaper.setKillCommit(true);
                    System.out.println(x.getName()+"  killed Successfully");
                    submitJoblist.remove(x);
                    flag = true;
                    if(waitJob.size() != 0){
                        //唤醒等待队列的一个作业，调用到进程池里面
                        PriorityRunnable pr = waitJob.removeFirst();
                        Thread thread = new Thread(pr,pr.getName());
                        thread.setPriority(1);
                        executorService.submit(new Thread(pr,pr.getName()));
                        submitJoblist.add(pr);
                    }
                }
            }
        } catch (Exception e) {

        }
        return flag;
    }

    public static void main(String[] args) {
//        System.out.println("Please input the max PCB number:");
//        Scanner scanner_PCB = new Scanner(System.in);
//        int number = Integer.parseInt(scanner_PCB.nextLine());
        Core.setPCB_Number(1);
        Thread main = Thread.currentThread();
        main.setPriority(10);
        System.out.println("成功进入JanOS");
        System.out.println("Welcome to JanOS"+"    Author:"+"JanLen Huang");
        boolean quit = false;
        Scanner scanner;
        while(!quit){
            System.out.print("[JanOS@root]# ");
            scanner = new Scanner(System.in);
            String commit = scanner.nextLine();
            Shell.dosCommand(commit);
        }
    }
}
