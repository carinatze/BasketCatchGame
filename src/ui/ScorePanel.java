package ui;

import model.CatchGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

// Represents score board
public class ScorePanel extends JPanel implements Observer {
    private static final String CAUGHT_TXT = "Items caught: ";
    private static final String LIFE_COUNTER_TXT = "Lives left: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;

    //	private CatchGame game;
    private JLabel thingsLbl;
    private JLabel livesLbl;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel() {
        setBackground(new Color(255, 255, 204));
        thingsLbl = new JLabel(CAUGHT_TXT + 0);
        thingsLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        livesLbl = new JLabel(LIFE_COUNTER_TXT + CatchGame.MAX_LIVES);
        livesLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(thingsLbl);
        add(Box.createHorizontalStrut(10));
        add(livesLbl);
    }

    // Updates the score panel (using the Observer pattern instead)
    // MODIFIES: this
    // EFFECTS:  updates number of things caught and lives left remaining to reflect state of game
    @Override
    public void update(Observable o, Object arg) {
        if (CatchGame.LIVES_REMAINING_CHANGED.equals(arg) || CatchGame.THING_CAUGHT.equals(arg)) {
            CatchGame catchGame = (CatchGame) o;
            thingsLbl.setText(CAUGHT_TXT + catchGame.getNumThingsCaught());
            livesLbl.setText(LIFE_COUNTER_TXT + catchGame.getLivesRemaining());
            repaint();
        }
    }
}
