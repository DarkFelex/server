package game.buildings;

import game.Village;
import game.units.Unit;

import java.util.HashMap;

import static game.buildings.BuildType.MILITARY;

/**
 * Created by nmikutskiy on 24.12.16.
 */
public class Barracks extends Build {
    private HashMap<String, Unit> unitsToCreate;

    public Barracks(){
        setBuildName("Barracks");
        setBuildType(MILITARY);
        setBuildLevel(0);
        setGoldToBuild(350);
        setHp(300);
        setImgUrl("images/building/barracks.img");
    }

    @Override
    public void makeResources(long currentTime, Village village) {

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
//
//    public unit createSpearman(){
//
//    }
}
