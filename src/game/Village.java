package game;

import java.awt.*;

/**
 * Created by nmikutskiy on 02.10.16.
 */
public class Village {
    private int x;
    private int y;
    private String ownerUser;
    private Image villageImageInTheMap;
    private Image villageImageInside;

    private Village(){
//        throw new Exception();
    }

    public Village(int x, int y, String ownerUser){
        this.x = x;
        this.y = y;
        this.ownerUser = ownerUser;
    }
}
