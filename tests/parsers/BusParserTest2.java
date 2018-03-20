package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.model.exception.RouteException;
import ca.ubc.cs.cpsc210.translink.parsers.BusParser;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BusParserTest2 {
    @Test
    public void testBusLocationsParserNormal() throws JSONException {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearBuses();
        String data = "";

        try {
            data = new FileDataProvider("buslocations2.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }

        BusParser.parseBuses(s, data);

        assertEquals(2, s.getBuses().size());
        System.out.println("added 2 out of the 4 buses succesfully");
    }

    @Test
    public void testBusLocationsJSONException() throws JSONException {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearBuses();
        String data = "";

        try {
            data = new FileDataProvider("buslocations3BadSyntax.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }

        try {
            BusParser.parseBuses(s, data);
            fail ("Should not have parsed!");
        } catch (JSONException e) {
            System.out.println("Bad syntax caught!!!");
        }

        assertEquals(0, s.getBuses().size());
    }

    @Test
    public void testBusLocationsParserGitTest() throws JSONException {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearBuses();
        String data = "";
        s.addRoute(new Route("004"));//added
        s.addRoute(new Route("014"));//added

        try {
            data = new FileDataProvider("buslocations4GitTest.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }

        BusParser.parseBuses(s, data);

        List<Route> routes = new ArrayList<>(s.getRoutes());
        Route checkRoute = routes.get(0);

        System.out.println(checkRoute.getName());
        //014, 49.264067, -123.167150, "HASTINGS", "EB1"

        assertEquals(1, s.getBuses().size());
    }
}
