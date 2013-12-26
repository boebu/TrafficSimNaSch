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

    private static final Dimension FRAME_SIZE = new Dimension(300, 300);
    private static JLabel elapsed = new JLabel("Elapsed Time");

    public static void init() {
        JFrame controlFrame = new JFrame("Traffic Simulator Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.pack();
        controlFrame.setLocation(1200, 0);
        controlFrame.setSize(FRAME_SIZE);

        // CONTROL BUTTON PANEL
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton reset = new JButton("Reset");
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(reset);

        // SIMULATOR SPEED PANEL
        JPanel speedPanel = new JPanel(new GridLayout(2,2));
        final JLabel speedText = new JLabel();
        JSlider s = new JSlider(100,4000);
        s.setMajorTickSpacing(1000);
        s.setMinorTickSpacing(100);
        s.setInverted(true);
        s.setValue(1000);
        s.setSnapToTicks(true);
        JLabel elapsedText = new JLabel("Elapsed Time:");
        elapsed.setText(new SimpleDateFormat("hh:mm:ss z").format(new Date(0)));
        speedText.setText("Speed (" + 1000 / (double) s.getValue() + "x):");
        speedPanel.add(speedText);
        speedPanel.add(s);
        speedPanel.add(elapsedText);
        speedPanel.add(elapsed);

        // VEHICLE PANEL
        JPanel vehiclePanel = new JPanel(new GridLayout(1,2));
        JLabel vehicleText = new JLabel("Vehicle Ratio:");
        JSlider v = new JSlider(0,10);
        v.setMajorTickSpacing(1);
        v.setPaintTicks(true);
        v.setValue((int) (Sim.simulator.getNewCarRatio() * 10));
        v.setPaintLabels(true);
        vehiclePanel.add(vehicleText);
        vehiclePanel.add(v);

        // ADD PANELS TO FRAME
        controlFrame.add(buttonPanel, BorderLayout.NORTH);
        controlFrame.add(speedPanel, BorderLayout.CENTER);
        controlFrame.add(vehiclePanel, BorderLayout.SOUTH);
        controlFrame.setVisible(true);


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

        s.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Sim.timer.setDelay(source.getValue());
                System.out.println(Sim.timer.getDelay());
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


    }

    public static void updateTime() {
        elapsed.setText(new SimpleDateFormat("hh:mm:ss").format(new Date((long)Sim.getElapsedTimeS()*1000)));
    }
}
