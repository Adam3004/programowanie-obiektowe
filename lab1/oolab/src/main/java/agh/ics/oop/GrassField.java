package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int amountOfGras;

    public GrassField(int amountOfGras) {
        super(new HashMap<>());
        this.amountOfGras = amountOfGras;
    }

    public int getAmountOfGras() {
        return amountOfGras;
    }

    private void removeGrass(Grass grass) {
        objectPositions.remove(grass.getPosition());
    }

    private Vector2d generatePlace() {
        Random random = new Random();
        return new Vector2d(random.nextInt((int) Math.sqrt(amountOfGras * 10)), random.nextInt((int) Math.sqrt(amountOfGras * 10)));
    }

    public void changeGrassPosition(Grass grass) {
        removeGrass(grass);
        int n = objectPositions.size();
        while (objectPositions.size() < n + 1) {
            place(new Grass(generatePlace()));
        }
    }

    @Override
    public void init() {
        int startingSize = objectPositions.size();
        while (objectPositions.size() < amountOfGras + startingSize) {
            place(new Grass(generatePlace()));
        }
    }

    @Override
    public Vector2d findBottomLeft() {
        int min_x = objectPositions.keySet().stream()
                .mapToInt(object -> object.x)
                .min().orElse(0);
        int min_y = objectPositions.keySet().stream()
                .mapToInt(object -> object.y)
                .min().orElse(0);
        return new Vector2d(min_x, min_y);
    }

    @Override
    public Vector2d findTopRight() {
        int max_x = objectPositions.keySet().stream()
                .mapToInt(object -> object.x)
                .max().orElse(0);
        int max_y = objectPositions.keySet().stream()
                .mapToInt(object -> object.y)
                .max().orElse(0);
        return new Vector2d(max_x, max_y);
    }

    @Override
    public int getWidth() {
        return findTopRight().x - findBottomLeft().x;
    }

    @Override
    public int getHeight() {
        return findTopRight().y - findBottomLeft().y;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }


}
