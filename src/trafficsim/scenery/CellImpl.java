package trafficsim.scenery;

import trafficsim.simulator.Vehicle;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class CellImpl implements Cell {

    Vehicle vehicle;
    Cell next;
    Cell previous;
    Cell right;
    Cell left;


    // define size of a Cell in [m]
    static double LENGTH = 7.5;


    @Override
    public void setPrevious(Cell c) {
        this.previous = c;
    }

    @Override
    public boolean hasPrevious() {
        if (previous == null) {
            return false;
        }  else {
            return true;
        }
    }

    @Override
    public Cell previous() {
        return previous;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void removeVehicle() {
        this.vehicle = null;
    }

    @Override
    public boolean hasVehicle() {
        if (vehicle == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        if (hasVehicle())    {
            return false;
        }               else {
            return true;
        }

    }

    @Override
    public boolean hasNext() {
        if (next == null) {
            return false;
        }  else {
            return true;
        }
    }

    @Override
    public Cell next() {
        return next;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setNext(Cell c) {
        this.next = c;

    }

    @Override
    public void setRight(Cell c) {
        this.right = c;
    }

    @Override
    public void setLeft(Cell c) {
        this.left = c;
    }


    @Override
    public boolean hasRight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Cell right() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasLeft() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Cell left() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
