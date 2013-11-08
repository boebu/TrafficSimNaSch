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

    private Vehicle vehicle;

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Vehicle v) {
        this.vehicle = v;
    }

    @Override
    public void drive()  {

        // accelerate if speed < maxspeed (1)
        vehicle.accelerate();

        // break down to free cells left in front (2)
        vehicle.getCell();
        Cell c = vehicle.getCell();
        int i = 0;

        while(c.hasNext() && i < vehicle.getSpeed()) {

            if(!c.next().isEmpty()) {
               break;
            }  else {
               c = c.next();
               i++;
            }

        }
        vehicle.decelerate(i);

        // need a troedel variable
        double p = 0.15;
        // trödeln  (3)
        if(Math.random()<p) {
            vehicle.decelerate(i-1);
            vehicle.move(c.previous());
        }  else {
            vehicle.move(c);
        }



    }
}
