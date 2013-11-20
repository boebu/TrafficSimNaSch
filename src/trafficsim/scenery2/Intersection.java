package trafficsim.scenery2;

import javax.vecmath.Vector2d;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/18/13
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Intersection {

    private Point position;
    private Hashtable<Intersection, Street> outgoingStreets;
    private Hashtable<Intersection, Street> incomingStreets;
    private ArrayList<Street> sortedOutgoingStreets;

    public Intersection(int x, int y) {
        this.position = new Point(x,y);
        this.outgoingStreets = new Hashtable<Intersection, Street>();
        this.incomingStreets = new Hashtable<Intersection, Street>();
    }

    public ArrayList<Street> sort() {
        sortedOutgoingStreets = new ArrayList<Street>();
         for(Street s:outgoingStreets.values()) {
             sortedOutgoingStreets.add(s);
         }
        Collections.sort(sortedOutgoingStreets);
        return sortedOutgoingStreets;
    }


    public Point getPosition() {
        return this.position;
    }

    public Street getOutgoingStreet(Intersection i) {
        return this.outgoingStreets.get(i);
    }

    public Street getIncomingStreet(Intersection i) {
        return this.incomingStreets.get(i);
    }

    public void addOutgoingStreet(Intersection i, Street s) {
        this.outgoingStreets.put(i,s);
        i.addIncomingStreet(this,s);
    }

    public void addIncomingStreet(Intersection i, Street s) {
       this.incomingStreets.put(i,s);
    }

    public Set<Intersection> getNextIntersections() {
        return this.outgoingStreets.keySet();
    }


}
