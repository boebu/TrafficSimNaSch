package trafficsim.scenery2;

import javax.vecmath.Vector2d;
import java.awt.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/18/13
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Intersection {

    private Point position;
    private Hashtable<Intersection, Street> outgoingStreets;
    private Hashtable<Intersection, Street> incomingStreets;
    private Hashtable<Street, Hashtable<Direction, Street>> routing;
    private ArrayList<Street> sortedOutgoingStreets;
    private IntersectionController intersectionController;

    public Intersection(int x, int y) {
        this.position = new Point(x,y);
        this.outgoingStreets = new Hashtable<Intersection, Street>();
        this.incomingStreets = new Hashtable<Intersection, Street>();
        this.sortedOutgoingStreets = new ArrayList<Street>();
    }

    public void sort() {
//        Collections.sort(this.sortedOutgoingStreets, new Comparator<Street>() {
//            @Override
//            public int compare(Street o1, Street o2) {
//                double result1 = Math.toDegrees(Math.atan2(o1.getDirection().x ,o1.getDirection().y));
//                if(result1 < 0.0) {
//                    result1 = 180-(180-Math.abs(result1));
//                } else {
//                    result1 = 180+(180-result1);
//                }
//                double result2 = Math.toDegrees(Math.atan2(o2.getDirection().x ,o2.getDirection().y));
//                if(result2 < 0.0) {
//                    result2 = 180-(180-Math.abs(result2));
//                } else {
//                    result2 = 180+(180-result2);
//                }
//                if(result1 > result2) {
//                    return 1;
//                } else if(result1 < result2) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        });
        Collections.sort(sortedOutgoingStreets);

    }

    public void initRouting() {
        this.routing = new Hashtable<Street, Hashtable<Direction, Street>>();
        for(Street incoming:incomingStreets.values()) {
            this.routing.put(incoming,new Hashtable<Direction, Street>());
            this.routing.get(incoming).putAll(getOutgoingStreetDirection(incoming));
        }
    }

    public void setIntersectionController(IntersectionController ic) {
        this.intersectionController = ic;
        ic.setIntersection(this);
    }

    public Collection<Street> getIncomingStreets() {
        return this.incomingStreets.values();
    }

    private Hashtable<Direction, Street> getOutgoingStreetDirection(Street incoming) {
        // create orthogonal Vector to incoming street Direction
        Vector2d orth = new Vector2d(incoming.getDirection().y,incoming.getDirection().x);
        Hashtable<Direction, Street> routes = new Hashtable<Direction, Street>();
        Hashtable<Double, Street> httmp = new Hashtable<Double, Street>();
        Double[] angles = new Double[3];
        int i = 0;
        for(Street outgoing:outgoingStreets.values()) {

            Vector2d tmpv = new Vector2d(outgoing.getDirection());
            tmpv.add(incoming.getDirection());
            Double angle = Math.toDegrees(orth.angle(tmpv));
            httmp.put(angle,outgoing);
            if(!tmpv.equals(new Vector2d())) {
                angles[i] = angle;
                i++;
            }
        }
        Arrays.sort(angles);

        routes.put(Direction.RIGHT,httmp.get(angles[0]));
        if(angles.length >= 2) {
            routes.put(Direction.STRAIGHT, httmp.get(angles[1]));
        }
        if(angles.length == 3) {
            routes.put(Direction.LEFT,httmp.get(angles[2]));
        }


        return routes;
    }

//    public Street getLeftStreet(Street s) {
//         Vector2d orth = new Vector2d(s.getDirection().y,s.getDirection().x);
//
//        Vector2d inv = new Vector2d(s.getDirection());
//        inv.negate();
//
//        //System.out.println(s.getId()+ " (" + s.getDirection() +") orth( "+ orth + ") " + s.getDirection().equals(new Vector2d()) +" " + Math.toDegrees(orth.angle(s.getDirection())));
//
//        for(Street x:sortedOutgoingStreets) {
//             Vector2d tmpv = x.getDirection();
//             tmpv.add(s.getDirection());
//            //System.out.println("ANG " + tmpv + " this " +x.getId() + " end " + x.getDirection() + " s2 " + s.getDirection());
//
//             System.out.println(x.getId()+ " (" + x.getDirection() +") orth( "+ orth + ") " + x.getDirection().equals(new Vector2d()) +" " + Math.toDegrees(orth.angle(tmpv)));
//         }
//        return s;
//
//
//    }

    public Street getRoute(Street incoming, Direction dir) {
        return this.routing.get(incoming).get(dir);
    }

    public Point getPosition() {
        return this.position;
    }

    public Street getOutgoingStreet(Intersection i) {
        return this.outgoingStreets.get(i);
    }

    public Street getIncomingStreet(Intersection i) {
        return this.incomingStreets.get(i);
    }

    public void addOutgoingStreet(Intersection i, Street s) {
        this.outgoingStreets.put(i,s);
        this.sortedOutgoingStreets.add(s);
        s.setNextIntersection(i);
        i.addIncomingStreet(this,s);
    }

    public void addIncomingStreet(Intersection i, Street s) {
       this.incomingStreets.put(i,s);
    }

    public Set<Intersection> getNextIntersections() {
        return this.outgoingStreets.keySet();
    }

    public Set<Direction> getDirections(Street incoming) {
        return this.routing.get(incoming).keySet();
    }


}
