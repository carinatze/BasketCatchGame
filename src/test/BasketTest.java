package test;

import model.Basket;
import model.CatchGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasketTest {
    private static final int XLOC = CatchGame.WIDTH / 2;
    private Basket basket;

    @BeforeEach
    public void runBefore() {
        basket = new Basket(XLOC);
    }

    @Test
    public void testConstructor() {
        assertEquals(XLOC, basket.getX());
        assertTrue(basket.isFacingRight());
    }

    @Test
    public void testFaceRight() {
        basket.faceLeft();
        basket.faceRight();
        assertTrue(basket.isFacingRight());
    }

    @Test
    public void testFaceLeft() {
        basket.faceRight();
        basket.faceLeft();
        assertFalse(basket.isFacingRight());
    }

    @Test
    public void testMoveRightOnce() {
        basket.move();
        assertEquals(XLOC + Basket.SPEED, basket.getX());
    }

    @Test
    public void testMoveLeftOnce() {
        basket.faceLeft();
        basket.move();
        assertEquals(XLOC - Basket.SPEED, basket.getX());
    }

    @Test
    public void testMoveRightMany() {
        basket.faceRight();
        final int NUM_UPDATES = (CatchGame.WIDTH / 4) / Basket.SPEED;

        for (int count = 0; count < NUM_UPDATES; count++) {
            basket.move();
        }

        assertEquals(XLOC + NUM_UPDATES * Basket.SPEED, basket.getX());
    }

    @Test
    public void testMoveLeftMany() {
        final int NUM_UPDATES = (CatchGame.WIDTH / 4) / Basket.SPEED;

        basket.faceLeft();

        for (int count = 0; count < NUM_UPDATES; count++) {
            basket.move();
        }

        assertEquals(XLOC - NUM_UPDATES * Basket.SPEED, basket.getX());
    }

    @Test
    public void testLeftBoundary() {
        basket.faceLeft();
        while (basket.getX() > 0)
            basket.move();

        assertEquals(0, basket.getX());

        basket.move();
        assertEquals(0, basket.getX());
    }

    @Test
    public void testRightBoundary() {
        while (basket.getX() < CatchGame.WIDTH)
            basket.move();

        assertEquals(CatchGame.WIDTH, basket.getX());

        basket.move();
        assertEquals(CatchGame.WIDTH, basket.getX());
    }
}
