package scripts.API;

import org.tribot.api2007.types.RSTile;

public class Woodcutting {

    public enum Locations{
        LUM_OAK(new RSTile(3190, 3250, 0)),
        LUM_NORMAL(new RSTile(3197, 3245, 0)),
        LUM_WILLOW(null),
        YEW(null);

        Locations(RSTile loc) {
            location = loc;
        }

        public RSTile getLocation(){
            return location;
        }

        RSTile location;



    }
}
