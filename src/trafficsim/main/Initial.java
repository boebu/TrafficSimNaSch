package trafficsim.main;

import trafficsim.gui.StreetPanel;
import trafficsim.gui.SimulatorView;
import trafficsim.gui.VehiclePanel;
import trafficsim.scenery2.Intersection;
import trafficsim.scenery2.Street;
import trafficsim.scenery2.Vehicle;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 22.11.13
 * Time: 12:50
 * To change this template use File | Settings | File Templates.
 */
public class Initial {

    private static ArrayList<Intersection> intersections = new ArrayList<Intersection>();
    private static ArrayList<Street> streets = new ArrayList<Street>();
    private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public static void main(String[] args) throws InterruptedException {

        // INTERSECTIONS

        Intersection i1 = new Intersection(500,500);
        intersections.add(i1);
        Intersection i2 = new Intersection(200,500);
        intersections.add(i2);
        Intersection i3 = new Intersection(100,100);
        intersections.add(i3);
        Intersection i4 = new Intersection(500,50);
        intersections.add(i4);

        // STREETS

        Street st1 = new Street(5,2,"S1");
        streets.add(st1);
        Street st2 = new Street(5,2,"S2");
        streets.add(st2);
        Street st3 = new Street(5,2,"S3");
        streets.add(st3);
        Street st4 = new Street(5,2,"S4");
        streets.add(st4);
        Street st5 = new Street(5,2,"S5");
        streets.add(st5);
        Street st6 = new Street(5,2,"S6");
        streets.add(st6);
        Street st7 = new Street(5,2,"S7");
        streets.add(st7);
        Street st8 = new Street(5,2,"S8");
        streets.add(st8);

        // STREET <> INTERSECTIONS

        i1.addOutgoingStreet(i2, st1);
        i2.addOutgoingStreet(i1, st2);
        st1.initStreet(i1.getPosition(),i2.getPosition());
        st2.initStreet(i2.getPosition(),i1.getPosition());

        i2.addOutgoingStreet(i3, st3);
        i3.addOutgoingStreet(i2, st4);
        st3.initStreet(i2.getPosition(),i3.getPosition());
        st4.initStreet(i3.getPosition(),i2.getPosition());

        i3.addOutgoingStreet(i4, st5);
        i4.addOutgoingStreet(i3, st6);
        st5.initStreet(i3.getPosition(),i4.getPosition());
        st6.initStreet(i4.getPosition(),i3.getPosition());

        i4.addOutgoingStreet(i1, st7);
        i1.addOutgoingStreet(i4, st8);
        st7.initStreet(i4.getPosition(),i1.getPosition());
        st8.initStreet(i1.getPosition(),i4.getPosition());

        // VEHICLES

        Vehicle v1 = new Vehicle(st2);
        vehicles.add(v1);
        Vehicle v2 = new Vehicle(st4);
        vehicles.add(v2);
        Vehicle v3 = new Vehicle(st1);
        vehicles.add(v3);
        Vehicle v4 = new Vehicle(st1);
        vehicles.add(v4);

        System.out.println(v1.getPosition());
        System.out.println(v2.getPosition());

        // PANELS & VIEW

        StreetPanel streetPanel = new StreetPanel();
        VehiclePanel vehiclePanel = new VehiclePanel();
        SimulatorView view = new SimulatorView();

        streetPanel.setIntersections(intersections);
        streetPanel.setStreets(streets);
        vehiclePanel.setVehicles(vehicles);

        view.create(streetPanel, vehiclePanel);

        // TEST RUNNING

        while(true){
            Thread.sleep(1000);

            for(Vehicle vehicle : vehicles){
                vehicle.calcNewPosition();
                vehicle.move();
            }

            vehiclePanel.repaint();
        }

    }
}
