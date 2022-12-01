package agh.ics.oop;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private final SortedSet<Vector2d> xAxis;
    private final SortedSet<Vector2d> yAxis;

    public MapBoundary() {
        this.xAxis = new TreeSet<>(Comparator.comparing(Vector2d::getX));
        this.yAxis = new TreeSet<>(Comparator.comparing(Vector2d::getY));
    }

    public void addElementToAxes(IMapObject object) {
        xAxis.add(object.getPosition());
        yAxis.add(object.getPosition());
    }

    public SortedSet<Vector2d> getXAxis() {
        return xAxis;
    }

    public SortedSet<Vector2d> getYAxis() {
        return yAxis;
    }

    public void repairAxes(Map<Vector2d, IMapObject> objectPositions) {
        for (Vector2d vector2d : objectPositions.keySet()) {
            xAxis.add(vector2d);
            yAxis.add(vector2d);
        }
    }

    public void removePosition(Vector2d oldPosition) {
        xAxis.remove(oldPosition);
        yAxis.remove(oldPosition);
    }

    //    dodaje tylko 1 x i jesli sie przesunie animala no to się robi problem, bo nie ma trawnika co tam był
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removePosition(oldPosition);
        xAxis.add(newPosition);
        yAxis.add(newPosition);
    }

//    te dodatkowe funkcje rozwiązują powyższy problem trawnika który nie jest uwzględniany jeśli mnie go animal, ale na
//    niego nie wejdzie (np animal idzie po x od 5 do 1 i na wysokosci y=3, a trawa jest na y=5. Jeśli animal przejdzie
//    z (3,3) na (2,3) to trawy nie będzie w liscie
}
