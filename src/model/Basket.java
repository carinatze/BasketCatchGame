package model;

import java.awt.*;

// Represents a basket
public class Basket extends Sprite {

    public static final int SPEED = 2;
    private static final int SIZE_X = 30;
    private static final int SIZE_Y = 12;
    private static final int BASKET_Y = CatchGame.HEIGHT - 40;
    private static final Color COLOR = new Color(139, 69, 19);
    private static final int LEFT = -1;
    private static final int RIGHT = 1;

    private int direction;

    // Constructs a basket
    // EFFECTS: basket is constructed and located at position (x, BASKET_Y) and not moving
    public Basket(int x) {
        super(x, BASKET_Y, SIZE_X, SIZE_Y);
        direction = RIGHT;
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

        if (isFacingRight()) {
            x = x + SPEED;
        } else {
            x = x - SPEED;
        }
        super.move();
    }

    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillRect(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
//        Polygon front = frontBasket();
//        g.fillPolygon(front);
        g.setColor(savedCol);
    }

    // EFFECTS: returns a polygon that represents front of tank
    private Polygon frontBasket() {
        Polygon front = new Polygon();

        if (direction == RIGHT) {
            front.addPoint(x + SIZE_X / 2, BASKET_Y + SIZE_Y / 2);
            front.addPoint(x + SIZE_X, BASKET_Y);
            front.addPoint(x + SIZE_X / 2, BASKET_Y - SIZE_Y / 2);
        }
        else {
            front.addPoint(x - SIZE_X / 2, BASKET_Y + SIZE_Y / 2);
            front.addPoint(x - SIZE_X, BASKET_Y);
            front.addPoint(x - SIZE_X / 2, BASKET_Y - SIZE_Y / 2);
        }
        return front;
    }
}
