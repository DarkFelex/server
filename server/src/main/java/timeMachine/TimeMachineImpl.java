package timeMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by nmikutskiy on 05.01.17.
 */
public class TimeMachineImpl implements TimeMachine {
    private long currentGameTime;
    private boolean isActiveTime;
    private Timer timer = new Timer();

    //TODO: Сделать singletone
    public TimeMachine getInstance(){
        return this;
    }

    public TimeMachineImpl(){
        currentGameTime = 0; //TODO: получать сохранённое значение из бд, чтобы при рестартах время не сбивалось в ноль
    }

    public TimeMachineImpl(long startWithTime){
        currentGameTime = startWithTime;
    }

    private List<EachSecondTimeListener> listeners = new CopyOnWriteArrayList<>();

    public int addEachSecondListener(EachSecondTimeListener toAdd){
        listeners.add(toAdd);
        return listeners.indexOf(toAdd);
    }

    @Override
    public void delFromEachSecondListener(Object objToDel) {
        listeners.remove(objToDel);
    }

    /**
     * Ищем все лисенеры, у который статус finished и удаляем их
     */
    @Override
    public void cleanFinishedTasks() {
        //TODO: Удалять из подписок выполненные таски
        listeners.stream().filter(t -> t.isTaskFinished() == true).forEach(t -> delFromEachSecondListener(t));
        //Этот падает с Exception in thread "Timer-0" java.util.ConcurrentModificationException
    }


    public void startTime(){
        timer.schedule(new NewTickTask(), 5_000, 1_000);
        System.out.println("Delay to start game time is 5 sec");
        isActiveTime = true;
    }

    public void stopTime(){
        //TODO: останавливать игровае время с возможностью продолжения
        isActiveTime = false;
    }

    @Override
    public boolean getState() {
        return isActiveTime;
    }

    public long getCurrentGameTime() {
        return currentGameTime;
    }

    /**
     * Метод с таймером для построек зданий и создания юнитов
     */
    @Override
    public void newActionAfterTimer(TimerTask task) {
        task.run();
    }

    public void makeNewTick(long currentGameTime){
        for (EachSecondTimeListener listener: listeners) {
            listener.newTick(currentGameTime);
        }
//        System.out.printf("Time: new tick = %d seconds\n", currentGameTime);
    }

    /**
     * Каждый тик игровго времени
     */
    class NewTickTask extends TimerTask{

        @Override
        public void run() {
            currentGameTime += 1;
            makeNewTick(currentGameTime);
        }
    }

    /**
     * Таймеры для построек зданий и создания юнитов
     */
//    class ActionTask extends TimerTask {
//
//        @Override
//        public void run() {
//
//        }
//    }
}
