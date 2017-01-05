package timeMachine;

/**
 * Created by nmikutskiy on 05.01.17.
 */
public interface TimeMachine {
    public void addListener(TimeListener toAdd);
    public void startTime();
    public void stopTime();
    public boolean getState();
    public long getCurrentGameTime();
}
