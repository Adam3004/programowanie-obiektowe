package agh.ics.oop;

public class Animal {
    private MapDirection mapDirection;
    private Vector2d currPosition;

    public Animal() {
        this.mapDirection = MapDirection.NORTH;
        this.currPosition = new Vector2d(2, 2);
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
        Vector2d maxVector = new Vector2d(4, 4);
        Vector2d minVector = new Vector2d(0, 0);
        switch (option) {
            case ADD -> {
                tempVector = tempVector.add(mapDirection.toUnitVector());
                if (tempVector.precedes(maxVector) && tempVector.follows(minVector)) {
                    this.currPosition = tempVector;
                }
            }
            case SUBTRACT -> {
                tempVector = tempVector.subtract(mapDirection.toUnitVector());
                if (tempVector.precedes(maxVector) && tempVector.follows(minVector)) {
                    this.currPosition = tempVector;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Animal{" +
                "mapDirection=" + mapDirection +
                ", position=" + currPosition +
                '}';
    }
}
