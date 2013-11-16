package trafficsim.scenery;

import trafficsim.simulator.Vehicle2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/10/13
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class StraightStreetElement implements StreetElement {


    private static int MAX_SPEED = 3;

    private int length;
    private StreetElement next;
    private StreetElement previous;
    private StreetElement left;
    private StreetElement right;
    private Direction direction;
    private ArrayList<ArrayList<Vehicle2>> lanesInDirection;



    private ArrayList<ArrayList<Vehicle2>> lanesOffDirection;
    private ElementType type = ElementType.STRAIGHT;

    // XXX: need a start direction for each element
    // define size of a Element

    public StraightStreetElement(StreetElement previous, int numOfLanesInDirection, int numOfLanesOffDirection, int length) {
        this.length = length;
        this.previous = previous;
        this.previous.setNext(this);

        this.direction = DirectionHelper.calculateDirection(previous, this);
        this.lanesInDirection = new ArrayList<ArrayList<Vehicle2>>(numOfLanesInDirection);
        for(int i=0;i<numOfLanesInDirection;i++) {
            this.lanesInDirection.add(new ArrayList<Vehicle2>());
        }
        this.lanesOffDirection = new ArrayList<ArrayList<Vehicle2>>(numOfLanesOffDirection);
        for(int i=0;i<numOfLanesOffDirection;i++) {
            this.lanesOffDirection.add(new ArrayList<Vehicle2>());
        }
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SPEED;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
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
    public boolean hasLeft() {
        if (left == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public StreetElement left() {
        return left;  //To change body of implemented methods use File | Settings | File Templates.
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
    public void setNext(StreetElement next) {
        this.next = next;
    }

    @Override
    public void setPrevious(StreetElement previous) {
        this.previous = previous;
    }

    @Override
    public void setLeft(StreetElement left) {
        this.left = left;
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
    public void moveVehicles() {
        //To change body of implemented methods use File | Settings | File Templates.
        moveVehiclesInLane(lanesInDirection);
    }

    @Override
    public void calculateNextRound() {

        calculateMovement(lanesInDirection.iterator());

    }

    @Override
    public ArrayList<ArrayList<Vehicle2>> getLanes(StreetElement oldElement) {
        if(oldElement == previous) {
            return lanesInDirection;
        } else {
            return lanesOffDirection;
        }
    }

    private void calculateMovement(Iterator<ArrayList<Vehicle2>> laneit) {

        int j=0;
        while(laneit.hasNext()) {

            ArrayList<Vehicle2> lane = laneit.next();

            for(int i=0;i<(lane.size()-1);i++) {
                lane.get(i).setNewPosition(lane.get(i).calculateMovement(lane.get(i+1).getPosition()));
            }

            int last = lane.size()-1;
            if(last >= 0)  {
            if(lane.get(last).getPosition()+lane.get(last).getMaxMovement() <= length) {
                lane.get(last).setNewPosition(lane.get(last).calculateMovement(length));
            } else {
                int newpos;
                if(!next.getLanes(this).get(j).isEmpty()) {  //is no next??? nullpointer here
                    newpos = lane.get(last).calculateMovement(next.getLanes(this).get(j).get(0).getPosition());
                } else {
                    newpos = lane.get(last).calculateMovement(next.getLength());
                }
                lane.get(last).setNewPosition(newpos - length);
                next.getLanes(this).get(j).add(0, lane.remove(last));
            }
            j++;
            }

        }
    }

    private void moveVehiclesInLane(ArrayList<ArrayList<Vehicle2>> lanes) {
        for(ArrayList<Vehicle2> lane: lanes) {
            for(Vehicle2 v:lane) {
                v.move();
            }
        }
    }

// rules inside Element
    // Vehicle2 needs Direction
//    private int calculateMovement(Vehicle2 v) {
//        if(7*v.getSpeed() + v.getPosition() < getLength()) {
//           v.setPosition(7*v.getSpeed() + v.getPosition());
//        } else {
//           v.setPosition(7*v.getSpeed() + v.getPosition() - getLength());
//           next.setVehicle2(LANEID,v);
//
//        }
//        return (int) (7*v.getSpeed());
//
//    }


}
