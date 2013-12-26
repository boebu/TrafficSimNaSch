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

    private static final int RADIUS = 16;
    private Point position;
    private Hashtable<Intersection, Street> outgoingStreets;
    private Hashtable<Intersection, Street> incomingStreets;
    private Hashtable<Street, Hashtable<Direction, Street>> routing;
    private ArrayList<Street> sortedOutgoingStreets;
    private ArrayList<IntersectionStreet> StreetPhases = new ArrayList<IntersectionStreet>();
    private Random random = new Random();
    private int interatePhasesTick = 10;
    private int ticks = 0;
    private int maxPhase;
    private int phase;
    private boolean delayed = false;
    private IntersectionController intersectionController;

    public Intersection(int x, int y) {
        this.position = new Point(x,y);
        this.outgoingStreets = new Hashtable<Intersection, Street>();
        this.incomingStreets = new Hashtable<Intersection, Street>();
        this.sortedOutgoingStreets = new ArrayList<Street>();
        this.phase = 0;
    }

    public int getRadius() {
        return RADIUS;
    }

    public int getInteratePhasesTick() {
        return interatePhasesTick;
    }

    public void setInteratePhasesTick(int interatePhasesTick) {
        this.interatePhasesTick = interatePhasesTick;
    }

    public void iteratePhase() {
       if(this.ticks >= this.interatePhasesTick || this.delayed) {
           // need to check if intersection is empty
           System.out.println(this + " " + isIntersectionEmpty());
           if(isIntersectionEmpty()) {
               if(this.phase < this.maxPhase) {
                   this.phase++;
               } else {
                   this.phase = 0;
               }
               this.ticks = 0;
               this.delayed = false;
           } else {
               this.delayed = true;
           }
       } else {
           this.ticks++;
       }

       System.out.println("PHASE " + this.phase);
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
        initFlowPhases();
    }

    private void initFlowPhases() {
        int tmpphase = 0;
        for(Street incoming:this.incomingStreets.values()) {
            for(Street outgoing: this.routing.get(incoming).values()) {
                IntersectionStreet is = new IntersectionStreet(5,incoming.getNumOfLanes(),incoming.getId() + outgoing.getId());
                is.initStreet(incoming.getEnd(),outgoing.getStart());
                for(IntersectionStreet tmpis: StreetPhases) {
                    if(!checkStreetCollision(tmpis,is) || (tmpis.getEnd() == is.getStart() && tmpis.getStart() == tmpis.getEnd())) {
                        is.setPhase(tmpis.getPhase());
                        StreetPhases.add(is);
                        break;
                    } else {
                    }
                }
                if(!StreetPhases.contains(is)) {
                    is.setPhase(tmpphase);
                    StreetPhases.add(is);
                    tmpphase++;
                }

            }
        }
        this.maxPhase = tmpphase-1;
        for(IntersectionStreet x: StreetPhases) {
            System.out.println(x.getId() + " " + x.getStart() + " " + x.getEnd() + " " +  x.getPhase());
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
        Vector2d orth = new Vector2d(-incoming.getDirection().y,incoming.getDirection().x);
        Hashtable<Direction, Street> routes = new Hashtable<Direction, Street>();
        Hashtable<Double, Street> httmp = new Hashtable<Double, Street>();
        //Double[] angles = new Double[3];
        ArrayList<Double> angles = new ArrayList<Double>();
        for(Street outgoing:outgoingStreets.values()) {

            Vector2d tmpv = new Vector2d(outgoing.getDirection());
            tmpv.add(incoming.getDirection());
            Double angle = Math.toDegrees(orth.angle(tmpv));
            httmp.put(angle,outgoing);
            if(!tmpv.equals(new Vector2d())) {
                angles.add(angle);
            }
        }
        Collections.sort(angles);

        routes.put(Direction.RIGHT,httmp.get(angles.get(0)));
        if(angles.size() >= 2) {
            routes.put(Direction.STRAIGHT, httmp.get(angles.get(1)));
        }
        if(angles.size() == 3) {
            routes.put(Direction.LEFT,httmp.get(angles.get(2)));
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

    protected void addIncomingStreet(Intersection i, Street s) {
       this.incomingStreets.put(i,s);
    }

    public Set<Intersection> getNextIntersections() {
        return this.outgoingStreets.keySet();
    }

    public Set<Direction> getDirections(Street incoming) {
        return this.routing.get(incoming).keySet();
    }

    public Direction getNewDirection(Street incoming) {
        Set<Direction> directions = this.routing.get(incoming).keySet();
        return (Direction)directions.toArray()[this.random.nextInt(directions.size())];
    }

    public Street getIntersectionStreet(Street from, Street to) {
         for(IntersectionStreet is: this.StreetPhases) {
             if(is.getStart() == from.getEnd() && is.getEnd() == to.getStart() && is.getPhase() == this.phase) {
                 return is;
             }
         }
         return null;
    }

    private static boolean checkStreetCollision(Street s1, Street s2) {
        Point a1 = s1.getStart();
        Point a2 = s1.getEnd();
        Point b1 = s2.getStart();
        Point b2 = s2.getEnd();
        // structure idea from http://www.spieleprogrammierer.de/wiki/2D-Kollisionserkennung
        double denom = (b2.y - b1.y) * (a2.x - a1.x) - (b2.x-b1.x) * (a2.y-a1.y);
        if (Math.abs(denom) < Math.exp(-6)) {
            return false;
        }
        double ua = ((b2.x-b1.x) * (a1.y - b1.y) - (b2.y - b1.y) * (a1.x - b1.x)) / denom;
        double ub = ((a2.x-a1.x) * (a1.y - b1.y) - (a2.y - a1.y) * (a1.x - b1.x)) / denom;
        return ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1;
    }

    private boolean isIntersectionEmpty() {
        for(IntersectionStreet is: StreetPhases) {
            if(is.getVehicles().size() != 0) {
                return false;
            }
        }
        return true;
    }


}
