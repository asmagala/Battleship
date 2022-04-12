package com.kodilla.shipfight;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ShipFight extends Application {

    List<Rect> ships = new ArrayList<>();
    ShipFieldArr myField = new ShipFieldArr();
    ShipFieldArr enemyField = new ShipFieldArr();
    Button readyButton = new Button("I'm ready!");
    Button newGameButton = new Button("New Game");

    private int idx;
    private boolean isReady = false;

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

    private void setAllShipsStatusNone() {
        for (Rect ship : ships ) {
            ship.setShipStatus(ShipStatus.NONE);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        myField.initShipField();
        enemyField.initShipField();
        readyButton.setLayoutX(300);
        readyButton.setLayoutY(50);
        newGameButton.setLayoutX(200);
        newGameButton.setLayoutY(50);

        Text caption = new Text(100, 30, "Smiley Face");
        caption.setFill(Color.BLUE);
        caption.setFont(Font.font("Verdana", 15));

        for(int i = 0; i < 10; i++) {
            ships.add(i, new Rect());
            ships.get(i).setIndex(i);
            ships.get(i).setOneUnitHeight(lGratePosition.getFieldLength());
            ships.get(i).setOneUnitWidth(lGratePosition.getFieldLength());

            ships.get(i).setDirection(Direction.HORIZONTAL);

            ships.get(i).setFill(Color.TRANSPARENT);
            ships.get(i).setStroke(Color.DARKBLUE);
            ships.get(i).setStrokeWidth(1);

            int ix = i;
            ships.get(i).setOnMouseClicked(e -> {
                if (ships.get(ix).getShipStatus() == ShipStatus.CHOSEN) {
                    int X = (int) Math.floor((e.getX() - lGratePosition.getOffsetX()) / lGratePosition.getFieldLength());
                    int Y = (int) Math.floor((e.getY() - lGratePosition.getOffsetY()) / lGratePosition.getFieldLength());
                    caption.setText("X = " + X + " Y = " + Y );
                    ships.get(ix).setX(GrateConverter.TableX2Pixel(lGratePosition, X));
                    ships.get(ix).setY(GrateConverter.TableY2Pixel(lGratePosition, Y));
                    ships.get(ix).setShipStatus(ShipStatus.NONE);
                } else {
                    for (Rect ship : ships) {
                        ship.setShipStatus(ShipStatus.NONE);

                        if ((ship.getX() == ship.getDefaultX()) && (ship.getY() == ship.getDefaultY())) {
                            ship.setStrokeWidth(1);
                            ship.setStroke(Color.DARKBLUE);
                        }
                    }
                    ships.get(ix).setStrokeWidth(4);
                    ships.get(ix).setShipStatus(ShipStatus.CHOSEN);
                }
            });
        }

        for(int i = 0; i < 4; i++) {
            ships.get(i).setDefaultPosition(
                    lGratePosition.getOffsetX() + i * (lGratePosition.getFieldLength() + 5),
                    lGratePosition.getOffsetY() + lGratePosition.getFieldLength() * lGratePosition.getFieldCount() + 5);

                /*
                if (ships.get(ix).getStroke() == Color.DARKBLUE) {
                    ships.get(ix).setStroke(Color.GREEN);
                } else {
                    ships.get(ix).setStroke(Color.DARKBLUE);
                }

                 */

            /*
            ships.get(i).setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent t) {
                    //t.getTarget()
                    //caption.setText("////////////" + t.getSource().toString() + "\n" + t.getTarget().toString());
                    caption.setText(ships.toString());

                }
            });

             */
        }

        for(int i = 0; i < 3; i++) {
            ships.get(i + 4).setDefaultPosition(
                    lGratePosition.getOffsetX() + i * ( 2 * lGratePosition.getFieldLength() + 5),
                    lGratePosition.getOffsetY() + lGratePosition.getFieldLength() * lGratePosition.getFieldCount() + lGratePosition.getFieldLength() + 10);
            ships.get(i + 4).setRank(2);
            ships.get(i + 4).setDirection(Direction.HORIZONTAL);
            ships.get(i + 4).setWidth(2 * lGratePosition.getFieldLength());
        }

        for(int i = 0; i < 2; i++) {
            ships.get(i + 7).setDefaultPosition(
                    lGratePosition.getOffsetX() + i * ( 3 * lGratePosition.getFieldLength() + 5),
                    lGratePosition.getOffsetY() + lGratePosition.getFieldLength() * lGratePosition.getFieldCount() + 2 *  lGratePosition.getFieldLength() + 15);
            ships.get(i + 7).setRank(3);
            ships.get(i + 7).setDirection(Direction.HORIZONTAL);
            ships.get(i + 7).setWidth(3 * lGratePosition.getFieldLength());
        }

        ships.get(9).setDefaultPosition(
                lGratePosition.getOffsetX(),
                lGratePosition.getOffsetY() + lGratePosition.getFieldLength() * lGratePosition.getFieldCount() + 3 *  lGratePosition.getFieldLength() + 20);
        ships.get(9).setRank(4);;
        ships.get(9).setDirection(Direction.HORIZONTAL);
        ships.get(9).setWidth(4 * lGratePosition.getFieldLength());
/*
        rRect.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                if (ships.get(idx).getStroke() == Color.DARKBLUE) {
                    ships.get(idx).setStroke(Color.GREEN);
                } else {
                    ships.get(idx).setStroke(Color.DARKBLUE);
                }
                //caption.setText(t.getSource().toString() + "\n" + t.getTarget().toString());
                //caption.setText(ships.toString());

            }
        });
*/
        Group root = new Group();
        root.getChildren().add(0, Grate.getGrate(lGratePosition));
        root.getChildren().add(1, Grate.getGrate(rGratePosition));
        root.getChildren().add(2, caption);
        root.getChildren().add(3, readyButton);
        root.getChildren().add(4, newGameButton);

        for(int i = 0; i < 10; i++) {
            root.getChildren().add(5 + i, ships.get(i));
        }

        Scene scene = new Scene(root, 900, 600, Color.ALICEBLUE);

        scene.setOnMouseMoved(e -> {
            if ((isInside((int)e.getX(), (int)e.getY(), lGratePosition)) && (ships.get(idx).getShipStatus().equals(ShipStatus.CHOSEN))){
                int X = (int) Math.floor((e.getX() - lGratePosition.getOffsetX()) / lGratePosition.getFieldLength());
                int Y = (int) Math.floor((e.getY() - lGratePosition.getOffsetY()) / lGratePosition.getFieldLength());
                caption.setText("jjjjjj" +  ships.get(idx).getShipStatus());
                ships.get(idx).setX(GrateConverter.TableX2Pixel(lGratePosition, X));
                ships.get(idx).setY(GrateConverter.TableY2Pixel(lGratePosition, Y));
            } else {
                // ships.get(idx).setPosition2Default();
            }
        });


        scene.setOnMouseClicked(event -> {
            if (event.getTarget().getClass().getSimpleName().equals("Rect")) {
                Rect r = (Rect) event.getTarget();
                idx = r.getIndex();
                caption.setText(event.getTarget().getClass().getSimpleName() + " " + idx);
            }
        });

        scene.setOnScroll((ScrollEvent event) -> {
            for(Rect ship : ships) {
                if (ship.getShipStatus() == ShipStatus.CHOSEN) {
                    if (ship.getDirection() == Direction.HORIZONTAL) {
                        ship.setDirection(Direction.VERTICAL);
                        ship.setHeight(ship.getRank() * lGratePosition.getFieldLength() );
                        ship.setWidth(lGratePosition.getFieldLength() );
                        ship.setStroke(Color.GREEN);
                    } else {
                        ship.setDirection(Direction.HORIZONTAL);
                        ship.setWidth(ship.getRank() * lGratePosition.getFieldLength() );
                        ship.setHeight(lGratePosition.getFieldLength() );
                        ship.setStroke(Color.BROWN);
                    }
                    System.out.println("ship # " + ship.getIndex() + " direction is " + ship.getDirection().toString());
                }
            }
        });

        readyButton.setOnAction(event -> {
            caption.setText("I'm ready!!!");
        });

        newGameButton.setOnAction(event -> {
            for( int i = 0; i < ships.size(); i++) {
                ships.get(i).setX(ships.get(i).getDefaultX());
                ships.get(i).setY(ships.get(i).getDefaultY());
                ships.get(i).setStroke(Color.DARKBLUE);
                ships.get(i).setStrokeWidth(1);
                ships.get(i).setDirection(Direction.HORIZONTAL);
            }
            caption.setText("You can start new game now.");
        });




/*
        scene.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                if (t.getTarget().getClass().getSimpleName().equals("Rect")) {
                    Rect r = (Rect) t.getTarget();
                    idx = r.getIndex();
                    caption.setText(t.getTarget().getClass().getSimpleName() + " " + idx);

                    if(ships.get(idx).getShipStatus().equals(ShipStatus.CHOSEN)) {
                        int X = (int) Math.floor((t.getX() - lGratePosition.getOffsetX()) / lGratePosition.getFieldLength());
                        int Y = (int) Math.floor((t.getY() - lGratePosition.getOffsetY()) / lGratePosition.getFieldLength());
                        caption.setText("X = " + X + " Y = " + Y );
                        ships.get(idx).setX(GrateConverter.TableX2Pixel(lGratePosition, X));
                        ships.get(idx).setY(GrateConverter.TableY2Pixel(lGratePosition, Y));
                        ships.get(idx).setShipStatus(ShipStatus.NONE);
                    }
                }
            }
        });
*/




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
