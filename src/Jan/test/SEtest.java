package Jan.test;

import org.junit.Test;

public class SEtest {
    public void sample(boolean flag){
        if(flag){
            return;
        }
        System.out.println("sssss");
    }
    @Test
    public void test1(){
        sample(false);
    }
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {

                    synchronized (obj) {
                        try {

                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName());
                        obj.notify();
                    }

            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                    synchronized (obj) {
                        try {

                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName());
                        obj.notify();
                    }

            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                    synchronized (obj) {
                        System.out.println(Thread.currentThread().getName());
                        obj.notify();
                    }

            }
        },"C").start();
    }
}
