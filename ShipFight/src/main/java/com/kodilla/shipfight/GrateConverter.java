package com.kodilla.shipfight;

public class GrateConverter {

    public static int TableX2Pixel(GratePosition grate, int tableX) {
        return grate.getOffsetX() + tableX * grate.getFieldLength();
    }

    public static int TableY2Pixel(GratePosition grate, int tableY) {
        return grate.getOffsetY() + tableY * grate.getFieldLength();
    }

    public static int PixelX2TableX(GratePosition grate, int pixelX) {
        return (int) Math.floor((pixelX - grate.getOffsetX()) / grate.getFieldLength());
    }

    public static int PixelY2TableY(GratePosition grate, int pixelY) {
        return (int) Math.floor((pixelY - grate.getOffsetY()) / grate.getFieldLength());
    }

    public static int CorrectX(GratePosition grate, Rect ship, int X) {
        int rank = ship.getRank();
        Direction direction = ship.getDirection();
        if ((rank > 1) && (direction == Direction.HORIZONTAL) && (X > (grate.getFieldCount() - rank))) {
            X = grate.getFieldCount() - rank;
        }
        return X;
    }

    public static int CorrectY(GratePosition grate, Rect ship, int Y) {
        int rank = ship.getRank();
        Direction direction = ship.getDirection();

        if ((rank > 1) && (direction == Direction.VERTICAL) && (Y > (grate.getFieldCount() - rank))) {
            Y = grate.getFieldCount() - rank;
        }
        return Y;
    }


}
