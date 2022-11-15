package agh.ics.oop;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationEngine implements IEngine {
    private MoveDirection[] directions;
    private IWorldMap iWorldMap;
    private List<Animal> animalPositions;
    private JFrame jFrame;

    public SimulationEngine(MoveDirection[] directions, IWorldMap iWorldMap, Vector2d[] startingPositions) {
        this.animalPositions = Arrays.stream(startingPositions)
                .map(position -> new Animal(iWorldMap, position))
                .filter(iWorldMap::place)
                .collect(Collectors.toList());
        this.iWorldMap = iWorldMap;
        this.directions = directions;
        this.jFrame = new JFrame("AnimalsWindow");
    }


    //    żeby wygodniej odpalać testy należy zakomentować linijki poniżej tych z "*"
    @Override
    public void run() {
//        *
        createAnimalsWindow();
        int n = animalPositions.size();
        for (int i = 0; i < directions.length; i++) {
//            *
            updateScreen();
            animalPositions.get(i % n).move(directions[i]);
//            System.out.println(iWorldMap);
        }
//        *
        updateScreen();
    }

    private void createAnimalsWindow() {
        RectangularMap map = (RectangularMap) iWorldMap;
        jFrame.setSize(map.getWidth() * 50, map.getHeight() * 70);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private void updateScreen() {
        jFrame.getContentPane().removeAll();
        jFrame.repaint();
        RectangularMap map = (RectangularMap) iWorldMap;
        JTextArea area = new JTextArea(map.toString());
        area.setSize(map.getWidth() * 50, map.getHeight() * 70);
        float value = (float) (10 * Math.log((map.getWidth() + map.getHeight())));
        area.setFont(area.getFont().deriveFont(value));
        jFrame.add(area);
        jFrame.revalidate();
        jFrame.repaint();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
