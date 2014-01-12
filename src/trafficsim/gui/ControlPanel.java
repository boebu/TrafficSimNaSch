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
        // Frame
        JFrame controlFrame = new JFrame("Traffic Simulator Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.pack();
        controlFrame.setLocation(1200, 0);
        controlFrame.setSize(FRAME_SIZE);

        // Panel (Start, Stop, Reset Buttons)
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        JButton start = createButton("Start", buttonPanel);
        JButton stop = createButton("Stop", buttonPanel);
        JButton reset = createButton("Reset", buttonPanel);

        // Panel (Time, Speed, Ratio, Iterate)
        JPanel sviPanel = new JPanel(new GridLayout(4,2));
        createText("Elapsed Time:", sviPanel);
        elapsed.setText(new SimpleDateFormat("hh:mm:ss z").format(new Date(0)));
        sviPanel.add(elapsed);
        final JLabel speedText = createText("", sviPanel);
        JSlider s = createSlider(100, 4000, 1000, 1000, 100, false, sviPanel);
        speedText.setText("Speed (" + 1000 / (double) s.getValue() + "x):");
        createText("Vehicle Ratio:", sviPanel);
        JSlider v = createSlider(0, 10, (int) (Sim.simulator.getNewCarRatio() * 10), 1, 0, true, sviPanel);
        createText("Intersection Iterate:", sviPanel);
        JSlider i = createSlider(5, 20, Sim.simulator.getNewIntersectionIterate(), 5, 1, true, sviPanel);

        // Panel (Scenario Buttons)
        JPanel scenPanel = new JPanel(new GridLayout(1,3));
        JButton scen1 = createButton("Scenario 1", scenPanel);
        JButton scen2 = createButton("Scenario 2", scenPanel);
        JButton scen3 = createButton("Scenario 3", scenPanel);

        // Add Panels to Frame
        controlFrame.add(buttonPanel, BorderLayout.NORTH);
        controlFrame.add(sviPanel, BorderLayout.CENTER);
        controlFrame.add(scenPanel, BorderLayout.SOUTH);

        controlFrame.setVisible(true);

        // Button Listeners
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
                loadScenario(1);
            }
        });
        scen2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadScenario(2);
            }
        });
        scen3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadScenario(3);
            }
        });

        // Slider Listeners
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

    // Create a new Text Label
    private static JLabel createText(String text, JPanel panel){
        JLabel label = new JLabel(text);
        panel.add(label);
        return label;
    }

    // Create a new Button
    private static JButton createButton(String text, JPanel panel){
        JButton button = new JButton(text);
        panel.add(button);
        return button;
    }

    // Create a new Slider
    private static JSlider createSlider(int min, int max, int value, int majorTick, int minorTick, boolean labels, JPanel panel){
        JSlider slider = new JSlider(min, max);
        slider.setValue(value);
        slider.setMajorTickSpacing(majorTick);
        slider.setMinorTickSpacing(minorTick);
        slider.setPaintTicks(true);
        slider.setPaintLabels(labels);
        slider.setSnapToTicks(true);
        slider.setSnapToTicks(true);
        panel.add(slider);
        return slider;
    }

    // Load Scenario
    public static void loadScenario(int scen){
        Sim.timer.restart();
        Sim.timer.stop();
        Sim.resetElapsedTime();
        elapsed.setText("0");
        Sim.simulator.reset();
        Sim.simulator.resetStreet();
        Sim.setScenario(scen);
        Sim.simulator.streetPanel.repaint();
        Sim.simulator.vehiclePanel.repaint();
    }

    // Update Elapsed Label
    public static void updateTime() {
        elapsed.setText(new SimpleDateFormat("hh:mm:ss").format(new Date((long)Sim.getElapsedTimeS()*1000)));
    }
}
