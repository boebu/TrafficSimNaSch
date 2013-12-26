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

    private static final Dimension FRAME_SIZE = new Dimension(1200, 1000);

    public static void create(StreetPanel streetPanel, VehiclePanel vehiclePanel) {
        streetPanel.setSize(FRAME_SIZE);
        vehiclePanel.setSize(FRAME_SIZE);

        JFrame simFrame = new JFrame("Traffic Simulator");
        simFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simFrame.pack();
        simFrame.setLocation(0, 0);
        simFrame.setSize(FRAME_SIZE);
        simFrame.setVisible(true);

        simFrame.add(vehiclePanel);
        simFrame.add(streetPanel);

    }
}
