package trafficsim.scenery;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Cell {

    public boolean hasVehicle();

    public Vehicle getVehicle();

    public boolean hasNext();

    public Cell getNext();

}
