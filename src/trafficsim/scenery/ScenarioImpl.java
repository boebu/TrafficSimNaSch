package trafficsim.scenery;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/31/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScenarioImpl implements Scenario {

    ArrayList<Lane> lanes;
    String name;
    int id;

    public ScenarioImpl(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public ArrayList<Lane> getLanes() {
        return lanes;
    }

    @Override
    public void addLane(Lane lane) {
        lanes.add(lane);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Lane getLane(int index) {
        return lanes.get(index);
    }

}
