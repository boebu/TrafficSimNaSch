package trafficsim.scenery;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 10/18/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Scenario {

    public Lane[] getLanes();

    public void addLane(Lane lane);

    public Lane getLane(int index);

}
