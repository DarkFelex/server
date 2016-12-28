package game.buildings;

import static game.buildings.BuildType.INDUSTRY;

/**
 * Created by nmikutskiy on 28.12.16.
 */
public class WoodFactory extends Build {
    public WoodFactory(){
        setBuildName("Wood Factory");
        setBuildType(INDUSTRY);
        setBuildLevel(0);
        setGoldToBuild(30);
        setHp(70);
        setImgUrl("images/building/woodfactory.img");
    }
    @Override
    public boolean startBuild() {
        if (getBuildLevel() != 0) return false;
        setBuildLevel(1);
        return true;
    }

    @Override
    public boolean upgradeBuilding() {
        setBuildLevel(getBuildLevel() + 1);
        setGoldToBuild(getGoldToBuild() * getBuildLevel());
        return true;
    }
}
