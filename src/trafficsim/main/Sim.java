package trafficsim.main;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 12/22/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sim {
    public static Timer timer;
    public static int defaultinterval = 1000;
    public static Simulator simulator;
    public static int x = 0;

    public Sim() {

    }

    public static void main(String args[]) {
        Sim.simulator = new Simulator();
        Sim.simulator.initScenery();
        Sim.simulator.initGUI();

        timer = new Timer(Sim.defaultinterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sim.simulator.tick();
                System.out.println(Sim.timer);
            }
        });
        timer.start();

    }


}
