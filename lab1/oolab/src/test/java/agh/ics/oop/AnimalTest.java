package agh.ics.oop;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    private Animal animal1;
    private Animal animal2;
    private Animal animal3;
    private Animal animal4;
    private Animal animal5;
    private Animal animal6;
    private String[] args1;
    private String[] args2;
    private String[] args3;
    private String[] args4;

    @BeforeEach
    public void beforeEach() {
        animal1 = new Animal();
        animal2 = new Animal();
        animal3 = new Animal();
        animal4 = new Animal();
        animal5 = new Animal();
        animal6 = new Animal();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("All tests in MapDirectionTest are ended");
    }


    // is MapDirection ok
    @Test
    public void orientationCheck() {
        animal2.move(MoveDirection.LEFT);
        animal2.move(MoveDirection.LEFT);

        animal3.move(MoveDirection.RIGHT);
        animal3.move(MoveDirection.RIGHT);

        animal4.move(MoveDirection.RIGHT);
        animal4.move(MoveDirection.RIGHT);
        animal4.move(MoveDirection.RIGHT);
        animal4.move(MoveDirection.RIGHT);

        animal5.move(MoveDirection.RIGHT);

        animal6.move(MoveDirection.LEFT);
        try {
            assertEquals(animal1.getMapDirection(), MapDirection.NORTH);

            assertEquals(animal2.getMapDirection(), MapDirection.SOUTH);

            assertEquals(animal3.getMapDirection(), MapDirection.SOUTH);

            assertEquals(animal4.getMapDirection(), MapDirection.NORTH);

            assertEquals(animal5.getMapDirection(), MapDirection.EAST);

            assertEquals(animal6.getMapDirection(), MapDirection.WEST);
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    //    checking moving and isAt method
    @Test
    public void isAtTest() {
        animal2.move(MoveDirection.FORWARD);

        animal3.move(MoveDirection.BACKWARD);

        animal4.move(MoveDirection.BACKWARD);
        animal4.move(MoveDirection.FORWARD);

        animal5.move(MoveDirection.BACKWARD);
        animal5.move(MoveDirection.BACKWARD);

        try {
            assertTrue(animal1.isAt(new Vector2d(2, 2)));
            assertFalse(animal1.isAt(new Vector2d(2, 3)));
            assertTrue(animal1.isAt(animal1.getCurrPosition()));

            assertFalse(animal2.isAt(new Vector2d(2, 2)));
            assertTrue(animal2.isAt(new Vector2d(2, 3)));
            assertTrue(animal2.isAt(animal2.getCurrPosition()));

            assertFalse(animal3.isAt(new Vector2d(2, 2)));
            assertTrue(animal3.isAt(new Vector2d(2, 1)));
            assertTrue(animal3.isAt(animal3.getCurrPosition()));

            assertTrue(animal4.isAt(new Vector2d(2, 2)));
            assertFalse(animal4.isAt(new Vector2d(2, 1)));
            assertTrue(animal4.isAt(animal4.getCurrPosition()));

            assertFalse(animal5.isAt(new Vector2d(2, 2)));
            assertTrue(animal5.isAt(new Vector2d(2, 0)));
            assertTrue(animal5.isAt(animal5.getCurrPosition()));
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    // checking if animal is still in required area
    @Test
    public void stayingInRangeTests() {
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);

        animal2.move(MoveDirection.BACKWARD);
        animal2.move(MoveDirection.BACKWARD);
        animal2.move(MoveDirection.BACKWARD);
        animal2.move(MoveDirection.BACKWARD);
        animal2.move(MoveDirection.BACKWARD);

        animal3.move(MoveDirection.LEFT);
        animal3.move(MoveDirection.FORWARD);
        animal3.move(MoveDirection.FORWARD);
        animal3.move(MoveDirection.FORWARD);
        animal3.move(MoveDirection.FORWARD);
        animal3.move(MoveDirection.FORWARD);


        animal4.move(MoveDirection.LEFT);
        animal4.move(MoveDirection.LEFT);
        animal4.move(MoveDirection.LEFT);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.BACKWARD);
        animal4.move(MoveDirection.FORWARD);


        animal5.move(MoveDirection.FORWARD);
        animal5.move(MoveDirection.RIGHT);
        animal5.move(MoveDirection.FORWARD);
        animal5.move(MoveDirection.LEFT);
        animal5.move(MoveDirection.FORWARD);
        animal5.move(MoveDirection.RIGHT);
        animal5.move(MoveDirection.FORWARD);
        animal5.move(MoveDirection.LEFT);
        animal5.move(MoveDirection.FORWARD);
        animal5.move(MoveDirection.RIGHT);
        animal5.move(MoveDirection.FORWARD);
        animal5.move(MoveDirection.LEFT);

        try {
            assertTrue(animal1.isAt(new Vector2d(2, 4)));
            assertEquals(animal1.getMapDirection(), MapDirection.NORTH);

            assertTrue(animal2.isAt(new Vector2d(2, 0)));
            assertEquals(animal2.getMapDirection(), MapDirection.NORTH);

            assertTrue(animal3.isAt(new Vector2d(0, 2)));
            assertEquals(animal3.getMapDirection(), MapDirection.WEST);

            assertTrue(animal4.isAt(new Vector2d(4, 2)));
            assertEquals(animal4.getMapDirection(), MapDirection.EAST);

            assertTrue(animal5.isAt(new Vector2d(4, 4)));
            assertEquals(animal5.getMapDirection(), MapDirection.NORTH);

        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }


    //    checking both moving and rotating
    @Test
    public void orientationAndPositionAdvanceTests() {
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.LEFT);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.LEFT);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.LEFT);
        animal1.move(MoveDirection.FORWARD);

        animal2.move(MoveDirection.FORWARD);
        animal2.move(MoveDirection.LEFT);
        animal2.move(MoveDirection.FORWARD);
        animal2.move(MoveDirection.RIGHT);
        animal2.move(MoveDirection.FORWARD);
        animal2.move(MoveDirection.LEFT);
        animal2.move(MoveDirection.FORWARD);
        animal2.move(MoveDirection.RIGHT);

        animal3.move(MoveDirection.BACKWARD);
        animal3.move(MoveDirection.BACKWARD);
        animal3.move(MoveDirection.BACKWARD);
        animal3.move(MoveDirection.BACKWARD);
        animal3.move(MoveDirection.BACKWARD);
        animal3.move(MoveDirection.LEFT);
        animal3.move(MoveDirection.LEFT);
        animal3.move(MoveDirection.FORWARD);


        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.LEFT);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.LEFT);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.RIGHT);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.FORWARD);
        animal4.move(MoveDirection.RIGHT);
        animal4.move(MoveDirection.BACKWARD);
        animal4.move(MoveDirection.LEFT);

        try {
            assertTrue(animal1.isAt(new Vector2d(2, 2)));
            assertEquals(animal1.getMapDirection(), MapDirection.EAST);

            assertTrue(animal2.isAt(new Vector2d(0, 4)));
            assertEquals(animal2.getMapDirection(), MapDirection.NORTH);

            assertTrue(animal3.isAt(new Vector2d(2, 0)));
            assertEquals(animal3.getMapDirection(), MapDirection.SOUTH);

            assertTrue(animal4.isAt(new Vector2d(0, 1)));
            assertEquals(animal4.getMapDirection(), MapDirection.WEST);
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    //    checking if input works well
    @Test
    public void inputTests() {
        args1 = new String[]{"f", "l", "f", "l", "f", "l", "f"};
        args2 = new String[]{"qwer", "forward", "left", "forward", "left", "forward", "left", "forward", "left"};
        MoveDirection[] moves1 = OptionsParser.parse(args1);
        MoveDirection[] moves2 = OptionsParser.parse(args2);
        applyMovesOnAnimal(moves1, animal1);
        applyMovesOnAnimal(moves2, animal2);

        args3 = new String[]{"brr", "f", "b", "backward", "l", "r", "a", "b", "l", "l"};
        args4 = new String[]{"abcd", "forward", "backward", "backward", "l", "right", "aaa", "LeFfTl", "left", "left", "samolot"};
        MoveDirection[] moves3 = OptionsParser.parse(args3);
        MoveDirection[] moves4 = OptionsParser.parse(args4);
        applyMovesOnAnimal(moves3, animal3);
        applyMovesOnAnimal(moves4, animal4);

        try {
            assertTrue(animal1.isAt(new Vector2d(2, 2)));
            assertEquals(animal1.getMapDirection(), MapDirection.EAST);
            assertTrue(animal2.isAt(new Vector2d(2, 2)));
            assertEquals(animal2.getMapDirection(), MapDirection.NORTH);

            assertTrue(animal3.isAt(new Vector2d(2, 0)));
            assertEquals(animal3.getMapDirection(), MapDirection.SOUTH);
            assertTrue(animal3.isAt(new Vector2d(2, 0)));
            assertEquals(animal3.getMapDirection(), MapDirection.SOUTH);
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    private void applyMovesOnAnimal(MoveDirection[] moves, Animal animal) {
        Arrays.stream(moves)
                .forEach(animal::move);
    }


}
