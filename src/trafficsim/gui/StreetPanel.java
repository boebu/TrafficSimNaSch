package trafficsim.gui;


import trafficsim.scenery2.Direction;
import trafficsim.scenery2.Intersection;
import trafficsim.scenery2.Street;

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
    private ArrayList<Intersection> intersections = new ArrayList<Intersection>();
    private ArrayList<Street> streets = new ArrayList<Street>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // DRAW INTERSECTIONS
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

        // DRAW STREETS
        g.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(1));

        for(Street street : streets){

            for(int i = 0; i <= street.getNumOfLanes(); i++){
                g.drawLine(street.getLaneStart(i).x, street.getLaneStart(i).y, street.getLaneEnd(i).x, street.getLaneEnd(i).y);
            }
            g.drawLine(street.getStart().x, street.getStart().y, street.getEnd().x, street.getEnd().y);
        }
    }

    public void setIntersections(ArrayList<Intersection> intersections) {
        this.intersections = intersections;
    }

    public void setStreets(ArrayList<Street> streets){
        this.streets = streets;
    }
}
