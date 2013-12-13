package trafficsim.test;

import trafficsim.scenery2.Direction;
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

        Intersection i1 = new Intersection(500,500);
        Intersection i2 = new Intersection(600,500);
        Intersection i3 = new Intersection(400,500);
        Intersection i4 = new Intersection(500,200);
        Intersection i5 = new Intersection(500,800);




        Street st1 = new Street(5,2,"S1");
        Street st2 = new Street(5,2,"S2");
        Street st3 = new Street(5,2,"S3");
        Street st4 = new Street(5,2,"S4");
        Street st5 = new Street(5,2,"S5");
        Street st6 = new Street(5,2,"S6");
        i1.addOutgoingStreet(i2, st1);
        i2.addOutgoingStreet(i1,st2);
        i1.addOutgoingStreet(i3,st3);
        i3.addOutgoingStreet(i1,st6);
        i1.addOutgoingStreet(i4,st4);
        i1.addOutgoingStreet(i5,st5);




        st1.initStreet(i1.getPosition(),i2.getPosition());
        st2.initStreet(i2.getPosition(),i1.getPosition());
        st3.initStreet(i1.getPosition(),i3.getPosition());
        st4.initStreet(i1.getPosition(),i4.getPosition());
        st5.initStreet(i1.getPosition(),i5.getPosition());
        st6.initStreet(i3.getPosition(),i1.getPosition());
        st1.resetStartEndPoint();
        st2.resetStartEndPoint();
        st3.resetStartEndPoint();
        st4.resetStartEndPoint();
        st5.resetStartEndPoint();
        st6.resetStartEndPoint();






        i1.initRouting();
        //i1.initIntersectionController();

        System.out.println(i1.getRoute(st2, Direction.STRAIGHT).getId());


        Vehicle v = new Vehicle(st2,"x");

        System.out.println(v.getPosition());
        System.out.println(v.getSpeed());
        v.calcNewPosition();
        v.move();
        System.out.println(v.getPosition());
        System.out.println(v.getSpeed());

        Vehicle v2 = new Vehicle(st2,"y");

        v.calcNewPosition();

        v.move();
        System.out.println(v.getPosition());
        System.out.println(v.getSpeed());

        v.calcNewPosition();

        v.move();

        System.out.println(v.getPosition());
        System.out.println(v.getSpeed());

        v.calcNewPosition();

        v.move();

        System.out.println(v.getPosition());
        System.out.println(v.getSpeed());

        v.calcNewPosition();

        v.move();

        System.out.println(v.getPosition());
        System.out.println(v.getSpeed());

        v.calcNewPosition();

        v.move();

        System.out.println(v.getPosition());
        System.out.println(v.getSpeed());

        i1.iteratePhase();
        i1.iteratePhase();
        i1.iteratePhase();
        i1.iteratePhase();
        i1.iteratePhase();


    }





}
