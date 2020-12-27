package ui;

import model.CatchGame;

import javax.swing.*;
import java.awt.*;

// panel where the game is rendered
public class GamePanel extends JPanel {
    private static final String OVER = "GAME OVER";
    private static final String REPLAY = "r to replay";
    private static final String QUIT = "q to quit";
    private CatchGame game;
    private static final Color BACKGROUND_COLOR = new Color(238, 254, 255);

    // Constructs a game panel
    // EFFECTS:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(CatchGame g) {
        setPreferredSize(new Dimension(CatchGame.WIDTH, CatchGame.HEIGHT));
        setBackground(BACKGROUND_COLOR);
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (game.isOver()) {
            gameOver(g);
        }
    }

    // Draws the game
    // MODIFIES: g
    // EFFECTS:  the game is drawn onto the Graphics object g
    private void drawGame(Graphics g) {
        game.draw(g);
    }

    // Draws the "game over" message and replay instructions
    // MODIFIES: g
    // EFFECTS:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));
        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, CatchGame.HEIGHT / 2 - 20);
        centreString(REPLAY, g, fm, CatchGame.HEIGHT / 2 + 20);
        centreString(QUIT, g, fm, CatchGame.HEIGHT / 2 + 40);
        g.setColor(saved);
    }

    // Centres a string on the screen
    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (CatchGame.WIDTH - width) / 2, yPos);
    }
}
