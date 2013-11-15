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

    // define size of a Cell in [m]
    public static int MAXSPEED = 5;

    @Override
    public int getMaxSpeed() {
        return MAXSPEED;
    }

    Cell current;
    Driver driver;
    int speed = 0;

    public VehicleImpl(Cell current) {
        this.current = current;
        this.current.setVehicle(this);
        this.driver = new DriverImplDefault();
        this.driver.setVehicle(this);
        this.speed = (int)Math.round(Math.random()*5);

    }

    public VehicleImpl(Cell current, Driver driver) {
        this.current = current;
        this.current.setVehicle(this);
        this.driver = driver;
        this.driver.setVehicle(this);

    }

    @Override
    public int getSpeed() {
        return speed;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decelerate(int s) {
        this.speed = s;
    }

    @Override
    public void accelerate() {
        if(speed < MAXSPEED) {
            speed++;
        }
    }

    @Override
    public Driver getDriver() {
        return driver;
    }

    @Override
    public Cell getCell() {
        return current;
    }
    @Override
    public void move(Cell c) {
        this.current.removeVehicle();
        this.current = c;
        this.current.setVehicle(this);
    }
}
