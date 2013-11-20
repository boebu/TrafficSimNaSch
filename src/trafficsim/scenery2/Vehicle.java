package trafficsim.scenery2;

import javax.vecmath.Vector2d;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vehicle {

    private int laneId;
    private Vector2d direction;
    private Vector2d position;
    private Street currentStreet;

    public Vehicle(Street s) {
        this.currentStreet = s;
        s.enterStreet(this);
        this.direction = s.getDirection();

        this.position = new Vector2d(s.getStart().getX(),s.getStart().getY());
        this.laneId = 0;
    }


    public Vector2d getDirection() {
        return this.direction;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public int getCurrentLaneId() {
        return this.laneId;
    }

    public void move() {
        this.position = new Vector2d(this.position.x,this.position.y+50);
    }
}
