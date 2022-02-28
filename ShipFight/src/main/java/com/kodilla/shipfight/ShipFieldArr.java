package com.kodilla.shipfight;

public class ShipFieldArr {
    private ShipFieldStatus[][] shipField = new ShipFieldStatus[10][10];

    public void initShipField() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                shipField[i][j] = ShipFieldStatus.EMPTY;
            }
        }
    }

}
