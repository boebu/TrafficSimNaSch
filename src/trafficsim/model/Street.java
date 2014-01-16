package trafficsim.model;


import javax.vecmath.Vector2d;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/18/13
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Street implements Comparable<Street>{

    private static int LANE_WIDTH=6;
    private static int INTERSECTION_CORRECTION=36;
    private int speedlimit;
    private int lanes = 1;
    private Vector2d direction;
    private Vector2d orthogonal;
    private Point start;
    private Point end;
    private Intersection nextIntersection;
    // debug
    private String id;
    ArrayList<ArrayList<Vehicle>> vehiclesOnStreet;

    public Street(int speedlimit, int lanes, String id) {
        this.speedlimit = speedlimit;
        this.lanes = lanes;
        this.id = id;

        vehiclesOnStreet = new ArrayList<ArrayList<Vehicle>>();
        //lanes in direction
        for(int i=0;i<this.lanes;i++) {
            vehiclesOnStreet.add(new ArrayList<Vehicle>());
        }
    }

    public void initStreet(Point s, Point e) {
        this.start = s;
        this.end = e;
        this.calculateDirection();
    }

    public int getSpeedlimit() {
        return this.speedlimit;
    }

    public int getNumOfLanes() {
        return this.lanes;
    }

    public Point getStart() {
        return this.start;
    }

    public String getId() {
        return this.id;
    }

    public Point getEnd() {
        return this.end;
    }

    public void calculateDirection() {
        // define Direction Vector
        // define ortogonal Vector to Direction (90 Degree clockwise used for LanePoint Calculating
        Vector2d start = new Vector2d(this.start.getX(),this.start.getY());
        this.direction = new Vector2d(this.end.getX(), this.end.getY());
        this.direction.sub(start);
        this.direction.normalize();
        this.orthogonal = new Vector2d(-this.direction.y,this.direction.x);
        this.orthogonal.normalize();


    }

    public void resetStartEndPoint() {

        // Reset Start / End Point to add some free Space for the Intersection (Intersection R=16)
        Vector2d newStart = new Vector2d(this.direction);
        newStart.scale(INTERSECTION_CORRECTION);
        newStart.add(new Vector2d(this.start.getX(),this.start.getY()));
        this.start = new Point((int)newStart.x,(int)newStart.y);

        Vector2d newEnd = new Vector2d(this.direction);
        newEnd.negate();
        newEnd.scale(INTERSECTION_CORRECTION);
        newEnd.add(new Vector2d(this.end.x,this.end.y));
        this.end = new Point((int)newEnd.x,(int)newEnd.y);
    }

    public Vector2d getDirection() {
        return this.direction;
    }

    public void enterStreet(Vehicle v) {
        // need direction here for correct lane selection
        this.vehiclesOnStreet.get(v.getCurrentLaneId()).add(v);
        Vector2d s = new Vector2d(this.getLaneStart(v.getCurrentLaneId()).x,this.getLaneStart(v.getCurrentLaneId()).y);
        Vector2d vthis = new Vector2d(v.getPosition());
        Vehicle ne = this.getNextVehicle(v);
        vthis.sub(s);
        if(ne != null) {
            Vector2d vnext = new Vector2d(ne.getPosition());
            vnext.sub(s);
            if(vthis.length() > vnext.length()) {
                System.out.println("FAIL");
            }
        }

    }

    public void leaveStreet(Vehicle v) {
        this.vehiclesOnStreet.get(v.getCurrentLaneId()).remove(v);
    }

    public Vehicle getNextVehicle(Vehicle v) {
        ArrayList<Vehicle> actualLane = this.vehiclesOnStreet.get(v.getCurrentLaneId());
        for(int i=0;i<actualLane.size();i++) {
            Vehicle vtmp = actualLane.get(i);
            try {
                if( v == vtmp ) {
                    return actualLane.get(i-1);
                }
            } catch(IndexOutOfBoundsException ex) {
                return null;
            }
        }
        return null;
    }

    public Vehicle getFirstVehicle(int laneid) {
        int size = this.vehiclesOnStreet.get(laneid).size();
        try {
            return this.vehiclesOnStreet.get(laneid).get(size-1);
        } catch(IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public double getStreetLength() {
        Vector2d l = new Vector2d(this.end.x,this.end.y);
        l.sub(new Vector2d(this.start.x,this.end.y));
        return l.length();
    }

    @Override
    public int compareTo(Street o) {
        double result1 = Math.toDegrees(Math.atan2(this.getDirection().x ,this.getDirection().y));
        if(result1 < 0.0) {
            result1 = 180-(180-Math.abs(result1));
        } else {
            result1 = 180+(180-result1);
        }
        double result2 = Math.toDegrees(Math.atan2(o.getDirection().x ,o.getDirection().y));
        if(result2 < 0.0) {
            result2 = 180-(180-Math.abs(result2));
        } else {
            result2 = 180+(180-result2);
        }
        if(result1 > result2) {
            return 1;
        } else if(result1 < result2) {
            return -1;
        } else {
            return 0;
        }
    }

    public Intersection getNextIntersection() {
        return this.nextIntersection;
    }

    public void setNextIntersection(Intersection i) {
        this.nextIntersection = i;
    }

    public Point getLaneStart(int laneId) {
        return getLanePoint(this.start,laneId);
    }

    public Point getLaneEnd(int laneId) {
        return getLanePoint(this.end,laneId);
    }

    public int getMaxSpeed() {
        return this.speedlimit;
    }

    private Point getLanePoint(Point p, int laneId) {
       Vector2d tmpV = new Vector2d(this.orthogonal);
        tmpV.normalize();
        tmpV.scale((laneId)*LANE_WIDTH);
        return new Point((int)(p.x+tmpV.x),(int)(p.y+tmpV.y));
    }

    public String toString() {
        return "Street " + this.id + " S("+this.start.getX()+"/"+this.start.getY()+") E("+this.end.getX()+"/"+this.end.getY()+")";
    }

    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle>  allvehicles = new ArrayList<Vehicle>();
        for(ArrayList<Vehicle> av: this.vehiclesOnStreet) {
            allvehicles.addAll(av);
        }
        return allvehicles;
    }


}
