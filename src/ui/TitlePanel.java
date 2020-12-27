package ui;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 25;
    private JLabel titleLbl;

    public TitlePanel() {
        setBackground(new Color(211, 250, 231));
        titleLbl = new JLabel("Basket Catch Game");
        titleLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(titleLbl);
    }
}
