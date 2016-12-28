package game.resources;

/**
 * Created by nmikutskiy on 27.12.16.
 */
public class Wood extends Resource {
    public Wood(){
        setName("Wood");
        setImgUrl("images/resources/wood.img");
    }

    public Wood(int value){
        setValue(value);
    }
}
