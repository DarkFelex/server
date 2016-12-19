package game;

import base.GameService;

/**
 * Created by nmikutskiy on 18.10.16.
 */
public class GameServiceImpl implements GameService{
    Map map = new Map("FirstMap", 30, 15);

    @Override
    public Map createCleanMap() {
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


}
