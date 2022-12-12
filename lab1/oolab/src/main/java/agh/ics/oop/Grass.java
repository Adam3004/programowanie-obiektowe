package agh.ics.oop;

public class Grass implements IMapObject {
    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return position;
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
        return "oolab/src/main/resources/grass.png";
    }

    @Override
    public String toString() {
        return "*";
    }
}
