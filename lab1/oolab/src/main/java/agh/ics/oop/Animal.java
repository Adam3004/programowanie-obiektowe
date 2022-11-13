package agh.ics.oop;

import java.util.Locale;

public class Animal {
    private MapDirection mapDirection;
    private Vector2d currPosition;
    private IWorldMap map;

//    no more necessary
//    public Animal() {
//        this.mapDirection = MapDirection.NORTH;
//        this.currPosition = new Vector2d(2, 2);
//    }

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

    public Vector2d getCurrPosition() {
        return currPosition;
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
            case ADD -> {
                tempVector = tempVector.add(mapDirection.toUnitVector());
                if (map.canMoveTo(tempVector)) {
                    this.currPosition = tempVector;
                }
            }
            case SUBTRACT -> {
                tempVector = tempVector.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(tempVector)) {
                    this.currPosition = tempVector;
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.valueOf(mapDirection.name().toLowerCase(Locale.ROOT).charAt(0)).toUpperCase();
    }
//
//    @Override
//    public boolean canMoveTo(Vector2d position) {
//        Vector2d maxVector = new Vector2d(4, 4);
//        Vector2d minVector = new Vector2d(0, 0);
//        return position.precedes(maxVector) && position.follows(minVector);
//    }
//
//    @Override
//    public boolean place(Animal animal) {
//        return false;
//    }
//
//    @Override
//    public boolean isOccupied(Vector2d position) {
//        return false;
//    }
//
//    @Override
//    public Object objectAt(Vector2d position) {
//        return null;
//    }
}
