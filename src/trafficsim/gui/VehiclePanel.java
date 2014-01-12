package trafficsim.gui;

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
    // --Commented out by Inspection (12/23/13 1:01 AM):private Vector2d defvector = new Vector2d(1,0);

    @Override
    protected void paintComponent(Graphics g) {
        int vehicleSize = 4;

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        /*
         * DRAW VEHICLE, COLOR DEPENDS ON SPEED
         */

        for(Vehicle vehicle : vehicles){

            switch(vehicle.getSpeed()) {
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
            g2d.fillOval((int) vehicle.getPosition().x, (int) vehicle.getPosition().y, vehicleSize, vehicleSize);
        }
    }

    /*
     * SET VEHICLES
     */

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


}
