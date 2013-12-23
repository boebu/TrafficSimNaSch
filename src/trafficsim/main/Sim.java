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
    public static int DEFAULT_INTERVAL = 1000;
    public static Simulator simulator;
    public static int x = 0;
    private static double elapsedTimeS = 0;
    // 1s => 1s => 1s
    // 2x => 2s => 1/2s
    // 5x => 5s => 1/5s
    // 1/2x => 0.5s = 2s

    public Sim() {

    }

    public static double getElapsedTimeS() {
        return elapsedTimeS;
    }

    public static void resetElapsedTime() {
        elapsedTimeS = 0;
    }

    public static void main(String args[]) {
        Sim.simulator = new Simulator();
        Sim.simulator.initScenery();
        Sim.simulator.initGUI();
                   // Sim.DEFAULT_INTERVAL
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTimeS += DEFAULT_INTERVAL/timer.getDelay();
                Sim.simulator.tick();
                System.out.println(elapsedTimeS);
            }
        });
        //timer.start();

    }


}
