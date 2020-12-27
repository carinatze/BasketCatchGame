package model;

import java.awt.*;

// Represents a thing coming from the top of the screen
public class Egg extends Sprite {
    public static final int DOWNWARD_MOVEMENT = 2;
    public static final int SIZE_X = 15;
    public static final int SIZE_Y = 18;
    private static final Color COLOR = new Color(210, 180, 140);

    // Constructor
    // EFFECTS: egg is constructed at position (x, y)
    public Egg(int x, int y) {
        super(x, y, SIZE_X, SIZE_Y);
    }

    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillOval(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        g.setColor(savedCol);
    }

    @Override
    public void move() {
        y = y + DOWNWARD_MOVEMENT;

        super.move();
    }

    // Effects: returns true if this thing has collided with other Sprite; false otherwise
    public boolean collidedWith(Sprite other) {
        Rectangle thisBoundingRect = new Rectangle(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        Rectangle otherBoundingRect = new Rectangle(other.getX() - other.getWidth() / 2, other.getY() - other.getHeight() / 2,
                other.getWidth(), other.getHeight());
        return thisBoundingRect.intersects(otherBoundingRect);
    }
}
