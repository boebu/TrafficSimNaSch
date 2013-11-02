package trafficsim.gui;

/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 18.10.13
 * Time: 14:16
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Viewer
{
    public static void main(String[] args) throws InterruptedException
    {
        //Set Cells for Simulator
        final int SIM_WIDTH = 60;
        final int SIM_HEIGHT = 60;

        //Set Cell Size
        final int SIM_CELL_SIZE = 15;

        //Set Simulator Frame Width & Height (+ windows header 23px)
        final int SIM_FRAME_WIDTH = SIM_WIDTH * SIM_CELL_SIZE;
        final int SIM_FRAME_HEIGHT = SIM_HEIGHT * SIM_CELL_SIZE + 23;

        //Set Controller Frame Width & Height (+ windows header 23px)
        final int CON_FRAME_WIDTH = 300;
        final int CON_FRAME_HEIGHT = 300 + 23;

        final int DELAY = 100;

        /*
         * SIMULATOR FRAME
         */

        JFrame simFrame = new JFrame();
        GridLayout simLayout = new GridLayout(SIM_WIDTH, SIM_HEIGHT);

        //Set Parameters to Simulator Window
        simFrame.setSize(SIM_FRAME_WIDTH, SIM_FRAME_HEIGHT);
        simFrame.setTitle("Traffic Simulator");
        simFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simFrame.setLocation(0, 0);
        simFrame.setLayout(simLayout);


        /*
         * CONTROLLER FRAME
         */

        JFrame conFrame = new JFrame();
        GridLayout conLayout = new GridLayout(0,1);

        //Set Parameters to Controller Window
        conFrame.setSize(CON_FRAME_WIDTH, CON_FRAME_HEIGHT);
        conFrame.setTitle("Traffic Simulator Control");
        conFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conFrame.setLocation(SIM_FRAME_WIDTH, 0);
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
                if (playButton.getText() == "Play") {
                    playButton.setText("Pause");
                }
                else {
                    playButton.setText("Play");
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setText("Play");
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
            }
        });

        dawdleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dawdleLabel.setText("Trödelwahrscheinlichkeit: " + dawdleSlider.getValue());
            }
        });

        /*
         * SHOW FRAMEs
         */

        simFrame.setVisible(true);
        conFrame.setVisible(true);

        while (true) {
            Thread.sleep(DELAY);
            //game.nextGeneration();
            //component.repaint();
        }
    }

}
