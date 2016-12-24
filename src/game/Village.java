package game;

import com.google.gson.Gson;
import game.buildings.Build;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by nmikutskiy on 02.10.16.
 */
public class Village {
    private int x;
    private int y;
    private String ownerUser;
    private String villageName;
    private String villageImageInTheMapUrl;
    private String villageImageInsideUrl;
    private int villageLevel = 0;
    private HashMap<Integer, Build> areaForBuildings = new HashMap<>(11);
    private Object[] units;

    private Village(){
//        throw new Exception();
    }

    public Village(int x, int y, String ownerUser, String villageName){
        this.x = x;
        this.y = y;
        this.ownerUser = ownerUser;
        this.villageName = villageName;
        this.villageImageInTheMapUrl = "images/village.png";
        this.villageImageInsideUrl = "images/city.jpg";
    }

    @Override
    public String toString() {
        return String.format("Village: %s, on: %d-%d, owner: %s", villageName, x, y, ownerUser);
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOwnerUser() {
        return ownerUser;
    }

    public String getVillageName() {
        return villageName;
    }

    public String getVillageImageInTheMapUrl() {
        return villageImageInTheMapUrl;
    }

    public void setVillageImageInTheMapUrl(String villageImageInTheMapUrl) {
        this.villageImageInTheMapUrl = villageImageInTheMapUrl;
    }

    public String getVillageImageInsideUrl() {
        return villageImageInsideUrl;
    }

    public void setVillageImageInsideUrl(String villageImageInsideUrl) {
        this.villageImageInsideUrl = villageImageInsideUrl;
    }

    public int getVillageLevel() {
        return villageLevel;
    }

    public void setVillageLevel(int villageLevel) {
        this.villageLevel = villageLevel;
    }

    /**
     * Чтобы получить все клетки со зданиями или без
     * @return
     */
    public HashMap<Integer, Build> getAreaForBuildings() {
        return areaForBuildings;
    }

    public void setBuildOnAreaForBuildings(Integer areaNumber, Build build) {
        if (this.areaForBuildings.get(areaNumber) != null) return;
        getAreaForBuildings().put(areaNumber, build);
    }
}
