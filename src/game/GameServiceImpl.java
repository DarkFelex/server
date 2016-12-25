package game;

import base.GameService;
import game.buildings.Barracks;
import game.buildings.Build;
import game.buildings.Farm;
import game.buildings.Palace;
import game.buildings.Warehouse;

import java.util.HashMap;

/**
 * Created by nmikutskiy on 18.10.16.
 */
public class GameServiceImpl implements GameService{
    Map map;

    @Override
    public Map createCleanMap(String name, int sizeX, int sizeY) {
        map = new Map(name, sizeX,sizeY);
        return map;
    }

    @Override
    public Map getGameMap() {
        return map;
    }

    @Override
    public Village createVillage(int x, int y, String ownerUser, String villageName) {
        return map.getPlace(x, y)
                .addVillageOnPlace(villageName, ownerUser)
                .getVillage();
    }

    @Override
    public HashMap<Integer, Build> getVillageBuildingsArea(int x, int y) {
        return map.getPlace(x, y).getVillage().getAreaForBuildings();
    }

    @Override
    public void buildPalace(int x, int y, int areaNumber) {
        map.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new Palace());
    }

    @Override
    public void buildWarehouse(int x, int y, int areaNumber) {
        map.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new Warehouse());
    }

    @Override
    public void buildFarm(int x, int y, int areaNumber) {
        map.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new Farm());
    }

    @Override
    public void buildBarracks(int x, int y, int areaNumber) {
        map.getPlace(x, y).getVillage().setBuildOnAreaForBuildings(areaNumber, new Barracks());
    }

    @Override
    public void upgradeBuilding(int x, int y, int areaNumber) {
        map.getPlace(x, y).getVillage().getAreaForBuildings().get(areaNumber).upgradeBuilding();
    }

    @Override
    public Place getRegionOnTheMap(int x, int y) {
        Place place = map.getPlace(x, y);
        System.out.println("Get place: " + x + "-" + y);
        if (place == null) return null;
        return place;
    }

    @Override
    public Place setPlace(Place place) {
        if (map.setPlace(place)) return place;
        return null;
    }


}
