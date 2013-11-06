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

    ArrayList<Lane> lanes = new ArrayList<Lane>();
    String name;
    int id;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    public ScenarioImpl(String name, int id, int nroflanes,int lanelength) {
        this.name = name;
        this.id = id;
        for(int i=0;i<nroflanes;i++) {
            lanes.add(i,new LaneImpl(lanelength));
        }
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
