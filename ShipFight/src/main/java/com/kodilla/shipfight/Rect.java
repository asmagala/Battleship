package com.kodilla.shipfight;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rect extends Rectangle {
    private int index;
    private int defaultX;
    private int defaultY;
    private Direction direction = Direction.HORIZONTAL;
    private int rank = 1;
    private ShipStatus shipStatus = ShipStatus.NONE;

    public void setDefaultPosition(int X, int Y) {
        defaultX = X;
        defaultY = Y;
        setPosition2Default();
    }
    public void setPosition2Default() {
        setX(defaultX);
        setY(defaultY);
    }
    public void setDirection(Direction dir) {
        direction = dir;

    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public int getDefaultX() {
        return defaultX;
    }
    public int getDefaultY() {
        return defaultY;
    }
    public Direction getDirection() {
        return direction;
    }
    public int getRank() {
        return rank;
    }

    public ShipStatus getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(ShipStatus shipStatus) {
        this.shipStatus = shipStatus;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
