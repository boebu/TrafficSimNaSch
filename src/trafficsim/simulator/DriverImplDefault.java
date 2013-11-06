package trafficsim.simulator;

import trafficsim.scenery.Cell;
import trafficsim.scenery.Scenario;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/1/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class DriverImplDefault implements Driver {

    Vehicle vehicle;

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Vehicle v) {
        this.vehicle = v;
    }

    Scenario scenario;
    @Override
    public void drive() {
        vehicle.getCell();

        Cell c = vehicle.getCell();
        Cell first = c;
        int i = 0;

        while(c.hasNext() && i < 5) {

            if(!c.next().isEmpty()) {
               break;
            }  else {
               c = c.next();
               i++;
            }


        }
        System.out.println(i);
        vehicle.move(c);



        //To change body of implemented methods use File | Settings | File Templates.
    }
}
