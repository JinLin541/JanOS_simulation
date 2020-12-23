package Jan.core;

public class DeadPaper {
    private boolean killCommit;
    private int id;
    private long birthTime;
    public DeadPaper(boolean killCommit,int id){
        this.killCommit = killCommit;
        this.id = id;
        birthTime = System.currentTimeMillis();
    }
    public void setBirthTime(long time){
        birthTime = time;
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

    public long getLifeTime() {
        return System.currentTimeMillis()-birthTime;
    }
}
