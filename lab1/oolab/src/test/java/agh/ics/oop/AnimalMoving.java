package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalMoving {
    private MoveDirection[] directions;
    private IWorldMap map;
    private Vector2d[] positions;
    private IEngine engine;
    private Animal animal1;
    private Animal animal2;
    private Animal animal3;
    private Animal animal4;
    private String[] args;

    @BeforeEach
    public void doBefore() {
        map = new RectangularMap(10, 5);
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
    }

    //    area check and possibility of collision
    @Test
    public void test1() {
        args = new String[]{"forward", "left", "f", "forward", "f", "l", "f", "l", "f", "l", "f", "l", "f", "f", "f", "f", "f"};
        directions = new OptionsParser().parse(args);
        engine = new SimulationEngine(directions, map, positions);
        engine.run();
        try {
            assertTrue(map.isOccupied(new Vector2d(2, 4)));
            assertTrue(map.isOccupied(new Vector2d(3, 4)));

            animal1 = (Animal) map.objectAt(new Vector2d(2, 4));
            animal2 = (Animal) map.objectAt(new Vector2d(3, 4));

            assertEquals(animal1.getMapDirection(), MapDirection.NORTH);
            assertTrue(animal1.isAt(new Vector2d(2, 4)));
            assertEquals(animal2.getMapDirection(), MapDirection.WEST);
            assertTrue(animal2.isAt(new Vector2d(3, 4)));
        } catch (AssertionError | NullPointerException e) {
            e.printStackTrace();
        }
    }

//    input test
    @Test
    public void test2() {
        args = new String[]{"aaa" ,"left", "left", "f", "bbb", "forward", "f", "f", "f", "f", "f","LeFFt", "f", "f"};
        directions = new OptionsParser().parse(args);
        engine = new SimulationEngine(directions, map, positions);
        engine.run();
        try {
            assertTrue(map.isOccupied(new Vector2d(0, 2)));
            assertTrue(map.isOccupied(new Vector2d(0, 4)));

            animal1 = (Animal) map.objectAt(new Vector2d(0, 2));
            animal2 = (Animal) map.objectAt(new Vector2d(0, 4));

            assertEquals(animal1.getMapDirection(), MapDirection.WEST);
            assertTrue(animal1.isAt(new Vector2d(0, 2)));
            assertEquals(animal2.getMapDirection(), MapDirection.WEST);
            assertTrue(animal2.isAt(new Vector2d(0, 4)));
        } catch (AssertionError | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        args = new String[]{"left", "right", "f", "forward", "r", "l", "f", "f", "r", "l", "f", "l", "r", "f", "f", "left", "f", "f", "f"};
        directions = new OptionsParser().parse(args);
        engine = new SimulationEngine(directions, map, positions);
        engine.run();
        try {
            assertTrue(map.isOccupied(new Vector2d(2, 0)));
            assertTrue(map.isOccupied(new Vector2d(5, 3)));

            animal1 = (Animal) map.objectAt(new Vector2d(2, 0));
            animal2 = (Animal) map.objectAt(new Vector2d(5, 3));

            assertEquals(animal1.getMapDirection(), MapDirection.SOUTH);
            assertTrue(animal1.isAt(new Vector2d(2, 0)));
            assertEquals(animal2.getMapDirection(), MapDirection.EAST);
            assertTrue(animal2.isAt(new Vector2d(5, 3)));
        } catch (AssertionError | NullPointerException e) {
            e.printStackTrace();
        }
    }

// trying to make a collision and put animal on the other
    @Test
    public void test4() {
        positions = new Vector2d[]{new Vector2d(3, 6), new Vector2d(0, 3), new Vector2d(3, 0), new Vector2d(6, 3), new Vector2d(6,3)};
        map = new RectangularMap(7, 7);
        args = new String[]{"r", "r", "r", "l", "r", "f", "l", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        directions = new OptionsParser().parse(args);
        engine = new SimulationEngine(directions, map, positions);
        engine.run();
        try {
            assertTrue(map.isOccupied(new Vector2d(3, 3)));
            assertTrue(map.isOccupied(new Vector2d(3, 2)));
            assertTrue(map.isOccupied(new Vector2d(3, 4)));
            assertTrue(map.isOccupied(new Vector2d(4, 3)));

            animal1 = (Animal) map.objectAt(new Vector2d(3, 3));
            animal2 = (Animal) map.objectAt(new Vector2d(3, 2));
            animal3 = (Animal) map.objectAt(new Vector2d(3, 4));
            animal4 = (Animal) map.objectAt(new Vector2d(4, 3));

            assertEquals(animal1.getMapDirection(), MapDirection.EAST);
            assertTrue(animal1.isAt(new Vector2d(3, 3)));
            assertEquals(animal2.getMapDirection(), MapDirection.NORTH);
            assertTrue(animal2.isAt(new Vector2d(3, 2)));
            assertEquals(animal3.getMapDirection(), MapDirection.SOUTH);
            assertTrue(animal3.isAt(new Vector2d(3, 4)));
            assertEquals(animal4.getMapDirection(), MapDirection.WEST);
            assertTrue(animal4.isAt(new Vector2d(4, 3)));
        } catch (AssertionError | NullPointerException e) {
            e.printStackTrace();
        }
    }


}
