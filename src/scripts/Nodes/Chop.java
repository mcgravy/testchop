package scripts.Nodes;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;
import scripts.API.Node;
import scripts.API.Tools;
import scripts.API.Woodcutting;

import java.util.function.BooleanSupplier;

public class Chop extends Node {
    @Override
    public void execute() {
        System.out.println("chopping");
        Tools.mapescape();

        RSObject[] trees = Objects.findNearest(10,"Tree");
            if (trees.length < 1)
                return;
            for(RSObject tree : trees){
                if (tree != null)
                    if(!tree.click("Chop down")){
                        Camera.setCameraAngle(90);
                        Camera.turnToTile(tree);
                        tree.click("Chop down");
                    }
                    else{
                        Timing.waitCondition(new BooleanSupplier() {
                            @Override
                            public boolean getAsBoolean() {
                                return Player.getAnimation() == 879;
                            }
                        }, General.random(4000, 6000));

                        Timing.waitCondition(new BooleanSupplier() {
                            @Override
                            public boolean getAsBoolean() {
                                return Player.getAnimation() == -1;
                            }
                        }, General.random(20000, 25000));
                        return;
                    }
            }
    }
    @Override
    public boolean validate() {
        if (Player.getPosition().distanceTo(Woodcutting.Locations.LUM_NORMAL.getLocation()) <= 15 && !Inventory.isFull())
            return true;
        else
            return false;
    }
}
