package game.units;

/**
 * Created by nmikutskiy on 02.10.16.
 */
public abstract class Units {
    String name;
    int amount;
    String imgUrl;
    int HP;
    int attack;
    int defence;
    int armor;
    String userOwner;
    String cordCurrentLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public String getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(String userOwner) {
        this.userOwner = userOwner;
    }

    public String getCordCurrentLocation() {
        return cordCurrentLocation;
    }

    public void setCordCurrentLocation(String cordCurrentLocation) {
        this.cordCurrentLocation = cordCurrentLocation;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public abstract void attack(Object target);
    public abstract void move();
}
