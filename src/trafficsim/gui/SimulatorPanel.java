package trafficsim.gui;

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
    private final int CELL_SIZE = 12;
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

                    switch(cell.getVehicle().getSpeed()) {
                        case 0: g.setColor(new Color(255,0,0));
                                break;
                        case 1: g.setColor(new Color(255, 74, 22));
                            break;
                        case 2: g.setColor(new Color(255, 196, 42));
                            break;
                        case 3: g.setColor(new Color(196, 255, 22));
                            break;
                        case 4: g.setColor(new Color(137, 255, 9));
                            break;
                        case 5: g.setColor(new Color(0, 255,0));
                            break;
                    }
                    g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                }
                else {
                    g.setColor(new Color(0,0,0));
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