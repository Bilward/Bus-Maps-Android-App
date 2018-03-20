package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.*;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test the RouteManager
 */
public class RouteManagerTest {

    @BeforeEach
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    @Test
    public void testBasic() {
        Route r43 = new Route("43");
        Route r = RouteManager.getInstance().getRouteWithNumber("43");
        assertEquals(r43, r);
    }

    @Test
    public void testGetRouteWithNumber1() {
        RouteManager.getInstance().getRouteWithNumber("43");
        RouteManager.getInstance().getRouteWithNumber("12");
        RouteManager.getInstance().getNumRoutes();
        assertEquals(2, RouteManager.getInstance().getNumRoutes());

        RouteManager.getInstance().getRouteWithNumber("43", "Home");
        assertEquals(2, RouteManager.getInstance().getNumRoutes());

        RouteManager.getInstance().getRouteWithNumber("64", "Homee");
        assertEquals(3, RouteManager.getInstance().getNumRoutes());

        RouteManager.getInstance().getRouteWithNumber("12");
        assertEquals(3, RouteManager.getInstance().getNumRoutes());
    }

    @Test
    public void testIterator() {
        RouteManager.getInstance().getRouteWithNumber("43");
        RouteManager.getInstance().getRouteWithNumber("12");
        RouteManager.getInstance().getNumRoutes();
        assertEquals(2, RouteManager.getInstance().getNumRoutes());

        RouteManager.getInstance().getRouteWithNumber("43", "Home");
        assertEquals(2, RouteManager.getInstance().getNumRoutes());

        RouteManager.getInstance().getRouteWithNumber("64", "Homee");
        assertEquals(3, RouteManager.getInstance().getNumRoutes());

        for (Route next : RouteManager.getInstance()) {
            System.out.println(next);
        }
    }

    @Test
    public void testRoute() {
        RouteManager.getInstance().getRouteWithNumber("22", "Boom");
        RouteManager.getInstance().getRouteWithNumber("64", "lol");

        RouteManager.getInstance().getRouteWithNumber("22").getNumber();
        assertEquals("22", RouteManager.getInstance().getRouteWithNumber("22").getNumber());


        RouteManager.getInstance().getRouteWithNumber("22").
                addStop(new Stop(104, "Kerris", new LatLon(48, -128)));
        Stop s1 = new Stop(104, "Kerris", new LatLon(48, -128));
        List<Stop> loStops = new LinkedList<>();
        loStops.add(s1);
        assertEquals(loStops, RouteManager.getInstance().getRouteWithNumber("22").getStops());

        RouteManager.getInstance().getRouteWithNumber("22").hasStop(s1);
        assertEquals(true, RouteManager.getInstance().getRouteWithNumber("22").hasStop(s1));

        RouteManager.getInstance().getRouteWithNumber("22").removeStop(s1);

        RouteManager.getInstance().getRouteWithNumber("22").setName("Boom revised");
        assertEquals(2, RouteManager.getInstance().getNumRoutes());

        Route r = RouteManager.getInstance().getRouteWithNumber("100", "ded");

        RouteManager.getInstance().getRouteWithNumber("22").
                addPattern(new RoutePattern("P1", "Pond", "West", r));

        RouteManager.getInstance().getRouteWithNumber("22").
                addPattern(new RoutePattern("P2", "Pond", "East", r));

        RouteManager.getInstance().getRouteWithNumber("22").getPattern("P1");
        RoutePattern RP1 = new RoutePattern("P1", "Pond", "West", r);
        assertEquals(RP1, RouteManager.getInstance().getRouteWithNumber("22").getPattern("P1"));

        RouteManager.getInstance().getRouteWithNumber("22").getPattern("P1", "Pond", "West");
        assertEquals(RP1, RouteManager.getInstance().getRouteWithNumber("22").getPattern("P1", "Pond", "West"));

        RouteManager.getInstance().getRouteWithNumber("22").getPattern("P3"); // Doesnt exist

        RouteManager.getInstance().getRouteWithNumber("22").getPattern("P4", "Brock", "South");

        RoutePattern RP2 = new RoutePattern("P2", "Pond", "East", r);
        RoutePattern RP3 = new RoutePattern("P3", "", "", r);
        RoutePattern RP4 = new RoutePattern("P4", "Brock", "South", r);

        List<RoutePattern> loRPs = new LinkedList<>();
        loRPs.add(RP1);
        loRPs.add(RP2);
        loRPs.add(RP3);
        loRPs.add(RP4);

        assertEquals(loRPs, RouteManager.getInstance().getRouteWithNumber("22").getPatterns());

        RouteManager.getInstance().getRouteWithNumber("22").getName();

        RouteManager.getInstance().getRouteWithNumber("22").addPattern(RP3);

        Route r22 = RouteManager.getInstance().getRouteWithNumber("22");
        Route rNull = null;
        Arrival aNull = null;

        RouteManager.getInstance().getRouteWithNumber("22").equals(r22);
        RouteManager.getInstance().getRouteWithNumber("22").equals(rNull);
        RouteManager.getInstance().getRouteWithNumber("22").equals(aNull);
        assertEquals(false, RouteManager.getInstance().getRouteWithNumber("22").equals(rNull));

        RouteManager.getInstance().getRouteWithNumber("22").hashCode();
        assertEquals(1600, RouteManager.getInstance().getRouteWithNumber("22").hashCode());

        assertEquals("Route 22", RouteManager.getInstance().getRouteWithNumber("22").toString());

        Stop s2 = StopManager.getInstance().getStopWithNumber(25, "Point", new LatLon(48.5, -129.5));
        Stop s3 = StopManager.getInstance().getStopWithNumber(30, "Brock", new LatLon(48.6, -129.6));

        RouteManager.getInstance().getRouteWithNumber("22").addStop(s2);
        RouteManager.getInstance().getRouteWithNumber("22").addStop(s3);

        for(Stop s : r22){
            System.out.println(s);
        }

        r22.getStops().get(0).addRoute(r22);
        r22.getStops().get(0).getRoutes().size();
        r22.getStops().get(0).setName("WWWOW");
        assertEquals(1, r22.getStops().get(0).getRoutes().size());
    }

    @Test
    public void testRoutePattern() {
        Route r = RouteManager.getInstance().getRouteWithNumber("100", "ded");
        RoutePattern RP2 = new RoutePattern("P2", "Pond", "East", r);
        RoutePattern RP3 = new RoutePattern("P3", "", "", r);
        RoutePattern RP4 = new RoutePattern("P4", "Brock", "South", r);

        RouteManager.getInstance().getRouteWithNumber("22").addPattern(RP2);
        RouteManager.getInstance().getRouteWithNumber("22").addPattern(RP3);
        RouteManager.getInstance().getRouteWithNumber("22").addPattern(RP4);

        assertEquals("Pond", RP2.getDestination());
        assertEquals("East", RP2.getDirection());
        assertEquals(2530, RP2.hashCode());
        List<RoutePattern> boom = new LinkedList<>();
        assertEquals(boom, RP2.getPath());
        List<LatLon> paths = new LinkedList<>();
        paths.add(new LatLon(0, 0));
        paths.add(new LatLon(1, 1));
        RP2.setPath(paths);
        assertEquals(paths, RP2.getPath());
    }
}
