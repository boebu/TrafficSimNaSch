package trafficsim.scenery;

import trafficsim.simulator.Vehicle2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/13/13
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class CrossStreetElement implements StreetElement  {

    private static int MAX_SPEED = 3;

    private int length;
    private StreetElement next;
    private StreetElement previous;
    private StreetElement left;
    private StreetElement right;
    private Direction direction;
    private ElementType type = ElementType.CROSS;

    private ArrayList<ArrayList<Vehicle2>> lanesInDirection;
    private ArrayList<ArrayList<Vehicle2>> lanesOffDirection;
    private ArrayList<ArrayList<Vehicle2>> lanesInLeft;
    private ArrayList<ArrayList<Vehicle2>> lanesOffLeft;
    private ArrayList<ArrayList<Vehicle2>> lanesInRight;
    private ArrayList<ArrayList<Vehicle2>> lanesOffRight;

    // need a place to store Vehicle2 during transformation


    public CrossStreetElement(StreetElement previous,int numOfLanesInDirection, int numOfLanesOffDirection,boolean leftExit, boolean rightExit) {
        this.previous = previous;
        this.previous.setNext(this);
        this.direction = DirectionHelper.calculateDirection(previous, this);

        this.lanesInDirection = new ArrayList<ArrayList<Vehicle2>>(numOfLanesInDirection);
        this.lanesOffDirection = new ArrayList<ArrayList<Vehicle2>>(numOfLanesOffDirection);
        if (leftExit) {
            this.lanesInLeft = new ArrayList<ArrayList<Vehicle2>>(numOfLanesInDirection);
            this.lanesOffLeft = new ArrayList<ArrayList<Vehicle2>>(numOfLanesOffDirection);
        }
        if (rightExit) {
            this.lanesInRight = new ArrayList<ArrayList<Vehicle2>>(numOfLanesInDirection);
            this.lanesOffRight = new ArrayList<ArrayList<Vehicle2>>(numOfLanesOffDirection);
        }

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
        return next;  //To change body of implemented methods use File | Settings | File Templates.
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
        return previous;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPrevious(StreetElement previous) {
        this.previous = previous;
    }

    @Override
    public boolean hasLeft() {
        if (left == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public StreetElement left() {
        return left;
    }

    @Override
    public void setLeft(StreetElement left) {
        this.left = left;
    }

    @Override
    public boolean hasRight() {
        if (right == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public StreetElement right() {
        return right;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setRight(StreetElement right) {
        this.right = right;
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
    public int getLength() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void moveVehicles() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMaxSpeed() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void calculateNextRound() {

    }

    @Override
    public ArrayList<ArrayList<Vehicle2>> getLanes(StreetElement oldElement) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
