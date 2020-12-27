package test;

import model.Basket;
import model.CatchGame;
import model.Egg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EggTest {

    private static final int X = CatchGame.WIDTH / 2;
    private static final int Y = 50;
    private Egg egg;

    @BeforeEach
    public void runBefore() {
        egg = new Egg(X, Y);
    }


    @Test
    public void testUpdate() {
        final int NUM_UPDATES = 8;

        egg.move();
        assertEquals(X, egg.getX());
        assertEquals(Y + Egg.DOWNWARD_MOVEMENT, egg.getY());

        for(int count = 1; count < NUM_UPDATES; count++) {
            egg.move();
        }

        assertEquals(Y + NUM_UPDATES * Egg.DOWNWARD_MOVEMENT, egg.getY());
    }

    @Test
    public void testCollideWith() {
        Basket b = new Basket(0);
        assertFalse(egg.collidedWith(b));

        b = new Basket(egg.getX());
        assertFalse(egg.collidedWith(b));

        b = new Basket(egg.getX());
        final int NUM_UPDATES = (b.getY() - egg.getY())/ egg.DOWNWARD_MOVEMENT;
        for(int count = 1; count < NUM_UPDATES; count++) {
            egg.move();
        }
        assertTrue(egg.collidedWith(b));

    }
}
