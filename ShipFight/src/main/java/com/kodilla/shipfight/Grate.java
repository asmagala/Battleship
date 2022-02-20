package com.kodilla.shipfight;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Grate {

    public static Group getGrate(int offsetX, int offsetY, int fieldLength, int fieldCount) {
        Group root = new Group();
        for (int i = 0; i <= fieldCount; i++) {
            Line hLine = new Line();
            hLine.setStartX(offsetX + fieldLength * i);
            hLine.setStartY(offsetY);
            hLine.setEndX(offsetX + fieldLength * i);
            hLine.setEndY(offsetY + fieldLength * fieldCount);
            root.getChildren().add(hLine);

            Line vLine = new Line();
            vLine.setStartX(offsetX);
            vLine.setStartY(offsetY + fieldLength * i);
            vLine.setEndX(offsetX + fieldLength * fieldCount);
            vLine.setEndY(offsetY + fieldLength * i);
            root.getChildren().add(vLine);
        }
        return root;
    }
}
