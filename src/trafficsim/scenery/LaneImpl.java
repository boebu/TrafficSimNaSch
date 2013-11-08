package trafficsim.scenery;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/25/13
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class LaneImpl implements Lane{

    ArrayList<Cell> cells = new ArrayList<Cell>();
    int nrofcells;

    public LaneImpl(int length) {
        this.nrofcells = (int) (length / CellImpl.LENGTH);
        for(int i=0;i<nrofcells;i++)  {
            cells.add(i,new CellImpl());
            if (i>0) {
                cells.get(i-1).setNext(cells.get(i));
                cells.get(i).setPrevious(cells.get(i-1));
            }

        }
    }

    @Override
    public int countCells() {
        return nrofcells;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Cell getFirstCell() {
        return cells.get(0);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Cell getLastCell() {
        return cells.get(cells.size()-1);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasCell(Cell c) {
        if (this.cells.indexOf(c) == -1) {
            return false;
        }  else {
            return true;
        }
    }

    public ArrayList<Cell> getCells() {
       return cells;
    }
}
