package game.buildings;

import game.Village;
import game.resources.Resource;
import game.resources.Resources;

import static game.buildings.BuildType.INDUSTRY;
import static game.resources.Resources.WOOD;

/**
 * Created by nmikutskiy on 28.12.16.
 */
public class WoodFactory extends Build {
    public WoodFactory(Village village){

        setBuildName("Wood Factory");
        setBuildType(INDUSTRY);
        setBuildLevel(0);
        setGoldToBuild(30);
        setHp(70);
        setImgUrl("images/building/woodfactory.img");
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
    @Override
    public void makeResources(long currentTime, Village village){
        if (currentTime % 5 == 0){//Раз в 5 секунд
            int value = village.getResources().get(WOOD).getValue();
            village.getResources().get(WOOD).setValue(++value);
            System.out.println(village.getResources().get(WOOD).getName() + " = " + village.getResources().get(WOOD).getValue());
        }
    }
}
