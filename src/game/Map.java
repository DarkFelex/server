package game;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.String.format;

/**
 * Created by nmikutskiy on 02.10.16.
 */
public class Map {
    private int sizeX;
    private int sizeY;
    private String mapName;
    private HashMap<String, Place> map = new HashMap<String, Place>(sizeX * sizeY);

    private Map(){

    }
    public Map(String mapName, int sizeX, int sizeY) {
        if (mapName != null && mapName.trim() != "" && sizeX > 0 && sizeY > 0) {
            this.mapName = mapName;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            System.out.println(format("Map %s (%d/%d) is created successfully!", mapName, sizeX, sizeY));
        }else System.out.println(format("ERROR! Map %s (%d/%d) is not created!", mapName, sizeX, sizeY));
    }

    public boolean setPlace(Place place){
        if (place.getX() > 0 && place.getX() <= sizeX
                && place.getY() > 0 && place.getY() <= sizeY){
            map.put(format("%d-%d", place.getX(), place.getY()), place);
            return true;
        }
        return false;
    }

    public Place getPlace(int x, int y){
        return map.get(format("%d-%d", x, y));
    }

}
