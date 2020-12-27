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
        private CatchGame catchGame;
        private TitlePanel tp;
        private GamePanel gp;
        private ScorePanel sp;
        private Timer t;

        // Constructs main window
        // effects: sets up window in which basket catch game will be played
    public BasketCatchGame() {
        super("Basket Catch Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        catchGame = new CatchGame();
        tp = new TitlePanel();
        gp = new GamePanel(catchGame);
        sp = new ScorePanel();
        add(gp);
        add(tp, BorderLayout.NORTH);
        add(sp, BorderLayout.SOUTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        t.start();
        catchGame.addObserver(sp);
        }

        // Set up timer
        // modifies: none
        // effects:  initializes a timer that updates game each INTERVAL milliseconds
        private void addTimer() {
            t = new Timer(INTERVAL, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    catchGame.update();
                    gp.repaint();
                }
            });
        }


        // modifies: this
        // effects:  location of frame is set so frame is centred on desktop
        private void centreOnScreen() {
            Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
        }

        // key handler
        private class KeyHandler extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                catchGame.keyPressed(e.getKeyCode());
            }
        }

        // Play the game
        public static void main(String[] args) {
            new BasketCatchGame();
        }
    }
