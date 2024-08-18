package main;

import entity.Ball;
import entity.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private MouseInputs mouseInputs;

    private Player player;
    private Game game;
    private Ball ball;

    public Panel(Player player, Game game, Ball ball) {

        this.player = player;
        this.game = game;
        this.ball = ball;

        this.mouseInputs = new MouseInputs();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(this.mouseInputs);
        addMouseMotionListener(this.mouseInputs);

        setPanelSize();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.game.render(g);
    }

    public Player getPlayer() {
        return this.player;
    }
    public Ball getBall() {
        return this.ball;
    }
}
