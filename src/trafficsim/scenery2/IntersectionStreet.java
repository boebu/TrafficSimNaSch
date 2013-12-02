package trafficsim.scenery2;

import javax.vecmath.Vector2d;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/27/13
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntersectionStreet extends Street {

    private int phase;

      public IntersectionStreet(int speedlimit, int lanes, String id) {
          super(speedlimit,lanes,id);
          this.phase = -1;
      }

    public int getPhase() {
        return this.phase;
    }

    public void setPhase(int p) {
        this.phase = p;
    }



}
