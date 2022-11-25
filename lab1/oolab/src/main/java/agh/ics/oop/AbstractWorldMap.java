package agh.ics.oop;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    Map<Vector2d, IMapObject> objectPositions;

    public AbstractWorldMap(Map<Vector2d, IMapObject> objectPositions) {
        this.objectPositions = objectPositions;
    }

    public Map<Vector2d, IMapObject> getObjectPositions() {
        return objectPositions;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Vector2d findBottomLeft();

    public abstract Vector2d findTopRight();

    public void init() {
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapObject iMapObject = objectPositions.get(oldPosition);
        objectPositions.remove(oldPosition);
        objectPositions.put(newPosition, iMapObject);
    }

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(IMapObject object) {
        if (!isOccupied(object.getPosition())) {
            objectPositions.put(object.getPosition(), object);
            if (object instanceof Animal){
                ((Animal) object).addObserver(this);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        List<Vector2d> occupyingObjects = objectPositions.keySet().stream()
                .filter(object -> object.equals(position))
                .toList();
        return occupyingObjects.size() > 0;
    }

    @Override
    public Object objectAt(Vector2d position) {
        List<Vector2d> occupyingObjects = objectPositions.keySet().stream()
                .filter(object -> object.equals(position))
                .toList();
        if (occupyingObjects.size() > 0) {
            return objectPositions.get(occupyingObjects.get(0));
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(findBottomLeft(), findTopRight());
    }
}
