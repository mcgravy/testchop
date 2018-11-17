package scripts.API;

import org.tribot.api.General;
import org.tribot.api2007.Interfaces;

public class Tools {
    public static void mapescape(){
        if (Interfaces.get(595, 37) != null)
            Interfaces.get(595, 37).click("Close");
    }
}
