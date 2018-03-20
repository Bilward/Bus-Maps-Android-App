package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.parsers.RouteParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.RouteDataMissingException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for the RouteParser
 */
// TODO: Write more tests

public class RouteParserTest2 {
    @BeforeEach
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    @Test
    public void testRouteParserRDexceptionWhenRouteNameIsMissing() throws RouteDataMissingException, JSONException, IOException {
        RouteParser p = new RouteParser("allroutes2.json");
        try {
            p.parse();
            fail ("should not have parsed");
        } catch (RouteDataMissingException e) {
            System.out.println("EXPECTED MISSING RouteNo!!! :)");
        }
    }

    @Test
    public void testRouteParserPatternNameMissing() throws RouteDataMissingException, JSONException, IOException {
        RouteParser p = new RouteParser("allroutes3PatternNoMissing.json");
        try {
            p.parse();
            fail ("should not have parsed");
        } catch (RouteDataMissingException e) {
            System.out.println("EXPECTED MISSING PatternNo!!! :)");
        }
    }

    @Test
    public void testRouteParserBadSyntax() throws RouteDataMissingException, JSONException, IOException {
        RouteParser p = new RouteParser("allroutes4JSONException.json");
        try {
            p.parse();
            fail ("should not have parsed");
        } catch (JSONException e) {
            System.out.println("EXPECTED bad syntax!!! :)");
        }
    }
}
