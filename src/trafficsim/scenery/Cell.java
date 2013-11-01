package trafficsim.scenery;

import trafficsim.simulator.Vehicle;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Cell {


    public boolean isEmpty();

    public void setVehicle(Vehicle vehicle);

    public void removeVehicle();

    public boolean hasVehicle();
}
