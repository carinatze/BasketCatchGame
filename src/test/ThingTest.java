package test;

import model.Basket;
import model.CatchGame;
import model.Thing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThingTest {

    private static final int XLOC = CatchGame.WIDTH / 2;
    private static final int YLOC = 50;
    private Thing thing;

    @BeforeEach
    public void runBefore() {
        thing = new Thing(XLOC, YLOC);
    }


    @Test
    public void testUpdate() {
        final int NUM_UPDATES = 8;

        thing.move();
        // can't test XLOC due to random jiggle behaviour
        assertEquals(YLOC + Thing.DOWNWARD_MOVEMENT, thing.getY());

        for(int count = 1; count < NUM_UPDATES; count++) {
            thing.move();
        }

        assertEquals(YLOC + NUM_UPDATES * Thing.DOWNWARD_MOVEMENT, thing.getY());
    }

    @Test
    public void testCollideWith() {
        Basket b = new Basket(0);
        assertFalse(thing.collidedWith(b));

        b = new Basket(thing.getX());
        assertTrue(thing.collidedWith(b));

        b = new Basket(thing.getX() + Thing.SIZE_X / 2);
        assertTrue(thing.collidedWith(b));

        b = new Basket(thing.getX() + Thing.SIZE_X / 2);
        assertFalse(thing.collidedWith(b));

        b = new Basket(thing.getX() - Thing.SIZE_X / 2);
        assertTrue(thing.collidedWith(b));

        b = new Basket(thing.getX() - Thing.SIZE_X / 2);
        assertFalse(thing.collidedWith(b));

        b = new Basket(thing.getX());
        assertTrue(thing.collidedWith(b));

        b = new Basket(thing.getX());
        assertFalse(thing.collidedWith(b));
    }
}
