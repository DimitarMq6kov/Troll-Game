package main;

import entity.Ball;
import entity.Player;
import utils.Direction;

import java.awt.*;

public class Game implements Runnable {

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Panel panel;
    private Window window;

    private Thread thread;

    private Player player;
    private Ball ball;

    public Game() {

        this.player = new Player(20, 720 / 2);
        this.ball = new Ball(1200, 720 / 2);
        this.ball.setDirection(Direction.DOWNRIGHT);
        this.panel = new Panel(player, this);
        this.window = new Window(panel);

        this.panel.setFocusable(true);

        startGame();
    }

    private void startGame() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    private void update() {
        this.player.update();
        this.ball.move();
    }

    public void render(Graphics g) {
        this.player.render(g);
        this.ball.render(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000 / FPS_SET;
        double timePerUpdate = 1000000000 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        this.panel.requestFocusInWindow();
        this.panel.setFocusable(true);

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                panel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
