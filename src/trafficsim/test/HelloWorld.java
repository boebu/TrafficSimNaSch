package trafficsim.test;

import trafficsim.scenery2.Intersection;
import trafficsim.scenery2.Street;
import trafficsim.scenery2.Vehicle;


import javax.vecmath.Vector2d;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/11/13
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {
    public static void main(String[] args) {

        Intersection i1 = new Intersection(400,300);
        Intersection i2 = new Intersection(100,100);
        Intersection i3 = new Intersection(0,100);
        Intersection i4 = new Intersection(100,0);




        Street st1 = new Street(5,2);
        Street st2 = new Street(5,2);
        Street st3 = new Street(5,2);
        Street st4 = new Street(5,2);
        i1.addOutgoingStreet(i2, st1);
        i2.addOutgoingStreet(i1,st2);
        i1.addOutgoingStreet(i3,st3);
        i1.addOutgoingStreet(i4,st4);

        System.out.println("*****");

        System.out.println(i1.getOutgoingStreet(i2));
        System.out.println(i2.getIncomingStreet(i1));
        System.out.println("*****");
        System.out.println(i2.getOutgoingStreet(i1));
        System.out.println(i1.getIncomingStreet(i2));

        st1.initStreet(i1.getPosition(),i2.getPosition());
        st2.initStreet(i2.getPosition(),i1.getPosition());
        st3.initStreet(i1.getPosition(),i3.getPosition());
        st4.initStreet(i1.getPosition(),i4.getPosition());

        System.out.println(i1.sort());

        for(Street s:i1.sort()) {
            System.out.println("Angle: " + s.getDirection().angle(new Vector2d(0,1)));
        }

        System.out.println("****");
        System.out.println(st1.getStart());


        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i1.getNextIntersections());
        System.out.println(i2.getNextIntersections());

        Vehicle v = new Vehicle(st1);

        System.out.println(v.getDirection());
        System.out.println(v.getPosition());
               v.move();
        Vehicle v2 = new Vehicle(st1);
        System.out.println(v.getDirection());
        System.out.println(v.getPosition());

        System.out.println(v.getDirection());
        System.out.println(v2.getDirection());
        System.out.println(st1.getDirection());

    }

}
