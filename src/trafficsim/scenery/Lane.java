package trafficsim.scenery;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Lane {

    public int countCells();

    public Cell getFirstCell();

    public Cell getLastCell();

    public boolean hasCell(Cell c);

    public ArrayList<Cell> getCells();

}
