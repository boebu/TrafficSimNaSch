package trafficsim.test;

import trafficsim.scenery.*;
import trafficsim.simulator.Vehicle2;
import trafficsim.simulator.VehicleImpl2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/11/13
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {
    public static void main(String[] args) {

        ArrayList<StreetElement> street = new ArrayList<StreetElement>();

        StreetElement s1 =  new StartStreetElement(Direction.EAST);
        StreetElement s2 = new StraightStreetElement(s1,1,1,200);
        street.add(s1);
        street.add(s2);
        street.add(new StraightStreetElement(street.get(1),1,1,200));
        street.add(new StraightStreetElement(street.get(2),1,1,200));
        street.add(new StraightStreetElement(street.get(3),1,1,200));
        Vehicle2 v = new VehicleImpl2(5,100);

        //System.out.println(street.get(1).getLanes(street.get(0)));
        street.get(1).getLanes(street.get(0)).get(0).add(v);


   //     for(int i=0;i<5;i++){
     //       for(int j=0;j<4;j++) {
       //       street.get(j).calculateNextRound();


         //   }
      //      v.move();

    //        System.out.println(v.getPosition());
    //    }

      System.out.println(street.get(1).getLanes(street.get(0)));
        street.get(1).calculateNextRound();
        System.out.println(v.getPosition());
        v.move();
        System.out.println(v.getPosition());

        System.out.println(street.get(1).getLanes(street.get(0)));
        street.get(1).calculateNextRound();
        System.out.println(v.getPosition());
        v.move();
        System.out.println(v.getPosition());

        System.out.println(street.get(1).getLanes(street.get(0)));
        street.get(1).calculateNextRound();
        System.out.println(v.getPosition());
        v.move();
        System.out.println(v.getPosition());

        System.out.println(street.get(2).getLanes(street.get(1)));
        street.get(1).calculateNextRound();
        System.out.println(v.getPosition());
        v.move();
        System.out.println(v.getPosition());

    }

}
