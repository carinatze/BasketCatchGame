package model;

import javax.swing.*;
import java.awt.*;

// Represents a basket
public class Basket extends Sprite {

    public static final int DX = 2;
    private static final int SIZE_X = 15;
    private static final int SIZE_Y = 8;
    private static final int BASKET_Y = CatchGame.HEIGHT - 40;
    private static final Color COLOR = new Color(51, 153, 255);
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private Image basketIcon;

    private int direction;

    // Constructs a basket
    // Effects: basket is located at position (x, BASKET_Y) and not moving
    public Basket(int x) {
        super(x, BASKET_Y, SIZE_X, SIZE_Y);
        direction = 0;
        loadImage();
        setSurfaceSize();
    }

    // EFFECTS: returns true if basket is facing right, false otherwise
    public boolean isFacingRight() {
        return direction == RIGHT;
    }

    // Faces basket to the right
    // modifies: this
    // effects: basket is facing right
    public void faceRight() {
        direction = RIGHT;
    }

    // Faces basket to the left
    // modifies: this
    // effects: basket is facing left
    public void faceLeft() {
        direction = LEFT;
    }

    @Override
    public void move() {
        x = x + direction * DX;

        super.move();
    }

    private void loadImage() {

        basketIcon = new ImageIcon("basket.png").getImage();
    }

    private void setSurfaceSize() {

        Dimension d = new Dimension();
        d.width = basketIcon.getWidth(null);
        d.height = basketIcon.getHeight(null);
        d.setSize(10,10);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g1.drawImage(basketIcon,0, 0, null);

//        Color savedCol = g.getColor();
//        g.setColor(COLOR);
//        g.fillRect(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
////        Polygon tankFront = createTankFront();
////        g.fillPolygon(tankFront);
//        g.setColor(savedCol);
    }
//
//    // EFFECTS: returns a polygon that represents front of tank
//    private Polygon createTankFront() {
//        Polygon tankFront = new Polygon();
//
//        if (direction == RIGHT) {
//            tankFront.addPoint(x + SIZE_X / 2, BASKET_Y + SIZE_Y / 2);
//            tankFront.addPoint(x + SIZE_X, BASKET_Y);
//            tankFront.addPoint(x + SIZE_X / 2, BASKET_Y - SIZE_Y / 2);
//        }
//        else {
//            tankFront.addPoint(x - SIZE_X / 2, BASKET_Y + SIZE_Y / 2);
//            tankFront.addPoint(x - SIZE_X, BASKET_Y);
//            tankFront.addPoint(x - SIZE_X / 2, BASKET_Y - SIZE_Y / 2);
//        }
//
//        return tankFront;
//    }
}
