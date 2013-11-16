package trafficsim.scenery;

import trafficsim.simulator.Vehicle;
import trafficsim.simulator.Vehicle2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/11/13
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class StartStreetElement implements StreetElement {

    private static int LENGTH = 100;
    private static int MAX_SPEED = 5;

    private StreetElement next;
    private Direction direction;
    private StreetElement previous = null;
    private ElementType type = ElementType.STARTPOINT;

    public StartStreetElement(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SPEED;
    }

    @Override
    public void calculateNextRound() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList<ArrayList<Vehicle2>> getLanes(StreetElement oldElement) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void moveVehicles() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setNext(StreetElement next) {
        this.next = next;
    }

    @Override
    public void setPrevious(StreetElement previous) {

    }

    @Override
    public void setLeft(StreetElement left) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setRight(StreetElement right) {
        //To change body of implemented methods use File | Settings | File Templates.
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
    public boolean hasPrevious() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StreetElement previous() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
    public boolean hasRight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StreetElement right() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ElementType getType() {
        return type;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Vehicle2[] getVehicles() {
        return new Vehicle2[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
