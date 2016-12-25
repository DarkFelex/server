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
    private final int regionId;
    private boolean isPlaceForVillage;
    private String placeImageUrl;
    private Village village;
    private Object[] resources;
    private Object[] units;

    private Place() {
        x = -1;
        y = -1;
        regionId = -1;
    }

    public Place(int x, int y, int regionId) {
        this.x = x;
        this.y = y;
        this.regionId = regionId;
        this.setPlaceImageUrl(String.format("images/map/region/%d/%d-%d.img", 1, x, y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPlaceImageUrl() {
        return placeImageUrl;
    }

    public void setPlaceImageUrl(String placeImageUrl) {
        this.placeImageUrl = placeImageUrl;
    }

    public Place addVillageOnPlace(String villageName, String user) {
        if (isPlaceForVillage){
            //Проверки на возможность строить делаются в сервлете
            this.village = new Village(this.x, this.y, user, villageName);
            System.out.println("Create village: " + this.village.toString());
        } else System.out.println(String.format("Impossible to create new village on: %d-%d", this.getX(), this.getY()));
        return this;
    }

    @Override
    public String toString() {
        return String.format("regionId=%d %d-%d", regionId, x, y);
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Place enableVillageBuildingOnPlace(){
        this.isPlaceForVillage = true;
        return this;
    }

    public Village getVillage(){
        return this.village;
    }
}
