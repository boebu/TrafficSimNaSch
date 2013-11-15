package trafficsim.scenery;

import trafficsim.simulator.Vehicle2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/13/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MoveCalculator {

    public void moveVehicle(ArrayList<Vehicle2> actualLane, ArrayList<Vehicle2> nextLane) {
        for(int i=0;i<actualLane.size();i++) {
            Vehicle2 v = actualLane.get(i);
            if((v.getPosition()+v.getMaxSpeed())<0);

        }

    }

}
