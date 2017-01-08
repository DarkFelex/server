package game.buildings;

import static game.buildings.BuildType.INDUSTRY;

/**
 * Created by nmikutskiy on 24.12.16.
 */
public class Farm extends Build {
    public Farm(){
        setBuildName("Farm");
        setBuildType(INDUSTRY);
        setBuildLevel(0);
        setGoldToBuild(5);
        setHp(150);
        setImgUrl("images/building/farm.img");
    }
    @Override
    public boolean finishBuild() {
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
