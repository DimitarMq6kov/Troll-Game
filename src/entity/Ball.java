package entity;

import java.awt.*;

public class Ball extends Entity {
    public Ball (float posX, float posY) {
        super(posX, posY);
    }

    public void render(Graphics g) {
        g.setColor(new Color(255, 0, 255));
        g.fillOval((int) posX, (int) posY, 20, 20);
    }
}
