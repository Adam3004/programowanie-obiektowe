package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class App extends Application {
    private IWorldMap map;

    @Override
    public void init() throws Exception {
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Animals");
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        Scene scene = new Scene(gridPane, 600, 600);
        createMap(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createMap(GridPane gridPane) {
        GrassField grassField = (GrassField) map;
        gridPane.add(new Text(" y\\x "), 0, 0, 1, 1);
        gridPane.getColumnConstraints().add(new ColumnConstraints(25));
        gridPane.getRowConstraints().add(new RowConstraints(25));

        Vector2d bottomLeft = grassField.findBottomLeft();
        Vector2d topRight = grassField.findTopRight();


        for (int i = 1; i <= topRight.getX() - bottomLeft.getX() + 1; i++) {
            Label label = new Label(Integer.toString(bottomLeft.getX() + i - 1));
            gridPane.add(label, i, 0, 1, 1);
            gridPane.getColumnConstraints().add(new ColumnConstraints(40));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i <= topRight.getY() - bottomLeft.getY() + 1; i++) {
            Label label = new Label(Integer.toString(bottomLeft.getY() + i - 1));
            gridPane.add(label, 0, i, 1, 1);
            gridPane.getRowConstraints().add((new RowConstraints(40)));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i <= topRight.getX() - bottomLeft.getX() + 1; i++) {
            for (int j = 1; j <= topRight.getY() - bottomLeft.getY() + 1; j++) {
                Label label = new Label(getObject(new Vector2d(bottomLeft.getX() + i - 1, bottomLeft.getY() + j - 1)));
                gridPane.add(label, i, j, 1, 1);
                gridPane.setHalignment(label, HPos.CENTER);
                label.setFont(new Font(30));
            }
        }
    }

    private String getObject(Vector2d vector2d) {
        String object = "";
        if (map.isOccupied(vector2d)) {
            object = map.objectAt(vector2d).toString();
        }
        return object;
    }
}
