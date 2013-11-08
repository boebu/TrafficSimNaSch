package trafficsim.main;

import trafficsim.gui.SimulatorPanel;
import trafficsim.gui.SimulatorController;
import trafficsim.gui.SimulatorView;
import trafficsim.scenery.Scenario;
import trafficsim.scenery.ScenarioImpl;

/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 02.11.13
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class Main {

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

        while (true) {

            Thread.sleep(DELAY);

            if (cont.getStatus() == cont.STATUS_PLAY) {

                test = test + 5;

                if (test >= CELLS) {
                    test = 0;
                }
            }

            panl.repaint();
        }
    }

}
