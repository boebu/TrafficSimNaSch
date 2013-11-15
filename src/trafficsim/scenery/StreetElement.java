package trafficsim.scenery;

import trafficsim.simulator.Vehicle2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/10/13
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StreetElement {

    // navigation between StreetCells
    public boolean hasNext();

    public StreetElement next();

    public void setNext(StreetElement next);

    public boolean hasPrevious();

    public StreetElement previous();

    public void setPrevious(StreetElement previous);

    public boolean hasLeft();

    public StreetElement left();

    public void setLeft(StreetElement left);

    public boolean hasRight();

    public StreetElement right();

    public void setRight(StreetElement right);

    //Get StreetType
    public ElementType getType();

    // get czrrent Vehicle22s
    public Vehicle2[] getVehicles();

    public Direction getDirection();

    public int getLength();

    public int getMaxSpeed();

    public void calculateNextRound();

    public void moveVehicles();

    public ArrayList<ArrayList<Vehicle2>> getLanes(StreetElement oldElement);

}
