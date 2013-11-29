package trafficsim.scenery2;


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

    private int speedlimit;


    private int lanes = 1;



    private Vector2d direction;
    private Point start;
    private Point end;
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

    public Point getStart() {
        return this.start;
    }

    public String getId() {
        return this.id;
    }

    public Point getEnd() {
        return this.end;
    }

    public int getLanes() {
        return this.lanes;
    }

    public void calculateDirection() {
        Vector2d start = new Vector2d(this.start.getX(),this.start.getY());
        this.direction = new Vector2d(this.end.getX(), this.end.getY());
        this.direction.sub(start);
        this.direction.normalize();
    }

    public Vector2d getDirection() {
        return this.direction;
    }

    public void enterStreet(Vehicle v) {
        // need direction here for correct lane selection
        this.vehiclesOnStreet.get(v.getCurrentLaneId()).add(v);
    }

    public void leaveStreet(Vehicle v) {
        this.vehiclesOnStreet.get(v.getCurrentLaneId()).remove(v);
    }

    public Vehicle getNextVehicle(Vehicle v) {
        ArrayList<Vehicle> actualLane = this.vehiclesOnStreet.get(v.getCurrentLaneId());
        for(int i=0;i<actualLane.size();i++) {
            try {
                Vehicle vtmp = actualLane.get(i);
                if( v == vtmp ) {
                    return actualLane.get(i+1);
                }
            } catch(ArrayIndexOutOfBoundsException ex) {
                return null;
            }
        }
        return null;
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


}
