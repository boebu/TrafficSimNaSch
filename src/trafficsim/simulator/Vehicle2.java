package trafficsim.simulator;


import trafficsim.scenery.Direction;


/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/13/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Vehicle2 {
    public int getSpeed();

    public int getMaxSpeed();

    public void accelerate();

    public void decelerate(int s);

    public Driver getDriver();

    public int getPosition();

    public void setNewPosition(int position);

    public int calculateMovement(int maxPosMovement);

    public int getMaxMovement();

    public void move();


}
