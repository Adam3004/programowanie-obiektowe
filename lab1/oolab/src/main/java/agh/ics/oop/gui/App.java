package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Objects;


public class App extends Application implements IPositionChangeObserver {
    private IWorldMap map;
    private GridPane gridPane;
    private SimulationEngine engine;

    private Thread engineThread;

    @Override
    public void init() throws Exception {
        try {
            String[] args = {};
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            engine = new SimulationEngine(map, positions);
            engine.setEngineObserver(this);
            engine.setMoveDelay(500);
            prepareDirections(args);
            prepareMap();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Animals");
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        Button startButton = new Button("Start Simulation");
        TextField inputs = new TextField();
        HBox smallerBox = new HBox(startButton, inputs);
        smallerBox.setAlignment(Pos.BOTTOM_LEFT);
        VBox box = new VBox(gridPane, smallerBox);
        VBox.setMargin(gridPane, new Insets(10, 0, 0, 10));
        VBox.setMargin(smallerBox, new Insets(30, 0, 0, 10));
        Scene scene = new Scene(box, 600, 600);
        createMap(gridPane);
        startButton.setOnAction(event -> {
            String newMoves = inputs.getText();
            if (newMoves.length() > 0) {
                try {
                    prepareDirections(newMoves.split(" "));
                    createMap(gridPane);
                    engineThread.start();
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareDirections(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);

        engine.setDirections(directions);
        engineThread = new Thread(engine);
    }


    private void prepareMap() {
        engineThread.start();
    }


    private void createMap(GridPane gridPane) {
        repairLines();

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


        for (int i = topRight.getY() - bottomLeft.getY() + 1; i > 0; i--) {
            Label label = new Label(Integer.toString(topRight.getY() - i + 1));
            gridPane.add(label, 0, i, 1, 1);
            gridPane.getRowConstraints().add((new RowConstraints(40)));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i <= topRight.getX() - bottomLeft.getX() + 1; i++) {
            for (int j = topRight.getY() - bottomLeft.getY() + 1; j > 0; j--) {
                Vector2d tmpVector = new Vector2d(bottomLeft.getX() + i - 1, topRight.getY() - j + 1);
                if (map.isOccupied(tmpVector)) {
                    VBox vbox = createObjectToDisplay(tmpVector);
                    gridPane.add(vbox, i, j, 1, 1);
                    Objects.requireNonNull(vbox).setAlignment(Pos.BASELINE_CENTER);
                }
            }
        }
    }

    private void repairLines() {
        gridPane.setGridLinesVisible(false);
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);
    }

    private VBox createObjectToDisplay(Vector2d vector2d) {
        IMapObject object = getObject(vector2d);
        if (object == null) {
            return null;
        }
        GuiElementBox displayElement;
        try {
            displayElement = new GuiElementBox(object.getImageOfObject(), vector2d);
            displayElement.createImage();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return displayElement.getVBox();
    }

    private IMapObject getObject(Vector2d vector2d) {
        IMapObject object = null;
        if (map.isOccupied(vector2d)) {
            object = (IMapObject) map.objectAt(vector2d);
        }
        return object;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            createMap(gridPane);
        });
    }
}
