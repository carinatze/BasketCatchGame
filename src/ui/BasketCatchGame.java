package ui;

import model.CatchGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BasketCatchGame extends JFrame {
        private static final int INTERVAL = 20;
        private CatchGame game;
        private GamePanel gp;
        private ScorePanel sp;
        private Timer t;

        // Constructs main window
        // effects: sets up window in which Space Invaders game will be played
    public BasketCatchGame() {
            super("Catch Game");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setUndecorated(true);
            game = new CatchGame();
            gp = new GamePanel(game);
            sp = new ScorePanel();
            add(gp);
            add(sp, BorderLayout.NORTH);
            addKeyListener(new KeyHandler());
            pack();
            centreOnScreen();
            setVisible(true);
            addTimer();
            t.start();

//        // added new line to add observer (console writer)
//        game.addObserver(new ConsoleWriter());
//        // added new line to add observer (score panel)
//        game.addObserver(sp);
        }

        // Set up timer
        // modifies: none
        // effects:  initializes a timer that updates game each
        //           INTERVAL milliseconds
        private void addTimer() {
            t = new Timer(INTERVAL, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    game.update();
                    gp.repaint();
                }
            });
        }

        // Centres frame on desktop
        // modifies: this
        // effects:  location of frame is set so frame is centred on desktop
        private void centreOnScreen() {
            Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
        }

        /*
         * A key handler to respond to key events
         */
        private class KeyHandler extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                game.keyPressed(e.getKeyCode());
            }
        }

        // Play the game
        public static void main(String[] args) {
            new BasketCatchGame();
        }
    }