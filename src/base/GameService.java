package base;

import game.Map;
import game.Place;
import game.Village;
import game.buildings.Build;

import java.util.HashMap;

/**
 * Игровая механика и карта
 *
 * Created by nmikutskiy on 18.10.16.
 */
public interface GameService {
    public Map createCleanMap(String name, int sizeX, int sizeY);
    public Map getGameMap();

    public Place getRegionOnTheMap(int x, int y);
    public Place setPlace(Place place);
    public Village createVillage(int x, int y, String ownerUser, String villageName);
    public HashMap<Integer, Build> getVillageBuildingsArea(int x, int y);
    public void buildPalace(int x, int y, int areaNumber);
    public void buildWarehouse(int x, int y, int areaNumber);
    public void buildFarm(int x, int y, int areaNumber);
    public void buildBarracks(int x, int y, int areaNumber);
    public void upgradeBuilding(int x, int y, int areaNumber);

}
