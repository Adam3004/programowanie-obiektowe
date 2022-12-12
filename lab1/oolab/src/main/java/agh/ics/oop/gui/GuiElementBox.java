package agh.ics.oop.gui;

import agh.ics.oop.Vector2d;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private final ImageView imageView;
    private final Label label;
    private final VBox vBox;

    //    url = "src/main/resources/grass.png"
    public GuiElementBox(String url, Vector2d vector2d) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(url));
        this.label = new Label(vector2d.toString());
        this.imageView = new ImageView(image);
        this.vBox = new VBox();
    }

    public void createImage() throws FileNotFoundException {
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        GridPane.setHalignment(imageView, HPos.CENTER);
        GridPane.setHalignment(label, HPos.CENTER);
        vBox.getChildren().add(imageView);
        vBox.getChildren().add(label);
        GridPane.setHalignment(vBox, HPos.CENTER);
    }

    public VBox getVBox() {
        return vBox;
    }
}
