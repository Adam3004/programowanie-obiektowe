package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        super(new HashMap<>(), new MapBoundary());
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Vector2d findBottomLeft() {
        return new Vector2d(0, 0);
    }

    @Override
    public Vector2d findTopRight() {
        return new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d maxVector = new Vector2d(width - 1, height - 1);
        Vector2d minVector = new Vector2d(0, 0);
        return position.precedes(maxVector) && position.follows(minVector) && !isOccupied(position);
    }

}
