package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import ca.ubc.cs.cpsc210.translink.parsers.BusParser;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BusParserTestGit {

//    @Test
//    public void testBusLocationsParserNormal() throws JSONException {
//        Stop s = StopManager.getInstance().getStopWithNumber(51479);
//        s.clearBuses();
//        String data = "";
//        s.addRoute(new Route("004"));//added
//        s.addRoute(new Route("014"));//added
//
//        try {
//            data = new FileDataProvider("buslocations.json").dataSourceToString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Can't read the bus locations data");
//        }
//
//        BusParser.parseBuses(s, data);
//
//        Route route = new Route("014");
//        RoutePattern newRoutePattern = new RoutePattern("EB1",
//                "HASTINGS", "EAST", route);
//        List<LatLon> points = new ArrayList<>();
//        points.add(new LatLon(49.264067, -123.167150));
//        newRoutePattern.setPath(points);
//        route.addPattern(newRoutePattern);
//
//        assertEquals(route, s.getBuses().get(0).getRoute());
//        assertEquals(4, s.getBuses().size());
//    }
}
