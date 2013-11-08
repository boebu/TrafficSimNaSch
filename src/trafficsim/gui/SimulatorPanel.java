package trafficsim.gui;

import trafficsim.scenery.Cell;
import trafficsim.scenery.Lane;
import trafficsim.scenery.Scenario;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 06.11.13
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class SimulatorPanel extends JPanel {
    private final int CELL_SIZE = 4;
    private Scenario scen;

    @Override
    protected void paintComponent(Graphics g) {
        int i = 0;
        int j = 0;
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(Lane lane : scen.getLanes()) {
            for(Cell cell : lane.getCells()) {
                if(cell.hasVehicle()) {
                    g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
                else {
                g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
                i++;
            }
            j++;
            i = 0;
        }
    }

    public void setScen(Scenario scen) {
        this.scen = scen;
    }
}