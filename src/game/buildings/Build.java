package game.buildings;

/**
 * Created by nmikutskiy on 02.10.16.
 */
public abstract class Build {
    private String buildName;
    private BuildType buildType;
    private String imgUrl;
    private int hp;
    private int buildLevel;
    private int goldToBuild;

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public BuildType getBuildType() {
        return buildType;
    }

    public void setBuildType(BuildType buildType) {
        this.buildType = buildType;
    }

    public int getBuildLevel() {
        return buildLevel;
    }

    public void setBuildLevel(int buildLevel) {
        this.buildLevel = buildLevel;
    }

    public int getGoldToBuild() {
        return goldToBuild;
    }

    public void setGoldToBuild(int goldToBuild) {
        this.goldToBuild = goldToBuild;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public abstract boolean startBuild();
    public abstract boolean upgradeBuilding();

}
