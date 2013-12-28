package trafficsim.main;

import trafficsim.gui.ControlPanel;
import trafficsim.gui.SimulatorView;
import trafficsim.gui.StreetPanel;
import trafficsim.gui.VehiclePanel;
import trafficsim.scenery2.Intersection;
import trafficsim.scenery2.Street;
import trafficsim.scenery2.Vehicle;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 12/4/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Simulator {
    private static ArrayList<Intersection> intersections = new ArrayList<Intersection>();
    private static ArrayList<Street> streets = new ArrayList<Street>();
    private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    // CAR RATIO < 0, <= 1
    private static double NEW_CAR_RATIO = 0.8;
    StreetPanel streetPanel = new StreetPanel();
    public VehiclePanel vehiclePanel = new VehiclePanel();
    SimulatorView view = new SimulatorView();
    ControlPanel controlPanel = new ControlPanel();

    public void initScenery(int scenery) {
        switch(scenery) {
            case 1: scenery1();
                break;
            case 2: scenery2();
                break;
            case 3: scenery3();
                break;
        }
    }

    private void scenery1(){
        // INTERSECTIONS
        Intersection i1 = new Intersection(600,600);
        intersections.add(i1);
        Intersection i2 = new Intersection(300,600);
        intersections.add(i2);
        Intersection i3 = new Intersection(100,100);
        intersections.add(i3);
        Intersection i4 = new Intersection(600,100);
        intersections.add(i4);
        Intersection i5 = new Intersection(800,400);
        intersections.add(i5);
        Intersection i6 = new Intersection(1000,50);
        intersections.add(i6);

        // STREETS
        Street st1 = new Street(5,2,"S1");
        streets.add(st1);
        Street st2 = new Street(5,2,"S2");
        streets.add(st2);
        Street st3 = new Street(5,2,"S3");
        streets.add(st3);
        Street st4 = new Street(5,2,"S4");
        streets.add(st4);
        Street st5 = new Street(5,2,"S5");
        streets.add(st5);
        Street st6 = new Street(5,2,"S6");
        streets.add(st6);
        Street st7 = new Street(5,2,"S7");
        streets.add(st7);
        Street st8 = new Street(5,2,"S8");
        streets.add(st8);
        Street st9 = new Street(5,2,"S9");
        streets.add(st9);
        Street st10 = new Street(5,2,"S10");
        streets.add(st10);
        Street st11 = new Street(5,2,"S11");
        streets.add(st11);
        Street st12 = new Street(5,2,"S12");
        streets.add(st12);
        Street st13 = new Street(5,2,"S13");
        streets.add(st13);
        Street st14 = new Street(5,2,"S14");
        streets.add(st14);
        Street st15 = new Street(5,2,"S15");
        streets.add(st15);
        Street st16 = new Street(5,2,"S16");
        streets.add(st16);
        Street start1 = new Street(5,2,"Start1");
        streets.add(start1);
        Street end1 = new Street(5,2,"End1");
        streets.add(end1);

        // STREET <> INTERSECTIONS
        i1.addOutgoingStreet(i2, st1);
        i2.addOutgoingStreet(i1, st2);
        st1.initStreet(i1.getPosition(),i2.getPosition());
        st2.initStreet(i2.getPosition(),i1.getPosition());
        st1.resetStartEndPoint();
        st2.resetStartEndPoint();

        i2.addOutgoingStreet(i3, st3);
        i3.addOutgoingStreet(i2, st4);
        st3.initStreet(i2.getPosition(),i3.getPosition());
        st4.initStreet(i3.getPosition(),i2.getPosition());
        st3.resetStartEndPoint();
        st4.resetStartEndPoint();

        i3.addOutgoingStreet(i4, st5);
        i4.addOutgoingStreet(i3, st6);
        st5.initStreet(i3.getPosition(),i4.getPosition());
        st6.initStreet(i4.getPosition(),i3.getPosition());
        st5.resetStartEndPoint();
        st6.resetStartEndPoint();

        i4.addOutgoingStreet(i1, st7);
        i1.addOutgoingStreet(i4, st8);
        st7.initStreet(i4.getPosition(),i1.getPosition());
        st8.initStreet(i1.getPosition(),i4.getPosition());
        st7.resetStartEndPoint();
        st8.resetStartEndPoint();

        i4.addOutgoingStreet(i5,st9);
        i5.addOutgoingStreet(i1,st10);
        i5.addOutgoingStreet(i4,st11);
        i1.addOutgoingStreet(i5,st12);
        st9.initStreet(i4.getPosition(), i5.getPosition());
        st10.initStreet(i5.getPosition(),i1.getPosition());
        st11.initStreet(i5.getPosition(),i4.getPosition());
        st12.initStreet(i1.getPosition(),i5.getPosition());
        st9.resetStartEndPoint();
        st10.resetStartEndPoint();
        st11.resetStartEndPoint();
        st12.resetStartEndPoint();

        i4.addOutgoingStreet(i6,st13);
        i6.addOutgoingStreet(i4,st14);
        i5.addOutgoingStreet(i6,st15);
        i6.addOutgoingStreet(i5,st16);
        st13.initStreet(i4.getPosition(),i6.getPosition());
        st14.initStreet(i6.getPosition(),i4.getPosition());
        st15.initStreet(i5.getPosition(),i6.getPosition());
        st16.initStreet(i6.getPosition(),i5.getPosition());
        st13.resetStartEndPoint();
        st14.resetStartEndPoint();
        st15.resetStartEndPoint();
        st16.resetStartEndPoint();

        Intersection istart = new Intersection(0,600);
        istart.addOutgoingStreet(i2,start1);
        start1.initStreet(istart.getPosition(),i2.getPosition());
        start1.resetStartEndPoint();

        Intersection iend = new Intersection(1200,50);
        i6.addOutgoingStreet(iend,end1);
        end1.initStreet(i6.getPosition(),iend.getPosition());
        end1.resetStartEndPoint();

        i1.initRouting();
        i2.initRouting();
        i3.initRouting();
        i4.initRouting();
        i5.initRouting();
        i6.initRouting();
    }

    private void scenery2(){
        // INTERSECTIONS
        Intersection i1 = new Intersection(200,100);
        intersections.add(i1);
        Intersection i2 = new Intersection(200,300);
        intersections.add(i2);
        Intersection i3 = new Intersection(200,600);
        intersections.add(i3);
        Intersection i4 = new Intersection(600,600);
        intersections.add(i4);
        Intersection i5 = new Intersection(800,600);
        intersections.add(i5);
        Intersection i6 = new Intersection(800,400);
        intersections.add(i6);
        Intersection i7 = new Intersection(800,100);
        intersections.add(i7);
        Intersection i8 = new Intersection(600,100);
        intersections.add(i8);
        Intersection i9 = new Intersection(600,400);
        intersections.add(i9);

        // STREETS
        Street st1 = new Street(5,3,"S1");
        streets.add(st1);
        Street st2 = new Street(5,3,"S2");
        streets.add(st2);
        Street st3 = new Street(5,3,"S3");
        streets.add(st3);
        Street st4 = new Street(5,3,"S4");
        streets.add(st4);
        Street st5 = new Street(5,3,"S5");
        streets.add(st5);
        Street st6 = new Street(5,3,"S6");
        streets.add(st6);
        Street st7 = new Street(5,3,"S7");
        streets.add(st7);
        Street st8 = new Street(5,3,"S8");
        streets.add(st8);
        Street st9 = new Street(5,3,"S9");
        streets.add(st9);
        Street st10 = new Street(5,3,"S10");
        streets.add(st10);
        Street st11 = new Street(5,3,"S11");
        streets.add(st11);
        Street st12 = new Street(5,3,"S12");
        streets.add(st12);
        Street st13 = new Street(5,3,"S13");
        streets.add(st13);
        Street st14 = new Street(5,3,"S14");
        streets.add(st14);
        Street st15 = new Street(5,3,"S15");
        streets.add(st15);
        Street st16 = new Street(5,3,"S16");
        streets.add(st16);
        Street st17 = new Street(5,3,"S17");
        streets.add(st17);
        Street st18 = new Street(5,3,"S18");
        streets.add(st18);
        Street st19 = new Street(5,3,"S19");
        streets.add(st19);
        Street st20 = new Street(5,3,"S20");
        streets.add(st20);
        Street st21 = new Street(5,3,"S21");
        streets.add(st21);
        Street st22 = new Street(5,3,"S22");
        streets.add(st22);
        Street st23 = new Street(5,3,"S23");
        streets.add(st23);
        Street st24 = new Street(5,3,"S24");
        streets.add(st24);
        Street start1 = new Street(5,3,"Start1");
        streets.add(start1);
        Street end1 = new Street(5,3,"End1");
        streets.add(end1);

        // STREET <> INTERSECTIONS
        i1.addOutgoingStreet(i2, st1);
        i2.addOutgoingStreet(i1, st2);
        st1.initStreet(i1.getPosition(),i2.getPosition());
        st2.initStreet(i2.getPosition(),i1.getPosition());
        st1.resetStartEndPoint();
        st2.resetStartEndPoint();

        i2.addOutgoingStreet(i3, st3);
        i3.addOutgoingStreet(i2, st4);
        st3.initStreet(i2.getPosition(),i3.getPosition());
        st4.initStreet(i3.getPosition(),i2.getPosition());
        st3.resetStartEndPoint();
        st4.resetStartEndPoint();

        i3.addOutgoingStreet(i4, st5);
        i4.addOutgoingStreet(i3, st6);
        st5.initStreet(i3.getPosition(),i4.getPosition());
        st6.initStreet(i4.getPosition(),i3.getPosition());
        st5.resetStartEndPoint();
        st6.resetStartEndPoint();

        i4.addOutgoingStreet(i5, st7);
        i5.addOutgoingStreet(i4, st8);
        st7.initStreet(i4.getPosition(),i5.getPosition());
        st8.initStreet(i5.getPosition(),i4.getPosition());
        st7.resetStartEndPoint();
        st8.resetStartEndPoint();

        i5.addOutgoingStreet(i6, st9);
        i6.addOutgoingStreet(i5, st10);
        st9.initStreet(i5.getPosition(),i6.getPosition());
        st10.initStreet(i6.getPosition(),i5.getPosition());
        st9.resetStartEndPoint();
        st10.resetStartEndPoint();

        i6.addOutgoingStreet(i7, st11);
        i7.addOutgoingStreet(i6, st12);
        st11.initStreet(i6.getPosition(),i7.getPosition());
        st12.initStreet(i7.getPosition(),i6.getPosition());
        st11.resetStartEndPoint();
        st12.resetStartEndPoint();

        i7.addOutgoingStreet(i8, st13);
        i8.addOutgoingStreet(i7, st14);
        st13.initStreet(i7.getPosition(),i8.getPosition());
        st14.initStreet(i8.getPosition(),i7.getPosition());
        st13.resetStartEndPoint();
        st14.resetStartEndPoint();

        i8.addOutgoingStreet(i1, st15);
        i1.addOutgoingStreet(i8, st16);
        st15.initStreet(i8.getPosition(),i1.getPosition());
        st16.initStreet(i1.getPosition(),i8.getPosition());
        st15.resetStartEndPoint();
        st16.resetStartEndPoint();

        i2.addOutgoingStreet(i9, st17);
        i9.addOutgoingStreet(i2, st18);
        st17.initStreet(i2.getPosition(),i9.getPosition());
        st18.initStreet(i9.getPosition(),i2.getPosition());
        st17.resetStartEndPoint();
        st18.resetStartEndPoint();

        i4.addOutgoingStreet(i9, st19);
        i9.addOutgoingStreet(i4, st20);
        st19.initStreet(i4.getPosition(),i9.getPosition());
        st20.initStreet(i9.getPosition(),i4.getPosition());
        st19.resetStartEndPoint();
        st20.resetStartEndPoint();

        i6.addOutgoingStreet(i9, st21);
        i9.addOutgoingStreet(i6, st22);
        st21.initStreet(i6.getPosition(),i9.getPosition());
        st22.initStreet(i9.getPosition(),i6.getPosition());
        st21.resetStartEndPoint();
        st22.resetStartEndPoint();

        i8.addOutgoingStreet(i9, st23);
        i9.addOutgoingStreet(i8, st24);
        st23.initStreet(i8.getPosition(),i9.getPosition());
        st24.initStreet(i9.getPosition(),i8.getPosition());
        st23.resetStartEndPoint();
        st24.resetStartEndPoint();

        Intersection istart = new Intersection(0,300);
        istart.addOutgoingStreet(i2,start1);
        start1.initStreet(istart.getPosition(),i2.getPosition());
        start1.resetStartEndPoint();

        Intersection iend = new Intersection(1000,100);
        i7.addOutgoingStreet(iend,end1);
        end1.initStreet(i7.getPosition(),iend.getPosition());
        end1.resetStartEndPoint();

        i1.initRouting();
        i2.initRouting();
        i3.initRouting();
        i4.initRouting();
        i5.initRouting();
        i6.initRouting();
        i7.initRouting();
        i8.initRouting();
        i9.initRouting();
    }

    private void scenery3(){
        // INTERSECTIONS

        Intersection i1 = new Intersection(600,600);
        intersections.add(i1);
        Intersection i2 = new Intersection(300,600);
        intersections.add(i2);
        Intersection i3 = new Intersection(100,100);
        intersections.add(i3);
        Intersection i4 = new Intersection(600,100);
        intersections.add(i4);
        Intersection i5 = new Intersection(800,400);
        intersections.add(i5);
        Intersection i6 = new Intersection(1000,50);
        intersections.add(i6);

        // STREETS
        Street st1 = new Street(5,3,"S1");
        streets.add(st1);
        Street st2 = new Street(5,3,"S2");
        streets.add(st2);
        Street st3 = new Street(5,3,"S3");
        streets.add(st3);
        Street st4 = new Street(5,3,"S4");
        streets.add(st4);
        Street st5 = new Street(5,3,"S5");
        streets.add(st5);
        Street st6 = new Street(5,3,"S6");
        streets.add(st6);
        Street st7 = new Street(5,3,"S7");
        streets.add(st7);
        Street st8 = new Street(5,3,"S8");
        streets.add(st8);
        Street st9 = new Street(5,3,"S9");
        streets.add(st9);
        Street st10 = new Street(5,3,"S10");
        streets.add(st10);
        Street st11 = new Street(5,3,"S11");
        streets.add(st11);
        Street st12 = new Street(5,3,"S12");
        streets.add(st12);
        Street st13 = new Street(5,3,"S13");
        streets.add(st13);
        Street st14 = new Street(5,3,"S14");
        streets.add(st14);
        Street st15 = new Street(5,3,"S15");
        streets.add(st15);
        Street st16 = new Street(5,3,"S16");
        streets.add(st16);
        Street start1 = new Street(5,3,"Start1");
        streets.add(start1);
        Street end1 = new Street(5,3,"End1");
        streets.add(end1);

        // STREET <> INTERSECTIONS
        i1.addOutgoingStreet(i2, st1);
        i2.addOutgoingStreet(i1, st2);
        st1.initStreet(i1.getPosition(),i2.getPosition());
        st2.initStreet(i2.getPosition(),i1.getPosition());
        st1.resetStartEndPoint();
        st2.resetStartEndPoint();

        i2.addOutgoingStreet(i3, st3);
        i3.addOutgoingStreet(i2, st4);
        st3.initStreet(i2.getPosition(),i3.getPosition());
        st4.initStreet(i3.getPosition(),i2.getPosition());
        st3.resetStartEndPoint();
        st4.resetStartEndPoint();

        i3.addOutgoingStreet(i4, st5);
        i4.addOutgoingStreet(i3, st6);
        st5.initStreet(i3.getPosition(),i4.getPosition());
        st6.initStreet(i4.getPosition(),i3.getPosition());
        st5.resetStartEndPoint();
        st6.resetStartEndPoint();

        i4.addOutgoingStreet(i1, st7);
        i1.addOutgoingStreet(i4, st8);
        st7.initStreet(i4.getPosition(),i1.getPosition());
        st8.initStreet(i1.getPosition(),i4.getPosition());
        st7.resetStartEndPoint();
        st8.resetStartEndPoint();

        i4.addOutgoingStreet(i5,st9);
        i5.addOutgoingStreet(i1,st10);
        i5.addOutgoingStreet(i4,st11);
        i1.addOutgoingStreet(i5,st12);
        st9.initStreet(i4.getPosition(), i5.getPosition());
        st10.initStreet(i5.getPosition(),i1.getPosition());
        st11.initStreet(i5.getPosition(),i4.getPosition());
        st12.initStreet(i1.getPosition(),i5.getPosition());
        st9.resetStartEndPoint();
        st10.resetStartEndPoint();
        st11.resetStartEndPoint();
        st12.resetStartEndPoint();

        i4.addOutgoingStreet(i6,st13);
        i6.addOutgoingStreet(i4,st14);
        i5.addOutgoingStreet(i6,st15);
        i6.addOutgoingStreet(i5,st16);
        st13.initStreet(i4.getPosition(),i6.getPosition());
        st14.initStreet(i6.getPosition(),i4.getPosition());
        st15.initStreet(i5.getPosition(),i6.getPosition());
        st16.initStreet(i6.getPosition(),i5.getPosition());
        st13.resetStartEndPoint();
        st14.resetStartEndPoint();
        st15.resetStartEndPoint();
        st16.resetStartEndPoint();

        Intersection istart = new Intersection(0,600);
        istart.addOutgoingStreet(i2,start1);
        start1.initStreet(istart.getPosition(),i2.getPosition());
        start1.resetStartEndPoint();

        Intersection iend = new Intersection(1200,50);
        i4.addOutgoingStreet(iend,end1);
        end1.initStreet(i4.getPosition(),iend.getPosition());
        end1.resetStartEndPoint();

        i1.initRouting();
        i2.initRouting();
        i3.initRouting();
        i4.initRouting();
        i5.initRouting();
        i6.initRouting();
    }

    public void initGUI() {


        streetPanel.setIntersections(intersections);
        streetPanel.setStreets(streets);
        vehiclePanel.setVehicles(vehicles);

        view.create(streetPanel, vehiclePanel);
        controlPanel.init();
    }

    private int x = 0;

    public double getNewCarRatio() {
        return NEW_CAR_RATIO;
    }

    public void setNewCarRatio(double n) {
        NEW_CAR_RATIO = n;
    }

    public int getNewIntersectionIterate() {
        return intersections.get(0).getInteratePhasesTick();
    }

    public void setNewIntersectionIterate(int n) {
        for(Intersection i: intersections) {
            i.setInteratePhasesTick(n);
        }
    }

    public void tick() {

        Street end1 = streets.get(streets.size()-1);
        for(Vehicle vdel:end1.getVehicles()) {
            end1.leaveStreet(vdel);
            vehicles.remove(vdel);
            System.out.println("VEhicle removed: " + vdel);
        }


        for(Intersection i: intersections) {
            i.iteratePhase();
        }

        for(Vehicle vehicle : vehicles){
            vehicle.calcNewPos();


        }

        for(Vehicle v: vehicles) {
            v.move();
        }

        if(Math.random()<NEW_CAR_RATIO) {
            Street enter1 = streets.get(streets.size()-2);
            // test if startpoint is free
            int lane = ((int)(Math.random() * 100)) % enter1.getNumOfLanes();
            Vehicle first = enter1.getFirstVehicle(lane);
            if(first != null) {
                if(first.getPosition().x != enter1.getLaneStart(lane).x || first.getPosition().y != enter1.getLaneStart(lane).y) {
                    vehicles.add(new Vehicle(streets.get(streets.size()-2),lane,"VX"));
                } else {
                    System.out.println("WHAAAAT");
                }
            } else {
                vehicles.add(new Vehicle(streets.get(streets.size()-2),lane,"VX"));
            }

        }

        vehiclePanel.repaint();
        controlPanel.updateTime();
    }

    public void reset() {
        for(Vehicle v:vehicles) {
            v.getCurrentStreet().leaveStreet(v);
        }
        vehicles.clear();
    }


}

