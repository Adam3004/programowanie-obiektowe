package agh.ics.oop;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    private Vector2d zeroVector2d;
    private Vector2d firstVector2d;
    private Vector2d secondVector2d;
    private Vector2d thirdVector2d;
    private Vector2d forthVector2d;
    private Vector2d fifthVector2d;
    private Vector2d sixthVector2d;
    private Vector2d seventhVector2d;

    @BeforeEach
    public void beforeEach() {
        zeroVector2d = new Vector2d(0, 0);
        firstVector2d = new Vector2d(1, 2);
        secondVector2d = new Vector2d(1, -1);
        thirdVector2d = new Vector2d(1, 2);
        forthVector2d = new Vector2d(3, 4);
        fifthVector2d = new Vector2d(1, 3);
        sixthVector2d = new Vector2d(2, 2);
        seventhVector2d = new Vector2d(0, 2);
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("All tests have been completed");
    }

    @Test
    public void equalsTest() {
        try {
            assertTrue(firstVector2d.equals(thirdVector2d));
            assertFalse(firstVector2d.equals(secondVector2d));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    public void toStringTest() {
        try {
            assertEquals(firstVector2d.toString(), "(1,2)");
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    public void precedesTest() {
        try {
            assertTrue(zeroVector2d.precedes(firstVector2d));
            assertFalse(forthVector2d.precedes(zeroVector2d));
            assertTrue(firstVector2d.precedes(forthVector2d));
            assertFalse(firstVector2d.precedes(secondVector2d));
            assertTrue(firstVector2d.precedes(thirdVector2d));
            assertTrue(firstVector2d.precedes(fifthVector2d));
            assertFalse(fifthVector2d.precedes(thirdVector2d));
            assertTrue(firstVector2d.precedes(sixthVector2d));
            assertFalse(firstVector2d.precedes(seventhVector2d));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    public void follows() {
        try {
            assertTrue(forthVector2d.follows(firstVector2d));
            assertFalse(firstVector2d.follows(forthVector2d));
            assertTrue(firstVector2d.follows(thirdVector2d));
            assertTrue(thirdVector2d.follows(firstVector2d));
            assertTrue(firstVector2d.follows(secondVector2d));
            assertFalse(secondVector2d.follows(firstVector2d));
            assertTrue(firstVector2d.follows(seventhVector2d));
            assertFalse(secondVector2d.follows(firstVector2d));
            assertFalse(firstVector2d.follows(forthVector2d));
            assertFalse(zeroVector2d.follows(sixthVector2d));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    void upperRightTest() {
        try {
            assertEquals(firstVector2d.upperRight(zeroVector2d), new Vector2d(1, 2));
            assertEquals(zeroVector2d.upperRight(firstVector2d), new Vector2d(1, 2));
            assertEquals(fifthVector2d.upperRight(sixthVector2d), new Vector2d(2, 3));
            assertEquals(sixthVector2d.upperRight(fifthVector2d), new Vector2d(2, 3));
            assertEquals(secondVector2d.upperRight(seventhVector2d), new Vector2d(1, 2));
            assertEquals(seventhVector2d.upperRight(secondVector2d), new Vector2d(1, 2));
            assertEquals(secondVector2d.upperRight(zeroVector2d), new Vector2d(1, 0));
            assertEquals(zeroVector2d.upperRight(secondVector2d), new Vector2d(1, 0));
            assertEquals(firstVector2d.upperRight(thirdVector2d), new Vector2d(1, 2));
            assertEquals(thirdVector2d.upperRight(firstVector2d), new Vector2d(1, 2));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    void lowerLeftTest() {
        try {
            assertEquals(firstVector2d.lowerLeft(zeroVector2d), new Vector2d(0, 0));
            assertEquals(zeroVector2d.lowerLeft(firstVector2d), new Vector2d(0, 0));
            assertEquals(fifthVector2d.lowerLeft(sixthVector2d), new Vector2d(1, 2));
            assertEquals(sixthVector2d.lowerLeft(fifthVector2d), new Vector2d(1, 2));
            assertEquals(secondVector2d.lowerLeft(seventhVector2d), new Vector2d(0, -1));
            assertEquals(seventhVector2d.lowerLeft(secondVector2d), new Vector2d(0, -1));
            assertEquals(secondVector2d.lowerLeft(zeroVector2d), new Vector2d(0, -1));
            assertEquals(zeroVector2d.lowerLeft(secondVector2d), new Vector2d(0, -1));
            assertEquals(firstVector2d.lowerLeft(thirdVector2d), new Vector2d(1, 2));
            assertEquals(thirdVector2d.lowerLeft(firstVector2d), new Vector2d(1, 2));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    public void addTest() {
        try {
            assertEquals(firstVector2d.add(secondVector2d), new Vector2d(2, 1));
            assertEquals(secondVector2d.add(firstVector2d), new Vector2d(2, 1));
            assertEquals(zeroVector2d.add(seventhVector2d), new Vector2d(0, 2));
            assertEquals(seventhVector2d.add(zeroVector2d), new Vector2d(0, 2));
            assertEquals(firstVector2d.add(forthVector2d), new Vector2d(4, 6));
            assertEquals(forthVector2d.add(firstVector2d), new Vector2d(4, 6));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    void subtractTest() {
        try {
            assertEquals(zeroVector2d.subtract(firstVector2d), new Vector2d(-1, -2));
            assertEquals(firstVector2d.subtract(zeroVector2d), new Vector2d(1, 2));
            assertEquals(firstVector2d.subtract(thirdVector2d), new Vector2d(0, 0));
            assertEquals(thirdVector2d.subtract(firstVector2d), new Vector2d(0, 0));
            assertEquals(sixthVector2d.subtract(secondVector2d), new Vector2d(1, 3));
            assertEquals(secondVector2d.subtract(sixthVector2d), new Vector2d(-1, -3));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    @Test
    void oppositeTest() {
        try {
            assertEquals(zeroVector2d.opposite(), new Vector2d(0, 0));
            assertEquals(firstVector2d.opposite(), new Vector2d(-1, -2));
            assertEquals(secondVector2d.opposite(), new Vector2d(-1, 1));
            assertEquals(thirdVector2d.opposite(), new Vector2d(-1, -2));
            assertEquals(forthVector2d.opposite(), new Vector2d(-3, -4));
            assertEquals(fifthVector2d.opposite(), new Vector2d(-1, -3));
            assertEquals(sixthVector2d.opposite(), new Vector2d(-2, -2));
            assertEquals(seventhVector2d.opposite(), new Vector2d(0, -2));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }
}