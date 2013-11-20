package trafficsim.main;

import trafficsim.gui.SimulatorController;
import trafficsim.gui.SimulatorPanel;
import trafficsim.gui.SimulatorView;
import trafficsim.simulator.Driver;
import trafficsim.simulator.VehicleImpl;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/8/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Simulator {

    static Scenario scenario;
    static ArrayList<Driver> drivers = new ArrayList<Driver>();

    public static void main(String[] args) throws InterruptedException
    {
       scenario = new ScenarioImpl("Scenario Nagsch1", 1, 1, 750);
       SimulatorPanel panl = new SimulatorPanel();
       SimulatorController cont = new SimulatorController();
       SimulatorView view = new SimulatorView();

        panl.setScen(scenario);

        cont.create();
        view.create(panl);

        drivers.add(new VehicleImpl(scenario.getLane(0).getFirstCell()).getDriver());

        while (true) {

            Thread.sleep(100);
            panl.repaint();


            if (cont.getStatus() == cont.STATUS_PLAY) {
                if(Math.random()<0.4) {
                    drivers.add(new VehicleImpl(scenario.getLane(0).getFirstCell()).getDriver());
                }

                for(Driver driver:drivers)  {
                    driver.drive();
                }
            }



        }

    }


}
