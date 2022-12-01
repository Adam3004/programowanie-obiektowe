package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] directions;
    private final IWorldMap iWorldMap;
    private Map<Vector2d, IMapObject> objectPositions;
    private final JFrame jFrame;
    private final Vector2d[] startingPositions;
    private final List<Animal> animals;

    public SimulationEngine(MoveDirection[] directions, IWorldMap iWorldMap, Vector2d[] startingPositions) {
        this.startingPositions = startingPositions;
        this.iWorldMap = iWorldMap;
        this.directions = directions;
        this.jFrame = new JFrame("AnimalsWindow");
        animals = new ArrayList<>();
    }


    private void positionAnimalsOnMap() throws IllegalArgumentException{
        objectPositions = ((AbstractWorldMap) iWorldMap).getObjectPositions();
        for (Vector2d position : startingPositions) {
            iWorldMap.place(new Animal(iWorldMap, position));
        }
    }

    private void getAnimals() {
        for (Map.Entry<Vector2d, IMapObject> entry : objectPositions.entrySet()) {

            animals.add((Animal) entry.getValue());

        }
    }


    //    żeby wygodniej odpalać testy należy zakomentować linijki poniżej tych z "*"
    @Override
    public void run() throws IllegalArgumentException {
        positionAnimalsOnMap();
        int n = objectPositions.size();
        getAnimals();
        ((AbstractWorldMap) iWorldMap).init();
        //        *
//        createAnimalsWindow();
        for (int i = 0; i < directions.length; i++) {
//            *
//            updateScreen();
            animals.get(i % n).move(directions[i]);
//            System.out.println(iWorldMap);
        }
//        *
//        updateScreen();
    }

    private void createAnimalsWindow() {
        if (iWorldMap.getClass().equals(GrassField.class)) {
            GrassField map = (GrassField) iWorldMap;
            jFrame.setSize(map.getWidth() * 50, map.getHeight() * 70);
        } else {
            RectangularMap map = (RectangularMap) iWorldMap;
            jFrame.setSize(map.getWidth() * 50, map.getHeight() * 70);
        }
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private void updateScreen() {
        jFrame.getContentPane().removeAll();
        jFrame.repaint();
        if (iWorldMap.getClass().equals(GrassField.class)) {
            GrassField map = (GrassField) iWorldMap;
            JTextArea area = new JTextArea(map.toString());
            area.setSize(map.getWidth() * 50, map.getHeight() * 70);
            float value = (float) (10 * Math.log((map.getWidth() + map.getHeight())));
            area.setFont(area.getFont().deriveFont(value));
            jFrame.add(area);
        } else {
            RectangularMap map = (RectangularMap) iWorldMap;
            JTextArea area = new JTextArea(map.toString());
            area.setSize(map.getWidth() * 50, map.getHeight() * 70);
            float value = (float) (10 * Math.log((map.getWidth() + map.getHeight())));
            area.setFont(area.getFont().deriveFont(value));
            jFrame.add(area);
        }
        jFrame.revalidate();
        jFrame.repaint();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
