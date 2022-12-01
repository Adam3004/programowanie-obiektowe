package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionParserTest {
    private String[] args;
    private Vector2d[] positions;


    @Test
    public void parseWithProblemTest() {
//        good input
        args = new String[]{"forward", "left", "f", "forward", "f", "l", "f", "l", "f", "l", "f", "l", "f", "f", "f", "f", "f"};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        assertDoesNotThrow(() -> new OptionsParser().parse(args));
    }

    @Test
    public void parseWithOutProblemTest() {
//        wrong input
        args = new String[]{"forward", "left", "f", "forward", "f", "z", "f", "l", "f", "l", "f", "l", "f", "f", "f", "f", "f"};
        ;
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        assertThrows(IllegalArgumentException.class, () -> new OptionsParser().parse(args));
    }
}
