package timeMachine;

import java.util.TimerTask;

/**
 * Created by nmikutskiy on 05.01.17.
 */
public interface TimeMachine {
    int addEachSecondListener(EachSecondTimeListener toAdd);
    void delFromEachSecondListener(Object objToDel);
    void cleanFinishedTasks();

    void startTime();
    void stopTime();
    boolean getState();
    long getCurrentGameTime();
    void newActionAfterTimer(TimerTask task);
}
