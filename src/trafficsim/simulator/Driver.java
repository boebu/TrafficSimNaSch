package trafficsim.simulator;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/1/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Driver {
    public void drive();
    public Vehicle getVehicle();
    public void setVehicle(Vehicle v);
}
