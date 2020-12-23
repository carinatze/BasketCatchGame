package test;

import model.Basket;
import model.CatchGame;
import model.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketCatchGameTest {
        private CatchGame catchGame;

        @BeforeEach
        public void runBefore() {
            catchGame = new CatchGame();
        }

        @Test
        public void testConstructor() {
            Basket b = catchGame.getBasket();
            assertEquals(catchGame.WIDTH / 2, b.getX());
            List<Sprite> sprites = catchGame.getSprites();
            assertEquals(1, sprites.size());
        }

        @Test
        public void testUpdate() {
            Basket b = catchGame.getBasket();
            assertEquals(catchGame.WIDTH / 2, b.getX());
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2 + Basket.DX, b.getX());
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2 + 2 * Basket.DX, b.getX());
        }

        @Test
        public void testNonKeyPadKeyEvent() {
            Basket b = catchGame.getBasket();
            catchGame.keyPressed(KeyEvent.VK_LEFT);
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2 - Basket.DX, b.getX());
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2 - 2 * Basket.DX, b.getX());
            catchGame.keyPressed(KeyEvent.VK_RIGHT);
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2 - Basket.DX, b.getX());
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2, b.getX());
        }

        @Test
        public void testKeyPadKeyEvent() {
            Basket b = catchGame.getBasket();
            catchGame.keyPressed(KeyEvent.VK_KP_LEFT);
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2 - Basket.DX, b.getX());
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2 - 2 * Basket.DX, b.getX());
            catchGame.keyPressed(KeyEvent.VK_KP_RIGHT);
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2 - Basket.DX, b.getX());
            catchGame.update();
            assertEquals(catchGame.WIDTH / 2, b.getX());
        }
}
