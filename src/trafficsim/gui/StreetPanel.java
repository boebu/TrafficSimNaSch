package trafficsim.gui;

import trafficsim.model.Direction;
import trafficsim.model.Intersection;
import trafficsim.model.Street;
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

public class StreetPanel extends JLayeredPane {
    // Array Lists (Intersections, Street)
    private ArrayList<Intersection> intersections = new ArrayList<Intersection>();
    private ArrayList<Street> streets = new ArrayList<Street>();

    @Override
    protected void paintComponent(Graphics g) {
        // Paint Component
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Panel Background Color
        this.setOpaque(true);
        this.setBackground(Color.BLACK);

        // Author Text
        g.setColor(Color.WHITE);
        g.drawString("Traffic Sim by Boban Glisovic and Fabian Hutzli", 0, 10);

        // Draw Intersections (Red Lines)
        g.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(1));

        for(Intersection intersection : intersections){
            for(Street incomingStreet : intersection.getIncomingStreets()){
                Street rightStreet = intersection.getRoute(incomingStreet, Direction.RIGHT);
                g.drawLine(incomingStreet.getLaneEnd(incomingStreet.getNumOfLanes()).x,
                        incomingStreet.getLaneEnd(incomingStreet.getNumOfLanes()).y,
                        rightStreet.getLaneStart(rightStreet.getNumOfLanes()).x,
                        rightStreet.getLaneStart(rightStreet.getNumOfLanes()).y);
            }
        }

        // Draw Streets
        g.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1));

        for(Street street : streets){
            for(int i = 0; i <= street.getNumOfLanes(); i++){
                g.drawLine(street.getLaneStart(i).x, street.getLaneStart(i).y, street.getLaneEnd(i).x, street.getLaneEnd(i).y);
            }
            g.drawLine(street.getStart().x, street.getStart().y, street.getEnd().x, street.getEnd().y);
        }
    }

    // Set Intersections
    public void setIntersections(ArrayList<Intersection> intersections) {
        this.intersections = intersections;
    }

    // Set Streets
    public void setStreets(ArrayList<Street> streets){
        this.streets = streets;
    }
}
