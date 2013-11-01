package trafficsim.simulator;

import trafficsim.scenery.CellImpl;
import trafficsim.scenery.Cell;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/23/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class VehicleImpl implements Vehicle {

    Cell current;


    @Override
    public int getSpeed() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSpeed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Cell getCell() {
        return current;
    }
}
