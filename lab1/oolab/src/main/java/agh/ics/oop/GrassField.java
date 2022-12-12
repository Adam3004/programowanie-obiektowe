package agh.ics.oop;

import java.util.HashMap;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int amountOfGras;
    private Vector2d tmpVec;

    public GrassField(int amountOfGras) {
        super(new HashMap<>(), new MapBoundary());
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

    public void changeGrassPosition(Grass grass) throws IllegalArgumentException {
        mapBoundary.removePosition(grass.getPosition());
        removeGrass(grass);
        int n = objectPositions.size();
        while (objectPositions.size() < n + 1) {
            tmpVec = generatePlace();
            if (!isOccupied(tmpVec)) {
                place(new Grass(tmpVec));
            }
        }

    }

    @Override
    public void init() throws IllegalArgumentException {
        int startingSize = objectPositions.size();
        while (objectPositions.size() < amountOfGras + startingSize) {
            tmpVec = generatePlace();
            if (!isOccupied(tmpVec)) {
                place(new Grass(tmpVec));
            }
        }
    }

    @Override
    public Vector2d findBottomLeft() {
        if (!mapBoundary.getXAxis().isEmpty() && !mapBoundary.getYAxis().isEmpty()) {
            return new Vector2d(mapBoundary.getXAxis().first().getX(), mapBoundary.getYAxis().first().getY());
        } else {
            return new Vector2d(0, 0);
        }
    }

    @Override
    public Vector2d findTopRight() {
        if (!mapBoundary.getXAxis().isEmpty() && !mapBoundary.getYAxis().isEmpty()) {
            return new Vector2d(mapBoundary.getXAxis().last().getX(), mapBoundary.getYAxis().last().getY());
        } else {
            return new Vector2d(0, 0);
        }
    }

    @Override
    public int getWidth() {
        return findTopRight().getX() - findBottomLeft().getX();
    }

    @Override
    public int getHeight() {
        return findTopRight().getY() - findBottomLeft().getY();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }


}
