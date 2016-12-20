package game;

import base.GameService;

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
    public Village createVillage(Village village) {
        return map.getPlace(village.getX(), village.getY())
                .addVillageOnPlace(village.getVillageName(), village.getOwnerUser())
                .getVillage();
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
