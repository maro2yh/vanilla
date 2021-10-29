package vanilla.stocks.scheduler.task;

public interface IVanillaSchedule {

    public void start(int time);
    
    public void start(String cron);
    
    public void stop();
    
    public boolean isRunning();
    
    public void run();
}
