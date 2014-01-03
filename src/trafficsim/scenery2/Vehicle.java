package trafficsim.scenery2;

import javax.vecmath.Vector2d;
import java.awt.*;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vehicle {

    private static int MAX_SPEED = 5;
    private static int SPEEDMULTIPLIER = 7;
    private static int VEHICLE_LENGTH = 5;
    private int laneId;
    private Vector2d direction;
    private Vector2d position;
    private int speed;
    private Vector2d newPos;
    private Street currentStreet;
    private Street nextStreet;
    private Street nextStreetAfterIntersection;
    private Direction intersectionDirection = null;
    private boolean changeStreet = false;
    private boolean isInIntersection = false;
    private Intersection currentIntersection = null;

    //debug
    private String id;

    public Vehicle(Street s, int laneid, String id) {
        this.id = id;
        this.currentStreet = s;
        this.laneId = laneid;




        this.direction = new Vector2d(s.getDirection());
        this.direction.normalize();
        this.speed = 3;
        //this.position = new Vector2d(s.getStart().getX(),s.getStart().getY());

        System.out.println("LANEID:" + this.laneId);

        this.position = new Vector2d((s.getLaneStart(this.laneId)).getX(),s.getLaneStart(this.laneId).getY());
        s.enterStreet(this);
        System.out.println("POS: " +this.position);
    }


    public Vector2d getDirection() {
        return this.direction;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public int getCurrentLaneId() {
        return this.laneId;
    }

    public void move() {
        // switch newpos to pos       yy

        this.position.set(this.newPos);
        if(changeStreet) {
            this.currentStreet.leaveStreet(this);
            this.nextStreet.enterStreet(this);
            this.currentStreet = this.nextStreet;
            this.direction = this.currentStreet.getDirection();
            this.changeStreet = false;
        }
    }

    public void calcNewPos() {
        accelerate();

        //  step 3 decelerate -1 with P factor = 0.15
        double p = 0.15;
        if(Math.random()<p) {
            decelerate(1);
        }

        //normalize direction
        this.direction.normalize();

        // max meters vehicles can move
        double maxVehicleMove = this.speed * SPEEDMULTIPLIER;

        // get next vehicle (if null there is no next vehicle)
        Vehicle next = this.currentStreet.getNextVehicle(this);
        Vector2d maxEndPoint;
        if(next == null) {
            // no vehicle in front of this street
            maxEndPoint = new Vector2d(this.currentStreet.getLaneEnd(this.laneId).x,this.currentStreet.getLaneEnd(this.laneId).y);
            maxEndPoint.sub(this.position);
            this.newPos = new Vector2d(this.direction);
            if(maxEndPoint.length() < maxVehicleMove) {
                // if max move is greater than street length, street change becomes nessecary
                if(isInIntersection) {
                    // if vehicle is in an intersection Street
                    this.isInIntersection = false;
                    this.changeStreet = true;
                    this.nextStreet = this.nextStreetAfterIntersection;
                    this.intersectionDirection = null;
                    maxVehicleMove = maxVehicleMove - maxEndPoint.length();
                    Vector2d isVO = new Vector2d(this.nextStreet.getDirection());
                    isVO.normalize();
                    Vehicle vnexts =  this.nextStreet.getFirstVehicle(this.laneId);
                    if(vnexts == null) {
                        Vector2d nssv = new Vector2d(this.nextStreet.getLaneStart(this.laneId).x,this.nextStreet.getLaneStart(this.laneId).y);
                        Vector2d nsev = new Vector2d(this.nextStreet.getLaneEnd(this.laneId).x,this.nextStreet.getLaneEnd(this.laneId).y);
                        nsev.sub(nssv);
                        if(nsev.length() < maxVehicleMove) {

                            decelerate(this.speed - (int)Math.floor(nsev.length()/SPEEDMULTIPLIER));
                        } else {
                            decelerate(this.speed - (int)Math.floor(maxVehicleMove/SPEEDMULTIPLIER));
                        }

                    } else {
                        Vector2d vnextsV = new Vector2d(vnexts.getPosition());
                        vnextsV.sub(new Vector2d(this.nextStreet.getLaneStart(this.laneId).x,this.nextStreet.getLaneStart(this.laneId).y));
                        double tmplen = vnextsV.length() - VEHICLE_LENGTH;
                        if(tmplen < 0){
                            tmplen=0;
                        }
                        if(tmplen > maxVehicleMove) {
                            decelerate(this.speed - (int) Math.floor(maxVehicleMove / SPEEDMULTIPLIER));
                        } else {
                            decelerate(this.speed - (int) Math.floor(tmplen / SPEEDMULTIPLIER));
                        }

                    }
                    isVO.scale(this.speed*SPEEDMULTIPLIER);
                    this.newPos = new Vector2d(this.nextStreet.getLaneStart(this.laneId).x,this.nextStreet.getLaneStart(this.laneId).y);
                    this.newPos.add(isVO);
                } else {
                    // vehicle is not in an intersection, so enter if possible
                    this.currentIntersection = this.currentStreet.getNextIntersection();
                    // get random new direction for this vehicle
                    if(this.intersectionDirection == null) {
                        this.intersectionDirection = this.currentIntersection.getNewDirection(this.currentStreet);
                    }
                    // calculate route (get outgoing street)
                    this.nextStreetAfterIntersection = this.currentIntersection.getRoute(this.currentStreet,this.intersectionDirection);
                    // get correct intersection street
                    Street iStreet = this.currentIntersection.getIntersectionStreet(this.currentStreet,this.nextStreetAfterIntersection);
                    if(iStreet == null) {
                        // if null means redlight, vehicle has to stop
                        decelerate(this.speed - (int) Math.floor(maxEndPoint.length() / SPEEDMULTIPLIER));
                        this.newPos.scale(this.speed*SPEEDMULTIPLIER);
                        this.newPos.add(this.position);
                    }  else {
                        // vehicle can enter intersection
                        this.changeStreet = true;
                        this.nextStreet = iStreet;
                        this.isInIntersection = true;
                        Vector2d isV = new Vector2d(this.nextStreet.getDirection());
                        maxVehicleMove = maxVehicleMove - maxEndPoint.length();
                        isV.normalize();
                        // also check if entering intersection
                        Vehicle vnexts =  this.nextStreet.getFirstVehicle(this.laneId);
                        if(vnexts == null) {
                            if(maxVehicleMove > this.nextStreet.getStreetLength()) {
                                // 1 move can only cover 1 street change, so have to slow down maybe
                                decelerate(this.speed-(int)Math.floor(this.nextStreet.getStreetLength()/SPEEDMULTIPLIER));
                            } else {
                                decelerate(this.speed-(int)Math.floor(maxVehicleMove/SPEEDMULTIPLIER));
                            }
                        } else {
                            Vector2d vnextsV = new Vector2d(vnexts.getPosition());
                            vnextsV.sub(new Vector2d(this.nextStreet.getLaneStart(this.laneId).x,this.nextStreet.getLaneStart(this.laneId).y));
                            double tmplen = vnextsV.length() - VEHICLE_LENGTH;
                            if(tmplen < 0){
                                tmplen=0;
                            }
                            if(tmplen > maxVehicleMove) {
                                decelerate(this.speed - (int) Math.floor(maxVehicleMove / SPEEDMULTIPLIER));
                            } else {
                                decelerate(this.speed - (int) Math.floor(tmplen / SPEEDMULTIPLIER));
                            }
                        }



                        isV.scale(this.speed*SPEEDMULTIPLIER);
                        this.newPos = new Vector2d(this.nextStreet.getLaneStart(this.laneId).x,this.nextStreet.getLaneStart(this.laneId).y);
                        this.newPos.add(isV);

                    }


                }
                  // street change
            } else {
                this.newPos.scale(this.speed*SPEEDMULTIPLIER);
                this.newPos.add(this.position);
            }



        } else {
            double a = next.getPosition().x;
            maxEndPoint = new Vector2d(next.getPosition());
            maxEndPoint.sub(this.position);
            double len = maxEndPoint.length();
            if(len > 0) {
                maxEndPoint.normalize();
            }
            maxEndPoint.scale(len-VEHICLE_LENGTH);
            if(maxEndPoint.length() < maxVehicleMove) {
                decelerate(this.speed - (int)Math.floor(maxEndPoint.length()/SPEEDMULTIPLIER));
            }
            this.newPos = new Vector2d(this.direction);
            this.newPos.scale(this.speed * SPEEDMULTIPLIER);
            this.newPos.add(this.position);
        }

        Vector2d s;
        Vector2d e;
        if(this.changeStreet) {
            s = new Vector2d(this.nextStreet.getLaneStart(this.laneId).x,this.nextStreet.getLaneStart(this.laneId).y);
            e = new Vector2d(this.nextStreet.getLaneEnd(this.laneId).x,this.nextStreet.getLaneEnd(this.laneId).y);
        } else {
            s = new Vector2d(this.currentStreet.getLaneStart(this.laneId).x,this.currentStreet.getLaneStart(this.laneId).y);
            e = new Vector2d(this.currentStreet.getLaneEnd(this.laneId).x,this.currentStreet.getLaneEnd(this.laneId).y);
        }
        //debugging puroposes
        Vector2d x = new Vector2d(this.newPos);
        e.sub(s);
        x.sub(s);
        if(x.length() > e.length()) {
            System.out.println("FAIlHOCH10");
        }




    }

    public int getSpeed() {
        return this.speed;
    }

    public String getId() {
        return this.id;
    }

    public Street getCurrentStreet() {
        return this.currentStreet;

    }

    private void accelerate() {
         if(this.speed < MAX_SPEED) {
             this.speed++;
         }
    }

    private void decelerateTo(int s) {
        this.speed = s;
    }

    private void decelerate(int s) {
        this.speed = this.speed - s;
        if(this.speed < 0) {
            this.speed = 0;
        } else if(this.speed > 5) {
            System.out.println("BRK");
        }


    }


}
