package trafficsim.scenery;

import trafficsim.simulator.Vehicle;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Cell {


    public boolean isEmpty();

    public void setVehicle(Vehicle vehicle);

    public Vehicle getVehicle();

    public void removeVehicle();

    public boolean hasVehicle();

    public void setNext(Cell c);

    public boolean hasNext();

    public Cell next();

    public void setPrevious(Cell c);

    public boolean hasPrevious();

    public Cell previous();

    public void setRight(Cell c);

    public boolean hasRight();

    public Cell right();

    public void setLeft(Cell c);

    public boolean hasLeft();

    public Cell left();
}
