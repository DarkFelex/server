package game.buildings;

import static game.buildings.BuildType.INFRASTRUCTURE;

/**
 * Created by nmikutskiy on 24.12.16.
 */
public class Palace extends Build {
    public Palace(){
        setBuildName("Palace");
        setBuildType(INFRASTRUCTURE);
        setBuildLevel(0);
        setGoldToBuild(50);
        setHp(12000);
        setImgUrl("images/building/palace.img");
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
