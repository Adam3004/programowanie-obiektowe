package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IWorldMapTests {
    private String[] args;
    private Vector2d[] positions;

    private int counter;

    @BeforeEach
    public void doBefore() {
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
    }

    @Test
    public void grassFieldTest() {
        args = new String[]{"forward", "left", "f", "forward", "f", "l", "f", "l", "f", "l", "f", "l", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
//        System.out.println(map.getObjectPositions());
        try {
            assertTrue(map.isOccupied(new Vector2d(2, 11)));
            assertTrue(map.isOccupied(new Vector2d(1, 4)));
            counter = 0;
            for (Map.Entry<Vector2d, IMapObject> elem : map.getObjectPositions().entrySet()) {
                if (elem.getValue() instanceof Grass) {
                    counter += 1;
                }
            }
            assertEquals(map.getAmountOfGras(), counter);

            Animal animal1 = (Animal) map.objectAt(new Vector2d(2, 11));
            Animal animal2 = (Animal) map.objectAt(new Vector2d(1, 4));

            assertEquals(animal1.getMapDirection(), MapDirection.NORTH);
            assertTrue(animal1.isAt(new Vector2d(2, 11)));
            assertEquals(animal2.getMapDirection(), MapDirection.WEST);
            assertTrue(animal2.isAt(new Vector2d(1, 4)));
        } catch (AssertionError | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void grassFieldTest2() {
        args = new String[]{};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
//        System.out.println(map.getObjectPositions());
        try {
            assertTrue(map.isOccupied(new Vector2d(2, 2)));
            assertTrue(map.isOccupied(new Vector2d(3, 4)));
            counter = 0;
            for (Map.Entry<Vector2d, IMapObject> elem : map.getObjectPositions().entrySet()) {
                if (elem.getValue() instanceof Grass) {
                    counter += 1;
                }
            }
            assertEquals(map.getAmountOfGras(), counter);

            Animal animal1 = (Animal) map.objectAt(new Vector2d(2, 2));
            Animal animal2 = (Animal) map.objectAt(new Vector2d(3, 4));

            assertEquals(animal1.getMapDirection(), MapDirection.NORTH);
            assertTrue(animal1.isAt(new Vector2d(2, 2)));
            assertEquals(animal2.getMapDirection(), MapDirection.NORTH);
            assertTrue(animal2.isAt(new Vector2d(3, 4)));
        } catch (AssertionError | NullPointerException e) {
            e.printStackTrace();
        }
//        System.out.println(map);
    }

    @Test
    public void rectangularMapTest() {
        args = new String[]{"forward", "left", "f", "forward", "f", "l", "f", "l", "f", "l", "f", "l", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 15);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        try {
            assertTrue(map.isOccupied(new Vector2d(2, 11)));
            assertTrue(map.isOccupied(new Vector2d(1, 4)));

            Animal animal1 = (Animal) map.objectAt(new Vector2d(2, 11));
            Animal animal2 = (Animal) map.objectAt(new Vector2d(1, 4));

            assertEquals(animal1.getMapDirection(), MapDirection.NORTH);
            assertTrue(animal1.isAt(new Vector2d(2, 11)));
            assertEquals(animal2.getMapDirection(), MapDirection.WEST);
            assertTrue(animal2.isAt(new Vector2d(1, 4)));
        } catch (AssertionError | NullPointerException e) {
            e.printStackTrace();
        }
//        System.out.println(map);
    }
}
