package trafficsim.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 06.11.13
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class SimulatorView {

    private static final Dimension PANEL_SIZE = new Dimension(900, 900);

    public static void create(SimulatorPanel simPanel) {
        simPanel.setPreferredSize(PANEL_SIZE);

        JFrame simFrame = new JFrame("Traffic Simulator");
        simFrame.getContentPane().add(simPanel, BorderLayout.CENTER);
        simFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simFrame.pack();
        simFrame.setLocation(0, 0);
        simFrame.setVisible(true);

        simFrame.getContentPane().add(simPanel);
    }
}
