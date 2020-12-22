package ui;

import model.CatchGame;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private static final String CAUGHT_TXT = "Things caught: ";
    private static final String LIFE_COUNTER_TXT = "Lives left: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;

    //	private SIGame game;
    private JLabel thingsLbl;
    private JLabel livesLbl;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel() {
        //		game = g;
        setBackground(new Color(80, 10, 180));
        thingsLbl = new JLabel(CAUGHT_TXT + 0);
        thingsLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        livesLbl = new JLabel(LIFE_COUNTER_TXT + CatchGame.MAX_LIVES);
        livesLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(thingsLbl);
        add(Box.createHorizontalStrut(10));
        add(livesLbl);
    }
}
