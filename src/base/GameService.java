package base;

import game.Map;
import game.Village;

/**
 * Игровая механика и карта
 *
 * Created by nmikutskiy on 18.10.16.
 */
public interface GameService {
    public Map createCleanMap();
    public Map getGameMap();
    public Village createVillage(Village village);
}
