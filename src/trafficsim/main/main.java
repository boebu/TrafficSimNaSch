package trafficsim.main;

import trafficsim.gui.SimulatorPanel;
import trafficsim.gui.SimulatorController;
import trafficsim.gui.SimulatorView;
import trafficsim.scenery.Scenario;
import trafficsim.scenery.ScenarioImpl;
import trafficsim.simulator.Vehicle;
import trafficsim.simulator.VehicleImpl;


/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 02.11.13
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class main {

    public static void main(String[] args) throws InterruptedException
    {
        final String NAME = "Scenario 1";
        final int ID = 1;
        final int LANES = 3;
        final double CELL_LENGTH = 7.5;
        final int CELLS = 60;
        final int LENGTH = (int) (CELL_LENGTH * CELLS);
        final int DELAY = 1000;

        int test = 0;

        Scenario scen = new ScenarioImpl(NAME, ID, LANES, LENGTH);
        SimulatorPanel panl = new SimulatorPanel();
        SimulatorController cont = new SimulatorController();
        SimulatorView view = new SimulatorView();

        panl.setScen(scen);

        cont.create();
        view.create(panl);

        Vehicle v1 = new VehicleImpl(scen.getLane(0).getFirstCell());
        Vehicle v2 = new VehicleImpl(scen.getLane(1).getFirstCell());

        while (true) {
            test = test + 5;
            if (test >= CELLS) {
                test = 0;
            }
            Thread.sleep(DELAY);

            v1.move(scen.getLane(0).getCells().get(test));
            v2.move(scen.getLane(1).getCells().get(test/2));

            panl.repaint();
        }
    }

}
