package com.kodilla.shipfight;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ShipFight extends Application {

    private int offsetXFirst = 100;
    private int offsetYFirst = 100;
    private int offsetXSecond = 500;
    private int offsetYSecond = 100;
    private int fieldLength = 30;
    private int fieldCount = 10;

    public static void main(String[] args) {
        launch(args);
    }

    private Boolean isInside(int pointX, int pointY, int offsetX, int offsetY, int fieldLength, int fieldCount ) {
        if ((pointX > offsetX) && (pointX < offsetX + fieldCount * fieldLength) &&
                (pointY > offsetY) && (pointY < offsetY + fieldLength * fieldCount)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

 // create and configure the text
        Text caption = new Text(120, 60, "Smiley Face");
        caption.setFill(Color.BLUE);
        caption.setFont(Font.font("Verdana", 15));

        Rectangle pRect = new Rectangle();
        pRect.setX(-1000);
        pRect.setY(-1000);
        pRect.setHeight(fieldLength);
        pRect.setWidth(fieldLength);
        pRect.setFill(Color.TRANSPARENT);
        pRect.setStroke(Color.RED);
        pRect.setStrokeWidth(3);

        Group root = new Group();
        root.getChildren().add(0, Grate.getGrate(offsetXFirst, offsetYFirst, fieldLength, fieldCount));
        root.getChildren().add(1, Grate.getGrate(offsetXSecond, offsetYSecond, fieldLength, fieldCount));
        root.getChildren().add(2, caption);
        root.getChildren().add(3, pRect);

        Scene scene = new Scene(root, 900, 500, Color.ALICEBLUE);

        scene.setOnMouseMoved(e -> {
            if (isInside((int)e.getX(), (int)e.getY(), offsetXSecond, offsetYSecond, fieldLength, fieldCount)) {

                int X = (int) Math.floor((e.getX() - offsetXSecond) / fieldLength);
                int Y = (int) Math.floor((e.getY() - offsetYSecond) / fieldLength);
                caption.setText("X = " + X + " Y = " + Y );
                pRect.setX(offsetXSecond + fieldLength * X);
                pRect.setY(offsetYSecond + fieldLength * Y);
            } else {
                pRect.setX(-1000);
                pRect.setY(-1000);
            }
        });

        primaryStage.setTitle("Ship Battle");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*
        Group nnn = new Group();
        nnn = (Group)root.getChildren().get(0);
        nnn.getChildren().remove(nnn.getChildren().get(0));
        nnn.getChildren().remove(nnn.getChildren().get(7));
        root.getChildren().remove(2);

        //System.out.println(nnn);
         */
    }
}
