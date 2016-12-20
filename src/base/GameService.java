package base;

import game.Map;
import game.Place;
import game.Village;

/**
 * Игровая механика и карта
 *
 * Created by nmikutskiy on 18.10.16.
 */
public interface GameService {
    public Map createCleanMap(String name, int sizeX, int sizeY);
    public Map getGameMap();
    public Village createVillage(Village village);

    public Place getRegionOnTheMap(int x, int y);
    public Place setPlace(Place place);
}
