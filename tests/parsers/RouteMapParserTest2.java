package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.parsers.RouteMapParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test the parser for route pattern map information
 */

// TODO: Write more tests

public class RouteMapParserTest2 {
    @BeforeEach
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    private int countNumRoutePatterns() {
        int count = 0;
        for (Route r : RouteManager.getInstance()) {
            for (RoutePattern rp : r.getPatterns()) {
                count ++;
            }
        }
        return count;
    }

    @Test
    public void testRouteParserNormal() {
        RouteMapParser p = new RouteMapParser("allroutemaps2.txt");
        p.parse();
        countNumRoutePatterns(); // returning route instead of patterns
        assertEquals(1037, countNumRoutePatterns());
    }

    @Test
    public void testRouteParser3() {
        RouteMapParser p = new RouteMapParser("allroutemaps3.txt");
        p.parse();
        countNumRoutePatterns(); // returning route instead of patterns
        assertEquals(1, countNumRoutePatterns());
    }

}
