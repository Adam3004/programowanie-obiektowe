package agh.ics.oop;

import javax.swing.*;
import java.util.List;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] directions;
    private final IWorldMap iWorldMap;
    private List<IMapObject> objectPositions;
    private final JFrame jFrame;
    private final Vector2d[] startingPositions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap iWorldMap, Vector2d[] startingPositions) {
        this.startingPositions = startingPositions;
        this.iWorldMap = iWorldMap;
        this.directions = directions;
        this.jFrame = new JFrame("AnimalsWindow");
    }


    private void positionAnimalsOnMap() {
        objectPositions = ((AbstractWorldMap) iWorldMap).getObjectPositions();
        for (Vector2d position : startingPositions) {
            iWorldMap.place(new Animal(iWorldMap, position));
        }
    }


    //    żeby wygodniej odpalać testy należy zakomentować linijki poniżej tych z "*"
    @Override
    public void run() {
        positionAnimalsOnMap();
        int n = objectPositions.size();
        ((AbstractWorldMap) iWorldMap).init();
        IMapObject mapObject;
        //        *
//        createAnimalsWindow();
        for (int i = 0; i < directions.length; i++) {
//            *
//            updateScreen();
            mapObject = objectPositions.get(i % n);
            if (mapObject.getClass().equals(Animal.class)) {
                ((Animal) mapObject).move(directions[i]);
            }
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
