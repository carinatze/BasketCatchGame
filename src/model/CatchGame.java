package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class CatchGame extends Observable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final Random RND = new Random();
    public static final int MAX_LIVES = 5;

    private List<Sprite> sprites;
    private Basket basket;
    private boolean isGameOver;
    private int numLivesRemaining;
    private int numThingsCaught;

    // Constructor
    // Effects: sets up the basket catch game
    public CatchGame() {
        sprites = new ArrayList<>();
        initializeSprites();
        reset();
    }

    // modifies: this
    // EFFECTS:  updates basket and things
    public void update() {
        moveSprites();
        fall();
        checkCaught();
        checkMissedThings();
    }

    // Responds to key press codes
    // MODIFIES: this
    // EFFECTS:  turns basket and resets game in response to given key pressed code
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            basket.faceLeft();
            basket.move();
        } else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            basket.faceRight();
            basket.move();
        } else if (keyCode == KeyEvent.VK_R && isGameOver) {
            reset();
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    public void draw(Graphics g) {
        for (Sprite aSprite: sprites)
            aSprite.draw(g);
    }

    // Is game over?
    // EFFECTS: returns true if game is over, false otherwise
    public boolean isOver() { return isGameOver; }

    public int getNumThingsCaught() { return numThingsCaught; }

    public int getLivesRemaining() { return numLivesRemaining; }

    public List<Sprite> getSprites() { return sprites; }

    public Basket getBasket() { return basket; }

    // MODIFIES: this
    // EFFECTS: moves sprites to location at next time
    private void moveSprites() {
        for (Sprite next : sprites) {
            if (next instanceof Thing) {
                next.move();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  resets number of things caught and max lives; game is not over
    private void reset() {
        isGameOver = false;
        numThingsCaught = 0;
        numLivesRemaining = MAX_LIVES;
        setChanged();
        notifyObservers();
    }

    // Initializes sprites
    // MODIFIES: this
    // EFFECTS:  sets up list of sprites with no things and basket in middle of the screen
    private void initializeSprites() {
        sprites.clear();
        basket = new Basket(WIDTH / 2);
        sprites.add(basket);
    }

    // Check things
    // MODIFIES: this
    // EFFECTS:  removes any thing that has traveled past bottom of screen
    private void checkMissedThings() {
        List<Sprite> thingsToRemove = new ArrayList<>();

        for (Sprite next : sprites) {
            if (next.getY() > HEIGHT) {
                thingsToRemove.add(next);
                numLivesRemaining--;
                setChanged();
                notifyObservers();
                if (numLivesRemaining == 0) {
                    isGameOver = true;
                }
            }
        }
        sprites.removeAll(thingsToRemove);
        if (isGameOver) {
            initializeSprites();
        }
    }


    // MODIFIES: this
    // EFFECTS: randomly generates new thing at top of screen with random x coordinate.
    private void fall() {
        if (RND.nextInt(240) < 1) {
            Thing t = new Thing(RND.nextInt(WIDTH), 10);
            sprites.add(t);
        }
    }

    // Checks for collisions between thing and the basket
    // MODIFIES: this
    // EFFECTS:  removes thing that has been caught by the basket
    private void checkCaught() {
        List<Sprite> toBeRemoved = new ArrayList<>();
        for (Sprite next : sprites) {
            if (next instanceof Thing) {
                checkThingCaught((Thing) next, toBeRemoved);
            }
        }
        sprites.removeAll(toBeRemoved);
    }

    // MODIFIES: this, thingsToRemove
    // EFFECTS: if thing has been caught by a basket, increments number of things caught
    private void checkThingCaught(Thing target, List<Sprite> thingsToRemove) {
        for (Sprite next : sprites) {
            if (next instanceof Basket) {
                if (target.collidedWith(next)) {
                    thingsToRemove.add(target);
                    numThingsCaught++;
                    setChanged();
                    notifyObservers();
                }
            }
        }
    }
}
