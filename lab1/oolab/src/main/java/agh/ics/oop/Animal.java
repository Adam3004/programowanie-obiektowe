package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Animal implements IMapObject {
    private MapDirection mapDirection;
    private Vector2d currPosition;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map) {
        this.map = map;
        this.mapDirection = MapDirection.NORTH;
        this.currPosition = new Vector2d(2, 2);
        this.observers = new ArrayList<>();
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.currPosition = initialPosition;
        this.mapDirection = MapDirection.NORTH;
        this.observers = new ArrayList<>();
    }

    public boolean isAt(Vector2d position) {
        return this.currPosition.equals(position);
    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public void move(MoveDirection direction) throws IllegalArgumentException {
        switch (direction) {
            case RIGHT -> this.mapDirection = this.mapDirection.next();
            case LEFT -> this.mapDirection = this.mapDirection.previous();
            case FORWARD -> tryMoveAnimal(Option.ADD);
            case BACKWARD -> tryMoveAnimal(Option.SUBTRACT);
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void positionChanged(Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(getPosition(), newPosition);
            if (observer instanceof MapBoundary && map instanceof GrassField) {
                ((MapBoundary) observer).repairAxes(((GrassField) map).getObjectPositions());
            }
        }
    }

    private void tryMoveAnimal(Option option) throws IllegalArgumentException {
        Vector2d tempVector = new Vector2d(this.currPosition.getX(), this.currPosition.getY());
        switch (option) {
            case ADD -> tempVector = tempVector.add(mapDirection.toUnitVector());
            case SUBTRACT -> tempVector = tempVector.subtract(mapDirection.toUnitVector());
        }
        if (map.canMoveTo(tempVector)) {
            positionChanged(tempVector);
            this.currPosition = tempVector;
        } else if (map.objectAt(tempVector).getClass().equals(Grass.class)) {
            GrassField grassField = (GrassField) map;
            grassField.changeGrassPosition(((Grass) map.objectAt(tempVector)));
            positionChanged(tempVector);
            this.currPosition = tempVector;
        }
    }

    @Override
    public Vector2d getPosition() {
        return currPosition;
    }

    @Override
    public int getPositionX() {
        return getPosition().getX();
    }

    @Override
    public int getPositionY() {
        return getPosition().getY();
    }

    @Override
    public String getImageOfObject() {
        return "oolab/src/main/resources/bierdra" + this.toString() + ".png";
    }

    @Override
    public String toString() {
        return String.valueOf(mapDirection.name().toLowerCase(Locale.ROOT).charAt(0)).toUpperCase();
    }
}
