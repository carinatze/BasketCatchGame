package ui;

import model.CatchGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

// Represents score board
public class ScorePanel extends JPanel implements Observer {
    private static final String CAUGHT_TXT = "Eggs caught: ";
    private static final String LIFE_COUNTER_TXT = "Lives left: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;

    //	private CatchGame game;
    private JLabel eggsCaughtLbl;
    private JLabel livesLbl;

    // Constructs a score panel
    // effects: sets the background colour and draws initial labels; updates this with score
    public ScorePanel() {
        setBackground(new Color(255, 255, 204));
        eggsCaughtLbl = new JLabel(CAUGHT_TXT + 0);
        eggsCaughtLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        livesLbl = new JLabel(LIFE_COUNTER_TXT + CatchGame.MAX_LIVES);
        livesLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(eggsCaughtLbl);
        add(Box.createHorizontalStrut(10));
        add(livesLbl);
    }

    // Updates the score panel (using the Observer pattern instead)
    // MODIFIES: this
    // EFFECTS: updates number of eggs caught and lives left remaining to reflect state of game
    @Override
    public void update(Observable o, Object arg) {
            CatchGame catchGame = (CatchGame) o;
            eggsCaughtLbl.setText(CAUGHT_TXT + catchGame.getNumThingsCaught());
            livesLbl.setText(LIFE_COUNTER_TXT + catchGame.getLivesRemaining());
            repaint();
    }
}
