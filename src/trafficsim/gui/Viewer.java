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

public class Viewer {

    private static final Dimension SIM_PANEL_SIZE = new Dimension(900, 900);
    private static final Dimension CON_PANEL_SIZE = new Dimension(300, 300);

    public static void createUI() {
        DrawRectPanel drawRectPanel = new DrawRectPanel();
        createSimFrame(drawRectPanel);
        createConFrame(drawRectPanel);
    }

    private static void createSimFrame(final DrawRectPanel drawRectPanel) {
        drawRectPanel.setPreferredSize(SIM_PANEL_SIZE);

        /*final DrawPanelController gppController =
                new DrawPanelController(drawRectPanel);*/

        JFrame simFrame = new JFrame("Traffic Simulator");
        simFrame.getContentPane().add(drawRectPanel, BorderLayout.CENTER);
        //simFrame.getContentPane().add(gppController, BorderLayout.SOUTH);
        simFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simFrame.pack();
        simFrame.setLocation(0, 0);
        simFrame.setVisible(true);
    }

    private static void createConFrame(final DrawRectPanel drawRectPanel) {
        JFrame conFrame = new JFrame("Traffic Simulator Control");
        conFrame.setSize(CON_PANEL_SIZE);
        GridLayout conLayout = new GridLayout(0,1);

        conFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conFrame.setLocation(SIM_PANEL_SIZE.width, 0);
        conFrame.setLayout(conLayout);
        conFrame.setVisible(true);

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
                repaint(drawRectPanel);
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

    }

    public static void repaint(DrawRectPanel drawPanel) {
        drawPanel.setCellColor(Color.RED);
        drawPanel.repaint();
    }


    static class DrawRectPanel extends JPanel {
        private int cells = 60;
        private int lanes = 2;
        private Color cellColor = Color.black;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(cellColor);
            for (int i = 0; i < cells; i++) {
                for (int j = 0; j < lanes; j++) {
                    g.drawRect(i * 15, j * 15, 15, 15);
                }
            }
        }

        public void setCells(int cells) {
            this.cells = cells;
        }

        public void setLanes(int lanes) {
            this.lanes = lanes;
        }

        public void setCellColor(Color cellColor) {
            this.cellColor = cellColor;
        }
    }

    /*static class DrawPanelController extends JPanel {
        private DrawRectPanel drPanel;
        private JSlider widthSlider = new JSlider(0, 60, 60);
        private JSlider heightSlider = new JSlider(0, 60, 60);

        public DrawPanelController(DrawRectPanel drPanel) {
            this.drPanel = drPanel;

            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            add(new JLabel("Width"));
            add(widthSlider);
            add(new JLabel("Height"));
            add(heightSlider);

            SliderListener sliderListener = new SliderListener();
            widthSlider.addChangeListener(sliderListener);
            heightSlider.addChangeListener(sliderListener);
        }

        private class SliderListener implements ChangeListener {
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                if (slider == widthSlider) {
                    drPanel.setCells(slider.getValue());
                } else {
                    drPanel.setLanes(slider.getValue());
                }
                drPanel.repaint();
            }
        }
    }*/
}