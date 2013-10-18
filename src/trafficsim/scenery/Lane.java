package trafficsim.scenery;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Lane {

    public int countCells();

    public boolean hasLeft();

    public boolean hasRight();

    public Lane getLeft();

    public Lane getRight();

    public Cell getFirstCell();

    public Cell getLastCell();

    public void addCell(Cell cell);

    public Cell getCell(int index);
}
