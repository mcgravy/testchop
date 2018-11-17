package scripts.Nodes;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSNPC;
import scripts.API.BelvisGE.BelvisGE;
import scripts.API.BelvisGE.Offer;
import scripts.API.Node;

import java.util.function.BooleanSupplier;

public class BuyAXE extends Node {
    @Override
    public void execute() {
        System.out.println("Buying axe");
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

        }, General.random(3000, 6000));
        if (!Banking.isBankScreenOpen())
            return;


        if(Inventory.getCount("Coins") < 1500){//test if we have enough coins in our inventory to buy axe
            RSItem[] coins = Banking.find("Coins");
            int totalCoins = 0;
            if(coins.length != 0) {
                totalCoins = Inventory.getCount("Coins") + coins[0].getStack();//if we dont have enough in our inventory, check in the bank
            }
                if(totalCoins < 1500) {//if we don't have enough total coins to buy an axe, proceed to sell logs so we can afford axe
                    if (Interfaces.get(12, 23) == null)
                        return;
                    Interfaces.get(12, 23).click("Note");
                    RSItem[] Logs = Banking.find("Logs");
                    if (Logs.length == 0)
                        return;
                    int Logcounter = Logs[0].getStack();
                    Banking.withdraw(Logcounter, "Logs");

                    Offer kellogs = new Offer(Offer.Type.SELL, Logcounter, -2, "Logs");
                    Banking.close();
                    Timing.waitCondition(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return !Banking.isBankScreenOpen();
                        }
                    }, General.random(3000, 5000));

                    if (!BelvisGE.isOpen()) {
                        RSNPC[] clerks = NPCs.findNearest("Grand Exchange Clerk");
                        if (clerks.length < 1)
                            return;
                        if (!DynamicClicking.clickRSNPC(clerks[0], "Exchange Grand Exchange Clerk")) {
                            Camera.turnToTile(clerks[0]);
                            DynamicClicking.clickRSNPC(clerks[0], "Exchange Grand Exchange Clerk");
                        }
                        Timing.waitCondition(new BooleanSupplier() {
                            @Override
                            public boolean getAsBoolean() {
                                General.sleep(100);
                                return BelvisGE.isOpen();
                            }
                        }, General.random(4000, 6000));
                    }
                    kellogs.executeOffer();//SELLING LOGS
                    GrandExchange.close();
                    Timing.waitCondition(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return !BelvisGE.isOpen();
                        }
                    }, General.random(2000, 3000));

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

                    }, General.random(3000, 6000));
                    if (!Banking.isBankScreenOpen())
                        return;
                }
            }
        Banking.withdraw(2000, "Coins");
        Banking.close();
        Timing.waitCondition(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return !Banking.isBankScreenOpen();
            }
        }, General.random(3000, 5000));
        Offer buyAxe = new Offer(Offer.Type.BUY, 1, -2, "Adamant axe");
        if(!BelvisGE.isOpen()) {
            RSNPC[] clerks = NPCs.findNearest("Grand Exchange Clerk");
            if(clerks.length < 1)
                return;
            if(!DynamicClicking.clickRSNPC(clerks[0],"Exchange Grand Exchange Clerk")) {
                Camera.turnToTile(clerks[0]);
                DynamicClicking.clickRSNPC(clerks[0],"Exchange Grand Exchange Clerk");
            }
            Timing.waitCondition(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    General.sleep(100);
                    return BelvisGE.isOpen();
                }
            }, General.random(4000, 6000));
        }
        buyAxe.executeOffer();
        GrandExchange.close();
        Timing.waitCondition(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return !BelvisGE.isOpen();
            }
        }, General.random(2000, 3000));
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
        Banking.withdraw(1, "Adamant axe");
        Banking.close();
        Timing.waitCondition(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return !Banking.isBankScreenOpen();
            }
        }, General.random(3000, 5000));
        Magic.selectSpell("Lumbridge Home Teleport");
            General.random(1000,3000);
        GameTab.open(GameTab.TABS.INVENTORY);
    }

    @Override
    public boolean validate() {
        if (Player.getPosition().distanceTo(BelvisGE.GELoc) < 15 && Inventory.getCount("Adamant axe") == 0 && Skills.getActualLevel(Skills.SKILLS.WOODCUTTING) >= 31)
            return true;
        else
            return false;
    }
}
