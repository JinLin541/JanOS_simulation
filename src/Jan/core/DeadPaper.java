package Jan.core;

public class DeadPaper {
    private boolean killCommit;
    private int id;
    public DeadPaper(boolean killCommit,int id){
        this.killCommit = killCommit;
        this.id = id;
    }
    public void setKillCommit(boolean flag){
        this.killCommit = flag;
    }
    public boolean getKillCommit(){
        return killCommit;
    }
    public int getId() {
        return id;
    }
}
