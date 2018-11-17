package scripts.Nodes;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.API.Node;
import scripts.API.Tools;

import java.util.function.BooleanSupplier;

public class Walk2Bank extends Node {

    @Override
    public void execute() {
        System.out.println("Walking to bank");
        WebWalking.walkToBank(new BooleanSupplier() {
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
        if (Inventory.isFull() && !Banking.isInBank())
            return true;
        else
            return false;
    }
}
