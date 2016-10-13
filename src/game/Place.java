package game;

import com.google.gson.Gson;

import java.awt.*;

/**
 * Клетка на карте
 * <p>
 * Created by nmikutskiy on 04.10.16.
 */
public class Place {
    private final int x;
    private final int y;
    private boolean isPlaceForVillage;
    private Village village;
    private Object resources;
    private Object army;
    private Image placeImage;

    private Place() {
        x = -1;
        y = -1;
    }

    public Place(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Place addPlaceWithVillage(String villageName, String user) {
        if (isPlaceForVillage){
            this.village = new Village(this.x, this.y, user, villageName);
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("%1$02d|%2$02d", x, y);
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Place enableVillageBuildingOnPlace(){
        this.isPlaceForVillage = true;
        return this;
    }
}
