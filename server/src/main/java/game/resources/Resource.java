package game.resources;

/**
 * Created by nmikutskiy on 27.12.16.
 */
public abstract class Resource {
    private Resources name;
    private int value = 0;
    private String imgUrl;

    public Resources getName() {
        return name;
    }

    public void setName(Resources name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void add(int value){
        this.value += value;
    }

    public void subtract(int value){
        this.value -= value;
    }
}
