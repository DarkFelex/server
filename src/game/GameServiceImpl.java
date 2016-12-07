package game;

import base.GameService;
import game.Map;

/**
 * Created by nmikutskiy on 18.10.16.
 */
public class GameServiceImpl implements GameService{
    Map map = new Map();

    @Override
    public Map createMapInDB() {
        return map.saveMapInDB();
    }

}
