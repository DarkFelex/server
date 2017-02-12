package game;

import base.GameService;
import game.buildings.Barracks;
import game.buildings.Build;
import game.buildings.Farm;
import game.buildings.Palace;
import game.buildings.Warehouse;
import game.buildings.WoodFactory;
import game.units.Spearman;
import game.units.UnitType;
import game.units.Units;
import timeMachine.EachSecondTimeListener;
import timeMachine.TimeMachine;

import java.util.Map;

import static game.units.UnitType.SPEARMAN;
import static java.lang.String.format;

/**
 * Created by nmikutskiy on 18.10.16.
 */
public class GameServiceImpl implements GameService{
    GameMap gameMap;
    TimeMachine timeMachine;

    public void setTimeMachineInstandeLink(TimeMachine tm){
        timeMachine = tm;
    }

    @Override
    public GameMap createCleanMap(String name, int sizeX, int sizeY) {
        gameMap = new GameMap(name, sizeX,sizeY);
        return gameMap;
    }

    @Override
    public GameMap getGameMap() {
        return gameMap;
    }

    @Override
    public Village createVillage(int x, int y, String ownerUser, String villageName) {
        return gameMap.getPlace(x, y)
                .addVillageOnPlace(villageName, ownerUser)
                .getVillage();
    }

    @Override
    public Map<Integer, Build> getVillageBuildingsArea(int x, int y) {
        return gameMap.getPlace(x, y).getVillage().getAreaForBuildings();
    }

    @Override
    public void buildPalace(int x, int y, int areaNumber) {
        gameMap.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new Palace());
    }

    @Override
    public void buildWarehouse(int x, int y, int areaNumber) {
        gameMap.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new Warehouse());
    }

    @Override
    public void buildFarm(int x, int y, int areaNumber) {
        gameMap.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new Farm());
    }

    @Override
    public void buildBarracks(int x, int y, int areaNumber) {
        gameMap.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new Barracks());
        System.out.println("Barrack 0 level is built");

        long timeToFinish = timeMachine.getCurrentGameTime() + 100; //Barrack строится 100 секунд
        timeMachine.addEachSecondListener(new EachSecondTimeListener() {
            boolean isFinishedTask = false;
            @Override
            public void newTick(long currentGameTime) {
                if (currentGameTime == timeToFinish && !isFinishedTask){
                    upgradeBuilding(x, y, areaNumber);
                    System.out.println("Barrack building is finished! Lv = 1");
                } else {
                    if (currentGameTime > timeToFinish && !isFinishedTask) {
                        System.out.println("Listener is need to be deleted");
                        makeTaskFinished();
                        timeMachine.cleanFinishedTasks();
                    } else System.out.println("Not yet");
                }
            }

            @Override
            public void makeTaskFinished() {
                isFinishedTask = true;
            }

            @Override
            public boolean isTaskFinished() {
                return isFinishedTask;
            }
        });
    }

    @Override
    public void buildWoodFactory(int x, int y, int areaNumber) {
        //задержка на постройку не нужна: сначала построить здание 0-го уровня, потом поднять до 1
        gameMap.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new WoodFactory(gameMap.getPlace(x, y).getVillage()));
        System.out.println("Wood factory 0 level is built");

        long timeToFinish = timeMachine.getCurrentGameTime() + 10; //WoodFactory строится 10 секунд
        timeMachine.addEachSecondListener(new EachSecondTimeListener() {
            boolean isFinishedTask = false;
            @Override
            public void newTick(long currentGameTime) {
                if (currentGameTime == timeToFinish && !isFinishedTask){
                    upgradeBuilding(x, y, areaNumber);
                    System.out.println("Wood factory building is finished! Lv = 1");
                    timeMachine.addEachSecondListener(new EachSecondTimeListener() {
                        @Override
                        public void newTick(long currentGameTime) {
                            gameMap.getPlace(x, y).getVillage().getAreaForBuildings().get(areaNumber).
                                    makeResources(currentGameTime, gameMap.getPlace(x, y).getVillage());
                        }

                        @Override
                        public void makeTaskFinished() {

                        }

                        @Override
                        public boolean isTaskFinished() {
                            return false;
                        }
                    });
                } else {
                    if (currentGameTime > timeToFinish && !isFinishedTask) {
                        System.out.println("Listener is need to be deleted");
                        makeTaskFinished();
                        timeMachine.cleanFinishedTasks();
                    } else System.out.println("Not yet");
                }
            }

            @Override
            public void makeTaskFinished() {
                isFinishedTask = true;
            }

            @Override
            public boolean isTaskFinished() {
                return isFinishedTask;
            }
        });
    }

    @Override
    public void upgradeBuilding(int x, int y, int areaNumber) {
        gameMap.getPlace(x, y).getVillage().getAreaForBuildings().get(areaNumber).upgradeBuilding();
    }

    @Override
    public void delBuildOnAreaForBuildings(int x, int y, int areaNumber) {
        gameMap.getPlace(x, y).getVillage().getAreaForBuildings().remove(areaNumber);
    }

    @Override
    public long getCurrentServerTimeInSeconds() {
        return timeMachine.getCurrentGameTime();
    }

    @Override
    public void createSpearman(int x, int y, int amount, int barracsCordsArea, String userOwner) {
        System.out.println("Create spearmans...");

        long timeToFinish = timeMachine.getCurrentGameTime() + 30; //1 spearman строится 30 секунд
        //todo: сделать очередь в которую помещать таски на создание юнитов
        timeMachine.addEachSecondListener(new EachSecondTimeListener() {
            boolean isFinishedTask = false;
            @Override
            public void newTick(long currentGameTime) {
                if (currentGameTime == timeToFinish && !isFinishedTask){
                    System.out.println(userOwner + "Start to create slpearman " + amount);
                    timeMachine.addEachSecondListener(new EachSecondTimeListener() {
                        @Override
                        public void newTick(long currentGameTime) {
                            String buildName = gameMap.getPlace(x, y).getVillage().getAreaForBuildings().get(barracsCordsArea).getBuildName();
                            if (buildName == "Barracks"){//todo: имя здания вынести в список
                                Barracks barraks = (Barracks) gameMap.getPlace(x, y).getVillage().getAreaForBuildings().get(barracsCordsArea);
                                barraks.createSpearman(gameMap.getPlace(x, y).getVillage(), amount);
                            } else System.out.println("Попытка создать spearman не здании barraks");
                        }

                        @Override
                        public void makeTaskFinished() {

                        }

                        @Override
                        public boolean isTaskFinished() {
                            return false;
                        }
                    });
                } else {
                    if (currentGameTime > timeToFinish && !isFinishedTask) {
                        System.out.println("Listener is need to be deleted");
                        makeTaskFinished();
                        timeMachine.cleanFinishedTasks();
                    } else System.out.println("Not yet");
                }
            }

            @Override
            public void makeTaskFinished() {
                isFinishedTask = true;
            }

            @Override
            public boolean isTaskFinished() {
                return isFinishedTask;
            }
        });

        gameMap.getPlace(x, y).getVillage().getUnits().put(SPEARMAN, new Spearman(userOwner, format("%d-%d",x,y)));
    }

    @Override
    public Place getRegionOnTheMap(int x, int y) {
        Place place = gameMap.getPlace(x, y);
        System.out.println("Get place: " + x + "-" + y);
        if (place == null) return null;
        return place;
    }

    @Override
    public Place setPlace(Place place) {
        if (gameMap.setPlace(place)) return place;
        return null;
    }


}
