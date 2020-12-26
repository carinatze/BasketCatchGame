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

    public static final String THING_CAUGHT = "THING CAUGHT";
    public static final String LIVES_REMAINING_CHANGED = "LIVES CHANGED";

    private List<Sprite> sprites;
    private Basket basket;
    private boolean isGameOver;
    private int numLivesRemaining;
    private int numThingsInPlay;
    private int numThingsCaught;

    // Constructor
    // Effects: sets up the basket catch game
    public CatchGame() {
        sprites = new ArrayList<Sprite>();
        initializeSprites();
        reset();
    }

    // modifies: this
    // effects:  updates basket and things
    public void update() {
        moveSprites();
        checkThings();
        fall();
        checkCaught();
        checkGameOver();
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
        } else if (keyCode == KeyEvent.VK_X) {
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

    public int getNumThingsInPlay() { return numThingsInPlay; }

    public int getNumThingsCaught() { return numThingsCaught; }

    public int getLivesRemaning() { return numLivesRemaining; }

    public List<Sprite> getSprites() { return sprites; }

    public Basket getBasket() { return basket; }

    // moves the sprites
    // MODIFIES: this
    // EFFECTS: moves sprites to location at next time
    private void moveSprites() {
        for (Sprite next : sprites) {
            if (next instanceof Thing) {
                next.move();
            }
        }
    }

    // Sets / resets the game
    // MODIFIES: this
    // EFFECTS:  resets number of things in play and number of things caught;
    //           game is not over
    private void reset() {
        isGameOver = false;
        numThingsInPlay = 0;
        numThingsCaught = 0;
        numLivesRemaining = MAX_LIVES;
        // notify score panel when a new game starts
        setChanged();
        notifyObservers(LIVES_REMAINING_CHANGED);
        setChanged();
        notifyObservers(THING_CAUGHT);
    }

    // Initializes sprites
    // MODIFIES: this
    // EFFECTS:  sets up list of sprites with no things and basket half way across the screen
    private void initializeSprites() {
        sprites.clear();
        basket = new Basket(WIDTH / 2);
        sprites.add(basket);
    }

    // Check things
    // MODIFIES: this
    // EFFECTS:  removes any thing that has traveled off top of screen
    private void checkThings() {
        List<Sprite> thingsToRemove = new ArrayList<Sprite>();

        for (Sprite next : sprites) {
            if (next.getY() < 0) {
                thingsToRemove.add(next);
                numLivesRemaining--;
                // added new line to notify score panel when an item has travelled off screen
                setChanged();
                notifyObservers(LIVES_REMAINING_CHANGED);
            }
        }
        sprites.removeAll(thingsToRemove);
    }

    // fall!
    // modifies: this
    // effects: randomly generates new thing at top of screen with random x coordinate.
    private void fall() {
        if (RND.nextInt(240) < 1) {
            Thing t = new Thing(RND.nextInt(WIDTH), 10);
            sprites.add(t);
        }
    }

    // Checks for collisions between a thing and the basket
    // modifies: this
    // effects:  removes any thing that has been caught with the basket
    //           and removes corresponding thing from play
    private void checkCaught() {
        List<Sprite> toBeRemoved = new ArrayList<Sprite>();
        for (Sprite next : sprites) {
            if (next instanceof Thing) {
                checkThingCaught((Thing) next, toBeRemoved);
            }
        }
        sprites.removeAll(toBeRemoved);
    }

    // Has a given thing been caught by the basket?
    // MODIFIES: this, thingsToRemove
    // EFFECTS: if thing has been caught by a basket, removes thing from play;
    // increments number of things caught.
    private void checkThingCaught(Thing target, List<Sprite> thingsToRemove) {
        for (Sprite next : sprites) {
            if (next instanceof Basket) {
                if (target.collidedWith(next)) {
                    thingsToRemove.add(target);
                    numThingsInPlay--;
                    numThingsCaught++;
                    // added two lines
                    setChanged();
                    notifyObservers(THING_CAUGHT);
                }
            }
        }
    }

    // Is game over? (Has 5 things managed to be missed?)
    // modifies: this
    // effects:  if an 5 things has not been caught, game is marked as
    //           over and lists of caught things are cleared
    private void checkGameOver() {
        Integer counter = 0;
        for (Sprite next : sprites) {
                if (next.getY() > HEIGHT) {
                    counter++;
                    numLivesRemaining--;
                    setChanged();
                    notifyObservers(LIVES_REMAINING_CHANGED);
                    if (counter == 5) {
                    isGameOver = true;
                }
            }
        }
        if (isGameOver)
            initializeSprites();
    }
}
