package test;

import model.Basket;
import model.CatchGame;
import model.Thing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThingTest {

    private static final int X = CatchGame.WIDTH / 2;
    private static final int Y = 50;
    private Thing thing;

    @BeforeEach
    public void runBefore() {
        thing = new Thing(X, Y);
    }


    @Test
    public void testUpdate() {
        final int NUM_UPDATES = 8;

        thing.move();
        assertEquals(X, thing.getX());
        assertEquals(Y + Thing.DOWNWARD_MOVEMENT, thing.getY());

        for(int count = 1; count < NUM_UPDATES; count++) {
            thing.move();
        }

        assertEquals(Y + NUM_UPDATES * Thing.DOWNWARD_MOVEMENT, thing.getY());
    }

    @Test
    public void testCollideWith() {
        Basket b = new Basket(0);
        assertFalse(thing.collidedWith(b));

        b = new Basket(thing.getX());
        assertFalse(thing.collidedWith(b));

        b = new Basket(thing.getX());
        final int NUM_UPDATES = (b.getY() - thing.getY())/ thing.DOWNWARD_MOVEMENT;
        for(int count = 1; count < NUM_UPDATES; count++) {
            thing.move();
        }
        assertTrue(thing.collidedWith(b));

    }
}
