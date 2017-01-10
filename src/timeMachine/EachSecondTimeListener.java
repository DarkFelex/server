package timeMachine;

/**
 * Created by nmikutskiy on 05.01.17.
 */
public interface EachSecondTimeListener {
    void newTick(long currentGameTime);
    void makeTaskFinished();
    boolean isTaskFinished();
}
