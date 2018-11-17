package scripts;

import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.script.Script;
import scripts.API.Node;
import scripts.API.Tools;
import scripts.Nodes.*;

import java.util.Collections;
import java.util.LinkedList;

public class testchop extends Script {

    @Override
    public void run() {
        LinkedList<Node> nodes = new LinkedList<Node>();
        Collections.addAll(nodes, new Bank(), new Chop(), new Walk2Bank(), new Walk2Tree(), new Walk2ge(), new BuyAXE());
        while (true) {
            for (Node node : nodes) {
                if (node.validate())
                    node.execute();
            }
            Tools.mapescape();
            General.sleep(100);
        }
    }
}
