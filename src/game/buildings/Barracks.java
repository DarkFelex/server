package game.buildings;

import accounts.UserProfile;
import game.Village;
import game.units.Spearman;
import game.units.UnitType;
import game.units.Units;

import java.util.HashMap;

import static game.buildings.BuildType.MILITARY;
import static game.units.UnitType.SPEARMAN;

/**
 * Created by nmikutskiy on 24.12.16.
 */
public class Barracks extends Build {
    private HashMap<String, Units> unitsToCreate;

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

    public Units createSpearman(Village village, int amount){
        if (village.getUnits().get(SPEARMAN) == null){
            village.getUnits().put(SPEARMAN, new Spearman());
        }

        int currentSpearmanAmount = village.getUnits().get(SPEARMAN).getAmount();
        village.getUnits().get(SPEARMAN).setAmount(currentSpearmanAmount += amount);

        return village.getUnits().get(SPEARMAN);
    }
}
