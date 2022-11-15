package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;
    private List<Animal> positions;

    public RectangularMap(int width, int height) {
        this.positions = new ArrayList<>();
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
    public boolean canMoveTo(Vector2d position) {
        Vector2d maxVector = new Vector2d(width - 1, height - 1);
        Vector2d minVector = new Vector2d(0, 0);
        return position.precedes(maxVector) && position.follows(minVector) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getCurrPosition())) {
            positions.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        List<Animal> lista = positions.stream()
                .filter(animal -> animal.isAt(position))
                .collect(Collectors.toList());
        return lista.size() > 0;
    }

    @Override
    public Object objectAt(Vector2d position) {
        List<Animal> lista = positions.stream()
                .filter(animal -> animal.isAt(position))
                .collect(Collectors.toList());
        if (lista.size() > 0) {
            return lista.get(0);
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }
}
