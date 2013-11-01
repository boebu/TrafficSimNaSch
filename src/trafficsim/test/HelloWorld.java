package trafficsim.test;

import trafficsim.scenery.Cell;
import trafficsim.scenery.CellImpl;
import trafficsim.scenery.Lane;
import trafficsim.scenery.LaneImpl;
import trafficsim.simulator.VehicleImpl;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/11/13
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {
    public static void main(String[] args) {
        Lane lane = new LaneImpl(1500);

        lane.getFirstCell().setVehicle(new VehicleImpl());

        Cell c1 = new CellImpl();

        System.out.println(lane.countCells());
        System.out.println(lane.getFirstCell().hasVehicle());
        lane.getFirstCell().removeVehicle();
        System.out.println(lane.getFirstCell().hasVehicle());
        System.out.println(lane.hasCell(c1));


    }

}
