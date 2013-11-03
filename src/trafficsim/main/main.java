package trafficsim.main;

import trafficsim.gui.Viewer;
import trafficsim.simulator.Simulator;

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
        Viewer view = new Viewer();
        Simulator sim = new Simulator();

        view.createUI();

    }

}
