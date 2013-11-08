package trafficsim.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 06.11.13
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
public class SimulatorController {

    //Windows Size
    private final Dimension PANEL_SIZE = new Dimension(300, 300);
    public final int STATUS_STOP = 0;
    public final int STATUS_PLAY = 1;
    public final int STATUS_PAUSE = 2;
    //Status of simulation stopped = 0, running = 1, paused = 2
    private int status = 0;
    //Speed of Simulation
    private int speed = 0;
    //Percentage of Dawdler's
    private int dawdle = 0;

    public void create() {
        JFrame conFrame = new JFrame("Traffic Simulator Control");
        conFrame.setSize(PANEL_SIZE);
        GridLayout conLayout = new GridLayout(0,1);

        conFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conFrame.setLocation(900, 0);
        conFrame.setLayout(conLayout);

        /*
         * BUTTONs
         */

        final JButton playButton = new JButton("Play");
        final JButton stopButton = new JButton("Stop");
        conFrame.add(playButton);
        conFrame.add(stopButton);

        playButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getStatus() == STATUS_PLAY) {
                    playButton.setText("Play");
                    setStatus(STATUS_PAUSE);
                }
                else {
                    playButton.setText("Pause");
                    setStatus(STATUS_PLAY);
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setText("Play");
                setStatus(STATUS_STOP);

            }
        });

        /*
         * SLIDERs
         */

        final JSlider speedSlider = new JSlider(0, 100, 50);
        final JLabel speedLabel = new JLabel("Geschwindigkeit: " + speedSlider.getValue());
        final JSlider dawdleSlider = new JSlider(0, 100, 50);
        final JLabel dawdleLabel = new JLabel("Trödelwahrscheinlichkeit: " + dawdleSlider.getValue());
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setMajorTickSpacing(20);
        dawdleSlider.setPaintTicks(true);
        dawdleSlider.setPaintLabels(true);
        dawdleSlider.setMajorTickSpacing(20);
        conFrame.add(speedLabel);
        conFrame.add(speedSlider);
        conFrame.add(dawdleLabel);
        conFrame.add(dawdleSlider);

        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speedLabel.setText("Geschwindigkeit: " + speedSlider.getValue());
                setSpeed(speedSlider.getValue());
            }
        });

        dawdleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dawdleLabel.setText("Trödelwahrscheinlichkeit: " + dawdleSlider.getValue());
                setDawdle(dawdleSlider.getValue());
            }
        });

        conFrame.setVisible(true);

    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDawdle() {
        return this.dawdle;
    }

    public void setDawdle(int dawdle) {
        this.dawdle = dawdle;
    }

}
