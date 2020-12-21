package Jan.test;


import Jan.core.Attemper;
import Jan.core.Core;
import org.junit.Test;
import sun.awt.windows.WPrinterJob;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    @Test
    public static void test3(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
    }
    @Test
    public void test2(){
//        Attemper.putoIntoJobList(12);
//        Attemper.putoIntoJobList(13);
//        Attemper.putoIntoJobList(14);
//        Core.setPCB_Number(4);
//        Core.priorityAttemper();
    }
    @Test
    public void test1(){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try{
                        System.out.println(Thread.currentThread().getName());
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try{
                        System.out.println(Thread.currentThread().getName());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    public void attempermoni(){
        Attemper.putoIntoJobList(11,"A");
        Attemper.putoIntoJobList(11,"B");
        Attemper.putoIntoJobList(19,"C");
        Core.setPCB_Number(10);
        Core.priorityAttemper();
        boolean flag = Core.killThread(1);
        Core.killThread(2);
    }

    public static void main(String[] args) {
        test3();
    }
}
