package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
//        System.out.println(map);
    }

//    JFrame do dodatkowego

//    odp na pytanie
//    mechanizm który wyklczua można rozwiązać tworząc listę z informacją na których polach znajdują się zwierzęta
//    przed każdym ruchem można byłoby sprawdzać, czy pole na które zwierzak chce przejść jest wolne i jeśli tak
//    to nanosimy poprawkę na ową listę
}
