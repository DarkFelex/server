package game.units;

/**
 * Created by nmikutskiy on 30.12.16.
 */
public class Spearman extends Unit {
    public Spearman(){
        setName("Spearman");

        setImgUrl("images/spearman.jpg");
        setHP(50);
        setAttack(20);
        setDefence(5);
    }

    public Spearman(String userOwner, String cordCurrentLocation){
        setUserOwner(userOwner);
        setCordCurrentLocation(cordCurrentLocation);
    }

    @Override
    public void attack(Object target) {

    }

    @Override
    public void move() {

    }
}
