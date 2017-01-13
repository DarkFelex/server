package game.buildings;

import static game.buildings.BuildType.INDUSTRY;

/**
 * Created by nmikutskiy on 24.12.16.
 */
public class Warehouse extends Build {
    public Warehouse(){
        setBuildName("Warehouse");
        setBuildType(INDUSTRY);
        setBuildLevel(0);
        setGoldToBuild(20);
        setHp(50);
        setImgUrl("images/building/warehouse.img");
    }

    @Override
    public void makeResources(long currentTime) {

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
