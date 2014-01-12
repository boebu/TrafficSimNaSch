package trafficsim.gui;

import trafficsim.main.Sim;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

    // Set Frame Size
    private static final Dimension FRAME_SIZE = new Dimension(500, 500);
    private static JLabel elapsed = new JLabel("Elapsed Time");

    // Initialize
    public static void init() {
        /*
         * FRAME
         */

        JFrame controlFrame = new JFrame("Traffic Simulator Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.pack();
        controlFrame.setLocation(1200, 0);
        controlFrame.setSize(FRAME_SIZE);

        /*
         * CONTROL BUTTON PANEL
         */

        // Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        // Button
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton reset = new JButton("Reset");
        // Button -> Panel
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(reset);

        /*
         * SIMULATOR SPEED PANEL
         */

        JPanel sviPanel = new JPanel(new GridLayout(4,2));
        final JLabel speedText = new JLabel();
        JSlider s = new JSlider(100,4000);
        s.setMajorTickSpacing(1000);
        s.setMinorTickSpacing(100);
        s.setValue(1000);
        s.setSnapToTicks(true);
        s.setSnapToTicks(true);
        JLabel elapsedText = new JLabel("Elapsed Time:");
        elapsed.setText(new SimpleDateFormat("hh:mm:ss z").format(new Date(0)));
        speedText.setText("Speed (" + 1000 / (double) s.getValue() + "x):");
        sviPanel.add(speedText);
        sviPanel.add(s);
        sviPanel.add(elapsedText);
        sviPanel.add(elapsed);

        /*
         *  ADD VEHICLE & INTERSECTION TO PANEL
         */

        JLabel vehicleText = new JLabel("Vehicle Ratio:");
        JSlider v = new JSlider(0,10);
        v.setMajorTickSpacing(1);
        v.setPaintTicks(true);
        v.setValue((int) (Sim.simulator.getNewCarRatio() * 10));
        v.setPaintLabels(true);
        v.setSnapToTicks(true);
        sviPanel.add(vehicleText);
        sviPanel.add(v);
        JLabel intersectionText = new JLabel("Intersection Interate (Ticks):");
        JSlider i = new JSlider(5,20);
        i.setMajorTickSpacing(5);
        i.setMinorTickSpacing(1);
        i.setPaintTicks(true);
        i.setSnapToTicks(true);
        i.setValue(Sim.simulator.getNewIntersectionIterate());
        i.setPaintLabels(true);
        sviPanel.add(intersectionText);
        sviPanel.add(i);

        /*
         * VEHICLE & INTERSECTION PANEL
         */

        JPanel scenPanel = new JPanel(new GridLayout(1,3));
        JButton scen1 = new JButton("Scenario 1");
        JButton scen2 = new JButton("Scenario 2");
        JButton scen3 = new JButton("Scenario 3");
        // Button -> Panel
        scenPanel.add(scen1);
        scenPanel.add(scen2);
        scenPanel.add(scen3);

        /*
         * ADD PANELS TO FRAME
         */

        controlFrame.add(buttonPanel, BorderLayout.NORTH);
        controlFrame.add(sviPanel, BorderLayout.CENTER);
        controlFrame.add(scenPanel, BorderLayout.SOUTH);

        /*
         * SHOW CONTROL FRAME
         */

        controlFrame.setVisible(true);

        /*
         * BUTTON ACTION LISTENERS
         */

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
        scen1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sim.timer.restart();
                Sim.timer.stop();
                Sim.resetElapsedTime();
                elapsed.setText("0");
                Sim.simulator.reset();
                Sim.simulator.resetStreet();
                Sim.setScenario(1);
                Sim.simulator.streetPanel.repaint();
                Sim.simulator.vehiclePanel.repaint();
            }
        });
        scen2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sim.timer.restart();
                Sim.timer.stop();
                Sim.resetElapsedTime();
                elapsed.setText("0");
                Sim.simulator.reset();
                Sim.simulator.resetStreet();
                Sim.setScenario(2);
                Sim.simulator.streetPanel.repaint();
                Sim.simulator.vehiclePanel.repaint();
            }
        });
        scen3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sim.timer.restart();
                Sim.timer.stop();
                Sim.resetElapsedTime();
                elapsed.setText("0");
                Sim.simulator.reset();
                Sim.simulator.resetStreet();
                Sim.setScenario(3);
                Sim.simulator.streetPanel.repaint();
                Sim.simulator.vehiclePanel.repaint();
            }
        });

        /*
         * Slider Actions Listeners
         */

        s.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Sim.timer.setDelay(source.getValue());
                double value = 1000 / (double) source.getValue();
                value = Math.round(value * 100);
                value = value/100;
                speedText.setText("Speed (" + value + "x):");
            }
        });

        v.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Sim.simulator.setNewCarRatio((double) source.getValue() / 10);
            }
        });

        i.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Sim.simulator.setNewIntersectionIterate(source.getValue());
            }
        });

    }

    /*
     * UPDATE ELEMENT LABEL
     */

    public static void updateTime() {
        elapsed.setText(new SimpleDateFormat("hh:mm:ss").format(new Date((long)Sim.getElapsedTimeS()*1000)));
    }
}
