package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceTest {
    private String[] args;
    private Vector2d[] positions;

    @Test
    public void notThrowsTest() {
        args = new String[]{};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        IEngine engine = new SimulationEngine(map, positions);
        ((SimulationEngine) engine).setDirections(directions);
        assertDoesNotThrow(engine::run);
    }

    @Test
    public void ThrowsTest() {
        args = new String[]{};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(3, 4)};
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        IEngine engine = new SimulationEngine(map, positions);
        ((SimulationEngine) engine).setDirections(directions);
        assertThrows(IllegalArgumentException.class, engine::run);
    }
}
