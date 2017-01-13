package game.resources;

import static game.resources.Resources.FOOD;

/**
 * Created by nmikutskiy on 28.12.16.
 */
public class Food extends Resource {
    public Food(){
        setName(FOOD);
        setImgUrl("images/resources/food.img");
    }

    public Food(int value){
        setValue(value);
    }
}
