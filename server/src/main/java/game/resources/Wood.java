package game.resources;

import static game.resources.Resources.WOOD;

/**
 * Created by nmikutskiy on 27.12.16.
 */
public class Wood extends Resource {
    public Wood(){
        setName(WOOD);
        setImgUrl("images/resources/wood.img");
    }

    public Wood(int value){
        setValue(value);
    }
}
