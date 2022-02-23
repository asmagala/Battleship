package com.kodilla.shipfight;

public class GrateConverter {

    public static int TableX2Pixel(GratePosition grate, int tableX) {
        return grate.getOffsetX() + tableX * grate.getFieldLength();
    }

    public static int TableY2Pixel(GratePosition grate, int tableY) {
        return grate.getOffsetY() + tableY * grate.getFieldLength();
    }


}
