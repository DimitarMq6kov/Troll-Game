package main;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private Panel panel;

    public Window(Panel panel) {
        this.panel = panel;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Troll Game");

        panel.setBackground(new Color(26, 29, 36));

        add(panel);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        setResizable(false);
    }
}
