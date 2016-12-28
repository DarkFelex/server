package game.buildings;

import static game.buildings.BuildType.MILITARY;

/**
 * Created by nmikutskiy on 24.12.16.
 */
public class Barracks extends Build {
    public Barracks(){
        setBuildName("Barracks");
        setBuildType(MILITARY);
        setBuildLevel(0);
        setGoldToBuild(350);
        setHp(300);
        setImgUrl("images/building/barracks.img");
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
