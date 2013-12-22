package trafficsim.scenery2;

import javax.vecmath.Vector2d;
import java.awt.*;

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

    public Vehicle(Street s,String id) {
        this.id = id;
        this.currentStreet = s;
        if(Math.random()<0.5) {
            this.laneId = 1;
        } else {
            this.laneId = 0;
        }


        s.enterStreet(this);
        this.direction = new Vector2d(s.getDirection());
        this.direction.normalize();
        this.speed = 3;
        //this.position = new Vector2d(s.getStart().getX(),s.getStart().getY());



        this.position = new Vector2d((s.getLaneStart(this.laneId)).getX(),s.getLaneStart(this.laneId).getY());
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
            System.out.println("Leave: " + currentStreet + this.id + this.getPosition());
            this.currentStreet.leaveStreet(this);
            System.out.println("Enter: " + nextStreet + this.id + this.getPosition());
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
                    System.out.println("leaving intersection " + this.currentStreet);
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
                    System.out.println("entering intersetion");
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
                        System.out.println("oops, have to wait (redlight)" + this.id + "Street: " + this.currentStreet);
                        decelerate(this.speed - (int) Math.floor(maxEndPoint.length() / SPEEDMULTIPLIER));
                        System.out.println("SPEED: " + this.speed);
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
            if(this.position.y < a) {
                System.out.println("HELP");
            }
            maxEndPoint = new Vector2d(next.getPosition());
            maxEndPoint.sub(this.position);
            double len = maxEndPoint.length();
            maxEndPoint.normalize();
            maxEndPoint.scale(len-VEHICLE_LENGTH);
            if(maxEndPoint.length() < maxVehicleMove) {
                decelerate(this.speed - (int)Math.floor(maxEndPoint.length()/SPEEDMULTIPLIER));
                if(this.currentStreet.getLeftLane(this.laneId) > 0) {
                    System.out.println("MAYBE CHANGE STREET?");

                }
            }
            this.newPos = new Vector2d(this.direction);
            this.newPos.scale(this.speed * SPEEDMULTIPLIER);
            this.newPos.add(this.position);
        }

    }

    public boolean laneChangePossible(int laneid) {
       Vehicle v = this.currentStreet.getFirstVehicle(laneid);
       return false;
    }

    public void calcNewPosition2() {

        accelerate();

        //  step 3 decelerate -1 with P factor = 0.15
        double p = 0.15;
        if(Math.random()<p) {
            decelerate(1);
        }

        //normalize direction
        this.direction.normalize();

        double maxVehicleMove = this.speed * SPEEDMULTIPLIER;

        Vehicle next = this.currentStreet.getNextVehicle(this);
        Vector2d maxEndPoint;
        if(next == null) {
            maxEndPoint = new Vector2d(this.currentStreet.getEnd().x,this.currentStreet.getEnd().y);
        } else {
            maxEndPoint = new Vector2d(next.getPosition());
            double len = maxEndPoint.length();
            maxEndPoint.normalize();
            maxEndPoint.scale(len-VEHICLE_LENGTH);
        }
        maxEndPoint.sub(this.position);

        if(maxEndPoint.length() < maxVehicleMove && next != null) {
            // has next vehicle but not enough space to move -> decelerate
            decelerate((int)(maxVehicleMove-(maxVehicleMove-maxEndPoint.length()))/SPEEDMULTIPLIER);
            this.newPos = new Vector2d(this.direction);
            this.newPos.scale(maxEndPoint.length());
            this.newPos.add(this.position);
        } else if (maxEndPoint.length() < maxVehicleMove && next == null) {
            // has no next vehicle but not enough soace to move
            // should enter intersection, but first check if is "green" or decelerate
            this.newPos = new Vector2d(this.direction);
            this.newPos.scale(maxEndPoint.length());
            this.newPos.add(this.position);
            if(!isInIntersection) {
                this.currentIntersection = this.currentStreet.getNextIntersection();
                Direction dir = this.currentIntersection.getNewDirection(this.currentStreet);
                this.nextStreetAfterIntersection = this.currentIntersection.getRoute(this.currentStreet,dir);
                // enter intersection, leave street and calcuate move
                double maxVehicleMove2 = maxVehicleMove - maxEndPoint.length();

                Street iStreet = this.currentIntersection.getIntersectionStreet(this.currentStreet,this.nextStreetAfterIntersection);
                if(iStreet != null) {
                    this.changeStreet = true;
                    this.nextStreet = iStreet;
                    this.isInIntersection = true;
                    Vector2d isV = new Vector2d(this.nextStreet.getDirection());
                    if(maxVehicleMove2 > this.nextStreet.getStreetLength()) {
                        isV.scale(this.nextStreet.getStreetLength());
                        this.nextStreet = this.nextStreetAfterIntersection;
                        Vector2d nSV = new Vector2d(this.nextStreet.getDirection());
                        nSV.scale(maxVehicleMove2-this.nextStreet.getStreetLength());
                        this.newPos.add(nSV);
                        this.isInIntersection = false;
                    } else {
                        isV.scale(maxVehicleMove2);
                    }
                    this.newPos.add(isV);
                    ///
                } else {
                    System.out.println("SPEED BEFORE: " + this.speed);
                    decelerate((int)Math.floor(maxVehicleMove - (maxVehicleMove - maxEndPoint.length()))/SPEEDMULTIPLIER);
                    System.out.println("SLOW TO: " + ((int)(maxVehicleMove-(maxVehicleMove-maxEndPoint.length()))/SPEEDMULTIPLIER));
                    System.out.println("SPEED :" + this.speed);
                    this.newPos = new Vector2d(this.direction);
                    this.newPos.scale(this.speed*SPEEDMULTIPLIER);
                    this.newPos.add(this.position);
                    System.out.println("STOP can't enter Intersection");
                }
            } else {
                this.changeStreet = true;
                this.isInIntersection = false;
                this.nextStreet = this.nextStreetAfterIntersection;
                this.newPos = new Vector2d(this.direction);
                this.newPos.scale(maxVehicleMove);
                this.newPos.add(this.position);
            }


                // how enter intersection


        } else {
            this.newPos = new Vector2d(this.direction);
            this.newPos.scale(maxVehicleMove);
            this.newPos.add(this.position);
        }
        System.out.println("A");
        // edit later
        // move to maxVehicleMove
        //this.newPos = new Vector2d(this.direction);
        //this.newPos.scale(maxVehicleMove);
        //this.newPos.add(this.position);

    }

    public void calcNewPosition() {

        // step 1 nagsch model accelerate + 1 if not < maxspeed
        accelerate();


        // TODO: step 3 decelerate -1 with P factor = 0.15
        double p = 0.15;
        if(Math.random()<0.15) {
            decelerate(1);
        }

        // step 2 get maxlength till next vehicle and decelerate if necessary
        Vector2d nextV;
        Vehicle next = this.currentStreet.getNextVehicle(this);
        if(next == null) {
            Point end = this.currentStreet.getEnd();
            nextV = new Vector2d(end.x,end.y);
        }  else {
            nextV = new Vector2d(next.getPosition());
        }
        nextV.sub(this.position);
        Double maxlength = nextV.length();

        if(maxlength < (MAX_SPEED*SPEEDMULTIPLIER) && next == null) {
            System.out.println("is near intersection");
            System.out.println(this.currentStreet.getNextIntersection());
            this.nextStreet = this.currentStreet.getNextIntersection().getRoute(currentStreet,this.currentStreet.getNextIntersection().getNewDirection(currentStreet));
            System.out.println(this.nextStreet);
            decelerate(this.speed - (int) Math.floor(maxlength / SPEEDMULTIPLIER));
            // change to move() method
            //this.currentStreet.leaveStreet(this);
            //this.nextStreet.enterStreet(this);
            //this.currentStreet = this.nextStreet;
            this.direction = this.nextStreet.getDirection();
            this.position = new Vector2d(this.nextStreet.getLaneStart(this.laneId).x,this.nextStreet.getLaneStart(this.laneId).y);
            // TODO near intersection action?
            // slow down check intersection state based on route (go/nogo) wait for phase
            this.changeStreet = true;
        } else {
            if(maxlength < this.speed * SPEEDMULTIPLIER) {
                decelerate(this.speed - (int)Math.floor(maxlength / SPEEDMULTIPLIER));
            // TODO reaches end of street and enter a intersection, think about realizing this
            } else {

            }
        }


        this.direction.normalize();
        this.newPos = new Vector2d(this.direction);
        this.newPos.scale(this.speed*SPEEDMULTIPLIER);
        this.newPos.add(this.position);


    }

    private void calcPosAtIntersection() {


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
