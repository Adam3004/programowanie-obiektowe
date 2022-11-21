package agh.ics.oop;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements IWorldMap {
    final List<IMapObject> objectPositions;

    public AbstractWorldMap(List<IMapObject> objectPositions) {
        this.objectPositions = objectPositions;
    }

    public List<IMapObject> getObjectPositions() {
        return objectPositions;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Vector2d findBottomLeft();

    public abstract Vector2d findTopRight();

    public void init(){
    }

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(IMapObject object) {
        if (!isOccupied(object.getPosition())) {
            objectPositions.add(object);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position){
        List<IMapObject> occupyingObjects = objectPositions.stream()
                .filter(object -> object.getPosition().equals(position))
                .collect(Collectors.toList());
        return occupyingObjects.size() > 0;
    }

    @Override
    public Object objectAt(Vector2d position) {
        List<IMapObject> occupyingObjects = objectPositions.stream()
                .filter(object -> object.getPosition().equals(position))
                .collect(Collectors.toList());
        if (occupyingObjects.size() > 0) {
            return occupyingObjects.get(0);
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(findBottomLeft(), findTopRight());
    }
}
