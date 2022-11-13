package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationEngine implements IEngine {
    private MoveDirection[] directions;
    private IWorldMap iWorldMap;
    private List<Animal> animalPositions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap iWorldMap, Vector2d[] startingPositions) {
        this.animalPositions = Arrays.stream(startingPositions)
                .map(position -> new Animal(iWorldMap, position))
                .filter(iWorldMap::place)
                .collect(Collectors.toList());
        this.iWorldMap = iWorldMap;
        this.directions = directions;
    }


    @Override
    public void run() {
        int n = animalPositions.size();
        for (int i = 0; i < directions.length; i++) {
            animalPositions.get(i % n).move(directions[i]);
//            System.out.println(iWorldMap);
        }
//        i was trying to display animal moves, but without great effects
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                AnimalsWindow animalsWindow = new AnimalsWindow();
//                JPanel panel = new JPanel();
//                panel.setLayout(new FlowLayout());
//                JTextArea animalMap = new JTextArea(iWorldMap.toString());
//                panel.add(animalMap);
//                animalsWindow.add(panel);
//                for (int i = 0; i < directions.length; i++) {
//                    panel.remove(0);
//                    animalsWindow.remove(panel);
//                    animalPositions.get(i % n).move(directions[i]);
//                    System.out.println(iWorldMap);
//                    animalMap = new JTextArea(iWorldMap.toString());
//                    panel.add(animalMap);
//                    animalsWindow.add(panel);
//                    animalsWindow.setVisible(true);
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
    }
}
