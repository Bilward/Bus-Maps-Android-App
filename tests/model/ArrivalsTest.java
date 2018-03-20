package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Arrival;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test Arrivals
 */
public class ArrivalsTest {
    Route r;
    Arrival a;

    @BeforeEach
    public void setup() {
        r = RouteManager.getInstance().getRouteWithNumber("43");
        a = new Arrival(23, "Home", r);
    }

    @Test
    public void testConstructor() {
        assertEquals(23, a.getTimeToStopInMins());
        assertEquals(r, a.getRoute());
        assertEquals("Home", a.getDestination());
        assertEquals("", a.getStatus());

        a.setStatus("+");
        assertEquals("+", a.getStatus());
    }

    @Test
    public void testCompareTo() {
        Arrival a1 = new Arrival(22, "home", r);
        a.compareTo(a1);
        assertEquals(1, a.compareTo(a1));
    }
}
