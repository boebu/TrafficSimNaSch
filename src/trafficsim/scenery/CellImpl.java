package trafficsim.scenery;

import trafficsim.simulator.Vehicle;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class CellImpl implements Cell {

    Vehicle vehicle;

    // define size of a Cell in [m]
    static double LENGTH = 7.5;

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void removeVehicle() {
        this.vehicle = null;
    }

    @Override
    public boolean hasVehicle() {
        if (vehicle == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        if (hasVehicle())    {
            return true;
        }               else {
            return false;
        }

    }
}
