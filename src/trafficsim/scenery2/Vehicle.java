package trafficsim.scenery2;

import javax.vecmath.Vector2d;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vehicle {

    private static int MAX_SPEED = 5;
    private static int SPEEDMULTIPLIER = 7;
    private int laneId;
    private Vector2d direction;
    private Vector2d position;
    private int speed;
    private Vector2d newPos;
    private Street currentStreet;

    public Vehicle(Street s) {
        this.currentStreet = s;
        s.enterStreet(this);
        this.direction = new Vector2d(s.getDirection());
        this.direction.normalize();
        this.speed = 3;
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
        // switch newpos to pos       yy
        this.position.set(this.newPos);
    }

    public void calcNewPosition() {

        // step 1 nagsch model accelerate + 1 if not < maxspeed
        accelerate();

        // step 2 get maxlength till next vehicle and decelerate if necessary
        Vector2d nextV;
        Vehicle next = this.currentStreet.getNextVehicle(this);
        if(next == null) {
            Point end = this.currentStreet.getEnd();
            nextV = new Vector2d(end.x,end.y);
        }  else {
            nextV = new Vector2d(next.getPosition());
        }
        nextV.sub(this.position);
        Double maxlength = nextV.length();
        if(maxlength < this.speed * SPEEDMULTIPLIER) {
             decelerate(this.speed - (int)Math.floor(maxlength / SPEEDMULTIPLIER));
            // TODO reaches end of street and enter a intersection, think about realizing this
            // when do the effective street change if?? not when calculating, better later
            // use of intersection variable?
        }

        // TODO: step 3 decelerate -1 with P factor = 0.15


        this.direction.normalize();
        this.newPos = new Vector2d(this.direction);
        this.newPos.scale(this.speed*SPEEDMULTIPLIER);
        this.newPos.add(this.position);


    }

    public int getSpeed() {
        return this.speed;
    }

    private void accelerate() {
         if(this.speed < MAX_SPEED) {
             this.speed++;
         }
    }

    private void decelerate(int s) {
        this.speed = this.speed - s;
    }


}
