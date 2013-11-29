package trafficsim.gui;

import trafficsim.scenery2.Direction;
import trafficsim.scenery2.Intersection;
import trafficsim.scenery2.Street;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.vecmath.Vector2d;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: fabianhutzli
 * Date: 16.11.13
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class StreetPanel extends JPanel {
    private ArrayList<Intersection> intersections = new ArrayList<Intersection>();
    private ArrayList<Street> streets = new ArrayList<Street>();

    @Override
    protected void paintComponent(Graphics g) {
        /*int i = 0;
        int j = 0;*/
        super.paintComponent(g);

        /*for(Lane lane : scen.getLanes()) {
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
        }*/

        Graphics2D g2d = (Graphics2D) g;



        g.setColor(Color.RED);

        g2d.setStroke(new BasicStroke(1));


        //
        //
        // TEST

        //g.drawLine(50,100,100,200);




        //
        //
        // END TEST

        for(Street street : streets){

            g.drawLine(street.getStart().x, street.getStart().y, street.getEnd().x, street.getEnd().y);
            g.drawLine(street.getStart().x+4, street.getStart().y+4, street.getEnd().x+4, street.getEnd().y+4);

        }

        g.fillOval(600,600,4,4);

        g.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(2));

        for(Intersection intersection : intersections){
            g.drawRect(intersection.getPosition().x-10, intersection.getPosition().y-10, 20, 20);

        }

    }

    public void setIntersections(ArrayList<Intersection> intersections) {
        this.intersections = intersections;
    }

    public void setStreets(ArrayList<Street> streets){
        this.streets = streets;
    }
}
