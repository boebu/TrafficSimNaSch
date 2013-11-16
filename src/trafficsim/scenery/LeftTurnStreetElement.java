package trafficsim.scenery;

import trafficsim.simulator.Vehicle;
import trafficsim.simulator.Vehicle2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/11/13
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class LeftTurnStreetElement implements StreetElement {

    private static int LENGTH = 250;
    private static int MAX_SPEED = 4;

    private StreetElement next;
    private StreetElement previous;
    private Direction direction;
    private ArrayList<ArrayList<Vehicle>> lanesInDirection;
    private ArrayList<ArrayList<Vehicle>> lanesOffDirection;

    @Override
    public void calculateNextRound() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void moveVehicles() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private ElementType type = ElementType.LEFTTURN;

    public LeftTurnStreetElement(StreetElement previous, int numOfLanesInDirection, int numOfLanesOffDirection) {

        this.previous = previous;
        this.previous.setNext(this);

        this.direction = DirectionHelper.calculateDirection(previous, this);
        this.lanesInDirection = new ArrayList<ArrayList<Vehicle>>(numOfLanesInDirection);
        this.lanesOffDirection = new ArrayList<ArrayList<Vehicle>>(numOfLanesOffDirection);
    }

    @Override
    public boolean hasNext() {
        if (next == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public StreetElement next() {
        return next;
    }

    @Override
    public void setNext(StreetElement next) {
        this.next = next;
    }

    @Override
    public boolean hasPrevious() {
        if (previous == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public StreetElement previous() {
        return previous;
    }

    @Override
    public void setPrevious(StreetElement previous) {
        this.previous = previous;
    }

    @Override
    public boolean hasLeft() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StreetElement left() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLeft(StreetElement left) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasRight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StreetElement right() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setRight(StreetElement right) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ElementType getType() {
        return type;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Vehicle2[] getVehicles() {
        return new Vehicle2[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Direction getDirection() {
        return direction;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList<ArrayList<Vehicle2>> getLanes(StreetElement oldElement) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getLength() {
        return LENGTH;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SPEED;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
