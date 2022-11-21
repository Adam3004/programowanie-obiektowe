package agh.ics.oop;

import java.util.Locale;

public class Animal implements IMapObject {
    private MapDirection mapDirection;
    private Vector2d currPosition;
    private final IWorldMap map;

    public Animal(IWorldMap map) {
        this.map = map;
        this.mapDirection = MapDirection.NORTH;
        this.currPosition = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.currPosition = initialPosition;
        this.mapDirection = MapDirection.NORTH;

    }

    public boolean isAt(Vector2d position) {
        return this.currPosition.equals(position);
    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.mapDirection = this.mapDirection.next();
            case LEFT -> this.mapDirection = this.mapDirection.previous();
            case FORWARD -> tryMoveAnimal(Option.ADD);
            case BACKWARD -> tryMoveAnimal(Option.SUBTRACT);
        }
    }

    private void tryMoveAnimal(Option option) {
        Vector2d tempVector = new Vector2d(this.currPosition.x, this.currPosition.y);
        switch (option) {
            case ADD -> tempVector = tempVector.add(mapDirection.toUnitVector());
            case SUBTRACT -> tempVector = tempVector.subtract(mapDirection.toUnitVector());
        }
        if (map.canMoveTo(tempVector)) {
            this.currPosition = tempVector;
        } else if (map.objectAt(tempVector).getClass().equals(Grass.class)) {
            GrassField grassField = (GrassField) map;
            grassField.changeGrassPosition(((Grass) map.objectAt(tempVector)));
            this.currPosition = tempVector;
        }
    }

    @Override
    public Vector2d getPosition() {
        return currPosition;
    }

    @Override
    public String toString() {
        return String.valueOf(mapDirection.name().toLowerCase(Locale.ROOT).charAt(0)).toUpperCase();
    }
}
