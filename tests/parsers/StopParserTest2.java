package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.StopParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.StopDataMissingException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Tests for the StopParser
 */

// TODO: Write more tests

public class StopParserTest2 {
    @BeforeEach
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testStopParserStopDataMissingException() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stops2.json");

        try {
            p.parse();
            fail ("Stop Data should not have parsed!");
        } catch (StopDataMissingException e) {
            System.out.println("Stop Data missing exception caught!");
        }

        assertEquals(1, StopManager.getInstance().getNumStops());
    }

    @Test
    public void testStopParserBadSyntax() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stops3Syntax.json");

        try {
            p.parse();
            fail ("Stop Data should not have parsed!");
        } catch (JSONException e) {
            System.out.println("Stop Bad Format caught!");
        }

        assertEquals(0, StopManager.getInstance().getNumStops());
    }
}
