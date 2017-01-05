package timeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nmikutskiy on 05.01.17.
 */
public class TimeMachineImpl implements TimeMachine {
    private long currentGameTime;
    private boolean isActiveTime;
    private Timer timer = new Timer();


    public TimeMachineImpl(){
        currentGameTime = 0; //TODO: получать сохранённое значение из бд, чтобы при рестартах время не сбивалось в ноль
    }

    public TimeMachineImpl(long startWithTime){
        currentGameTime = startWithTime;
    }

    private List<TimeListener> listeners = new ArrayList<TimeListener>();

    public void addListener(TimeListener toAdd){
        listeners.add(toAdd);
    }

    public void makeNewTick(long currentGameTime){
        for (TimeListener listener: listeners) {
            listener.newTick(currentGameTime);
        }
        System.out.printf("Time: new tick = %d seconds\n", currentGameTime);
    }

    public void startTime(){
        timer.schedule(new NewTickTask(), 10_000, 1_000);
        isActiveTime = true;
    }

    public void stopTime(){

        isActiveTime = false;
    }

    @Override
    public boolean getState() {
        return isActiveTime;
    }

    public long getCurrentGameTime() {
        return currentGameTime;
    }

    class NewTickTask extends java.util.TimerTask{

        @Override
        public void run() {
            currentGameTime += 1;
            makeNewTick(currentGameTime);
        }
    }

    class ActionTask extends java.util.TimerTask {

        @Override
        public void run() {

        }
    }
}
