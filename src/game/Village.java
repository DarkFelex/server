package game;

import com.google.gson.Gson;

import java.awt.*;

/**
 * Created by nmikutskiy on 02.10.16.
 */
public class Village {
    private int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int y;
    private String ownerUser;

    public String getOwnerUser() {
        return ownerUser;
    }

    public String getVillageName() {
        return villageName;
    }

    private String villageName;
    private Image villageImageInTheMap;
    private Image villageImageInside;

    private Village(){
//        throw new Exception();
    }

    public Village(int x, int y, String ownerUser, String villageName){
        this.x = x;
        this.y = y;
        this.ownerUser = ownerUser;
        this.villageName = villageName;
    }

    @Override
    public String toString() {
        return String.format("Village: %s, on: %1$02d|%2$02d, owner: %s", villageName, x, y, ownerUser);
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
