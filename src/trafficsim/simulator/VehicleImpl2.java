package trafficsim.simulator;

import trafficsim.scenery.Direction;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/13/13
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class VehicleImpl2 implements Vehicle2 {

    static final int SPEEDMULTIPIER = 7;
    static final int MAXSPEED = 5;
    int position;
    int newposition;
    int speed;
    boolean moved;

    public VehicleImpl2(int speed, int position) {
        this.speed = speed;
        this.position = position;
    }

    @Override
    public int getSpeed() {
        return this.speed;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMaxSpeed() {
        return MAXSPEED;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void accelerate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void decelerate(int s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Driver getDriver() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getPosition() {
        return this.position;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setNewPosition(int position) {
        this.moved = false;
        this.newposition = position;
    }

    @Override
    public int calculateMovement(int maxPosMovement) {
        // do rules of nagsch model for speed
        return this.position + getMaxMovement();

    }

    @Override
    public void move() {
        if(!moved) {
        this.position = this.newposition;
        this.moved = true;
        }
    }

    @Override
    public int getMaxMovement() {
        return MAXSPEED * SPEEDMULTIPIER;
    }
}
