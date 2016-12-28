package game.resources;

/**
 * Created by nmikutskiy on 28.12.16.
 */
public class Food extends Resource {
    public Food(){
        setName("Food");
        setImgUrl("images/resources/food.img");
    }

    public Food(int value){
        setValue(value);
    }
}
