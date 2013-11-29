package trafficsim.scenery2;

import javax.vecmath.Vector2d;
import java.awt.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/26/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntersectionController {

    private Intersection intersection;
    Hashtable<Street,Street> connections2;
    ArrayList<Street> connections;
    ArrayList<Hashtable<Point,Point>> hashtest;


    public void setIntersection(Intersection i) {
        this.intersection = i;
    }

    public void calculateTrafficFlows() {



        for(Street incoming: this.intersection.getIncomingStreets()) {
            Point tmp1 = null;
            Point tmp2 = null;
            for(Direction direction:Direction.values()) {
                Street outgoing = this.intersection.getRoute(incoming,direction);

                if(hashtest.isEmpty()) {
                    hashtest.add(new Hashtable<Point, Point>());
                    hashtest.get(0).put(incoming.getEnd(),outgoing.getStart());
                } else {
                    Iterator<Hashtable<Point,Point>> it = hashtest.iterator();



                }
                Street crossStreet = new Street(incoming.getSpeedlimit(),incoming.getNumOfLanes(),"x");
                crossStreet.initStreet(incoming.getEnd(),outgoing.getStart());
                if(this.connections.size() == 0) {
                    this.connections.add(crossStreet);
                }
            }
        }



    }

    private static boolean checkStreetCollision(Point a1, Point a2, Point b1, Point b2) {
        // structure idea from http://www.spieleprogrammierer.de/wiki/2D-Kollisionserkennung
        double denom = (b2.y - b1.y) * (a2.x - a1.x) - (b2.x-b1.x) * (a2.y-a1.y);
        if (Math.abs(denom) < Math.exp(-6)) {
            return false;
        }
        double ua = ((b2.x-b1.x) * (a1.y - b1.y) - (b2.y - b1.y) * (a1.x - b1.x)) / denom;
        double ub = ((a2.x-a1.x) * (a1.y - b1.y) - (a2.y - a1.y) * (a1.x - b1.x)) / denom;
        return ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1;
    }


}
