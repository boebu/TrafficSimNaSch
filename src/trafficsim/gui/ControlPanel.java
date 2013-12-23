package trafficsim.gui;

import trafficsim.main.Sim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 12/23/13
 * Time: 2:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class ControlPanel {

    private static final Dimension FRAME_SIZE = new Dimension(300, 600);
    private static JLabel elapsed = new JLabel("Elapsed Time");

    public static void init() {
        JFrame controlFrame = new JFrame("Traffic Simulation Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.pack();
        controlFrame.setLocation(0, 0);
        controlFrame.setSize(FRAME_SIZE);
        controlFrame.setVisible(true);


        JPanel controlPanel = new JPanel(new GridLayout(1,1));
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton reset = new JButton("Reset");

        controlPanel.add(start);
        controlPanel.add(stop);
        controlPanel.add(reset);
        elapsed.setText(new SimpleDateFormat("hh:mm:ss z").format(new Date(0)));
        controlPanel.add(elapsed);
        controlFrame.setVisible(true);
        controlFrame.add(controlPanel);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sim.timer.start();
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sim.timer.stop();
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sim.timer.restart();
                Sim.timer.stop();
                Sim.resetElapsedTime();
                elapsed.setText("0");
                Sim.simulator.reset();
                Sim.simulator.vehiclePanel.repaint();
            }
        });


    }

    public static void updateTime() {
        elapsed.setText(new SimpleDateFormat("hh:mm:ss").format(new Date((long)Sim.getElapsedTimeS()*1000)));
    }
}
