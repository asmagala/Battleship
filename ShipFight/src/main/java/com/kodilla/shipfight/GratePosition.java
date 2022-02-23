package com.kodilla.shipfight;

public class GratePosition {
    private int offsetX;
    private int offsetY;
    private int fieldLength;
    private int fieldCount;

    public GratePosition(int offsetX, int offsetY, int fieldLength, int fieldCount) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.fieldLength = fieldLength;
        this.fieldCount = fieldCount;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }
}
