package trafficsim.main;

import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 12/22/13
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimTask extends TimerTask {
    private Simulator simulator;
    public SimTask(Simulator s) {
        this.simulator = s;
    }

    @Override
    public void run() {
        System.out.println("Executed TimerTask at  "+ System.currentTimeMillis());
        simulator.tick();

    }
}
