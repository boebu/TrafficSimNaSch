package trafficsim.gui;

import trafficsim.scenery2.Intersection;
import trafficsim.scenery2.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 16.11.13
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class VehiclePanel extends JLayeredPane {
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    @Override
    protected void paintComponent(Graphics g) {
        int vehicleSize = 4;

        super.paintComponent(g);

        for(Vehicle vehicle : vehicles){
            /*switch(vehicle.getSpeed()) {
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
            }*/

            g.fillOval((int) vehicle.getPosition().x, (int) vehicle.getPosition().y, vehicleSize, vehicleSize);
        }
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


}
