package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.*;
import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import ca.ubc.cs.cpsc210.translink.model.exception.StopException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Test the StopManager
 */
public class StopManagerTest {

    @BeforeEach
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testBasic() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        Stop r = StopManager.getInstance().getStopWithNumber(9999);
        assertEquals(s9999, r);

        assertEquals(null, StopManager.getInstance().getSelected());

        Stop r2 = StopManager.getInstance().getStopWithNumber(43);
        StopManager.getInstance().getStopWithNumber(43);
        try {
            StopManager.getInstance().setSelected(r2);
        } catch (StopException e) {
            fail("Should not have been thrown");
        }

        Stop s1 = new Stop(999, "ss", new LatLon(0,0));
        try {
            StopManager.getInstance().setSelected(s1);
        } catch (StopException e) {
            //expected;
        }

        StopManager.getInstance().clearSelectedStop();
        assertEquals(null, StopManager.getInstance().getSelected());

        StopManager.getInstance().clearStops();
        assertEquals(0, StopManager.getInstance().getNumStops());

        StopManager.getInstance().iterator();
    }

    @Test
    public void testGetStopWithNumber0() {
        StopManager.getInstance().getStopWithNumber(32);
        StopManager.getInstance().getStopWithNumber(22);
        assertEquals(2, StopManager.getInstance().getNumStops());
    }

    @Test
    public void testGetStopWithNumber1() {

        StopManager.getInstance().getStopWithNumber(22, "Hi", new LatLon(-30, 32));

        Stop r1 = StopManager.getInstance().getStopWithNumber(32, "Boom", new LatLon(48, -129.2));
        StopManager.getInstance().getStopWithNumber(32, "Boom", new LatLon(48, -129.2));

        assertEquals(2, StopManager.getInstance().getNumStops());

        assertEquals(null, StopManager.getInstance().findNearestTo(new LatLon(31, 101)));

        StopManager.getInstance().findNearestTo(new LatLon(48, -129.2));
        assertEquals(r1, StopManager.getInstance().findNearestTo(new LatLon(48, -129.2)));

    }

    @Test
    public void testStop() {
        Stop s1 = StopManager.getInstance().getStopWithNumber(25, "Point", new LatLon(48.5, -129.5));
        Stop s2 = StopManager.getInstance().getStopWithNumber(30, "Brock", new LatLon(48.6, -129.6));
        Stop s3 = StopManager.getInstance().getStopWithNumber(45, "Gage", new LatLon(48.7, -129.7));
        LatLon l3 = new LatLon(48.7, -129.7);
        assertEquals(25, s1.getNumber());
        assertEquals("Brock", s2.getName());
        assertEquals(l3, s3.getLocn());

        for(Stop s : StopManager.getInstance()){
            System.out.println(s);
        }

        Route r1 = new Route("30");
        r1.addStop(s3);

        Bus b1 = new Bus(r1, 48.9, -129.9,"UBC-terminal", "15");
        Bus b2 = new Bus(r1, 48.5, -129.7,"UBC-terminal", "20");
        try {
            s3.addBus(b1);
        } catch (RouteException e) {
        }

        try {
            s3.addBus(b2);
        } catch (RouteException e) {
        }

        try {
            s2.addBus(b1);
        } catch (RouteException e) {
            //expected
        }
        List<Route> loRoutes = new LinkedList<>();
        loRoutes.add(r1);
        s3.addRoute(r1);
        assertEquals(1, s3.getRoutes().size());
        s3.getRoutes();

        Route r2 = new Route("40");
        s3.addRoute(r2);
        s3.removeRoute(r2);
        assertEquals(true, s3.onRoute(r1));

        Arrival a1 = new Arrival(10, "UBC-t", r1);
        Arrival a2 = new Arrival(20, "UBC-t", r1);
        Arrival a3 = new Arrival(30, "UBC-t", r1);
        Arrival aT = new Arrival(25, "UBC-t", r1);

        s3.addArrival(a1);
        s3.addArrival(a2);
        s3.addArrival(a3);
        s3.addArrival(aT);

        s3.clearArrivals();

        List<Bus> buses = new LinkedList<>();
        buses.add(b1);
        buses.add(b2);

        LatLon fortest = new LatLon( 48.9, -129.9);
        assertEquals(fortest, b1.getLatLon());
        assertEquals("UBC-terminal", b1.getDestination());
        assertEquals("15", b1.getTime());

        assertEquals(buses, s3.getBuses());
        s3.clearBuses();

        assertEquals(45, s3.hashCode());

        s3.setName("BOOOM");
        s3.setLocn(new LatLon(46, 126));

        s3.iterator();
    }

    @Test
    public void testStopAddArrivalCodeCoverage() {
        Stop stop = new Stop(14, "boom", new LatLon(0, 0));
        Arrival arrival1 = new Arrival(5, "wow", new Route("14"));
        Arrival arrival2 = new Arrival(3, "wow", new Route("14"));
        stop.addArrival(arrival1);
        stop.addArrival(arrival2);
    }
}
