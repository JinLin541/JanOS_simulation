package Jan.shell;

import Jan.core.Attemper;
import Jan.core.Core;
import Jan.core.PriorityRunnable;
import edu.princeton.cs.algs4.StdOut;

import java.util.List;
import java.util.Scanner;

public class Shell {
    public static void dosCommand(String command){
        String[] buff = command.split(" ");
        switch (buff[0]){
            case "createJob":
                if(buff.length == 1){
                    createJob();
                }else{
                    createJob(buff[1],Integer.parseInt(buff[2]));
                }
                break;

            case "submit":
                Core.priorityAttemper();
                break;
            case "kill":
                killThread();
                break;
            case "displayProcess":
                Core.displayProcess();
                break;
            case "displayWait":
                Core.displayWait();
                break;
            default:
                if(!buff[0].equals("")){
                    System.out.println(buff[0]);
                }

        }
    }
    private static void killThread(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("please input UID that want to kill:");
        String info = scanner.nextLine();
        int id = Integer.parseInt(info);
        Core.killThread(id);
    }

    private static void createJob(String name,int priority){
        Attemper.putoIntoJobList(priority,name);
    }
    private static void createJob(){
        System.out.print("Please input the Job name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("Please input the Job priority: ");
        int p = Integer.parseInt(scanner.nextLine());
        //把输入的参数传到Attemper创建作业
        Attemper.putoIntoJobList(p,name);
    }
    public static void main(String[] args) {
        dosCommand("createJob");
    }
}
