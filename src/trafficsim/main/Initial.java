package trafficsim.main;

import trafficsim.gui.StreetPanel;
import trafficsim.gui.SimulatorView;
import trafficsim.scenery2.Intersection;
import trafficsim.scenery2.Street;

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

    public static void main(String[] args) {

        Intersection i1 = new Intersection(500,500);
        intersections.add(i1);
        Intersection i2 = new Intersection(200,500);
        intersections.add(i2);
        Intersection i3 = new Intersection(100,100);
        intersections.add(i3);
        Intersection i4 = new Intersection(500,50);
        intersections.add(i4);

        Street st1 = new Street(5,1,"S1");
        streets.add(st1);
        Street st2 = new Street(5,1,"S2");
        streets.add(st2);
        Street st3 = new Street(5,1,"S3");
        streets.add(st3);
        Street st4 = new Street(5,1,"S4");
        streets.add(st4);
        Street st5 = new Street(5,1,"S5");
        streets.add(st5);
        Street st6 = new Street(5,1,"S6");
        streets.add(st6);
        Street st7 = new Street(5,1,"S7");
        streets.add(st7);
        Street st8 = new Street(5,1,"S8");
        streets.add(st8);


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



        StreetPanel streetPanel = new StreetPanel();
        SimulatorView view = new SimulatorView();


        streetPanel.setIntersections(intersections);
        streetPanel.setStreets(streets);

        view.create(streetPanel);


    }



}
