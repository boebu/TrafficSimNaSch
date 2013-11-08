package trafficsim.test;

import trafficsim.scenery.*;
import trafficsim.simulator.*;

import java.util.ArrayList;
import java.util.Iterator;
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

        Scenario sc = new ScenarioImpl("Scenario 1",1,1,1500);


        for(Lane lane:sc.getLanes()) {
            for(Cell cell:lane.getCells()) {
                //System.out.println(cell + " -> " + cell.next());
            }
        }
        System.out.println(sc.getLane(0).getCells().get(0))  ;
        Vehicle v1 = new VehicleImpl(sc.getLane(0).getFirstCell());
        System.out.println(v1.getCell().isEmpty());
        System.out.println(v1.getCell().next().isEmpty())    ;

        Cell c = v1.getCell();
        int i = 0;
        sc.getLane(0).getCells().get(3).setVehicle(new VehicleImpl(sc.getLane(0).getCells().get(3)));
        while(c.hasNext() && i < 5) {

            System.out.println(c + " " + c.isEmpty())   ;
            c = c.next();
            i++;


        }

        v1.getDriver().drive();

        System.out.println(v1.getCell());


        System.out.println(sc.getLane(0).getCells().get(2).hasVehicle());
        System.out.println(sc.getLane(0).getCells().get(3).hasVehicle() );

        System.out.println(Math.random()<0.15);

    }

}
