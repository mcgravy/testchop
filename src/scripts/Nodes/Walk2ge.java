package scripts.Nodes;

import org.tribot.api2007.*;
import scripts.API.BelvisGE.BelvisGE;
import scripts.API.Node;
import scripts.API.Tools;
import scripts.API.Woodcutting;

import java.util.function.BooleanSupplier;

public class Walk2ge extends Node {

    @Override
    public void execute() {
        System.out.println("Walking to Ge");
        WebWalking.walkTo(BelvisGE.GELoc, new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                if(Interfaces.get(595, 37) != null)
                    return true;
                else
                    return false;
            }
        },500);
    }
    public static boolean canafford = false;

    @Override
    public boolean validate() {
        if (canafford == true && Skills.getActualLevel(Skills.SKILLS.WOODCUTTING) >= 31 &&
                !Equipment.isEquipped("Adamant axe" ) &&
                    Inventory.getCount("Adamant axe") < 1)
            return true;
        else
            return false;
    }
}
