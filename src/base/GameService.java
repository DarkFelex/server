package base;

import game.GameMap;
import game.Place;
import game.Village;
import game.buildings.Build;
import timeMachine.TimeMachine;

import java.util.Map;

/**
 * Игровая механика и карта
 * <p>
 * Created by nmikutskiy on 18.10.16.
 */
public interface GameService {
    GameMap createCleanMap(String name, int sizeX, int sizeY);

    GameMap getGameMap();

    void setTimeMachineInstandeLink(TimeMachine tm);

    Place getRegionOnTheMap(int x, int y);

    Place setPlace(Place place);

    Village createVillage(int x, int y, String ownerUser, String villageName);

    Map<Integer, Build> getVillageBuildingsArea(int x, int y);

    void buildPalace(int x, int y, int areaNumber);

    void buildWarehouse(int x, int y, int areaNumber);

    void buildFarm(int x, int y, int areaNumber);

    void buildBarracks(int x, int y, int areaNumber);

    void buildWoodFactory(int x, int y, int areaNumber);

    void upgradeBuilding(int x, int y, int areaNumber);

    void delBuildOnAreaForBuildings(int x, int y, int areaNumber);

    long getCurrentServerTimeInSeconds();


}
