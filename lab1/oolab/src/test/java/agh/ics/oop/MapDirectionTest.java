package agh.ics.oop;


import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MapDirectionTest {
    private MapDirection east;
    private MapDirection west;
    private MapDirection south;
    private MapDirection north;

    @BeforeEach
    public void beforeEach() {
        east = MapDirection.EAST;
        west = MapDirection.WEST;
        south = MapDirection.SOUTH;
        north = MapDirection.NORTH;
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("All tests in MapDirectionTest are ended");
    }

    @Test
    public void nextFunctionTest() {
        try {
            assertEquals(east.next(), MapDirection.SOUTH);
            assertEquals(south.next(), MapDirection.WEST);
            assertEquals(west.next(), MapDirection.NORTH);
            assertEquals(north.next(), MapDirection.EAST);
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    public void previousFunctionTest() {
        try {
            assertEquals(east.previous(), MapDirection.NORTH);
            assertEquals(south.previous(), MapDirection.EAST);
            assertEquals(west.previous(), MapDirection.SOUTH);
            assertEquals(north.previous(), MapDirection.WEST);
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

}
