package com.kodilla.shipfight;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ShipFight extends Application {

    List<Rect> ships = new ArrayList<>();

    private GratePosition lGratePosition = new GratePosition(100, 100, 30, 10);
    private GratePosition rGratePosition = new GratePosition(500, 100, 30, 10);

    public static void main(String[] args) {
        launch(args);
    }

    private Boolean isInside(int pointX, int pointY, GratePosition grate ) {
        if ((pointX > grate.getOffsetX()) && (pointX < grate.getOffsetX() + grate.getFieldCount() * grate.getFieldLength()) &&
                (pointY > grate.getOffsetY()) && (pointY < grate.getOffsetY() + grate.getFieldLength() * grate.getFieldCount())) {
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

        Rect pRect = new Rect();
        pRect.setDefaultPosition(500, 420);
        pRect.setHeight(lGratePosition.getFieldLength());
        pRect.setWidth(lGratePosition.getFieldLength());
        pRect.setFill(Color.TRANSPARENT);
        pRect.setStroke(Color.RED);
        pRect.setStrokeWidth(1);

        Rect rRect = new Rect();
        rRect.setX(550);
        rRect.setY(420);
        rRect.setHeight(rGratePosition.getFieldLength());
        rRect.setWidth(rGratePosition.getFieldLength());
        rRect.setFill(Color.TRANSPARENT);
        rRect.setStroke(Color.DARKBLUE);
        rRect.setStrokeWidth(1);

        ships.add(pRect);
        ships.add(rRect);

        rRect.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                if (rRect.getStroke() == Color.DARKBLUE) {
                    rRect.setStroke(Color.GREEN);
                } else {
                    rRect.setStroke(Color.DARKBLUE);
                }
                //caption.setText(t.getSource().toString() + "\n" + t.getTarget().toString());
                caption.setText(ships.toString());

            }
        });

        Group root = new Group();
        root.getChildren().add(0, Grate.getGrate(lGratePosition));
        root.getChildren().add(1, Grate.getGrate(rGratePosition));
        root.getChildren().add(2, caption);
        root.getChildren().add(3, pRect);
        root.getChildren().add(4, rRect);

        Scene scene = new Scene(root, 900, 500, Color.ALICEBLUE);

        scene.setOnMouseMoved(e -> {
            if (isInside((int)e.getX(), (int)e.getY(), rGratePosition)) {   //offsetXSecond, offsetYSecond, fieldLength, fieldCount)) {
                int X = (int) Math.floor((e.getX() - rGratePosition.getOffsetX()) / rGratePosition.getFieldLength());
                int Y = (int) Math.floor((e.getY() - rGratePosition.getOffsetY()) / rGratePosition.getFieldLength());
                caption.setText("X = " + X + " Y = " + Y );
                pRect.setX(GrateConverter.TableX2Pixel(rGratePosition, X));
                pRect.setY(GrateConverter.TableY2Pixel(rGratePosition, Y));
            } else {
                pRect.setPosition2Default();
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
