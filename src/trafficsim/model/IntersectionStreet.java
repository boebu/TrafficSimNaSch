package trafficsim.model;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/27/13
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntersectionStreet extends Street {
    //Instance variables
    private int phase;

    /**
     * Create new IntersectionStreetObject
     * @param speedlimit
     * @param lanes
     * @param id
     */
      public IntersectionStreet(int speedlimit, int lanes, String id) {
          super(speedlimit,lanes,id);
          this.phase = -1;
      }

    /**
     * get defined Phase
     * @return phase
     */
    public int getPhase() {
        return this.phase;
    }

    /**
     * set new phase
     * @param p
     */
    public void setPhase(int p) {
        this.phase = p;
    }



}
