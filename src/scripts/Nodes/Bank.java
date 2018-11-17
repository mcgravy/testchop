package scripts.Nodes;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSItem;
import scripts.API.Node;
import scripts.API.Tools;

import java.util.function.BooleanSupplier;

public class Bank extends Node {
    @Override
    public void execute() {
        System.out.println("Banking");
        Tools.mapescape();
        Banking.openBank();
        Timing.waitCondition(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                General.sleep(100);
                if (Banking.isBankScreenOpen())
                    return true;
                else
                    return false;
            }

        }, General.random(3000,6000));
        if (!Banking.isBankScreenOpen())
            return;

        String[] keepaxe ={"Bronze axe", "Adamant axe"};
        Banking.depositAllExcept(keepaxe);
        RSItem[] Logs = Banking.find("Logs");
        if (Logs.length == 0)
            return;
        if(Logs[0].getStack() > 50)
            Walk2ge.canafford = true;
    }

    @Override
    public boolean validate() {
        if (Banking.isInBank() && Inventory.isFull())
            return true;
        else
            return false;
    }
}
