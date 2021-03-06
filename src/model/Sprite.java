package model;

import java.awt.*;

/*
 * Represents a sprite
 */
public abstract class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    //Constructs a sprite
    //EFFECTS: sprite is constructed at the x,y coordinate with width and height
    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Moves the sprite
    // modifies: this
    // effects:  sprite has been moved
    public void move() {
        handleBoundary();
    }

    // Draws the sprite
    // modifies: g
    // effects: draws the sprite on the Graphics object g
    public abstract void draw(Graphics g);

    // Constrains sprite so that it doesn't travel off sides of screen
    // modifies: this
    // effects: sprite is constrained to remain within horizontal boundaries of game
    protected void handleBoundary() {
        if (x < 0)
            x = 0;
        else if (x > CatchGame.WIDTH)
            x = CatchGame.WIDTH;
    }
}

