package trafficsim.model;

import javax.vecmath.Vector2d;
import java.awt.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/18/13
 * Time: 11:49 PM
 * Intersection Model
 */
public class Intersection {

    // init instance variables
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

    public Intersection(int x, int y) {
        this.position = new Point(x,y);
        this.outgoingStreets = new Hashtable<Intersection, Street>();
        this.incomingStreets = new Hashtable<Intersection, Street>();
        this.sortedOutgoingStreets = new ArrayList<Street>();
        this.phase = 0;
    }

    /**
     * get the current iteratePhaseCounter
     * @return iteratePhaseCounter
     */
    public int getInteratePhasesTick() {
        return interatePhasesTick;
    }

    /**
     * sets the interatephasecounter
     * @param interatePhasesTick
     */
    public void setInteratePhasesTick(int interatePhasesTick) {
        this.interatePhasesTick = interatePhasesTick;
    }

    /**
     *  interate phase
     *  @param
     */
    public void iteratePhase() {
        // iterate trough StreetPhases based on tick rate
       if(this.ticks >= this.interatePhasesTick || this.delayed) {
           // need to check if intersection is empty
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
    }

    /**
     *   initializes Routing on Intersection
     *   @param
     */
    public void initRouting() {
        this.routing = new Hashtable<Street, Hashtable<Direction, Street>>();
        for(Street incoming:incomingStreets.values()) {
            this.routing.put(incoming,new Hashtable<Direction, Street>());
            this.routing.get(incoming).putAll(getOutgoingStreetDirection(incoming));
        }
        initFlowPhases();
    }

    /**
     * gets all incoming streets
     * @return incomingStreets[]
     */
    public Collection<Street> getIncomingStreets() {
        return this.incomingStreets.values();
    }

    /**
     * gets the route based on incoming street and direction
     * @param incoming
     * @param dir
     * @return Street
     */
    public Street getRoute(Street incoming, Direction dir) {
        return this.routing.get(incoming).get(dir);
    }

    /**
     * gets the Intersection Position
     * @return position
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Adds an outgoing street
     * @param i
     * @param s
     */
    public void addOutgoingStreet(Intersection i, Street s) {
        this.outgoingStreets.put(i,s);
        this.sortedOutgoingStreets.add(s);
        s.setNextIntersection(i);
        i.addIncomingStreet(this,s);
    }

    /**
     * helper method to allow a vehicle to choose a new direction based on some kind of randomness
     * @param incoming
     * @return Direction
     */
    public Direction getNewDirection(Street incoming) {
        Set<Direction> directions = this.routing.get(incoming).keySet();
        return (Direction)directions.toArray()[this.random.nextInt(directions.size())];
    }

    /**
     * get the Intersection street
     * @param from
     * @param to
     * @return Street
     */
    public Street getIntersectionStreet(Street from, Street to) {
        for(IntersectionStreet is: this.StreetPhases) {
            if(is.getStart() == from.getEnd() && is.getEnd() == to.getStart() && is.getPhase() == this.phase) {
                return is;
            }
        }
        return null;
    }

    /**
     * add an incoming street, this method should only be called from another Intersection Object
     * @param i
     * @param s
     */
    void addIncomingStreet(Intersection i, Street s) {
        this.incomingStreets.put(i,s);
    }

    /**
     * calculates a non conflicting flowphase
     */
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
    }

    /**
     * helper Method for flowphase generation
     * @param incoming
     * @return  Hashtable<Direction,Street>
     */
    private Hashtable<Direction, Street> getOutgoingStreetDirection(Street incoming) {
        // create orthogonal Vector to incoming street Direction
        Vector2d orth = new Vector2d(-incoming.getDirection().y,incoming.getDirection().x);
        Hashtable<Direction, Street> routes = new Hashtable<Direction, Street>();
        Hashtable<Double, Street> httmp = new Hashtable<Double, Street>();
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

    /**
     * helper method check if 2 streets are colliding or not
     * @param s1
     * @param s2
     * @return true|false
     */
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

    /**
     * helpermethod for iteratePhase, intersection needs to be empty for this
     * @return
     */
    private boolean isIntersectionEmpty() {
        for(IntersectionStreet is: StreetPhases) {
            if(is.getVehicles().size() != 0) {
                return false;
            }
        }
        return true;
    }


}
