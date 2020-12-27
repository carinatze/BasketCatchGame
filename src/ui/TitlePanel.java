package ui;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    private static final int LBL_WIDTH = 224;
    private static final int LBL_HEIGHT = 25;
    private JLabel titleLbl;

    public TitlePanel() {
        setBackground(new Color(255, 255, 204));
        titleLbl = new JLabel("BASKET CATCH GAME");
        Font font = new Font("Helvetica", Font.BOLD, 18);
        titleLbl.setFont(font);
        titleLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(titleLbl);
    }
}
