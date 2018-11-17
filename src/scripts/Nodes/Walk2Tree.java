package scripts.Nodes;

import org.tribot.api2007.*;
import scripts.API.Node;
import scripts.API.Tools;
import scripts.API.Woodcutting;

import java.util.function.BooleanSupplier;

public class Walk2Tree extends Node {
    @Override
    public void execute() {
        System.out.println("Walking to trees");
        WebWalking.walkTo(Woodcutting.Locations.LUM_NORMAL.getLocation(), new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                if(Interfaces.get(595, 37) != null)
                    return true;
                else
                    return false;
            }
        }, 500);
        Tools.mapescape();
    }

    @Override
    public boolean validate() {
        if (Player.getPosition().distanceTo(Woodcutting.Locations.LUM_NORMAL.getLocation()) > 15 && !Inventory.isFull()) {
            if (!(Inventory.getCount("Adamant axe") == 0) && Skills.getActualLevel(Skills.SKILLS.WOODCUTTING) >= 31 && !Walk2ge.canafford)
                return false;
            return true;
        }
        else
            return false;
    }
}
