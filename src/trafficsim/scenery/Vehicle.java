package trafficsim.scenery;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Vehicle {

    public int getSpeed();

    public void setSpeed();

    public Cell getCell();

    public void setCell(Cell cell);


}
