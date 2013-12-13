package trafficsim.main;

import trafficsim.scenery2.Vehicle;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 12/4/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Simulator implements Runnable{
    ArrayList<Vehicle> vehicles;
    public Simulator(ArrayList<Vehicle> r_vehicles) {
        this.vehicles = r_vehicles;
    }

    @Override
    public void run() {
        calculate();
    }

    private void calculate() {
        for(Vehicle v: this.vehicles) {
           v.calcNewPosition();
        }
        for (Vehicle v: this.vehicles) {
            v.move();
        }
    }
}
